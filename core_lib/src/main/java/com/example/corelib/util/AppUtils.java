package com.example.corelib.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * 应用工具类
 *
 * @author 彭翔宇
 */
public class AppUtils {
    private static AppUtils instance;

    private AppUtils() {
    }

    public static AppUtils getInstance() {
        if (instance == null) {
            synchronized (AppUtils.class) {
                if (instance == null) {
                    instance = new AppUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取网络状态
     *
     * @param context
     * @return boolean
     */
    public boolean isNetWork(Context context) {
        boolean netWork = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null) {
            netWork = true;
        }
        return netWork;
    }

    /**
     * 获取蓝牙开关状态
     *
     * @return
     */
    public String getBluetoothStatus() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String states = "";
        if (mBluetoothAdapter.isEnabled()) {
            states = "蓝牙开启";
        } else {
            states = "蓝牙关闭";
        }
        return states;
    }


    /**
     * 获取设备类型
     *
     * @return
     */
    public String getUnitType() {
        String model = Build.MODEL;
        return model;
    }

    /**
     * 获取设备编号
     *
     * @param context
     * @return
     */
    public String getEquipmentNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public String getPackgeName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取应用名
     *
     * @param context
     * @return
     */
    public String getAppName(Context context) {
        String appName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            appName = (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }


    /**
     * 网络类型
     */
    public String getNetType(Context context) {
        String netType = "4G";
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            switch (info.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    netType = "WiFi";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    switch (info.getSubtype()) {
                        // 电信2G
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                            netType = "2G";
                            break;
                        // 移动/联通2G
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                            netType = "2G";
                            break;
                        // 电信3G
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                            netType = "3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                            netType = "3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            netType = "3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            netType = "4G";
                            break;
                        default:
                            netType = "4G";
                            break;
                    }
                    break;
                default:
                    netType = "4G";
                    break;
            }
        }
        return netType;
    }

    /**
     * 运营商
     */
    public String getSimOperatorInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operatorString = telephonyManager.getSimOperator();

        if (operatorString == null) {
            return "Unkown";
        }
        if (operatorString.equals("46000") || operatorString.equals("46002")) {
            // 中国移动
            return "中国移动";
        } else if (operatorString.equals("46001")) {
            // 中国联通
            return "中国联通";
        } else if (operatorString.equals("46003")) {
            // 中国电信
            return "中国电信";
        }
        // error
        return "Unkown";
    }

    /**
     * 获得App当前版本号
     *
     * @param context
     * @return
     */
    public String getAppVersion(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info.versionName;
    }
}
