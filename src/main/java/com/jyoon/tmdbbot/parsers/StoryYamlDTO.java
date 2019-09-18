package com.jyoon.tmdbbot.parsers;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoryYamlDTO implements Serializable {
    @Getter @Setter
    List<PathYamlDTO> story = new ArrayList<>();
}