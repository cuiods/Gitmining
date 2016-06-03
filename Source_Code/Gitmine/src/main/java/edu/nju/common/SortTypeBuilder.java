package edu.nju.common;

/**
 * Created by Harry on 2016/5/12.
 * this is a tool to generate enumeration of <tt>SortType</tt> from string.
 */
public class SortTypeBuilder {

    public static SortType getSortType(String type){
        SortType sortType;
        switch(type){
            case "repo_star": sortType = SortType.Repo_Star;break;
            case "repo_fork": sortType = SortType.Repo_Fork;break;
            case "repo_update": sortType = SortType.Repo_Update;break;
            case "repo_watcher": sortType = SortType.Repo_Watch;break;
            case "repo_name": sortType = SortType.Repo_Name;break;
            case "hobby_match": sortType = SortType.Hobby_Match;break;
            case "user_followed": sortType = SortType.User_Follored;break;
            case "user_following": sortType = SortType.User_Folloring;break;
            case "user_ownrepos": sortType = SortType.User_Repos;break;
            case "user_update": sortType = SortType.User_Update;break;
            case "user_name": sortType = SortType.User_Name;break;
            default:sortType = null;
        }
        return sortType;
    }
}
