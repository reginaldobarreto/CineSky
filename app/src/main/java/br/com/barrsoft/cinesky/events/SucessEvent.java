package br.com.barrsoft.cinesky.events;

import java.util.List;
import br.com.barrsoft.cinesky.model.Models;

public class SucessEvent {

    private final List<Models> modelsList;

    public SucessEvent(List<Models> modelsList) {

        this.modelsList = modelsList;
    }

    public List<Models> getModelsList() {
        return modelsList;
    }
}