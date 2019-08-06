package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.PostListVo;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewListPostData> {

    ArrayList<PostListVo> postListVo;

    public PostListAdapter(ArrayList<PostListVo> postListVos) {
        this.postListVo = postListVos;
    }

    @NonNull
    @Override
    public ViewListPostData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*Se captura el contexto se infla la vista con el layaout de listado de post y se retorna*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item,null,false);
        return new ViewListPostData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.ViewListPostData holder, int position) {

        /*Se carga la información del titulo y del contenido del post*/
        holder.title.setText(postListVo.get(position).getTitle());
        holder.body.setText(postListVo.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postListVo.size();
    }

    public class ViewListPostData extends RecyclerView.ViewHolder {

        TextView title,body;

        public ViewListPostData(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body =  itemView.findViewById(R.id.body);
        }

    }
}
