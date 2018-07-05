package br.com.barrsoft.cinesky.services;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.barrsoft.cinesky.events.SucessEvent;
import br.com.barrsoft.cinesky.interfaces.Service;
import br.com.barrsoft.cinesky.model.Models;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {


    private static final String BASE_URL = "https://sky-exercise.herokuapp.com/api/";
    private Retrofit retrofit;
    private static final String TAG = ServiceProvider.class.getName();

    private Retrofit getretrofit(){

        //verificar existencia de instancia retrofit
        if(this.retrofit == null){
            //construir o retrofit
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    public void getMovieList(){
        Service service= getretrofit().create(Service.class);
        Call<List<Models>> listCall = service.getMoviesList();

        listCall.enqueue(new Callback<List<Models>>() {
            @Override
            public void onResponse(Call<List<Models>> call, Response<List<Models>> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"OnResponse Fail"  + response.code());
                }else{
                    List<Models> modelsList = new ArrayList<>();
                    modelsList = response.body();
                    Log.i(TAG, "OnResponse "+ response.body().size() +" itens");
                    EventBus.getDefault().post(new SucessEvent(modelsList));
                    Log.i(TAG,"Response" + response.body().get(1).getBackdrops_url().get(1));
                }
            }

            @Override
            public void onFailure(Call<List<Models>> call, Throwable t) {
                Log.i(TAG, "OnFailure " + t.getMessage());
            }
        });
    }
}