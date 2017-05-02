package com.example.android.androiddev;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.android.androiddev.Adapters.SubjectArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 12/2/2017.
 */

public class ListUtils {
    public static void setDynamicHeight(ListView mListView) {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null) {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * ((mListAdapter.getCount()!=0?mListAdapter.getCount() - 1:1)));
        mListView.setLayoutParams(params);
        mListView.requestLayout();

    }
    //make Dialog to add
    public static void addNewSubjectDialog(Context context, final SubjectDatabaseControl dbControl,final ArrayList<Subject> listOfSubject,final ListView listView,final String timeInDay){
        final View dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_new_subject,null);
        new AlertDialog.Builder(context).setView(dialogView).setTitle("ADD NEW SUBJECT")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nameEdit=(EditText)dialogView.findViewById(R.id.new_subject_name);
                        EditText timeFrom=(EditText)dialogView.findViewById(R.id.new_time_from);
                        EditText timeTo=(EditText)dialogView.findViewById(R.id.new_time_to);
                        //text can be null, i'll fix later
                        Subject newSubject=new Subject(nameEdit.getText().toString(),timeFrom.getText().toString(),timeTo.getText().toString());

                            dbControl.addSubject(newSubject,timeInDay);
                            listOfSubject.add(newSubject);
                            SubjectArrayAdapter adapter=(SubjectArrayAdapter)(listView.getAdapter());
                            adapter.notifyDataSetChanged();
                            setDynamicHeight(listView);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    public static void updateSubjectDialog(Context context,final SubjectDatabaseControl dbControl,final ListView listView, int position){
        final SubjectArrayAdapter adapter=(SubjectArrayAdapter)listView.getAdapter();
        final Subject updateSubject=adapter.getItem(position);

        final View dialogView=LayoutInflater.from(context).inflate(R.layout.dialog_new_subject,null);

        final EditText nameEdit=(EditText)dialogView.findViewById(R.id.new_subject_name);
        nameEdit.setText(updateSubject.getName());

        final EditText timeFromEdit=(EditText)dialogView.findViewById(R.id.new_time_from);
        timeFromEdit.setText(updateSubject.getTimeFrom());
        final EditText timeToEdit=(EditText)dialogView.findViewById(R.id.new_time_to);
        timeToEdit.setText(updateSubject.getTimeTo());

        new AlertDialog.Builder(context).setTitle("SETTING").setView(dialogView)
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateSubject.setName(nameEdit.getText().toString());
                        updateSubject.setTimeFrom(timeFromEdit.getText().toString());
                        updateSubject.setTimeTo(timeToEdit.getText().toString());
                        dbControl.updateSubject(updateSubject);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                adapter.remove(updateSubject);
                dbControl.deleteSubject(updateSubject);
                adapter.notifyDataSetChanged();
                setDynamicHeight(listView);
            }
        }).show();
        nameEdit.requestFocus();

    }

    public static void TKBTemplate(SubjectDatabaseControl dbControl){
        dbControl.addSubject("","7:00","7:45",SubjectDatabaseControl.TIME_IN_DAY_MORNING);
        dbControl.addSubject("","8:10","8:55",SubjectDatabaseControl.TIME_IN_DAY_MORNING);
        dbControl.addSubject("","8:58","9:43",SubjectDatabaseControl.TIME_IN_DAY_MORNING);
        dbControl.addSubject("","9:52","10:37",SubjectDatabaseControl.TIME_IN_DAY_MORNING);
        dbControl.addSubject("","10:40","11:25",SubjectDatabaseControl.TIME_IN_DAY_MORNING);

        dbControl.addSubject("","13:35","14:20",SubjectDatabaseControl.TIME_IN_DAY_AFTERNOON);
        dbControl.addSubject("","14:23","15:08",SubjectDatabaseControl.TIME_IN_DAY_AFTERNOON);
        dbControl.addSubject("","15:27","16:12",SubjectDatabaseControl.TIME_IN_DAY_AFTERNOON);
        dbControl.addSubject("","16:15","17:00",SubjectDatabaseControl.TIME_IN_DAY_AFTERNOON);

    }
}
