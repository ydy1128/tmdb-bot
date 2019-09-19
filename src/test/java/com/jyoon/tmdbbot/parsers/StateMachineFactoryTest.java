package com.jyoon.tmdbbot.parsers;

import com.jyoon.tmdbbot.TestConfig;
import com.jyoon.tmdbbot.engine.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import org.hamcrest.collection.IsMapContaining;
//import org.hamcrest.collection.*;
import org.hamcrest.collection.IsIn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class StateMachineFactoryTest {
    @Autowired
    StoryParser storyParser;

    @Autowired
    UtteranceParser utteranceParser;

    @Test
    public void testAddState(){
        storyParser.addState(new State("greet"), new State("search_genre"));
        storyParser.addState(new State("greet"), new State("search_cast"));
        storyParser.addState(new State("search_genre"), new State("search_cast"));
        storyParser.addState(new State("search_cast"), new State(""));

        assertThat("search_genre", IsIn.isIn(storyParser.getPaths().get("greet")));
        assertThat("search_cast", IsIn.isIn(storyParser.getPaths().get("greet")));
        assertThat("search_cast", IsIn.isIn(storyParser.getPaths().get("search_genre")));
        assertThat("", IsIn.isIn(storyParser.getPaths().get("search_cast")));
    }

    @Test
    public void testStoryParse(){
        storyParser.parse();
        assertThat("search_genre", IsIn.isIn(storyParser.getPaths().get("greet")));
        assertThat("search_genre", IsIn.isIn(storyParser.getPaths().get("search_crew")));
        assertThat("goodbye", IsIn.isIn(storyParser.getPaths().get("search_cast")));
        assertThat("", IsIn.isIn(storyParser.getPaths().get("goodbye")));
    }

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
