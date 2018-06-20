package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1, rellay2;
     private Button signup_now,login_now,bt_forgot;
	 private EditText type_password,type_username;
	 private String kd_username,kd_password;


	//public static final String KEY_PASSWORD = "password";
	//public static final String KEY_USERNAME = "username";
	
	public static final String LOGIN_URL = "http://192.168.43.92:80/testregister/checklogin.php";

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type_username = (EditText) findViewById(R.id.type_username);
		type_password = (EditText) findViewById(R.id.type_password);
		bt_forgot = (Button) findViewById(R.id.bt_forgot);
        signup_now = (Button) findViewById(R.id.signup_now);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        login_now = (Button) findViewById(R.id.login_now);
		
		signup_now.setOnClickListener(this);
		login_now.setOnClickListener(this);
        bt_forgot.setOnClickListener(this);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

		/*bt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotActivity.class));
            }
        });*/

    }

        @Override
        public void onClick(View v) {

        if(v.getId() == R.id.bt_forgot)
        {
            Intent intent = new Intent(this, ForgotActivity.class);
            startActivity(intent);
        }
		else if(v.getId() == R.id.signup_now)
		{
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
		}
		else if (v.getId() == R.id.login_now)
		{
		//getting value from edit texts
		 kd_username = type_username.getText().toString();
		 kd_password = type_password.getText().toString();
		
		if(kd_username.equals("") || kd_password.equals("")){
			Toast.makeText(MainActivity.this, "Enter valid Username & Password", Toast.LENGTH_SHORT).show();
		}
		else{
			
	//creating a string request
			StringRequest request = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() { //เพิ่มข้อมูลเข้าไป ใน URL ที่สร้าง ใช้ post
                @Override
                public void onResponse(String response) {
                    try{
					JSONObject jsonObject = new JSONObject(response);
					boolean responsestatus = jsonObject.getBoolean("success"); //success ใน php
					if(responsestatus){
						String name = jsonObject.getString("name"); //name จาก database
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
						//starting user profile activity
						Intent intent = new Intent(MainActivity.this,MenuActivity.class);
						intent.putExtra("name",name);
						intent.putExtra("kd_username",kd_username);
						startActivity(intent);
					}else {
                        Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                    //startActivity(new Intent(getApplicationContext(),MainActivity.class));
					}catch(JSONException e){
					e.printStackTrace();
					}
				}
            }, new Response.ErrorListener() {  //ถ้าเออเลอร์ เซิฟจะตอบมาว่า error
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {  //เหมือน hashmap ต้องกำหนด key และข้อมูลที่จะอัพขึ้นไป key อยู่ในไฟล์ php
                    Map<String, String> params = new HashMap<String, String>(); //ทำการกำหนดคีย์ ละข้อมูลที่รับเข้าไป
                    params.put("username", kd_username);
                    params.put("password", kd_password); //กำหนดคีย์จาก php และค่าที่รับมา

                    return params; // return ค่าไปให้ volley ว่าใช้งานได้หรือ error
                }
            };
			RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request); //แอดคิว สตริงรีเควส เมื่อ add เสร็จแล้ว ข้อมูลขะเพ่ิมไปในฐานข้อมูล
        }
			
		}
		}
	}


