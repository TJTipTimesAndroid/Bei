package com.bei.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bei.R;
import com.bei.bean.User;
import com.bei.common.BaseActivity;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xinwenbo on 15/9/24.
 */
public class LoginActivity extends BaseActivity {

    private TextInputLayout nameTextInput, pwdTextInput;
    private EditText nameEditText, pwdEditText;
    private User user;


    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.a_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        user = new User();
        initView();

    }

    private void initView() {
        nameTextInput = (TextInputLayout) findViewById(R.id.name_text_input);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        pwdTextInput = (TextInputLayout) findViewById(R.id.password_text_input);
        pwdEditText = (EditText) findViewById(R.id.password_edit_text);
        nameTextInput.setHint("用户名");
        pwdTextInput.setHint("密码");

        pwdEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String name = nameEditText.getText().toString();
                String pwd = pwdEditText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    nameTextInput.setError("请输入用户名");
                    return false;
                } else {
                    nameTextInput.setError(null);
                }

                if (TextUtils.isEmpty(pwd)) {
                    pwdTextInput.setError("请输入密码");
                    return false;
                } else {
                    pwdTextInput.setError(null);
                }

                if (pwd.length() < 6) {
                    pwdTextInput.setError("密码不正确");
                    return false;
                } else {
                    pwdTextInput.setError(null);
                }
                login(name, pwd);
                return false;
            }
        });
    }

    private void login(String name, String pwd) {
        user.setUsername(name);
        user.setPassword(pwd);
        user.login(LoginActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "Login Success-->" + user.getObjectId(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginActivity.this, "Login Failed-->" + i + " msg " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
