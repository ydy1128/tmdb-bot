package com.jyoon.tmdbbot.parsers;

import com.jyoon.tmdbbot.TestConfig;
import com.jyoon.tmdbbot.engine.Entity;
import com.jyoon.tmdbbot.parsers.EntityParser;
import com.jyoon.tmdbbot.repository.EntityRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class EntityParserTest {
    @Autowired
    EntityParser entityParser;

    @Autowired
    EntityRepository entityRepository;

    static File testFile;

    @BeforeClass
    public static void makeCustomFile() throws IOException {
        testFile = File.createTempFile("test_entities", ".md");

        BufferedWriter out = new BufferedWriter(new FileWriter(testFile));
        out.write("## lookup:dummy\n" +
                "- one\n" +
                "- two\n" +
                "- three");
        out.close();

        testFile.deleteOnExit();
    }

    @Before
    public void resetRepository(){
        entityRepository.deleteAll();
    }


    @Test
    public void fileLoadTest(){
        File entitiesFile = entityParser.loadEntityFile();
        assertTrue(entitiesFile.exists());
    }

    @Test
    public void testParse(){
        entityParser.loadEntityFile();
        entityParser.parse();
        List<Entity> entities = Arrays.asList(
                entityRepository.findByTypeAndValue("genre", "crime"),
                entityRepository.findByTypeAndValue("cast", "Angelina Jolie"),
                entityRepository.findByTypeAndValue("crew", "David Fincher"),
                entityRepository.findByTypeAndValue("company", "DC Entertainment"),
                entityRepository.findByTypeAndValue("crew", "Adam Sandler")
        );

        assertThat(entities.get(0).getType(), is("genre"));
        assertThat(entities.get(0).getValue(), is("crime"));
        assertThat(entities.get(1).getType(), is("cast"));
        assertThat(entities.get(1).getValue(), is("Angelina Jolie"));
        assertThat(entities.get(2).getType(), is("crew"));
        assertThat(entities.get(2).getValue(), is("David Fincher"));
        assertThat(entities.get(3).getType(), is("company"));
        assertThat(entities.get(3).getValue(), is("DC Entertainment"));
        assertNull(entities.get(4));
    }

    @Test
    public void customFileLoadTest(){
        assertTrue(testFile.exists());
        entityParser.loadCustomFile(testFile);
        entityParser.parse();
        Entity entite = entityRepository.findByTypeAndValue("dummy", "two");
        assertThat(entite.getType(), is("dummy"));
        assertThat(entite.getValue(), is("two"));
    }


}
