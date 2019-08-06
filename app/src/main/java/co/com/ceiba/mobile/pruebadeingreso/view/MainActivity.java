package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Se llama el metodo actionGetUser para
        que liste todos los usuarios
        */
        actionGetUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void actionGetUser() {

        try {

            /*Se crea la instancia de la clase usuario logica
              Y se realiza llamado al metodo que trae todos los
              Usuarios y se le pasa el contexto
            */
            InterUser interUser = new UserLogic();
            interUser.getUser(this);

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

}