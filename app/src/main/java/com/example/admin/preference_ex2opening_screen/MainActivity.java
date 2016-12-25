package com.example.admin.preference_ex2opening_screen;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //take a preference file - with in tvariable counter
        //if counter =0,display fragment 1 make counter=1;
        //if counter=1,display fragment 2
        SharedPreferences sharedPreferences=getSharedPreferences("firsttime",0);//1st time open the file
        int counter=sharedPreferences.getInt("counter",0);
        if(counter==1){
            //user is returning to user,so open fragment
            FragmentTwo fragmentTwo=new FragmentTwo();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,fragmentTwo);
            fragmentTransaction.commit();
            return;
            
        }
        //load f1
        FragmentOne fragmentOne=new FragmentOne();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,fragmentOne);
        fragmentTransaction.commit();
        SharedPreferences.Editor editor=sharedPreferences.edit();//putting in counter==1 in xml
        editor.putInt("counter",1);
        editor.commit();
        //after 5 sec load second fragment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //android os woill come
                FragmentTwo fragmentTwo=new FragmentTwo();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,fragmentTwo);
                fragmentTransaction.commit();

            }

        } ,5000);
    }
}
