package br.com.barrsoft.cinesky.datamanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.barrsoft.cinesky.view.ContentActivity;

public interface ContentContract {

    interface View{

        void setConteudo();
        void setTitulo();
        void getBundle();
    }

    interface Presenter{

    }
}
