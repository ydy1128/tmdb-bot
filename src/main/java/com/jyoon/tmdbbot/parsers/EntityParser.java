package com.jyoon.tmdbbot.parsers;

import com.jyoon.tmdbbot.engine.Entity;
import com.jyoon.tmdbbot.repository.EntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Slf4j
@Component
public class EntityParser {
    @Value("${storypath:../rasa_tmdb/data/nlu-lookup.md}")
    private String path;

    private File entitiesFile;

    @Autowired
    EntityRepository entityRepository;

    public File loadEntityFile(){
        entitiesFile = new File(path);
        return entitiesFile;
    }

    public File loadCustomFile(File customFile){
        entitiesFile = customFile;
        return entitiesFile;
    }

    public void parse(){
        if(entitiesFile == null)
            loadEntityFile();

        try {
            Scanner scanner = new Scanner(entitiesFile);
            String currentType = "";
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.startsWith("##")){
                    String[] lineSplitted = line.split("lookup:");
                    currentType = lineSplitted[1].replace("\n", "");
                }
                else if (line.startsWith("-")){
                    String entityString = line.replace("- ", "").replace("\n", "");
                    Entity entity = new Entity(currentType, entityString);
                    log.debug("saving entity: {}",entity);
                    entityRepository.save(entity);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
