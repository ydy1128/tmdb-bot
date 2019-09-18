package com.jyoon.tmdbbot.engine;

import com.jyoon.tmdbbot.parsers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StateMachineFactory {

    @Autowired
    StoryParser storyParser;

    @Autowired
    UtteranceParser utteranceParser;

    @Autowired
    EntityParser entityParser;

    @PostConstruct
    public void buildFactory(){
        storyParser.parse();
        utteranceParser.parse();
        entityParser.parse();
    }

    public StateMachine instansiate(String sessionId){
        // validate session
        // maybe change it to aop? --> brainstorm
        StateMachine stateMachine = new StateMachine();
        stateMachine.setRoot("greet");
        stateMachine.setStates(storyParser.getStates());
        stateMachine.setPaths(storyParser.getPaths());
        stateMachine.setUtterances(utteranceParser.getUtterances());
        stateMachine.setSlots(utteranceParser.getSlots());
        stateMachine.build();

        return stateMachine;
    }
}
