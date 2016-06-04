package edu.nju.temp.githubcrawl;

import edu.nju.dao.impl.DataUpdater;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by Harry on 2016/5/22.
 */
public class CrawlLauncher {
    public static void main(String [] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/applicationContext.xml");
        GithubJsonHandler handler = (GithubJsonHandler)context.getBean(GithubJsonHandler.class);
        handler.addOrgs();
        //handler.initRepoLabel();

//        new CrawlLauncher().saveDescriptionToFile();
    }

    public void saveDescriptionToFile(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/applicationContext.xml");
        DataUpdater dataUpdater = (DataUpdater)context.getBean(DataUpdater.class);
        List<Object[]> description = dataUpdater.getAllDescription();
        File file = new File("src/main/java/edu/nju/temp/description.txt");
        try {
            PrintStream stream = new PrintStream(file);
            for (Object[] item:description){
                stream.print(item[0]+" ");
                stream.println(item[1]);
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
