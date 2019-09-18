package com.jyoon.tmdbbot.parsers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UtteranceParser {
    @Value("${utterancepath:data/utterances.yml}")
    private String path;

    private File utterancesFile;

    public void parse() {
        File storyFile = new File(path);
        Constructor yamlConstructor = new Constructor(UtteranceParser.class);
        Yaml storyYaml = new Yaml(yamlConstructor);
        UtterancesYamlDTO dto = null;
        try {
            dto = storyYaml.load(new FileInputStream(storyFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getUtterances(){
        return new HashMap<>();
    }

    public Map<String, String> getSlots(){
        return new HashMap<>();
    }
}
