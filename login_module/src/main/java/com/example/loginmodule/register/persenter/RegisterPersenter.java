package com.example.loginmodule.register.persenter;

import com.example.corelib.base.BasePersenter;
import com.example.loginmodule.bean.RegisterResultBean;
import com.example.loginmodule.model.ModuleModel;
import com.example.loginmodule.model.ModuleModelImpl;
import com.example.loginmodule.register.view.RegisterInteface;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 * @author 13973
 */
public class RegisterPersenter implements BasePersenter {
    WeakReference<RegisterInteface> mView;
    ModuleModel model = new ModuleModelImpl();

    public RegisterPersenter(RegisterInteface view) {
        mView = new WeakReference<>(view);
    }

    public void register(String userName, String password, String repassword) {
        model.register(userName,password,repassword);
    }

    @Subscribe()
    public void uploadRegisterStatu(RegisterResultBean resultBeane){
        mView.get().registerResult(resultBeane);
    }


    @Override
    public void onDistroy() {

    }
}
