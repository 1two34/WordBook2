package com.example.myapplication.Adapter;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.OneLearn;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<OneLearn> oneLearns;
    private int layoutId;
    public MyAdapter(){

    }

    public MyAdapter(Context context,List<OneLearn> oneLearns,int layoutId){
        this.context = context;
        this.oneLearns = oneLearns;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return oneLearns.size();
    }

    @Override
    public Object getItem(int i) {
        return oneLearns.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyAdapter.ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(layoutId,null);
            viewHolder = new ViewHolder();
            viewHolder.word=view.findViewById(R.id.tv_item_word);
            viewHolder.tran=view.findViewById(R.id.tv_item_trans);
            view.setTag(viewHolder);
        }else{
            viewHolder= (MyAdapter.ViewHolder) view.getTag();
        }
        viewHolder.word.setText(oneLearns.get(i).getWord());
        viewHolder.tran.setText(oneLearns.get(i).getTranslation());
        return view;
    }

    static class ViewHolder{
        TextView word;
        TextView tran;
    }
}
