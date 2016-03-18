package edu.nju.git.VO;

import java.util.List;

import edu.nju.git.type.OwnerType;

/**
 * info of repository<br>
 * <b>Note:</b><br>Lists don't have a set method, use add method.<br>
 * For example:<br>
 * <code>repo.addIssue(name)</code>;
 * @author cuihao
 * @date 2016-03-02 12:25:14
 */
public class RepoVO {
	private String name;
	/**
	 * warning: can be enumerate
	 */
	private String language;
	private String ownerName;
	private OwnerType type;
	private String url;
	private String description;
	private String create_at;
	private String update_at;
	private int num_stars = 0;
	private int num_forks = 0;
	private int num_subscribers = 0;
	/**
	 * same measure unit as api provided
	 */
	private int size;
	
	
	private float famousInRadar;
	private float matureInRadar;
	private float popularInRadar;
	private float hotInRadar;
	private float sizeInRadar;
	
	public float getFamousInRadar() {
		return famousInRadar;
	}
	public void setFamousInRadar(float famousInRadar) {
		this.famousInRadar = famousInRadar;
	}
	public float getMatureInRadar() {
		return matureInRadar;
	}
	public void setMatureInRadar(float matureInRadar) {
		this.matureInRadar = matureInRadar;
	}
	public float getPopularInRadar() {
		return popularInRadar;
	}
	public void setPopularInRadar(float popularInRadar) {
		this.popularInRadar = popularInRadar;
	}
	public float getHotInRadar() {
		return hotInRadar;
	}
	public void setHotInRadar(float hotInRadar) {
		this.hotInRadar = hotInRadar;
	}
	public float getSizeInRadar() {
		return sizeInRadar;
	}
	public void setSizeInRadar(float sizeInRadar) {
		this.sizeInRadar = sizeInRadar;
	}
	//lists of info
	/**
	 * @deprecated
	 * size
	 */
	private List<UserBriefVO> info_contributor ;
	/**
	 * @deprecated
	 */
	private List<UserBriefVO> info_collaborator ;
	/**
	 * @deprecated
	 */
	private List<BranchVO> info_branch ;
	/**
	 * fullname
	 * @deprecated
	 */
	private List<RepoBriefVO> info_fork ;
	/**
	 * @deprecated
	 */
	private List<CommitVO> info_commit ;
	/**
	 * @deprecated
	 */
	private List<IssueVO> info_issue ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public OwnerType getType() {
		return type;
	}
	public void setType(OwnerType type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	public int getNum_stars() {
		return num_stars;
	}
	public void setNum_stars(int num_stars) {
		this.num_stars = num_stars;
	}
	public int getNum_forks() {
		return num_forks;
	}
	public void setNum_forks(int num_forks) {
		this.num_forks = num_forks;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNum_subscribers() {
		return num_subscribers;
	}
	public void setNum_subscribers(int num_subscribers) {
		this.num_subscribers = num_subscribers;
	}
	public List<UserBriefVO> getInfo_contributor() {
		return info_contributor;
	}
	public void setInfo_contributor(List<UserBriefVO> info_contributor) {
		this.info_contributor = info_contributor;
	}
	public List<UserBriefVO> getInfo_collaborator() {
		return info_collaborator;
	}
	public void setInfo_collaborator(List<UserBriefVO> info_collaborator) {
		this.info_collaborator = info_collaborator;
	}
	public List<BranchVO> getInfo_branch() {
		return info_branch;
	}
	public void setInfo_branch(List<BranchVO> info_branch) {
		this.info_branch = info_branch;
	}
	public List<RepoBriefVO> getInfo_fork() {
		return info_fork;
	}
	public void setInfo_fork(List<RepoBriefVO> info_fork) {
		this.info_fork = info_fork;
	}
	public List<CommitVO> getInfo_commit() {
		return info_commit;
	}
	public void setInfo_commit(List<CommitVO> info_commit) {
		this.info_commit = info_commit;
	}
	public List<IssueVO> getInfo_issue() {
		return info_issue;
	}
	public void setInfo_issue(List<IssueVO> info_issue) {
		this.info_issue = info_issue;
	}
	
	
	
}
