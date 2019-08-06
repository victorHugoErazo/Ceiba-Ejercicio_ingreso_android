package co.com.ceiba.mobile.pruebadeingreso.logic;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserListAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.UserListVo;
import co.com.ceiba.mobile.pruebadeingreso.dto.UserDTO;
import co.com.ceiba.mobile.pruebadeingreso.model.IUserRetrofitService;
import co.com.ceiba.mobile.pruebadeingreso.model.InterUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import co.com.ceiba.mobile.pruebadeingreso.R;


public class UserLogic implements InterUser {

    ArrayList<UserListVo> userListVo;
    RecyclerView recyclerViewUser;


    @Override
    public void getUser(Context context) throws Exception {

        try {

            final Activity activity = (Activity) context;
            activity.setContentView(R.layout.activity_main);
            userListVo = new ArrayList<>();
            recyclerViewUser = activity.findViewById(R.id.recyclerViewSearchResults);

            LinearLayoutManager manager = new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false);

            recyclerViewUser.setLayoutManager(manager);

            IUserRetrofitService getCone = new ConnectServiceRestRetrofit().getCone();

            /*Se realiza llamado al metodo que consulta todos los usuarios*/
            Call<List<UserDTO>> call = getCone.getUsers();

            call.enqueue(new Callback<List<UserDTO>>() {
                @Override
                public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {

                    if (!response.isSuccessful()) {

                        Log.i("Conexion", "No fallida::" + response.code());
                    } else {

                        List<UserDTO> userDTOList = response.body();

                        /*Se recorre los items y se agrega a un arrayList*/
                        for (UserDTO userDTO : userDTOList) {

                            userListVo.add(new UserListVo(userDTO.getName(), userDTO.getPhone(), userDTO.getEmail(), userDTO.getId()));
                        }

                        /*Se pasan al adaptador*/
                        UserListAdapter userListAdapter = new UserListAdapter(userListVo);
                        recyclerViewUser.setAdapter(userListAdapter);

                        /*Se muestra mensaje de cargando*/
                        Toast toast = Toast.makeText(activity, "Usuarios Cargados", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                    Log.i("Conexion", "Mensaje::" + t.getMessage());
                }
            });


        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            throw e;
        }
        getUserForName(context);
    }


    /*Metodo que realiza filtro por nombre de usuario*/
    @Override
    public void getUserForName(final Context context) throws Exception {

        try {

            final Activity activity = (Activity) context;
            final EditText textInputEditText = activity.findViewById(R.id.editTextSearch);

            /*Se detecta cambios en el campo y se recarga el adapter*/
            textInputEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    /*Con el arraylist de usuario cargados anterior mente
                    * Se compara con el actual en el campo de busqueda*/
                    ArrayList<UserListVo> arrayTemp = new ArrayList<>();
                    for (UserListVo temp : userListVo) {
                        if (temp.getName().toUpperCase().contains(charSequence.toString().toUpperCase())) {
                            arrayTemp.add(temp);
                        }
                    }

                    /*Se recarga el adapter con el nuevo array temporal*/
                    UserListAdapter userListAdapter = new UserListAdapter(arrayTemp);
                    recyclerViewUser.setAdapter(userListAdapter);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            throw e;
        }
    }

}
