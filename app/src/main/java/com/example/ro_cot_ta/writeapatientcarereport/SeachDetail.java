package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeachDetail extends AppCompatActivity {
    private Button detailP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach_detail);

        detailP = (Button) findViewById(R.id.detailP);

        detailP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SeeInfo.class));
            }
        });
    }
}
