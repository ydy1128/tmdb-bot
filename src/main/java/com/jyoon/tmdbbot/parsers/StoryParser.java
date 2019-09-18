package com.jyoon.tmdbbot.parsers;

import com.jyoon.tmdbbot.engine.State;
import com.jyoon.tmdbbot.engine.StateMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class StoryParser {
    @Value("${storypath:data/story.yml}")
    private String path;

    StoryYamlDTO dto;

    public void parse(){
        File storyFile = new File(path);
        Constructor yamlConstructor = new Constructor(StoryYamlDTO.class);
        Yaml storyYaml = new Yaml(yamlConstructor);
        try {
            dto = storyYaml.load(new FileInputStream(storyFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//        if (dto != null){
//            for (PathYamlDTO path : dto.getStory()){
//                // iterate through each two items
//                for (int i = 0; i < path.getPath().size() - 1; i++){
//                    String currentName = path.getPath().get(i);
//                    String nextName = path.getPath().get(i+1);
//                    State currentState = new State(currentName);
//                    State nextState = new State(nextName);
//                    stateMachine.addState(currentState, nextState);
//                }
//                stateMachine.addState(new State(path.getPath().get(path.getPath().size()-1)), null);
//            }
//        }
    }

    public Map<String, State> getStates(){
        return new HashMap<>();
    }

    public Map<String, Set<String>> getPaths(){
        return new HashMap<>();
    }

    public void addState(State newState, State nextState){
//        String currentStateName = newState.getName();
//        String nextStateName = nextState == null ?  "" : nextState.getName();
//        if (!states.containsKey(currentStateName)){
//            states.put(currentStateName, newState);
//            paths.put(currentStateName, new HashSet<>());
//        }
//
//        if (!paths.get(currentStateName).contains(nextStateName)){
//            paths.get(currentStateName).add(nextStateName);
//        }

    }
}
