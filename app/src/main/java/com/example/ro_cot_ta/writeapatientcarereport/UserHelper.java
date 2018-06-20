package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserHelper {

    Context context;
    SharedPreferences sharedPerfs;
    Editor editor;

    // Prefs Keys
    static String perfsName = "UserHelper";
    static int perfsMode = 0;

    public UserHelper(Context context) {
        this.context = context;
        this.sharedPerfs = this.context.getSharedPreferences(perfsName, perfsMode);
        this.editor = sharedPerfs.edit();
    }

    /////////////////////////////////////////////////////

    public void createSession(String sid) {

        editor.putBoolean("LoginStatus", true); //เก็บค่า สถานะ login กับ id แพทย์
        editor.putString("id", sid);

        editor.commit();
    }

    //////////////////////////////////////////////////////

    public void deleteSession() {
        editor.clear();
        editor.commit();
    }

    public boolean getLoginStatus() {
        return sharedPerfs.getBoolean("LoginStatus", false);
    }

    public String getid() {
        return sharedPerfs.getString("id", null);
    }
}