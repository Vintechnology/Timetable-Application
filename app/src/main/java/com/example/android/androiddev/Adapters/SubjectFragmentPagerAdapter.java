package com.example.android.androiddev.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.androiddev.Day_fragments.FridayFragment;
import com.example.android.androiddev.Day_fragments.MondayFragment;
import com.example.android.androiddev.Day_fragments.ThursdayFragment;
import com.example.android.androiddev.Day_fragments.TuesdayFragment;
import com.example.android.androiddev.Day_fragments.WednesdayFragment;
import com.example.android.androiddev.R;

/**
 * Created by Admin on 23/2/2017.
 */

public class SubjectFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public SubjectFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MondayFragment();
            case 1:
                return new TuesdayFragment();
            case 2:
                return new WednesdayFragment();
            case 3:
                return new ThursdayFragment();
            case 4:
                return new FridayFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return mContext.getString(R.string.monday_name);
            case 1:
                return mContext.getString(R.string.tuesday_name);
            case 2:
                return mContext.getString(R.string.wednesday_name);
            case 3:
                return mContext.getString(R.string.thursday_name);
            case 4:
                return mContext.getString(R.string.friday_name);
            default:
                return null;
        }
    }
}
