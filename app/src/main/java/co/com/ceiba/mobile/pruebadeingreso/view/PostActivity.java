package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.logic.PostLogic;
import co.com.ceiba.mobile.pruebadeingreso.model.InterPost;

public class PostActivity extends Activity {

    TextView name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        /*Se resiven todos los parametros enviados por el Adaptador de Post*/
        Intent intent = getIntent();
        String getId = intent.getExtras().getString("id");
        String getName = intent.getExtras().getString("name");
        String getPhone = intent.getExtras().getString("phone");
        String getEmail = intent.getExtras().getString("email");

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        name.setText(getName);
        phone.setText(getPhone);
        email.setText(getEmail);
        getPost(getId);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void getPost(String getId){

        try {

            /*Se crea la instancia para cargar los post del usuario por su respectivo ID*/
            InterPost interPost = new PostLogic();
            interPost.getPost(this,getId);

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

}
