package br.com.barrsoft.cinesky.interfaces;

import java.util.List;
import br.com.barrsoft.cinesky.model.Models;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("Movies")
    Call<List<Models>> getMoviesList();
}
