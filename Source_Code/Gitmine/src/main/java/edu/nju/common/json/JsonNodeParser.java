package edu.nju.common.json;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Harry on 2016/5/14.
 * this class is designed to delete useless information from json node about graphs read from <br/>
 * github and parse <tt>JsonNode</tt> to a <tt>Map</tt>
 */
@Service
public class JsonNodeParser {

    @Resource
    private JsonNodeReader jsonNodeReader;

    public JsonNodeParser() {
    }


}
