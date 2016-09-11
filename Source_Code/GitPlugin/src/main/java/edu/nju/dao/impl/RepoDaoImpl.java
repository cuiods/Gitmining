package edu.nju.dao.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.nju.dao.RepoDao;
import edu.nju.data.GithubReader;
import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * impl of repo dao
 */
@Repository
public class RepoDaoImpl implements RepoDao {

    @Resource
    private GithubReader githubReader;

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Map<String,Integer> getRepoFollowers(String owner, String name) {
        String url = githubReader.getFollowerUrl(owner, name);
        Map<String,Integer> result = new HashMap<>();
        try {
            HttpResponse<String> follwers = Unirest
                    .get(url)
                    .header("Accept","application/vnd.github.v3+json")
                    .asString();
            String response = follwers.getBody();
            if (response!=null && !response.isEmpty() && response.contains("{")) {
                JSONArray array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    result.put(object.getString("login"), object.getInt("contributions"));
                }
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getFollowerNum(String loginName) {
        String url = githubReader.getUserUrl(loginName);
        int result = 0;
        try {
            HttpResponse<String> follwers = Unirest
                    .get(url)
                    .header("Accept","application/vnd.github.v3+json")
                    .asString();
            String response = follwers.getBody();
            if (response!=null && !response.isEmpty() && response.contains("{")) {
                JSONObject object = new JSONObject(response);
                result = object.getInt("followers");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SecRepoEntity getRepo(String owner, String name) {
        SecRepoEntity entity = null;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SecRepoEntity where owner=:owner and name=:name ");
        query.setString("owner", owner);
        query.setString("name", name);
        List list = query.list();
        if (list!=null && list.size()>0) {
            entity = (SecRepoEntity) list.get(0);
        }
        session.close();
        return entity;
    }

    @Override
    public int[] getForkandSize(String owner, String name) {
        String url = githubReader.getRepoDetailUrl(owner, name);
        try {
            HttpResponse<String> repo = Unirest
                    .get(url)
                    .header("Accept","application/vnd.github.v3+json")
                    .asString();
            String response = repo.getBody();
            if (response!=null && !response.isEmpty() && response.contains("{")) {
                JSONObject object = new JSONObject(response);
                return new int[]{object.getInt("forks_count"),object.getInt("size")};
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new int[]{0,0};
    }
}
