package com.example.android.androiddev;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.androiddev.Adapters.SubjectFragmentPagerAdapter;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get and set View Pager
        ViewPager viewPager=(ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new SubjectFragmentPagerAdapter(this,getSupportFragmentManager()));
        //get and set Tab Layout
        TabLayout tabLayout=(TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }


}