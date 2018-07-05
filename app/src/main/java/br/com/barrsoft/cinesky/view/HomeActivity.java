package br.com.barrsoft.cinesky.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import br.com.barrsoft.cinesky.R;
import br.com.barrsoft.cinesky.adapters.RecyclerAdapter;
import br.com.barrsoft.cinesky.datamanager.HomeContract;
import br.com.barrsoft.cinesky.events.AdapterEvent;
import br.com.barrsoft.cinesky.events.SucessEvent;
import br.com.barrsoft.cinesky.model.Models;
import br.com.barrsoft.cinesky.presenter.HomePresenter;
import br.com.barrsoft.cinesky.services.ServiceProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @Nullable@BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    private List<Models> list = new ArrayList<>();
    private static final String TAG = HomeActivity.class.getName();
    public HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenter(this);
        presenter.requestMovieList();

        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(new RecyclerAdapter(list));

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SucessEvent sucessEvent) {
        /* Do something */

        list = sucessEvent.getModelsList();
        recyclerView.setAdapter(new RecyclerAdapter(list));
        progressBar.setVisibility(View.GONE);
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AdapterEvent adapterEvent) {

        /* Do something */

        int posicao = adapterEvent.getPosition();

        Intent intent = new Intent(HomeActivity.this, ContentActivity.class);
        intent.putExtra("titulo",list.get(posicao).getTitle());
        intent.putExtra("descricao", list.get(posicao).getOverview());
        intent.putExtra("imagem", list.get(posicao).getBackdrops_url().get(0));
        startActivity(intent);

        Log.i(TAG,"Titulo enviado: " + list.get(posicao).getTitle());
        Log.i(TAG,"Descricao enviado: " + list.get(posicao).getOverview());
        Log.i(TAG,"Imagem enviada: " + list.get(posicao).getBackdrops_url().get(0));

    };

}