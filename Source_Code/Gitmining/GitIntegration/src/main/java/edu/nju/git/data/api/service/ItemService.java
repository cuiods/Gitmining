package edu.nju.git.data.api.service;

public interface ItemService {

	/**
	 * <b>configure</b>
	 * <p/>http://www.gitmining.net/api/api/repository/{owner}/{reponame}/item/{item}
	 * <br/>item可接受的参数有：
	 * <br/>owner_name:项目所有者登录名 
	 * <br/>owner_id:所有者的id 
	 * <br/>owner_type:所有者的用户类型（分为User和Organization）
	 * <br/>html_url:项目主页
	 * <br/>url description:项目描述 
	 * <br/>fork:是否是fork了他人项目所产生的项目 
     * <br/>created_at:创建时间 
     * <br/>updated_at:更新时间 
     * <br/>pushed_at:最后一次push的时间
     * <br/>size:项目大小 
     * <br/>stargazers_count:点赞人数 
     * <br/>language:项目主语言 
     * <br/>forks:被fork的次数 
     * <br/>open_issues:open的issue数 
     * <br/>subscribers_count:关注者数量
     * </p>
     * <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/branch/{name}/item/{item}
     * <br/>查询单个项目的某个版本的某项信息
     * <br/>item可接受的参数有：
     * <br/>commit 对应提交的sha值 
     * <br/>name 版本号 fn 项目全称
     * <br/>return:List<String>
     * </p>
     * 
     * <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/pull/{number}/item/{item}
     * 查询某个Pull Request的某项信息
     * item可接受的参数有：
     * state, locked, title,body 
     * user, user_id, user_type
     * created_at, updates_at, closed_at, merged_at
     * </p>
     * 
     * <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/issue/{number}/item/{item}
     * 查询某个Issue的某项信息
     * item可接受的参数有：
     * state,locked,title,body
     * user,user_id,user_type
     * created_at,updates_at,closed_at,merged_at
     * </p>
     * 
     * http://www.gitmining.net/api/repository/{owner}/{reponame}/commit/{sha}/item/{item}
     * 查询某个commit的某项信息
     * item可接受的参数有：
     * committer,committer_email,Date,message
     * filenumber 更改文件数,additions 添加代码行数,deletions 删除代码行数,total 总共影响行数
     * 
     * 
     * 
	 * <br/><b>precondition</b>：item must be In line with the right format
	 * <br/><b>postcondition</b>：connect to the host, vist the api, reader the data.
	 * @param item 
	 * @return value , if not exsit then null
	 * @date 2016-03-08
	 */
	public Object getItem(String item);
}
