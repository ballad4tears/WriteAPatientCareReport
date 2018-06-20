package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by Ro-Cot-Ta on 13-May-18.
 */

public class infocase extends Fragment {
    private static final String TAG = "infocase";
    private static Context getActivity;

    private EditText td_symptom;
    private Button sumit,camera,microphon; //microphon,camera,mute,
    private String kd_symptom; //kd_microphon,kd_camera,kd_mute,kd_sumit,
    final String URL_infocase = "192.168.43.92:80/testregister/treatDetail.php";

    Intent intent,intent2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infocase, container, false);

        intent = new Intent(getActivity(), Camera.class);
        intent2 = new Intent(getActivity(), Audio.class);

        td_symptom =(EditText) view.findViewById(R.id.td_symptom);
        microphon = view.findViewById(R.id.microphon);
        sumit =(Button) view.findViewById(R.id.sumit);
        // mute = view.findViewById(R.id.mute);

        camera = view.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        microphon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(getActivity(), Audio.class);
                startActivity(intent2);
            }
        });




//        sumit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                onEditText();
//                onButtonClick();
//            }
//
//            private void onEditText() {
//
//                kd_symptom = td_symptom.getText().toString();       //ประกาศตัวเก็บค่า
//            }
//
//            private void onButtonClick() {
//                if (!kd_symptom.isEmpty()) {   //เช็คข้อมูล ถ้า emthy จะไม่ทำตามเงื่อนไข ถ้าไม่กรอกข้อมูลจะไม่ post ข้อมูลขึ้นไป
//
//                    //instantiate request queue
//                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext()); //เรียกตัว volley
//                    //Response using URL
//                    StringRequest request = new StringRequest(Request.Method.POST, URL_infocase, new Response.Listener<String>() { //เพิ่มข้อมูลเข้าไป ใน URL ที่สร้าง ใช้ post
//                        @Override
//                        public void onResponse(String response) { //response การตอบกลับ ว่า ตัว host มีการเพิ่มข้อมูลเรียบร้อย
//                            Log.d("onResponse", response);  //ดักจับ error
//                            td_symptom.setText("");
//
//                            Toast.makeText(infocase.this.getActivity(), "เพิ่มข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
//                        }
//                    }, new Response.ErrorListener() {  //ถ้าเออเลอร์ เซิฟจะตอบมาว่า error
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("onError",error.toString()); //ดักจับ error
//                            Toast.makeText(infocase.this.getActivity(),"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
//                        }
//                    }) {
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {  //เหมือน hashmap ต้องกำหนด key และข้อมูลที่จะอัพขึ้นไป key อยู่ในไฟล์ php
//                            Map<String, String> params = new HashMap<String, String>(); //ทำการกำหนดคีย์ ละข้อมูลที่รับเข้าไป
//                            params.put("td_symptom", kd_symptom);
//                            return params; // return ค่าไปให้ volley ว่าใช้งานได้หรือ error
//                        }
//                    };
//                    requestQueue.add(request); //แอดคิว สตริงรีเควส เมื่อ add เสร็จแล้ว ข้อมูลขะเพ่ิมไปในฐานข้อมูล
//                }else{
//                    Toast.makeText(infocase.this.getActivity(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        return view;
    }



}

