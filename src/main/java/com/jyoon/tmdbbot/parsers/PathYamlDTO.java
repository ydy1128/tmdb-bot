package com.jyoon.tmdbbot.parsers;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PathYamlDTO implements Serializable {
    @Getter @Setter
    List<String> path = new ArrayList<>();
}