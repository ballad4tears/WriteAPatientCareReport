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

public class SignUpActivity extends AppCompatActivity {

    private EditText d_name,license,d_username,d_password;
    private Button bt_signup;
    String URL = "http://192.168.43.92:80/testregister/InsertData.php";
    private String kd_name,kd_license,kd_username,kd_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        onBindView();

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditText(); //เรียกเมทธอด
                onButtonClick();
            }

            private void onEditText() {

                kd_name = d_name.getText().toString();       //ประกาศตัวเก็บค่า
                kd_license = license.getText().toString();
                kd_username = d_username.getText().toString();
                kd_password = d_password.getText().toString();
            }
        });
    }
	
	            private void onBindView() {
        d_name = (EditText) findViewById(R.id.d_name);
        license = (EditText) findViewById(R.id.license);
        d_username = (EditText) findViewById(R.id.d_username);
        d_password = (EditText) findViewById(R.id.d_password);
        bt_signup = (Button) findViewById(R.id.bt_signup);

    }

    private void onButtonClick() {
        if (!kd_name.isEmpty() && !kd_license.isEmpty() && !kd_username.isEmpty() && !kd_password.isEmpty()) {   //เช็คข้อมูล ถ้า emthy จะไม่ทำตามเงื่อนไข ถ้าไม่กรอกข้อมูลจะไม่ post ข้อมูลขึ้นไป
           
			//instantiate request queue
			RequestQueue requestQueue = Volley.newRequestQueue(this); //เรียกตัว volley
            //Response using URL
			StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //เพิ่มข้อมูลเข้าไป ใน URL ที่สร้าง ใช้ post
                @Override
                public void onResponse(String response) { //response การตอบกลับ ว่า ตัว host มีการเพิ่มข้อมูลเรียบร้อย
                    Log.d("onResponse", response);  //ดักจับ error
                    d_name.setText("");
                    license.setText("");
                    d_username.setText("");
                    d_password.setText("");
                    Toast.makeText(SignUpActivity.this, "เพิ่มข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }, new Response.ErrorListener() {  //ถ้าเออเลอร์ เซิฟจะตอบมาว่า error
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError",error.toString()); //ดักจับ error
                    Toast.makeText(SignUpActivity.this,"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {  //เหมือน hashmap ต้องกำหนด key และข้อมูลที่จะอัพขึ้นไป key อยู่ในไฟล์ php
                    Map<String, String> params = new HashMap<String, String>(); //ทำการกำหนดคีย์ ละข้อมูลที่รับเข้าไป
                    params.put("name", kd_name);
                    params.put("licence", kd_license);
                    params.put("username", kd_username);
                    params.put("password", kd_password); //กำหนดคีย์จาก php และค่าที่รับมา

                    return params; // return ค่าไปให้ volley ว่าใช้งานได้หรือ error
                }
            };
            requestQueue.add(request); //แอดคิว สตริงรีเควส เมื่อ add เสร็จแล้ว ข้อมูลขะเพ่ิมไปในฐานข้อมูล
        }else{
			Toast.makeText(SignUpActivity.this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
		}

    }

    /*        private void onBindView() {
        d_name = (EditText) findViewById(R.id.d_name);
        license = (EditText) findViewById(R.id.license);
        d_username = (EditText) findViewById(R.id.d_username);
        d_password = (EditText) findViewById(R.id.d_password);
        bt_signup = (Button) findViewById(R.id.bt_signup);

    }*/
}
