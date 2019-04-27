package com.example.nodrcom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConFig extends AppCompatActivity {

    private Button loginbtn, resetbtn;
    private TextView logintv, resettv;
    private EditText username, password;
    private RadioButton cmcc,ctcc,cucc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_fig);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loginbtn = (Button) findViewById(R.id.loginbtn);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        cmcc = (RadioButton) findViewById(R.id.cmcc);
        cucc = (RadioButton) findViewById(R.id.cucc);
        ctcc = (RadioButton) findViewById(R.id.ctcc);


        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String username1=sPreferences.getString("username", "");
        String password1 =sPreferences.getString("password", "");
        String net1 =sPreferences.getString("net", "");

        username.setText(username1);
        password.setText(password1);

        if(net1.equals("cmcc")) cmcc.setChecked(true);
        else if(net1.equals("cucc")) cucc.setChecked(true);
        else if(net1.equals("ctcc")) ctcc.setChecked(true);


        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                username.setText("");
                password.setText("");
                cmcc.setChecked(false);
                cucc.setChecked(false);
                ctcc.setChecked(false);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = username.getText().toString();
                String s2 = password.getText().toString();
                String s3 = "";
                if(cmcc.isChecked()) s3 = "cmcc";
                else if(cucc.isChecked()) s3 = "cucc";
                else if(ctcc.isChecked()) s3 = "ctcc";
              //  String s3 = netred.getTag().toString();
                myClass m = new myClass();
                boolean i = m.saveUserInfo(ConFig.this,s1,s2,s3);
                if(i) {
                    Toast.makeText(ConFig.this,"保存成功，密码为：" +s2,Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(ConFig.this,"填完整再保存好吧？",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
