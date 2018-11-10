package com.example.administrator.findkeydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.key)
    EditText key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                try {
                    findKey(str,msg);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage()+"",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void findKey(String str, TextView view){
        String content = view.getText().toString();
        String wordReg = "(?i)"+str;//忽略大小写
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(wordReg).matcher(content);
        while(matcher.find()){
            matcher.appendReplacement(sb, "<font color=\"#ff0014\">"+matcher.group()+"</font>");
        }
        matcher.appendTail(sb);
        content = sb.toString();
        view.setText(Html.fromHtml(content));
    }
}
