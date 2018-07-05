package br.com.barrsoft.cinesky.presenter;

import android.util.Log;
import br.com.barrsoft.cinesky.datamanager.HomeContract;
import br.com.barrsoft.cinesky.services.ServiceProvider;import br.com.barrsoft.cinesky.view.HomeActivity;

public class HomePresenter implements HomeContract.Presenter{

    private static final String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    public void requestMovieList() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.getMovieList();
        Log.i(TAG,"requestMovieList()");
    }

}