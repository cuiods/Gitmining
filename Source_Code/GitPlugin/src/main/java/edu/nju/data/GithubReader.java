package edu.nju.data;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * prepare to get stat data
 * @author cuihao
 */
@Repository
public class GithubReader {

    @Resource
    private SessionFactory sessionFactory;

    private static final String[] auth =  {
            "?client_id=c0ac13f51484e1dcdad6&client_secret=73c20d744d1fce8be5ce5d2a791774f79b7f648a",
            "?client_id=664f63808c14fd653d40&client_secret=d3f2b14218fbf526beca11f608d2cc1c7027c6d3",
            "?client_id=6fbaa89b6cc2e1adca05&client_secret=a6b23034611542b74dd013e1dcddc12b605cbbe6",
            "?client_id=ed1216fef5af28831e2d&client_secret=0c9c1e892653787fde9d1cce7a1f58365ba4599a",
            "?client_id=a933b93042f032033396&client_secret=39ce7e6532fed6803bcb4ca56b4b66779a89c8b7",
            "?client_id=7520cbd5813c2b44d00b&client_secret=93f6479c9f159ba0aeae5667f1fa20ea5c3f95b5"};

    private static int auth_count = 0;

    private final String repoUrl = "https://api.github.com/repos";

    private String getAuth(){
        return auth[(auth_count++)%auth.length];
    }

    private String getActivityUrl(String owner, String name) {
        return repoUrl+"/"+owner+"/"+name+"/stats/commit_activity"+getAuth();
    }

    private String getWeeklyUrl(String owner,String name) {
        return repoUrl+"/"+owner+"/"+name+"/stats/participation"+getAuth();
    }

    private String getPunchUrl(String owner, String name) {
        return repoUrl+"/"+owner+"/"+name+"/stats/punch_card"+getAuth();
    }

    public boolean updateActivity(String owner,String name) {
        HttpResponse<String> commentStatusResponse = null;
        try {
            String url = getActivityUrl(owner, name);
            for (int i = 0; i < 5; i++){
                commentStatusResponse = Unirest
                        .get(url)
                        .header("Accept","application/vnd.github.v3+json")
                        .asString();
                if (commentStatusResponse.getStatus()!=202) break;
                Thread.sleep(2500);
            }
            String result = commentStatusResponse.getBody();
            Session session = sessionFactory.openSession();
            Transaction trans=session.beginTransaction();
            Query queryupdate=session.createQuery("update SecRepoEntity repo set repo.activityJson=:json where repo.owner=:owner and repo.name=:name");
            queryupdate.setString("json",result);
            queryupdate.setString("owner",owner);
            queryupdate.setString("name",name);
            queryupdate.executeUpdate();
            trans.commit();
            session.close();
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateWeekly(String owner, String name){
        HttpResponse<String> commentStatusResponse = null;
        try {
            String url = getWeeklyUrl(owner, name);
            for (int i = 0; i < 5; i++){
                commentStatusResponse = Unirest
                        .get(url)
                        .header("Accept","application/vnd.github.v3+json")
                        .asString();
                if (commentStatusResponse.getStatus()!=202) break;
                Thread.sleep(2500);
            }
            String result = commentStatusResponse.getBody();
            Session session = sessionFactory.openSession();
            Transaction trans=session.beginTransaction();
            Query queryupdate=session.createQuery("update SecRepoEntity repo set repo.weeklyJson=:json where repo.owner=:owner and repo.name=:name");
            queryupdate.setString("json",result);
            queryupdate.setString("owner",owner);
            queryupdate.setString("name",name);
            queryupdate.executeUpdate();
            trans.commit();
            session.close();
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updatePunch(String owner, String name){
        HttpResponse<String> commentStatusResponse = null;
        try {
            String url = getPunchUrl(owner, name);
            for (int i = 0; i < 5; i++){
                commentStatusResponse = Unirest
                        .get(url)
                        .header("Accept","application/vnd.github.v3+json")
                        .asString();
                if (commentStatusResponse.getStatus()!=202) break;
                Thread.sleep(2500);
            }
            String result = commentStatusResponse.getBody();
            Session session = sessionFactory.openSession();
            Transaction trans=session.beginTransaction();
            Query queryupdate=session.createQuery("update SecRepoEntity repo set repo.punchJson=:json where repo.owner=:owner and repo.name=:name");
            queryupdate.setString("json",result);
            queryupdate.setString("owner",owner);
            queryupdate.setString("name",name);
            queryupdate.executeUpdate();
            trans.commit();
            session.close();
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
