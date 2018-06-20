package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fm_seach3 extends Fragment {
    private  static  final  String TAG = "fm_seach3";
    //private Button historyP;
    Intent intent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seach_3,container,false);

        intent = new Intent(getActivity(), SeachDetail.class);

       Button historyP =(Button) view.findViewById(R.id.historyP);

        historyP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        return view;
    }
}

