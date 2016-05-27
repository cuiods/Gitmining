package edu.nju.temp.githubcrawl;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.dao.impl.DataUpdater;
import edu.nju.entity.*;
import org.apache.commons.lang3.StringUtils;
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

//    private final String [] popularLanguage = {"javascript","ruby","python","c","css","php","shell","html",
//            "objective-c","java","c++","go","r","c#","perl"};

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

    public void initRepoLabel(){
        List<Object[]> list = updater.getAllDescription();
        for (Object[] item : list){
            saveRepoLabel(Long.valueOf(item[0].toString()), item[1].toString(),item[2].toString());
        }
    }

    public boolean saveRepoLabel(long repoID, String repoName, String description){
        SecRepoLabelEntity labelEntity = new SecRepoLabelEntity();
        labelEntity.setRepoId(repoID);
        if (StringUtils.containsIgnoreCase(description, "node.js") || StringUtils.containsIgnoreCase(repoName,"node.js")){
            labelEntity.setNodeJs(1);
        }
        if (StringUtils.containsIgnoreCase(description, "javascript") || StringUtils.containsIgnoreCase(repoName,"javascript")){
            labelEntity.setJavascript(1);
        }
        if (StringUtils.containsIgnoreCase(description, "library") || StringUtils.containsIgnoreCase(repoName,"library")){
            labelEntity.setLibrary(1);
        }
        if (StringUtils.containsIgnoreCase(description, "ruby") || StringUtils.containsIgnoreCase(repoName,"ruby")){
            labelEntity.setRuby(1);
        }
        if (StringUtils.containsIgnoreCase(description, "web") || StringUtils.containsIgnoreCase(repoName,"web")){
            labelEntity.setWeb(1);
        }
        if (StringUtils.containsIgnoreCase(description, "api") || StringUtils.containsIgnoreCase(repoName,"api")){
            labelEntity.setApi(1);
        }
        if (StringUtils.containsIgnoreCase(description, "vim") || StringUtils.containsIgnoreCase(repoName,"vim")){
            labelEntity.setVim(1);
        }
        if (StringUtils.containsIgnoreCase(description, "plugin") || StringUtils.containsIgnoreCase(repoName,"plugin")){
            labelEntity.setPlugin(1);
        }
        if (StringUtils.containsIgnoreCase(description, "rust") || StringUtils.containsIgnoreCase(repoName,"rust")){
            labelEntity.setRust(1);
        }
        if (StringUtils.containsIgnoreCase(description, "app") || StringUtils.containsIgnoreCase(repoName,"app")){
            labelEntity.setApp(1);
        }
        if (StringUtils.containsIgnoreCase(description, "client") || StringUtils.containsIgnoreCase(repoName,"client")){
            labelEntity.setClient(1);
        }
        if (StringUtils.containsIgnoreCase(description, "server") || StringUtils.containsIgnoreCase(repoName,"server")){
            labelEntity.setServer(1);
        }
        if (StringUtils.containsIgnoreCase(description, "python") || StringUtils.containsIgnoreCase(repoName,"python")){
            labelEntity.setPython(1);
        }
        if (StringUtils.containsIgnoreCase(description, "framework") || StringUtils.containsIgnoreCase(repoName,"framework")){
            labelEntity.setFramework(1);
        }
        if (StringUtils.containsIgnoreCase(description, "json") || StringUtils.containsIgnoreCase(repoName,"json")){
            labelEntity.setJson(1);
        }
        if (StringUtils.containsIgnoreCase(description, "browser") || StringUtils.containsIgnoreCase(repoName,"browser")){
            labelEntity.setBrowser(1);
        }
        if (StringUtils.containsIgnoreCase(description, "rails") || StringUtils.containsIgnoreCase(repoName,"rails")){
            labelEntity.setRails(1);
        }
        if (StringUtils.containsIgnoreCase(description, "css") || StringUtils.containsIgnoreCase(repoName,"css")){
            labelEntity.setCss(1);
        }
        if (StringUtils.containsIgnoreCase(description, "android") || StringUtils.containsIgnoreCase(repoName,"android")){
            labelEntity.setAndroid(1);
        }
        if (StringUtils.containsIgnoreCase(description, "jquery") || StringUtils.containsIgnoreCase(repoName,"jquery")){
            labelEntity.setJquery(1);
        }
        if (StringUtils.containsIgnoreCase(description, "html") || StringUtils.containsIgnoreCase(repoName,"html")){
            labelEntity.setHtml(1);
        }
        if (StringUtils.containsIgnoreCase(description, "test") || StringUtils.containsIgnoreCase(repoName,"test")){
            labelEntity.setTest(1);
        }
        if (StringUtils.containsIgnoreCase(description, "php") || StringUtils.containsIgnoreCase(repoName,"php")){
            labelEntity.setPhp(1);
        }
        if (StringUtils.containsIgnoreCase(description, "command") || StringUtils.containsIgnoreCase(repoName,"command")){
            labelEntity.setCommand(1);
        }
        if (StringUtils.containsIgnoreCase(description, "tool") || StringUtils.containsIgnoreCase(repoName,"tool")){
            labelEntity.setTool(1);
        }
        if (StringUtils.containsIgnoreCase(description, "demo") || StringUtils.containsIgnoreCase(repoName,"demo")){
            labelEntity.setDemo(1);
        }
        if (StringUtils.containsIgnoreCase(description, "wrapper") || StringUtils.containsIgnoreCase(repoName,"wrapper")){
            labelEntity.setWrapper(1);
        }
        if (StringUtils.containsIgnoreCase(description, "ios") || StringUtils.containsIgnoreCase(repoName,"ios")){
            labelEntity.setIos(1);
        }
        if (StringUtils.containsIgnoreCase(description, "linux") || StringUtils.containsIgnoreCase(repoName,"linux")){
            labelEntity.setLinux(1);
        }
        if (StringUtils.containsIgnoreCase(description, "windows") || StringUtils.containsIgnoreCase(repoName,"windows")){
            labelEntity.setWindows(1);
        }
        if (StringUtils.containsIgnoreCase(description, "os x") || StringUtils.containsIgnoreCase(repoName,"os x")){
            labelEntity.setOsX(1);
        }
        if (StringUtils.containsIgnoreCase(description, "django") || StringUtils.containsIgnoreCase(repoName,"django")){
            labelEntity.setDjango(1);
        }
        if (StringUtils.containsIgnoreCase(description, "google") || StringUtils.containsIgnoreCase(repoName,"google")){
            labelEntity.setGoogle(1);
        }
        if (StringUtils.containsIgnoreCase(description, "generator") || StringUtils.containsIgnoreCase(repoName,"generator")){
            labelEntity.setGenerator(1);
        }
        if (StringUtils.containsIgnoreCase(description, "docker") || StringUtils.containsIgnoreCase(repoName,"docker")){
            labelEntity.setDocker(1);
        }
        if (StringUtils.containsIgnoreCase(description, "image") || StringUtils.containsIgnoreCase(repoName,"image")){
            labelEntity.setImage(1);
        }
        if (StringUtils.containsIgnoreCase(description, "template") || StringUtils.containsIgnoreCase(repoName,"template")){
            labelEntity.setTemplate(1);
        }
        return updater.saveEntity(labelEntity);

    }


