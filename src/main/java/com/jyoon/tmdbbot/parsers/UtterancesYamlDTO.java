package com.jyoon.tmdbbot.parsers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class UtterancesYamlDTO {
    @Getter @Setter
    List<UtteranceYamlDTO> utterances = new ArrayList<>();
}
