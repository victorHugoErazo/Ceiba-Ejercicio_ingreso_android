package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.UserListVo;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;
import rx.subjects.PublishSubject;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewListUserData> {

    ArrayList<UserListVo> userListVo;

    public UserListAdapter(ArrayList<UserListVo> userListVo) {
        this.userListVo = userListVo;
    }

    @NonNull
    @Override
    public ViewListUserData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*Se captura el contexto se infla la vista con el layaout de listado de usuarios y se retorna*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, null, false);
        return new ViewListUserData(view);
    }

    private final PublishSubject<ArrayList<UserListVo>> onClickSubject = PublishSubject.create();

    @Override
    public void onBindViewHolder(@NonNull ViewListUserData holder, int position) {

        /*Se carga la información de los usuarios*/
        holder.id = userListVo.get(position).getId();
        holder.name.setText(userListVo.get(position).getName());
        holder.phone.setText(userListVo.get(position).getPhone());
        holder.email.setText(userListVo.get(position).getEmail());

    }


    @Override
    public int getItemCount() {
        return userListVo.size();
    }

    public class ViewListUserData extends RecyclerView.ViewHolder implements View.OnClickListener {
        int id;
        TextView name, phone, email;
        Button btn_view_post;

        public ViewListUserData(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            btn_view_post = itemView.findViewById(R.id.btn_view_post);
            btn_view_post.setOnClickListener(this);
        }

        /*Se crea un evento escucha para el boton ver publicaciones y se le envia los datos requeridos*/
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), PostActivity.class);
            intent.putExtra("id", String.valueOf(id));
            intent.putExtra("name", name.getText());
            intent.putExtra("phone", phone.getText());
            intent.putExtra("email", email.getText());
            view.getContext().startActivity(intent);
        }
    }


}
