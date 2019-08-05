package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewListUserData> {

    ArrayList<UserListVo> userListVo;

    public UserListAdapter(ArrayList<UserListVo> userListVo){
        this.userListVo = userListVo;
    }

    @NonNull
    @Override
    public ViewListUserData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,null,false);
        return new ViewListUserData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewListUserData holder, int position) {

        holder.name.setText(userListVo.get(position).getName());
        holder.phone.setText(userListVo.get(position).getPhone());
        holder.email.setText(userListVo.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userListVo.size();
    }

    public class ViewListUserData extends RecyclerView.ViewHolder {

        TextView name,phone,email;

        public ViewListUserData(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
        }
    }
}
