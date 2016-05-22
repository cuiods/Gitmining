package edu.nju.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.TimeTranslator;
import edu.nju.model.pojo.CodeFrequency;
import edu.nju.model.pojo.CommitChart;
import edu.nju.model.pojo.SimpleChart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/15.
 * test for json node parser
 */
public class JsonNodeParserTest {

    private JsonNodeReader reader;

    private TimeTranslator timeTranslator;

    private JsonNodeParser jsonNodeParser;


    public JsonNodeParserTest() {
        reader = new JsonNodeReader();
        timeTranslator = new TimeTranslator();
        jsonNodeParser = new JsonNodeParser();
        jsonNodeParser.setJsonNodeReader(reader);
        jsonNodeParser.setTimeTranslator(timeTranslator);
    }

    @Test
    public void getCommitByContributors() throws Exception {
        Map<String,CommitChart> map = jsonNodeParser.getCommitByContributors("rubinius", "rubinius");
        assertNotNull(map);
        assertTrue(map.size()>0);
        for (CommitChart chart: map.values()){
            System.out.println(chart.getContributorName()+" commit count "+chart.getCommitCount());
        }

        Map<String,CommitChart> dd = jsonNodeParser.getCommitByContributors("harry","no_repo");
        assertEquals(dd.size(), 0);
    }

    @Test
    public void getCodeFrequency() throws Exception {
        CodeFrequency codeFrequency = jsonNodeParser.getCodeFrequency("garlik", "4store");
        assertNotNull(codeFrequency);
        assertTrue(codeFrequency.getAdd().length>0);
        System.out.println("code frequency time "+codeFrequency.getField()[0]);
        System.out.println("code frequency additions "+codeFrequency.getAdd()[0]);
    }

    @Test
    public void getPunchCard() throws Exception {
        JsonNode punch = jsonNodeParser.getPunchCard("rubinius", "rubinius");
        assertNotNull(punch);
    }

}