//    public void trverse(){
//        //System.out.println("===============>>>"+GithubJsonHandler.class.getResource(""));
//        long since = 0;
//        File file = new File("src/main/java/edu/nju/temp/since_user.txt");
//        try{
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            //System.out.println("----->>>"+Long.parseLong(reader.readLine()));
//            since = Long.parseLong(reader.readLine());
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        JsonNode node = null;
//        while (((node=reader.traverseUsers(since))!=null) && (node.size()>0)){
//            for (JsonNode element: node){
//                String username = element.get("login").asText();
//                since = element.get("id").asLong();
//                String reposUrl = element.get("repos_url").asText();
//                addUser(username);
//                addRepos(reposUrl);
//                FileWriter writer = null;
//                try{
//                    writer = new FileWriter(file);
//                    writer.write(""+since);
//                    writer.flush();
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("==========================User id "+since+" all own repos completed!!!==========================");
//                //todo==========================
//                if (since>1){
//                    System.exit(0);
//                }
//            }
//
//        }
//
//    }

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
            saveRepoLabel(id,name,description);
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
//    public void addStargazer(String owner, String repo){
//        int page = 1;
//        String url = commonRepoUrl+owner+"/"+repo+"/stargazers";
//        JsonNode node = null;
//        while ((node = reader.getArrayNode(url, page)).size()>0){
//            for (JsonNode element: node){
//                String stargazer = element.get("login").asText();
//                addUser(stargazer);
//
//                SecStarEntity starEntity = new SecStarEntity();
//                starEntity.setRepoOwner(owner);
//                starEntity.setRepoName(repo);
//                starEntity.setStargazer(stargazer);
//
//                updater.saveEntity(starEntity);
//            }
//            page++;
//        }
//    }
}
