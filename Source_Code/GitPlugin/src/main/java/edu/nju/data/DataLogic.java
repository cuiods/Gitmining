package edu.nju.data;

import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * Created by cuihao on 2016/8/29.
 */
@Repository
public class DataLogic {
    @Resource
    private GithubReader githubReader;

    @Resource
    private SessionFactory sessionFactory;

    private SecRepoEntity getRepo(long id){
        Session session = sessionFactory.openSession();
        SecRepoEntity secRepoEntity = null;
        Query query = session.createQuery("from SecRepoEntity where id=:id");
        query.setLong("id",id);
        List results = query.list();
        if (results.size()>0) {
            secRepoEntity = (SecRepoEntity) results.get(0);
        }
        session.close();
        return secRepoEntity;
    }

    public void startLogic(int start, int max) {
        List<SecRepoEntity> entities = getRepos(start,max);
        int i = 0;
        File file = new File("output.txt");
        for (SecRepoEntity secRepoEntity:entities) {
            int total = max;
            if (secRepoEntity!=null) {
                githubReader.updateActivity(secRepoEntity.getOwner(),secRepoEntity.getName());
                githubReader.updateWeekly(secRepoEntity.getOwner(),secRepoEntity.getName());
                githubReader.updatePunch(secRepoEntity.getOwner(),secRepoEntity.getName());
            }
            i++;
            if (i%10==total%10) {
//            if (true) {
//                System.out.println("Complete:"+i*1.0/total);
                PrintWriter out = null;
                try{
                    FileWriter writer = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(writer);
                    out = new PrintWriter(bw);
                    //out.println("Complete: "+i*1.0/total);
                    out.println("Now at: "+(i+start-1));
                    System.out.println("===================Now at: "+(i+start-1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (out!=null){
                        out.close();
                    }
                }
            }
        }
        PrintWriter out = null;
        try{
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);
            out = new PrintWriter(bw);
            //out.println("Complete: "+i*1.0/total);
            int to = start+max;
            out.println("from "+start+" to "+to+" is completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (out!=null){
                out.close();
            }
        }
    }

    private List<SecRepoEntity> getRepos(int start, int max) {
        Session session = sessionFactory.openSession();
        List<SecRepoEntity> entities = null;
        Query query = session.createQuery("from SecRepoEntity ");
        query.setFirstResult(start);
        query.setMaxResults(max);
        entities = query.list();
        session.close();
        return entities;
    }
}
