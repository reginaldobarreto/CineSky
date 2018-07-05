package br.com.barrsoft.cinesky.events;

public class AdapterEvent {

    private final int position;

    public AdapterEvent(int position) {

        this.position = position;

    }

    public int getPosition() {
        return position;
    }
}