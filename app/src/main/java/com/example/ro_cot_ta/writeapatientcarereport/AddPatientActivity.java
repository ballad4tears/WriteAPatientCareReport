package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddPatientActivity extends AppCompatActivity {

    private Button bt_addpatient;
    private EditText patient_no,patient_name,patient_lastname,patient_address,patient_number,patient_allergic,keep;
    public String kd_no,kd_name,kd_username,kd_lasname,kd_address,kd_number,kd_allergic;
    private final String URL_addP = "http://192.168.43.92:80/testregister/InsertP.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
//        Intent intent = getIntent();
//        kd_username = intent.getStringExtra("kd_username");
        onBindView();

        bt_addpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onEditText();
                onButtonClick();

            }

            public void onEditText() {

                kd_name = patient_name.getText().toString();
                kd_lasname = patient_lastname.getText().toString();
                kd_no = patient_no.getText().toString();
                kd_address = patient_address.getText().toString();
                kd_number = patient_number.getText().toString();
                kd_allergic = patient_allergic.getText().toString();
                kd_username = keep.getText().toString();

            }
        });
    }

    private void onBindView() {

        bt_addpatient = (Button) findViewById(R.id.bt_addpatient);
        patient_no = (EditText) findViewById(R.id.patient_no);
        patient_name = (EditText) findViewById(R.id.patient_name);
        patient_lastname = (EditText) findViewById(R.id.patient_lastname);
        patient_address = (EditText) findViewById(R.id.patient_address);
        patient_number = (EditText) findViewById(R.id.patient_number);
        patient_allergic = (EditText) findViewById(R.id.patient_allergic);
        keep = (EditText) findViewById(R.id.keep);
    }

    private void onButtonClick() {
        if (!kd_no.isEmpty() && !kd_name.isEmpty()  && !kd_lasname.isEmpty() && !kd_address.isEmpty() && !kd_number.isEmpty()&& !kd_allergic.isEmpty() && !kd_username.isEmpty()) {   //เช็คข้อมูล ถ้า emthy จะไม่ทำตามเงื่อนไข ถ้าไม่กรอกข้อมูลจะไม่ post ข้อมูลขึ้นไป

            RequestQueue requestQueue = Volley.newRequestQueue(this); //เรียกตัว volley
            StringRequest request = new StringRequest(Request.Method.POST, URL_addP, new Response.Listener<String>() { //เพิ่มข้อมูลเข้าไป ใน URL ที่สร้าง ใช้ post
                @Override
                public void onResponse(String response) { //response การตอบกลับ ว่า ตัว host มีการเพิ่มข้อมูลเรียบร้อย
                    Log.d("onResponse", response);  //ดักจับ error

                    patient_name.setText("");
                    patient_no.setText("");
                    patient_lastname.setText("");
                    patient_address.setText("");
                    patient_number.setText("");
                    patient_allergic.setText("");
                    keep.setText("");
                    Intent i = new Intent(AddPatientActivity.this, informationP.class);
                    i.putExtra("patient_no",kd_no);
                    i.putExtra("patient_name",kd_name);
                    i.putExtra("patient_lastname",kd_lasname);
                    i.putExtra("patient_address",kd_address);
                    i.putExtra("patient_number",kd_number);
                    i.putExtra("patient_allergic",kd_allergic);
                    i.putExtra("keep",kd_username);
                    Toast.makeText(AddPatientActivity.this, "เพิ่มข้อมูลผู้ป่วยแล้ว", Toast.LENGTH_SHORT).show();
                    startActivity(i);


                }
            }, new Response.ErrorListener() {  //ถ้าเออเลอร์ เซิฟจะตอบมาว่า error
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError",error.toString()); //ดักจับ error
                    Toast.makeText(AddPatientActivity.this,"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {  //เหมือน hashmap ต้องกำหนด key และข้อมูลที่จะอัพขึ้นไป key อยู่ในไฟล์ php
                    Map<String, String> params = new HashMap<String, String>(); //ทำการกำหนดคีย์ ละข้อมูลที่รับเข้าไป
                    params.put("patient_no", kd_no);
                    params.put("patient_name", kd_name);
                    params.put("patient_lastname", kd_lasname);
                    params.put("patient_address", kd_address);
                    params.put("patient_number", kd_number);
                    params.put("patient_allergic", kd_allergic);//กำหนดคีย์จาก php และค่าที่รับมา
                    params.put("keep",kd_username);
                    return params; // return ค่าไปให้ volley ว่าใช้งานได้หรือ error
                }
            };
            requestQueue.add(request); //แอดคิว สตริงรีเควส เมื่อ add เสร็จแล้ว ข้อมูลขะเพ่ิมไปในฐานข้อมูล
        }else{
            Toast.makeText(AddPatientActivity.this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }

    }
}
