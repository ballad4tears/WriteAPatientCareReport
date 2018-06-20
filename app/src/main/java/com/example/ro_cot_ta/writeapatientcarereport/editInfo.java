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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ro_cot_ta.writeapatientcarereport.NetConnect.getHttpPost;

/**
 * Created by Ro-Cot-Ta on 18-May-18.
 */

public class editInfo extends android.support.v4.app.Fragment {

    private  static  final  String TAG = "editInfo";
    private static Context getActivity;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_info, container, false);

        final TextView tpatient_no = (TextView) view.findViewById(R.id.txtMemberID);
        final TextView tpatient_name = (TextView) view.findViewById(R.id.txtname);
        final TextView tpatient_lastname = (TextView) view.findViewById(R.id.txtlastname);
        final TextView tpatient_allergic = (TextView) view.findViewById(R.id.txtallergic);
        final TextView tpatient_address = (TextView) view.findViewById(R.id.txtaddress);
        final TextView tpatient_number = (TextView) view.findViewById(R.id.txtTel);
        String url = "http://192.168.43.92:80/testregister/new_dataP.php";
        Intent intent = getActivity().getIntent();
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


            Bundle c = getActivity().getIntent().getExtras();
            if (c != null) {
                strNo = c.getString("patient_no");
                strName = c.getString("patient_name");
                strLastname = c.getString("patient_lastname");
                strAllergic = c.getString("patient_allergic");
                strAdress = c.getString("patient_address");
                strTel = c.getString("patient_number");

            }
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

        return view;
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

}

