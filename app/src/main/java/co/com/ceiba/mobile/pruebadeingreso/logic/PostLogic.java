package co.com.ceiba.mobile.pruebadeingreso.logic;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.PostListAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.PostListVo;
import co.com.ceiba.mobile.pruebadeingreso.dto.PostDTO;
import co.com.ceiba.mobile.pruebadeingreso.model.IUserRetrofitService;
import co.com.ceiba.mobile.pruebadeingreso.model.InterPost;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostLogic implements InterPost {

    ArrayList<PostListVo> postListVos;
    RecyclerView recyclerViewPost;

    @Override
    public void getPost(final Context context,String getId) throws Exception {

        try {

            final Activity activity = (Activity) context;

            postListVos = new ArrayList<>();
            recyclerViewPost = activity.findViewById(R.id.recyclerViewPostsResults);

            LinearLayoutManager manager = new GridLayoutManager(context,1,LinearLayoutManager.VERTICAL,false);
            recyclerViewPost.setLayoutManager(manager );

            IUserRetrofitService getCone = new ConnectServiceRestRetrofit().getCone();

            /*Se realiza llamado al metodo que consulta todos post por id de usuario*/
            Call<List<PostDTO>> callPost = getCone.getPost(getId);

            callPost.enqueue(new Callback<List<PostDTO>>() {
                @Override
                public void onResponse(Call<List<PostDTO>> call, Response<List<PostDTO>> response) {

                    if(response.isSuccessful()){

                        List<PostDTO> postDTOList = response.body();

                        for(PostDTO postDTO: postDTOList){

                            /*Se recorre los items y se agrega a un arrayList*/
                            postListVos.add(new PostListVo(postDTO.getTitle(),postDTO.getBody()));
                        }

                        /*Se pasan al adaptador*/
                        PostListAdapter postListAdapter = new PostListAdapter(postListVos);
                        recyclerViewPost.setAdapter(postListAdapter);
                    }

                }

                @Override
                public void onFailure(Call<List<PostDTO>> call, Throwable t) {

                }
            });

        }catch (Exception e){
            Log.e("Error",e.getMessage());
            throw  e;
        }
    }

}
