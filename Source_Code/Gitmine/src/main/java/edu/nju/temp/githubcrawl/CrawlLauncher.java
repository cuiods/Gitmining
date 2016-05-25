package edu.nju.temp.githubcrawl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Harry on 2016/5/22.
 */
public class CrawlLauncher {
    public static void main(String [] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/applicationContext.xml");
        GithubJsonHandler handler = (GithubJsonHandler)context.getBean(GithubJsonHandler.class);
        handler.loopNoRepoUsers();
    }
}
