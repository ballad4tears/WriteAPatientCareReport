package com.example.ro_cot_ta.writeapatientcarereport;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class informationP extends Activity {

    private String strNo;
    private Button addinfoP;
    private EditText treatment_name, date_start,patient_id;
    String URL_treat = "http://192.168.43.92:80/testregister/treatment.php";
    private String kd_datestart, kd_treatment_name,kd_patientid;
    public String strName;
    public String strLastname;
    public String strAllergic;
    public String strAdress;
    public String strTel;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_p);

        addinfoP = (Button) findViewById(R.id.addinfoP);
        treatment_name = (EditText) findViewById(R.id.treatment_name);
        date_start = (EditText) findViewById(R.id.treatment_name);


        Bundle c = getIntent().getExtras();
        if (c != null) {
            strNo = c.getString("patient_no");
            strName = c.getString("patient_name");
            strLastname = c.getString("patient_lastname");
            strAllergic = c.getString("patient_allergic");
            strAdress = c.getString("patient_address");
            strTel = c.getString("patient_number");
        }

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        showInfo();


        addinfoP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onEditText(); //เรียกเมทธอด
                onButtonClick();
            }

            private void onEditText() {
                kd_patientid = patient_id.getText().toString();
                kd_treatment_name = treatment_name.getText().toString();       //ประกาศตัวเก็บค่า
                kd_datestart = date_start.getText().toString();

            }
        });
    }

    private void onButtonClick() {
        if (!kd_treatment_name.isEmpty() && !kd_datestart.isEmpty() && !kd_patientid.isEmpty()) {   //เช็คข้อมูล ถ้า emthy จะไม่ทำตามเงื่อนไข ถ้าไม่กรอกข้อมูลจะไม่ post ข้อมูลขึ้นไป

            //instantiate request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this); //เรียกตัว volley
            //Response using URL
            StringRequest request = new StringRequest(Request.Method.POST, URL_treat, new Response.Listener<String>() { //เพิ่มข้อมูลเข้าไป ใน URL ที่สร้าง ใช้ post
                @Override
                public void onResponse(String response) { //response การตอบกลับ ว่า ตัว host มีการเพิ่มข้อมูลเรียบร้อย
                    Log.d("onResponse", response);  //ดักจับ error
                    treatment_name.setText("");
                    date_start.setText("");
                    patient_id.setText("");
                    Toast.makeText(informationP.this, "เพิ่มข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), InformationPatient.class);
                    i.putExtra("patient_no",strNo);
                    i.putExtra("patient_name",strName);
                    i.putExtra("patient_lastname",strName);
                    i.putExtra("patient_address",strAdress);
                    i.putExtra("patient_number",strTel);
                    i.putExtra("patient_allergic",strAllergic);
                    startActivity(i);
                }
            }, new Response.ErrorListener() {  //ถ้าเออเลอร์ เซิฟจะตอบมาว่า error
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError", error.toString()); //ดักจับ error
                    Toast.makeText(informationP.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {  //เหมือน hashmap ต้องกำหนด key และข้อมูลที่จะอัพขึ้นไป key อยู่ในไฟล์ php
                    Map<String, String> params = new HashMap<String, String>(); //ทำการกำหนดคีย์ ละข้อมูลที่รับเข้าไป
                    params.put("treatment_name", kd_treatment_name);
                    params.put("date_start", kd_datestart); //กำหนดคีย์จาก php และค่าที่รับมา
                    params.put("patient_id",kd_patientid);
                    //params.put("pattid", /**/);
                    return params; // return ค่าไปให้ volley ว่าใช้งานได้หรือ error
                }
            };
            requestQueue.add(request); //แอดคิว สตริงรีเควส เมื่อ add เสร็จแล้ว ข้อมูลขะเพ่ิมไปในฐานข้อมูล


        } else {
            Toast.makeText(informationP.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
        }
    }

    public void showInfo() {

        final TextView tpatient_no = (TextView) findViewById(R.id.txtMemberID);
        final TextView tpatient_name = (TextView) findViewById(R.id.txtname);
        final TextView tpatient_lastname = (TextView) findViewById(R.id.txtlastname);
        final TextView tpatient_allergic = (TextView) findViewById(R.id.txtallergic);
        final TextView tpatient_address = (TextView) findViewById(R.id.txtaddress);
        final TextView tpatient_number = (TextView) findViewById(R.id.txtTel);

        String url = "http://192.168.43.92:80/testregister/new_dataP.php";
        Intent intent = getIntent();
        final String patient_id = intent.getStringExtra("patient_id");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("spatient_id", patient_id));

        String resultServer = getHttpPost(url, params);

        /*** Default Value ***/
        String strStatusID = "0";
        String strpatient_id = "0";
        String strError = "Unknow Status!";

        String strNo = "";
        String strName = "";
        String strLastname = "";
        String strAllergic = "";
        String strAdress = "";
        String strTel = "";




        try {
//            c = new JSONObject(resultServer);
//            AddPatientActivity fgf = new AddPatientActivity();

//            strNo = c.getString(fgf.kd_no);
//            strName = c.getString(fgf.kd_name);
//            strLastname = c.getString(fgf.kd_lasname);
//            strAllergic = c.getString(fgf. kd_allergic);
//            strAdress = c.getString(fgf.kd_address);
//            strTel = c.getString(fgf.kd_number);

          Bundle c = getIntent().getExtras();
            if (c != null) {
                strNo = c.getString("patient_no");
                strName = c.getString("patient_name");
                strLastname = c.getString("patient_lastname");
                strAllergic = c.getString("patient_allergic");
                strAdress = c.getString("patient_address");
                strTel = c.getString("patient_number");

            }
//            strError = c.getString("Error");
//            strNo = c.getString("patient_no");
//            strName = c.getString("patient_name");
//            strLastname = c.getString("patient_lastname");
//            strAllergic = c.getString("patient_allergic");
//            strAdress = c.getString("patient_address");
//            strTel = c.getString("patient_number");

        } catch (Exception e) {

// TODO Auto-generated catch block
            e.printStackTrace();
        }

        tpatient_name.setText(strLastname);
        if (strNo.equals("")) {
            tpatient_no.setText(strNo);
            tpatient_name.setText(strName);
            tpatient_lastname.setText(strLastname);
            tpatient_allergic.setText(strAllergic);
            tpatient_address.setText(strAdress);
            tpatient_number.setText(strTel);
        } else {
            tpatient_no.setText(strNo);
            tpatient_name.setText(strName);
            tpatient_lastname.setText(strLastname);
            tpatient_allergic.setText(strAllergic);
            tpatient_address.setText(strAdress);
            tpatient_number.setText(strTel);
        }

    }


    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_information_p, menu);
        return true;


    }*/
}
       /* addinfoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Case.class));
            }
        });*/


