package com.jyoon.tmdbbot.parsers;

import lombok.Getter;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class UtteranceParser {
    @Value("${utterancepath:data/utterances.yml}")
    private String filePath;

    private UtterancesYamlDTO dto;

    @Getter
    private Map<String, String> utterances = new HashMap<>();
    @Getter
    private Map<String, String> slots = new HashMap<>();


    public void parse() {
        File utterancesFile = new File(filePath);
        Constructor yamlConstructor = new Constructor(UtterancesYamlDTO.class);
        Yaml utterancesYaml = new Yaml(yamlConstructor);
        try {
            dto = utterancesYaml.load(new FileInputStream(utterancesFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (dto != null){
            for (UtteranceYamlDTO utterance : dto.utterances){
                String utterText = utterance.utterance;

                addUtterance(utterance.state, utterText);
                findSlot(utterText);
            }
        }
    }

    private void addUtterance(String state, String utterText){
        utterances.put(state, utterText);
    }

    void findSlot(String utterText){
        String slotRegex = "\\{(.*?)\\}";
        Pattern slotPattern = Pattern.compile(slotRegex);
        Matcher slotMatcher = slotPattern.matcher(utterText);
        while (slotMatcher.find()){
            String slotName = slotMatcher.group(1);
            log.debug("slot found: {}", slotName);
            slots.put(slotName, "");
        }
    }
}
