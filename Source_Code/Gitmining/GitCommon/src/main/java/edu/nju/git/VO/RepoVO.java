package edu.nju.git.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.nju.git.VO.chartvo.MyChartVO;

/**
 * info of repository<br>
 * the basic infomation about the repository, no list thing.
 * @author cuihao
 * @date 2016-03-02 12:25:14
 */
public class RepoVO implements Serializable{
	private static final long serialVersionUID = 8951738005334507950L;
	private String name;
	private String ownerName;
	private int size;
	private String language;
	//	private OwnerType type;
	private String url;
	private String description;
	private String create_at;
	private String update_at;

	private int num_stars;
	private int num_forks;
	private int num_subscribers;
	private int num_contributors;
	private int num_collaborators;
	private int num_commit;
	private int num_issue;
	private int num_pull;
	
	private double radar_popular;//calculated by num of subscriber and star
	private double radar_forks;
	private double radar_size;
	private double radar_complexity; //calculated by num of contributor\collabrator
	private double radar_activity;  //calculated by num of  commit and issue and pull.

	private String [] lineCharField;
	private Integer [] lineChartValue;

	private List<String> languagesField;
	private List<Integer> languagesLine;
	

	public RepoVO(String name, String ownerName, int size, String language, String url, String description,
				  String create_at, String update_at, int num_stars, int num_forks, int num_subscribers,
				  int num_contributors, int num_collaborators, int num_commit, int num_issue, int num_pull) {
		this.name = name;
		this.ownerName = ownerName;
		this.size = size;
		this.language = language;
		this.url = url;
		this.description = description;
		this.create_at = create_at;
		this.update_at = update_at;
		this.num_stars = num_stars;
		this.num_forks = num_forks;
		this.num_subscribers = num_subscribers;
		this.num_contributors = num_contributors;
		this.num_collaborators = num_collaborators;
		this.num_commit = num_commit;
		this.num_issue = num_issue;
		this.num_pull = num_pull;
	}

	//lists of info
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
	public String getUrl() {
		StringBuilder builder = new StringBuilder();
		builder.append("https://github.com/");
		builder.append(ownerName);
		builder.append("/");
		builder.append(name);
		return builder.toString();
	}

	public Integer[] getLineChartValue() {
		return lineChartValue;
	}

	public void setLineChartValue(Integer[] lineChartValue) {
		this.lineChartValue = lineChartValue;
	}

	public String[] getLineCharField() {
		return lineCharField;
	}

	public void setLineCharField(String[] lineCharField) {
		this.lineCharField = lineCharField;
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
	public double getRadar_popular() {
		return radar_popular;
	}
	public void setRadar_popular(double radar_popular) {
		this.radar_popular = radar_popular;
	}
	public double getRadar_forks() {
		return radar_forks;
	}
	public void setRadar_forks(double radar_forks) {
		this.radar_forks = radar_forks;
	}
	public double getRadar_size() {
		return radar_size;
	}
	public void setRadar_size(double radar_size) {
		this.radar_size = radar_size;
	}
	public double getRadar_complexity() {
		return radar_complexity;
	}
	public void setRadar_complexity(double radar_complexity) {
		this.radar_complexity = radar_complexity;
	}
	public double getRadar_activity() {
		return radar_activity;
	}
	public void setRadar_activity(double radar_activity) {
		this.radar_activity = radar_activity;
	}
	public int getNum_contributors() {
		return num_contributors;
	}
	public void setNum_contributors(int num_ontributors) {
		this.num_contributors = num_ontributors;
	}
	public int getNum_collaborators() {
		return num_collaborators;
	}
	public void setNum_collaborators(int num_collaboration) {
		this.num_collaborators = num_collaboration;
	}
	public int getNum_commit() {
		return num_commit;
	}
	public void setNum_commit(int num_commit) {
		this.num_commit = num_commit;
	}
	public int getNum_issue() {
		return num_issue;
	}
	public void setNum_issue(int num_issue) {
		this.num_issue = num_issue;
	}
	public int getNum_pull() {
		return num_pull;
	}
	public void setNum_pull(int num_pull) {
		this.num_pull = num_pull;
	}
	
	public MyChartVO getActivityChart(){
		String[] fields = getLineCharField();
    	Integer[] valuesTemp = getLineChartValue();
    	int[] values = new int[valuesTemp.length];
    	for(int i = 0; i < valuesTemp.length; i++){
    		values[i] = valuesTemp[i].intValue();
    	}
    	MyChartVO chartVO = new MyChartVO();
    	chartVO.setChartVO(fields, values);
    	return chartVO;
	}
	public List<String> getLanguagesField() {
		return languagesField;
	}

	public List<Integer> getLanguagesLine() {
		return languagesLine;
	}

	public RepoVO () {

	}
	/**
	 * initial value of languages
	 */
	public void setLanguagsCharts(Map<String, Integer> map){
		Set<String> set = map.keySet();
		this.languagesField = new ArrayList<>(set.size());
		
		this.languagesLine = new ArrayList<>(set.size());
		for (String key : set) {
			languagesField.add(key);
			languagesLine.add(map.get(key));
		}
//		System.out.println(languagesField);
//		System.out.println(languagesLine);
	}
}