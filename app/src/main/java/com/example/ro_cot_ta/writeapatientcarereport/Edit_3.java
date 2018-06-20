package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Edit_3 extends AppCompatActivity {

    private Button addCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_3);

        addCase = (Button) findViewById(R.id.addCase);

        addCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Case.class));
            }
        });
    }
}
