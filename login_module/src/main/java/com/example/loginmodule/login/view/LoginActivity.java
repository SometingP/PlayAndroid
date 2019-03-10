package com.example.loginmodule.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.corelib.base.BaseActivity;
import com.example.corelib.util.CheckStatus;
import com.example.loginmodule.R;
import com.example.loginmodule.bean.LoginResultBean;
import com.example.loginmodule.login.persenter.LoginPersenter;
import com.example.loginmodule.register.view.RegisterActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * 登录页面
 *
 * @author 彭翔宇
 */
@Route(path = "/loginmodule/login")
public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginInterface {
    private EditText mUserEdit;
    private EditText mPassEdit;
    private Button mLoginBtn;
    private Button mRegisterBtn;
    private ImageView mBackIm;
    private LoginPersenter persenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inttViews();
        initClickListener();
        EventBus.getDefault().register(persenter);
    }

    private void initClickListener() {
        mLoginBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mBackIm.setOnClickListener(this);
    }

    private void inttViews() {
        mLoginBtn = findViewById(R.id.btn_login);
        mRegisterBtn = findViewById(R.id.btn_register);
        mBackIm = findViewById(R.id.back);
        mPassEdit = findViewById(R.id.edit_password);
        mUserEdit = findViewById(R.id.edit_username);
        persenter = new LoginPersenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String userName = mUserEdit.getText().toString().trim();
                String password = mPassEdit.getText().toString().trim();
                if (CheckStatus.checkUserAndPass(userName, password)) {
                    persenter.login(userName, password);
                }
                mUserEdit.getText().clear();
                mPassEdit.getText().clear();
                break;
            case R.id.btn_register:
                //跳转注册页面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.back:
                break;
            default:
                break;
        }
    }


    /**
     * 根据登录请求返回数据判断是否登录成功
     *
     * @param result
     */
    @Override
    public void loginResult(LoginResultBean result) {
        if (result.getErrorCode() >= 0) {
            Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败！！" + result.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(persenter);
    }
}
