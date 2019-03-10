package com.example.loginmodule.register.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.corelib.base.BaseActivity;
import com.example.corelib.util.CheckStatus;
import com.example.loginmodule.R;
import com.example.loginmodule.bean.RegisterResultBean;
import com.example.loginmodule.register.persenter.RegisterPersenter;

import org.greenrobot.eventbus.EventBus;

/**
 * 注册页面
 *
 * @author 彭翔宇
 */
public class RegisterActivity extends BaseActivity implements RegisterInteface, View.OnClickListener {
    private RegisterPersenter mPersenter;
    private Button mRegisterBtn;
    private ImageView mBack;
    private EditText mUserName, mPassword, mRepassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addActivity(this);
        initsView();
        initClickListener();
        EventBus.getDefault().register(mPersenter);
    }

    private void initClickListener() {
        mRegisterBtn.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    private void initsView() {
        mRegisterBtn = findViewById(R.id.register_btn);
        mUserName = findViewById(R.id.register_username);
        mPassword = findViewById(R.id.register_password);
        mRepassword = findViewById(R.id.register_repassword);
        mBack = findViewById(R.id.back);
        mPersenter = new RegisterPersenter(this);
    }

    /**
     * 处理注册请求返回数据
     * @param result
     */
    @Override
    public void registerResult(RegisterResultBean result) {
        if (result.getErrorCode() != -1) {
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册失败！！" + result.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mPersenter);
        mPersenter.onDistroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                String username = mUserName.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mRepassword.getText().toString().trim();
                CheckStatus.checkUserAndPass(username, password, repassword);
                mPersenter.register(username, password, repassword);
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
