package com.example.myapplication.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.server.ItemManager;

public class MainActivity extends AppCompatActivity {
    private EditText edtWord;
    private EditText edtTrans;
    private TextView tv_result;
    private Button btn_enter;
    private Button btn_query;
    private Button btn_all;
    private ItemManager itemManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemManager = new ItemManager(MainActivity.this);
        findViews();
        setListeners();
        Log.i("msg",getFilesDir().getAbsolutePath()+"/WordDB.db");
    }

    private void findViews() {
        edtWord = findViewById(R.id.edt_Word);
        edtTrans = findViewById(R.id.edt_Trans);
        tv_result = findViewById(R.id.tv_result);
        btn_enter = findViewById(R.id.btn_enter);
        btn_query = findViewById(R.id.btn_query);
        btn_all = findViewById(R.id.btn_all);
    }

    public void setListeners(){
        btn_query.setOnClickListener(view -> {
            String word2 = edtWord.getText().toString();
            StringBuffer buffer = itemManager.queryWord(word2);
            tv_result.setText(buffer);
        });
        btn_enter.setOnClickListener(view -> {
            String word = edtWord.getText().toString();
            String trans = edtTrans.getText().toString();
            if(word.equals("")){
                Toast.makeText(MainActivity.this,"请输入单词",Toast.LENGTH_LONG).show();
            }else if(trans.equals("")){
                Toast.makeText(MainActivity.this,"请输入翻译",Toast.LENGTH_LONG).show();
            }else{
                itemManager.insertWord(word,trans);
                Toast.makeText(MainActivity.this,"单词录入成功",Toast.LENGTH_LONG).show();
            }
            edtTrans.setText("");
            edtWord.setText("");
        });
        btn_all.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

}