package com.longder.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Longder on 2016/5/18.
 */
public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        /**
         * 获取SharedPreferences对象。
         * 该方式：自动使用当前应用程序的包名作为前缀来命名SharedPreferences文件
         */
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        rememberPassword = (CheckBox) findViewById(R.id.remember_pass);
        //获取默认SharedPreferences中的数据（之前登陆时已经存储）
        boolean isRemembered = pref.getBoolean("remember_password", false);
        //去过记住了密码，就把账号和密码放入文本框中
        if (isRemembered) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if ("admin".equals(account) && "123456".equals(password)) {
                    //获取到SharedPreferences.Editor对象，用来存储数据
                    editor = pref.edit();
                    //检查记住密码的Checkbox是否选中
                    if (rememberPassword.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        //如果未选中就清空editor
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
