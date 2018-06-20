package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    TextView menu_name;
	private Button setting_p,add_p,seach_p,edit_p;
    public String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        add_p = (Button) findViewById(R.id.add_p);
        setting_p = (Button) findViewById(R.id.setting_p);
		menu_name = (TextView) findViewById(R.id.menu_name);
		seach_p = (Button) findViewById(R.id.seach_p);
		edit_p = (Button) findViewById(R.id.edit_p) ;

		//getting username from login activity putextra method
		Intent intent = getIntent();
		username = intent.getStringExtra("kd_username");
		menu_name.setText( intent.getStringExtra("name") );
        //Toast.makeText(MenuActivity.this, All.username,Toast.LENGTH_SHORT). show();


        seach_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SeachInfo.class));
            }
        });

        edit_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Edit.class));
            }
        });

        setting_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SettingActivity.class));
            }
        });

        add_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userintent = new Intent(getApplicationContext(),AddPatientActivity.class);
                userintent.putExtra("keep",username);
                startActivity(userintent);
            }
        });

    }
}
