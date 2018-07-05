package br.com.barrsoft.cinesky.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.HashMap;

import br.com.barrsoft.cinesky.R;
import br.com.barrsoft.cinesky.datamanager.ContentContract;
import br.com.barrsoft.cinesky.presenter.ContentPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentActivity extends AppCompatActivity implements ContentContract.View{

    private static final String TAG = ContentActivity.class.getName();
    @Nullable@BindView(R.id.progressBarContent) ProgressBar progressBar;
    @BindView(R.id.toolbarContent) Toolbar toolbarContent;
    @BindView(R.id.imageContent) ImageView imageContent;
    @BindView(R.id.textTituloContent) TextView textTituloContent;
    @BindView(R.id.textDescricaoContent) TextView textDescricaoContent;
    public ContentPresenter presenter;
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        ButterKnife.bind(this);

        getBundle();
        setConteudo();
        setTitulo();
        setSupportActionBar(toolbarContent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void setConteudo() {

        Log.i(TAG,"HashMap: " + hashMap.get("titulo"));

        textTituloContent.setText(hashMap.get("titulo"));
        textDescricaoContent.setText(hashMap.get("descricao"));
        Glide.with(this).load(hashMap.get("imagem")).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(imageContent);
    }

    @Override
    public void setTitulo() {
        Log.i(TAG,"SetTitulo: " + hashMap.get("titulo"));
        toolbarContent.setTitle(hashMap.get("titulo"));
    }

    @Override
    public void getBundle() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        hashMap.put("titulo", bundle.getString("titulo"));
        hashMap.put("descricao", bundle.getString("descricao"));
        hashMap.put("imagem", bundle.getString("imagem"));

        Log.i(TAG,"getBundle() " + hashMap.get("titulo"));
    }

}