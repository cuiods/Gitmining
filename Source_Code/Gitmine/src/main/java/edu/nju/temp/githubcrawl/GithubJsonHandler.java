package edu.nju.temp.githubcrawl;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.dao.impl.DataUpdater;
import edu.nju.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

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

    private final String [] popularLanguage = {"javascript","ruby","python","c","css","php","shell","html",
            "objective-c","java","c++","go","r","c#","perl"};

    public GithubJsonHandler(){

    }

    public void loopNoRepoUsers(){
        File file = new File("src/main/java/edu/nju/temp/current_user.txt");
        String username = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            username = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((username!=null)&&(!username.isEmpty())){
            //delete the last repo inserted
            updater.deleteLastRepo(username);
        }

        do {
            if ((username!=null)&&(!username.isEmpty())){
                try{
                    FileWriter writer = new FileWriter(file);
                    writer.write(username);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addUser(username);
                addRepos(commonUserUrl+username+"/repos");
                System.out.println("========================"+username+" done=============================");
            }
            List<String> list = updater.getNoRepoUsers();
            if (list.size()==0){
                System.out.println("ahhhhhhh!!!=====the users all have repos in our database!!!===========================");
                break;
            }
            else {
                username = list.get(0);
            }
        } while (true);

    }

    public void getMostStarReposByLanguage() throws IOException {
        File file = new File("src/main/java/edu/nju/temp/most_star_current_repo.txt");
        String repoid = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            repoid = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((repoid!=null)&&(!repoid.isEmpty())){
            //delete the last repo inserted
            //updater.deleteLastRepo(username);
        }

        //todo add all language traverse
        int page = 1;
        for (;page<=10;page++){
            JsonNode node = reader.getMostStarRepos("javascript",page,50);
            JsonNode array = node.get("items");
            for (JsonNode element:array){
                addUser(element.get("owner").get("login").asText());
                addRepo(element);
            }
        }
    }

    public void trverse(){
        //System.out.println("===============>>>"+GithubJsonHandler.class.getResource(""));
        long since = 0;
        File file = new File("src/main/java/edu/nju/temp/since_user.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            //System.out.println("----->>>"+Long.parseLong(reader.readLine()));
            since = Long.parseLong(reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode node = null;
        while (((node=reader.traverseUsers(since))!=null) && (node.size()>0)){
            for (JsonNode element: node){
                String username = element.get("login").asText();
                since = element.get("id").asLong();
                String reposUrl = element.get("repos_url").asText();
                addUser(username);
                addRepos(reposUrl);
                FileWriter writer = null;
                try{
                    writer = new FileWriter(file);
                    writer.write(""+since);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("==========================User id "+since+" all own repos completed!!!==========================");
                //todo==========================
                if (since>1){
                    System.exit(0);
                }
            }

        }

    }

    public void addUser(String username){

        if (!updater.existUser(username)){
            String url = commonUserUrl+username;
            JsonNode node = reader.getSimpleNode(url);
            if ((node!=null)&&(node.size()>0)){
                String avatar = node.get("avatar_url").asText();
                String name = node.get("name").asText();
                String email = node.get("email").asText();
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
                userEntity.setEmail(email.equals("null")?"":email);
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
    }

    public void addRepos(String reposUrl){
        int page=1;
        JsonNode node = null;
        while (((node = reader.getArrayNode(reposUrl, page))!=null) && (node.size()>0)){
            for (JsonNode element:node){
                addRepo(element);
            }

            page++;
        }
    }

    public boolean addRepo(JsonNode element){
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
        boolean result = false;
        if (result=updater.saveEntity(repoEntity)){
            addContributors(owner,name);
            addSubscriber(owner,name);
            //addStargazer(owner,name);     //this invoke spends too too too much time!!!  give up!
        }
        return result;
    }

    public void addContributors(String owner, String repo){
        int page=1;
        String url = commonRepoUrl+owner+"/"+repo+"/contributors";
        JsonNode node = null;
        while (((node = reader.getArrayNode(url, page))!=null) && (node.size()>0)){
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
        String url = commonRepoUrl+owner+"/"+repo+"/subscribers";
        JsonNode node = null;
        while (((node = reader.getArrayNode(url, page))!=null) && (node.size()>0)){
            for (JsonNode element: node){
                String subscriber = element.get("login").asText();
                addUser(subscriber);

                SecSubscriberEntity subscriberEntity = new SecSubscriberEntity();
                subscriberEntity.setRepoOwner(owner);
                subscriberEntity.setRepoName(repo);
                subscriberEntity.setSubscriber(subscriber);

                updater.saveEntity(subscriberEntity);
            }
            page++;
        }
    }

    //spend too much time!!!
    public void addStargazer(String owner, String repo){
        int page = 1;
        String url = commonRepoUrl+owner+"/"+repo+"/stargazers";
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
