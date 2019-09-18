package com.jyoon.tmdbbot.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class State {
    @Getter
    String name;
    @Getter
    List<String> intents;
    @Getter
    String utterance;

    public State(String name){
        this.name = name;
        intents = new ArrayList<>();
        utterance = "";
    }

    @Override
    public boolean equals(Object s) {
        if (s instanceof State)
            return this.name.equals(((State) s).name);
        else
            return false;
    }

    @Override
    public String toString() {
        return String.format("State(%s)",this.name);
    }
}
