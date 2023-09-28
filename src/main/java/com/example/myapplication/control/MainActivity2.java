package com.example.myapplication.control;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.entity.OneLearn;
import com.example.myapplication.server.ItemManager;
import com.example.myapplication.util.DBHelper;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView listView;
    private EditText edtWord;
    private EditText edtTrans;
    private Button btn_true;
    private Button btn_false;
    private Button btn_main2_return;
    private View view;
    private MyAdapter myAdapter;
    private ItemManager itemManager;
    private List<OneLearn> oneLearns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViews();
        setListener();
        itemManager = new ItemManager(this);
        oneLearns = itemManager.getAllWords();
        myAdapter = new MyAdapter(this,oneLearns,R.layout.item);
        listView.setAdapter(myAdapter);
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
            builder.setMessage("对于您的单词的管理");
            builder.setPositiveButton("修改我的单词", (dialogInterface, i1) ->{
                        updateAlert(i);
                    });
            builder.setNegativeButton("删除我的单词", (dialogInterface, i2) -> {
                itemManager.deleteWord(oneLearns.get(i).getId());
                oneLearns.remove(i);
                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity2.this,"成功删除",Toast.LENGTH_LONG).show();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        });
    }

    private void setListener() {
        btn_main2_return.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void updateAlert(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        view = LayoutInflater.from(this).inflate(R.layout.alert,null);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        edtWord = view.findViewById(R.id.edt_alert_word);
        edtTrans = view.findViewById(R.id.edt_alert_trans);
        btn_true = view.findViewById(R.id.btn_alert_true);
        btn_false = view.findViewById(R.id.btn_alert_false);
        edtWord.setText(oneLearns.get(i).getWord());
        edtTrans.setText(oneLearns.get(i).getTranslation());
        btn_true.setOnClickListener(view -> {
            itemManager.updateWord(oneLearns.get(i).getId(),edtWord.getText().toString(),edtTrans.getText().toString());
            oneLearns.get(i).setWord(edtWord.getText().toString());
            oneLearns.get(i).setTranslation(edtTrans.getText().toString());
            Log.i("msg",itemManager.getAllWords().toString());
            myAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });
        btn_false.setOnClickListener(view -> dialog.dismiss());
    }

    private void findViews() {
        listView = findViewById(R.id.lv);
        btn_main2_return = findViewById(R.id.btn_main2_return);
    }
}