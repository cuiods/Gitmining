package edu.nju.temp.githubcrawl;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.dao.impl.DataUpdater;
import edu.nju.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by Harry on 2016/5/21.
 * handle the json got from github
 */
@Service
public class GithubJsonHandler {

    @Resource
    private GithubReader reader;

    @Resource
    private DataUpdater updater;

    private final String commonRepoUrl = "https://api.github.com/repos/";
    private final String commonUserUrl = "https://api.github.com/users/";

    public GithubJsonHandler(){

    }

    public void trverse(){
        int since = 0;

    }

    public void addUser(String username){
        String url = commonUserUrl+username;
        JsonNode node = reader.getSimpleNode(url);
        if ((node!=null)&&(node.size()>0)){
            String avatar = node.get("avatar_url").asText();
            String name = node.get("name").asText();
            String company = node.get("company").asText();
            String blog = node.get("blog").asText();
            String location = node.get("location").asText();
            String bio = node.get("bio").asText();
            String createStr = node.get("created_at").asText();
            String updateStr = node.get("updated_at").asText();

            SecUserEntity userEntity = new SecUserEntity();
            userEntity.setId(node.get("id").asLong());
            userEntity.setLogin(node.get("login").asText());
            userEntity.setAvatarUrl(avatar.equals("null")?"":avatar);
            userEntity.setHtmlUrl(node.get("html_url").asText());
            userEntity.setType(node.get("type").asText());
            userEntity.setName(name.equals("null")?"":name);
            userEntity.setCompany(company.equals("null")?"":company);
            userEntity.setBlog(blog.equals("null")?"":blog);
            userEntity.setLocation(location.equals("null")?"":location);
            userEntity.setEmail(node.get("email").asText());
            userEntity.setBio(bio.equals("null")?"":bio);
            userEntity.setPublicRepos(node.get("public_repos").asInt());
            userEntity.setPublicGists(node.get("public_gists").asInt());
            userEntity.setFollowers(node.get("followers").asInt());
            userEntity.setFollowing(node.get("following").asInt());
            userEntity.setCreateAt(Timestamp.valueOf(createStr.substring(0,createStr.length()-1).replace('T',' ')));
            userEntity.setUpdateAt(Timestamp.valueOf(updateStr.substring(0,updateStr.length()-1).replace('T',' ')));
            updater.saveEntity(userEntity);
        }
    }

    public void addRepos(String reposUrl){
        int page=1;
        JsonNode node = null;
        while (((node=reader.getArrayNode(reposUrl,page)).size()>0)){
            for (JsonNode element:node){
                long id = element.get("id").asLong();
                String owner = element.get("owner").get("login").asText();
                String name = element.get("name").asText();
                String html = element.get("html_url").asText();
                String description = element.get("description").asText();
                int size = element.get("size").asInt();
                int starCount = element.get("stargazers_count").asInt();
                int watchCount = element.get("watchers_count").asInt();
                String language = element.get("language").asText();
                int forkCount = element.get("forks").asInt();
                String createStr = element.get("created_at").asText();
                String updateStr = element.get("updated_at").asText();

                SecRepoEntity repoEntity = new SecRepoEntity();
                repoEntity.setId(id);
                repoEntity.setOwner(owner);
                repoEntity.setName(name);
                repoEntity.setHtmlUrl(html);
                repoEntity.setDescription(description.equals("null")?"":description);
                repoEntity.setSize(size);
                repoEntity.setStarCount(starCount);
                repoEntity.setWatchersCount(watchCount);
                repoEntity.setLanguage(language.equals("null")?"":language);
                repoEntity.setForkCount(forkCount);
                repoEntity.setCreateAt(Timestamp.valueOf(createStr.substring(0,createStr.length()-1).replace('T',' ')));
                repoEntity.setUpdateAt(Timestamp.valueOf(updateStr.substring(0,updateStr.length()-1).replace('T',' ')));
                updater.saveEntity(repoEntity);

                addContributors(owner,name);
                addSubscriber(owner,name);
                addStargazer(owner,name);
            }

            page++;
        }
    }

    public void addContributors(String owner, String repo){
        int page=1;
        String url = commonRepoUrl+owner+"/"+repo+"/contributors";
        JsonNode node = null;
        while ((node = reader.getArrayNode(url, page)).size()>0){
            for (JsonNode element: node){
                String contributor = element.get("login").asText();
                int contributions = element.get("contributions").asInt();
                addUser(contributor);

                SecContributorEntity contributorEntity = new SecContributorEntity();
                contributorEntity.setRepoOwner(owner);
                contributorEntity.setRepoName(repo);
                contributorEntity.setContributor(contributor);
                contributorEntity.setContributions(contributions);

                updater.saveEntity(contributorEntity);
            }
            page++;
        }
    }

    public void addSubscriber(String owner, String repo){
        int page = 1;
        String url = commonUserUrl+owner+"/"+repo+"/subscribers";
        JsonNode node = null;
        while ((node = reader.getArrayNode(url, page)).size()>0){
            for (JsonNode element: node){
                String subscriber = element.get("login").asText();
                addUser(subscriber);

                SecSubscriberEntity subscriberEntity = new SecSubscriberEntity();
                subscriberEntity.setRepoOwner(owner);
                subscriberEntity.setRepoOwner(repo);
                subscriberEntity.setSubscriber(subscriber);

                updater.saveEntity(subscriberEntity);
            }
            page++;
        }
    }

    public void addStargazer(String owner, String repo){
        int page = 1;
        String url = commonUserUrl+owner+"/"+repo+"/stargazers";
        JsonNode node = null;
        while ((node = reader.getArrayNode(url, page)).size()>0){
            for (JsonNode element: node){
                String stargazer = element.get("login").asText();
                addUser(stargazer);

                SecStarEntity starEntity = new SecStarEntity();
                starEntity.setRepoOwner(owner);
                starEntity.setRepoName(repo);
                starEntity.setStargazer(stargazer);

                updater.saveEntity(starEntity);
            }
            page++;
        }
    }
}
