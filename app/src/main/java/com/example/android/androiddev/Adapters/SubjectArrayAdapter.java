package com.example.android.androiddev.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.androiddev.R;
import com.example.android.androiddev.Subject;

import java.util.ArrayList;


/**
 * Created by Admin on 11/2/2017.
 */

public class SubjectArrayAdapter extends ArrayAdapter<Subject> {

    public SubjectArrayAdapter(Context context, ArrayList<Subject> subjects){
        super(context,0,subjects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView=convertView;
        //kiểm tra xem View có phải là tái sử dụng ko, nếu ko tạo View mới từ layout
        if(currentView==null){
            currentView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Subject neededItem= getItem(position);

        TextView timeTextView=(TextView) currentView.findViewById(R.id.time_text_view);
        timeTextView.setText(neededItem.getTimeFrom()+"\n"+neededItem.getTimeTo());

        TextView subjectTextView=(TextView)currentView.findViewById(R.id.subject_text_view);
        subjectTextView.setText(neededItem.getName());

        return currentView;
    }
}
