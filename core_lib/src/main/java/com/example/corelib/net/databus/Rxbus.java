package com.example.corelib.net.databus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 彭翔宇
 */
public class Rxbus {
    private Set<Object> subscribers;
    private static volatile Rxbus Instance = null;

    private Rxbus() {
        subscribers = new CopyOnWriteArraySet();
    }

    public static synchronized Rxbus getInstance() {
        if (Instance == null) {
            synchronized (Rxbus.class) {
                if (Instance == null) {
                    Instance = new Rxbus();
                }
            }
        }
        return Instance;
    }

    /**
     * 注册
     *
     * @param subscriber
     */
    public synchronized void register(Object subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * 注销
     *
     * @param subscriber
     */
    public synchronized void unRegister(Object subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * 转接处理
     *
     * @param function
     */
    public void chainProcess(Function function) {
        Observable.just("")
                .subscribeOn(Schedulers.io())
                .map(function)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object data) throws Exception {
                        //data会传送到总线上
                        if (data == null) {
                            return;
                        }
                        //把数据送到P层
                        send(data);

                    }
                });
    }

    /**
     * 发送数据
     *
     * @param data
     */
    private void send(Object data) {
        for (Object subscriber : subscribers) {
            //扫描注解,将数据发送到注册的对象标记方法的位置
            //subscriber表示层
            try {
                callMethodByAnnotion(subscriber, data);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 判断注册观察者方法是否满足条件
     * 如果方法满足条件调用其方法将参数传入
     *
     * @param subscriber
     * @param data
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void callMethodByAnnotion(Object subscriber, Object data) throws InvocationTargetException, IllegalAccessException {
        //获取注册类中所有函数
        Method[] declaredMethods = subscriber.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            //判断方法是否有RegisterRxbus注解
            if (method.getAnnotation(RegisterRxbus.class) != null) {
                //获取方法传参类型
                Class<?> parameterType = method.getParameterTypes()[0];
                //判断方法传参类型名字与发送数据是否一致
                if (data.getClass().getName().equals(parameterType.getName())) {
                    method.invoke(subscriber, new Object[]{data});
                }
            }
        }
    }

}
