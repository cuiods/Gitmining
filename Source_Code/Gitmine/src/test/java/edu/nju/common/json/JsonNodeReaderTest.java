package edu.nju.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/14.
 * test class for json node reader, which read graph data from github.
 */
public class JsonNodeReaderTest {

    private JsonNodeReader reader;

    public JsonNodeReaderTest() {
        reader = new JsonNodeReader();
    }

    @Test
    public void getCommitByContributors() throws Exception {
        String ownername = "rubinius";
        String reponame = "rubinius";
        JsonNode node = reader.getCommitByContributors(ownername, reponame);
        assertNotNull(node);

        JsonNode nullNode = reader.getCommitByContributors("Harry1001","no_repo");
        assertNull(nullNode);

        for (JsonNode child : node){
            System.out.println(child.get("total").asInt());
            System.out.println(child.get("author").get("login").asText());

        }
    }

    @Test
    public void getCodeFrequency() throws Exception {
        JsonNode node = reader.getCodeFrequency("rubinius", "rubinius");
        assertNotNull(node);

        JsonNode nullNode = reader.getCodeFrequency("Harry1001", "no_repo");
        assertNull(nullNode);

        assertTrue(node.size()>0);
        System.out.println("code frequency size "+node.size());

    }

    @Test
    public void getPunchCard() throws Exception {
        JsonNode node = reader.getPunchCard("rubinius", "rubinius");
        assertNotNull(node);
        assertTrue(node.size()>0);

        JsonNode nullNode = reader.getPunchCard("Harry1001", "no_repo");
        assertNull(nullNode);
    }

}