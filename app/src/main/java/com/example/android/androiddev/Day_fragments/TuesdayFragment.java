package com.example.android.androiddev.Day_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.androiddev.ListUtils;
import com.example.android.androiddev.R;
import com.example.android.androiddev.Subject;
import com.example.android.androiddev.Adapters.SubjectArrayAdapter;
import com.example.android.androiddev.SubjectDatabaseControl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuesdayFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemLongClickListener{
    public static final String TABLE_NAME="tuesday_table";
    private SubjectDatabaseControl dbControl;
    private ArrayList<Subject> morningSubject;
    private ArrayList<Subject> eveningSubject;
    private ListView morningListView;
    private ListView eveningListView;



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_button_morning:
                ListUtils.addNewSubjectDialog(getContext(),dbControl,morningSubject,morningListView,SubjectDatabaseControl.TIME_IN_DAY_MORNING);
                break;
            case R.id.add_button_afternoon:
                ListUtils.addNewSubjectDialog(getContext(),dbControl,eveningSubject,eveningListView,SubjectDatabaseControl.TIME_IN_DAY_AFTERNOON);
                break;
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        ListUtils.updateSubjectDialog(getContext(),dbControl,(ListView)adapterView,i);
        return true;
    }

    public TuesdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_list_child,container,false);
        dbControl=new SubjectDatabaseControl(getContext(),TABLE_NAME);

        //ListUtils.TKBTemplate(dbControl);

        morningSubject=dbControl.getArrayListOfSubject(SubjectDatabaseControl.TIME_IN_DAY_MORNING);

        eveningSubject= dbControl.getArrayListOfSubject(SubjectDatabaseControl.TIME_IN_DAY_AFTERNOON);

        ImageView addButtonMorning=(ImageView)rootView.findViewById(R.id.add_button_morning);
        ImageView addButtonAfternoon=(ImageView)rootView.findViewById(R.id.add_button_afternoon);

        addButtonMorning.setOnClickListener(this);
        addButtonAfternoon.setOnClickListener(this);
        //tìm list view
        morningListView=(ListView)rootView.findViewById(R.id.morning_list_view);
        eveningListView=(ListView)rootView.findViewById(R.id.evening_list_view);
        //gắn Adapter
        morningListView.setAdapter(new SubjectArrayAdapter(getActivity(),morningSubject));
        eveningListView.setAdapter(new SubjectArrayAdapter(getActivity(),eveningSubject));

        morningListView.setOnItemLongClickListener(this);
        eveningListView.setOnItemLongClickListener(this);


        ListUtils.setDynamicHeight(morningListView);
        ListUtils.setDynamicHeight(eveningListView);
        return rootView;
    }

}
