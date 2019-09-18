package com.jyoon.tmdbbot.engine;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class StateMachine {
    @Setter
    private Map<String, Set<String>> paths;
    @Setter
    private Map<String, State> states;
    @Setter
    private Map<String, String> slots;
    private String current;

    public StateMachine(){
        log.debug("Initializing State Machine");
    }

    public void setRoot(String root){
        current = root;
    }

    public void build(){
        // check paths
        validatePaths();

        // print state machine
        log.info(this.toString());
    }

    public void setUtterances(Map<String, String> utterances){

    }

    private void validatePaths(){
        List<List<String>> paths = new ArrayList<>();
        List<String> pathStrings = new ArrayList<>();
        Queue<String> queue = new ArrayDeque<>();

        //TODO: implementation

    }

    private boolean checkLeafState(String stateName){
        Set<String> nextStates = paths.get(stateName);
        return nextStates.size() == 1 && nextStates.contains("");
    }

    @Override
    public String toString() {
        return "\n" + paths.keySet().stream().map(key ->
                "\t"+ states.get(key) + ": " +
                        paths.get(key).stream()
                                .map(name->
                                        name.equals("")? name : states.get(name).toString()
                                )
                                .collect(Collectors.joining(", ")
                )
        ).collect(Collectors.joining("\n"));

    }
}
