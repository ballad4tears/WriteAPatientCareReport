package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ro-Cot-Ta on 13-May-18.
 */

    public class Prescription extends Fragment {
        private static final String TAG = "infocase";

        String Tempdrug;
        Button sumit,microphon,camera;
        EditText drug;

        final String ServerURL = "http://192.168.43.92:80/testregister/getPrescription.php" ;
        Intent intent,intent2;;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.prescription,container,false);


            intent = new Intent(getActivity(), Camera.class);
            intent2 = new Intent(getActivity(), Audio.class);

            sumit = (Button) view.findViewById(R.id.sumit);
            drug = (EditText) view.findViewById(R.id.drug);
            microphon = (Button) view.findViewById(R.id.microphon);
            camera =(Button) view.findViewById(R.id.camera);


             camera.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(intent);
                }
            });

            microphon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(intent2);
                }
            });


            sumit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GetData();
                    InsertData(Tempdrug);
                }
            });
            //ref https://androidjson.com/android-php-send-data-mysql-database/
            return view;

        }


    public void GetData(){

        Tempdrug = drug.getText().toString();

    }

    public void InsertData(final String drug){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String drugHolder = drug ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("drug", drugHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(getActivity(),"Data Submit Successfully",Toast.LENGTH_SHORT).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(drug);
    }
    }

