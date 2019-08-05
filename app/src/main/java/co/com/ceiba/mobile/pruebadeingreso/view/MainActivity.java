package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.logic.UserLogic;
import co.com.ceiba.mobile.pruebadeingreso.model.InterUser;


public class MainActivity extends Activity {

//    TextInputEditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionGetUser();

        actionGetFilterName();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void actionGetUser() {
        try {
            InterUser interUser = new UserLogic();
            interUser.getUser(this);

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

    public void actionGetFilterName(){

        try {

            InterUser interUser = new UserLogic();
            interUser.getUserForName(this);
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }

    }


}