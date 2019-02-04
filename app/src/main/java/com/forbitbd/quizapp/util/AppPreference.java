package com.forbitbd.quizapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static final String SP_NAME="SSSPPP";
    private static final String NAME="NAME";
    private static final String AGE="age";
    private static final String ID="ID";
    private static final String EMAIL="EMAIL";
    private static final String PASSWORD="PASSWORD";
    private static final String LOGIN="LOGIN";
    private static final String TOKEN="TOKEN";


    private static AppPreference appPreference = null;

    SharedPreferences sp;

    private AppPreference(Context context) {
        sp = context.getSharedPreferences(SP_NAME,context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context){
        if(appPreference==null){
            appPreference = new AppPreference(context);
        }
        return appPreference;
    }


    public boolean isLogin(){
        boolean login = sp.getBoolean(LOGIN,false);
        return login;
    }

    public void setLogin(boolean value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(LOGIN,value);
        editor.apply();
    }


    public String getToken(){
        String token = sp.getString(TOKEN,null);
        return token;
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TOKEN,token);
        editor.apply();
    }

    public String getEmail(){
        String token = sp.getString(EMAIL,null);
        return token;
    }

    public void setEmail(String email){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(EMAIL,email);
        editor.apply();
    }

    public String getPassword(){
        String token = sp.getString(PASSWORD,null);
        return token;
    }

    public void setPassword(String password){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PASSWORD,password);
        editor.apply();
    }


    public String getName(){
        String token = sp.getString(NAME,null);
        return token;
    }

    public void setName(String name){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(NAME,name);
        editor.apply();
    }

    public String getID(){
        String token = sp.getString(ID,null);
        return token;
    }

    public void setID(String id){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(ID,id);
        editor.apply();
    }

}
