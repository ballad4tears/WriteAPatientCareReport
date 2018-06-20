package com.example.ro_cot_ta.writeapatientcarereport;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

public class Case extends AppCompatActivity {

    private static final  String TAG = "Case";
    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    public String strNo;
    public String strName;
    public String strLastname;
    public String strAllergic;
    public String strAdress;
    public String strTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        Log.d(TAG, "onCreate: Starting.");

//        Bundle c = getIntent().getExtras();
//        if (c != null) {
//            strNo = c.getString("patient_no");
//            strName = c.getString("patient_name");
//            strLastname = c.getString("patient_lastname");
//            strAllergic = c.getString("patient_allergic");
//            strAdress = c.getString("patient_address");
//            strTel = c.getString("patient_number");
//        }
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
//        Bundle bundle = new Bundle();
//        bundle.putString("patient_no", strNo);
//        bundle.putString("patient_name", strName);
//        bundle.putString("patient_lastname", strLastname);
//        bundle.putString("patient_allergic", strAllergic);
//        bundle.putString("patient_address", strAdress);
//        info infotest = new info();
//        infotest.setArguments(bundle);
        //adapter.addFragment(infotest, "ข้อมูลผู้ป่วย");
        adapter.addFragment(new infocase(), "ข้อมูลการรักษา");
        adapter.addFragment(new Prescription(), "สั่งยา");
        viewPager.setAdapter(adapter);
    }
        }