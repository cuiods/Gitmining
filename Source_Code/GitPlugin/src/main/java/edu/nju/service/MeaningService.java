package edu.nju.service;


import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * analysis of news and comments
 * @author cuihao
 */
public interface MeaningService {
    List<String> keywordsOfNews(String owner,String name) throws UnirestException, IOException;
    double positiveNews(String owner,String name) throws UnirestException, IOException;
    Map<String, Long> commonComments(String owner,String name) throws UnirestException, IOException;
    double positiveComments(String owner,String name) throws UnirestException, IOException;
}
