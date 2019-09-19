package com.jyoon.tmdbbot.parsers;

import com.jyoon.tmdbbot.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import org.hamcrest.collection.IsMapContaining;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UtteranceParserTest {
    @Autowired
    UtteranceParser utteranceParser;

    @Test
    public void testFindSlot(){
        utteranceParser.findSlot("I got some {genre} movies!");
        assertThat(utteranceParser.getSlots(), IsMapContaining.hasKey("genre"));
    }

    @Test
    public void testUtteranceParse(){
        utteranceParser.parse();
        assertThat(utteranceParser.getSlots(), IsMapContaining.hasKey("genre"));
        assertThat(utteranceParser.getSlots(), IsMapContaining.hasKey("genre"));
        assertThat(utteranceParser.getUtterances(), IsMapContaining.hasEntry("search_genre", "Here are some {genre} movies you would enjoy!"));
        assertThat(utteranceParser.getUtterances(), IsMapContaining.hasEntry("search_crew", "I got some movies directed by {crew}."));

    }
}
