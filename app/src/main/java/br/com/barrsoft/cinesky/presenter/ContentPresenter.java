package br.com.barrsoft.cinesky.presenter;

import br.com.barrsoft.cinesky.datamanager.ContentContract;

public class ContentPresenter implements ContentContract.Presenter{

    private static final String TAG = ContentPresenter.class.getName();
    private final ContentContract.View view;

    public ContentPresenter(ContentContract.View view) {

        this.view = view;
    }

}