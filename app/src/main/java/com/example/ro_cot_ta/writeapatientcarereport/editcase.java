package com.example.ro_cot_ta.writeapatientcarereport;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class editcase extends android.support.v4.app.Fragment{
    private  static  final  String TAG = "editcase";
    private Button nexttodia;
    Intent intent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_case,container,false);

        intent = new Intent(getActivity(), Edit_3.class);
        nexttodia =(Button) view.findViewById(R.id.nexttodia);
        nexttodia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        return view;
    }
}


