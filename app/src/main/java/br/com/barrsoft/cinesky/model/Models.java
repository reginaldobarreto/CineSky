package br.com.barrsoft.cinesky.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Models {

    @SerializedName("title")
    String title;
    @SerializedName("overview")
    String overview;
    @SerializedName("duration")
    String duration;
    @SerializedName("release_year")
    String release_year;
    @SerializedName("cover_url")
    String cover_url;
    @SerializedName("backdrops_url")
    List<String> backdrops_url;
    @SerializedName("id")
    String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public List<String> getBackdrops_url() {
        return backdrops_url;
    }

    public void setBackdrops_url(List<String> backdrops_url) {
        this.backdrops_url = backdrops_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
