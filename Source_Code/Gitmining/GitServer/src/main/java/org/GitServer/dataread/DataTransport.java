package org.GitServer.dataread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.GitServer.cacheinit.DataEncapsulation;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;

public class DataTransport {
	private static String url = "jdbc:mysql://139.129.48.182:3306/gitmining?characterEncoding=utf-8&useSSL=false";
	private static String user = "cuiods";
	private static String password = "0628";
	public static DataEncapsulation dataEncapsulation;
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	public static void main(String args[]) {
		DataTransport dataTransport = new DataTransport();
		dataTransport.init();
		dataTransport.updateUserCreateTime();
	}
	private void init() {
		Reader reader = new Reader();
		dataEncapsulation = reader.excute();

		try {
//			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
	}
	
	private void updateUserCreateTime(){
		String sql = "update tbl_user set create_at=? where login_name = ? ";
		List<UserPO> userPOs = dataEncapsulation.allUserPOs;
		for(int i = 0; i < userPOs.size(); i++) {
			UserPO userPO = userPOs.get(i);
			try {
				preparedStatement = connection.prepareStatement(sql);

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String temp = userPO.getCreate_at();
				if((temp!=null)&&(!temp.isEmpty())){
					String[] create = temp.substring(0, temp.length()-1).split("T");
					String[] one = create[0].split("-");
					String[] two = create[1].split(":");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(one[0]), Integer.parseInt(one[1]), Integer.parseInt(one[2]), Integer.parseInt(two[0]), Integer.parseInt(two[1]), Integer.parseInt(two[2]));
					timestamp = new Timestamp(calendar.getTimeInMillis());
				}
				preparedStatement.setTimestamp(1, timestamp);
				preparedStatement.setString(2, userPO.getLogin());
				preparedStatement.executeUpdate();
				if(i%50==0){
					System.out.println("completed:"+i*1.0/userPOs.size()*100+"%");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateRepoCreateTime(){
		String sql = "update tbl_repo set create_at=? where owner_name = ? and name = ?";
		List<RepoPO> repoPOs = dataEncapsulation.nameOrderRepoPOs;
		for(int i = 0; i < repoPOs.size(); i++) {
			RepoPO repoPO = repoPOs.get(i);
			try {
				preparedStatement = connection.prepareStatement(sql);

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String temp = repoPO.getCreate_at();
				if((temp!=null)&&(!temp.isEmpty())){
					String[] create = temp.substring(0, temp.length()-1).split("T");
					String[] one = create[0].split("-");
					String[] two = create[1].split(":");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(one[0]), Integer.parseInt(one[1]), Integer.parseInt(one[2]), Integer.parseInt(two[0]), Integer.parseInt(two[1]), Integer.parseInt(two[2]));
					timestamp = new Timestamp(calendar.getTimeInMillis());
				}
				preparedStatement.setTimestamp(1, timestamp);
				preparedStatement.setString(2, repoPO.getOwnerName());
				preparedStatement.setString(3, repoPO.getName());
				preparedStatement.executeUpdate();
				if(i%50==0){
					System.out.println("completed:"+i*1.0/repoPOs.size()*100+"%");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateRepoNums() {
		String sql = "update tbl_repo set num_subscriber=? , num_contributor=? , "
				+ "num_collaborator=?, num_commit=?, num_pull=?, num_issue=? "
				+ "where owner_name=? and name=?";
		Iterator<Map.Entry<String, List<String>>> iterator = dataEncapsulation.repoToContributor.entrySet().iterator();
		int count = 0;
		int sum = dataEncapsulation.nameOrderRepoPOs.size();
		while (iterator.hasNext()) {
			try {
				preparedStatement = connection.prepareStatement(sql);
				Map.Entry<String, List<String>> entry = iterator.next();
				String name = entry.getKey();
				preparedStatement.setInt(1, dataEncapsulation.repoToSubscriber.get(name).size());
				preparedStatement.setInt(2, entry.getValue().size());
				preparedStatement.setInt(3, dataEncapsulation.repoToCollab.get(name).size());
				preparedStatement.setInt(4, dataEncapsulation.repoToCommit.get(name).size());
				preparedStatement.setInt(5, dataEncapsulation.repoToPull.get(name).size());
				preparedStatement.setInt(6, dataEncapsulation.repoToIssue.get(name).size());
				preparedStatement.setString(7, name.split("/")[0]);
				preparedStatement.setString(8, name.split("/")[1]);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			count++;
			if (count%10==0) {
				System.out.println("completed:"+count*1.0/sum*100+"%");
			}
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addCollaborator(){
		String sql = "insert into tbl_collabrator(repo_owner,repo,collabrator) values(?,?,?)";
		Iterator<Map.Entry<String, List<String>>> iterator = dataEncapsulation.repoToCollab.entrySet().iterator();
		int count = 0;
		int sum = dataEncapsulation.nameOrderRepoPOs.size();
		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> entry = iterator.next();
			try {
				preparedStatement = connection.prepareStatement(sql);
				String[] repoName = entry.getKey().split("/");
				preparedStatement.setString(1, repoName[0]);
				preparedStatement.setString(2, repoName[1]);
				List<String> subscribors = entry.getValue();
				for (int i = 0; i < subscribors.size(); i++) {
					preparedStatement.setString(3, subscribors.get(i));
					preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println("Completed: "+count*1.0/sum*100+"%");
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addSubscribor(){
		String sql = "insert into tbl_subscriber(repo_owner,repo,subscriber) values(?,?,?)";
		Iterator<Map.Entry<String, List<String>>> iterator = dataEncapsulation.repoToSubscriber.entrySet().iterator();
		int count = 0;
		int sum = dataEncapsulation.nameOrderRepoPOs.size();
		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> entry = iterator.next();
			try {
				preparedStatement = connection.prepareStatement(sql);
				String[] repoName = entry.getKey().split("/");
				preparedStatement.setString(1, repoName[0]);
				preparedStatement.setString(2, repoName[1]);
				List<String> subscribors = entry.getValue();
				for (int i = 0; i < subscribors.size(); i++) {
					preparedStatement.setString(3, subscribors.get(i));
					preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println("Completed: "+count/sum*100+"%");
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addContributor(){
		String sql = "insert into tbl_contributor(owner_name,repo,contributor) values(?,?,?)";
		Iterator<Map.Entry<String, List<String>>> iterator = dataEncapsulation.repoToContributor.entrySet().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> entry = iterator.next();
			try {
				preparedStatement = connection.prepareStatement(sql);
				String[] repoName = entry.getKey().split("/");
				preparedStatement.setString(1, repoName[0]);
				preparedStatement.setString(2, repoName[1]);
				List<String> contributors = entry.getValue();
				for (int i = 0; i < contributors.size(); i++) {
					preparedStatement.setString(3, contributors.get(i));
					preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println("Completed: "+count);
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	private void addRepository() {
		String sql = "insert into tbl_repo(name,owner_name,size,language,url,description,create_at,update_at,num_star,num_fork,num_subscriber,num_contributor,num_collaborator,num_commit,num_pull,num_issue) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<RepoPO> repoPOs = dataEncapsulation.nameOrderRepoPOs;
		for(int i = 0; i < repoPOs.size(); i++) {
			RepoPO repoPO = repoPOs.get(i);
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, repoPO.getName());
				preparedStatement.setString(2, repoPO.getOwnerName());
				preparedStatement.setInt(3, repoPO.getSize());
				preparedStatement.setString(4, repoPO.getLanguage());
				preparedStatement.setString(5, repoPO.getUrl());
				preparedStatement.setString(6, repoPO.getDescription());
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String temp = repoPO.getCreate_at();
				if(temp!=null&&!temp.isEmpty()){
					String[] create = temp.substring(0, temp.length()-1).split("T");
					String[] one = create[0].split("-");
					String[] two = create[1].split(":");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(one[0]), Integer.parseInt(one[1]), Integer.parseInt(one[2]), Integer.parseInt(two[0]), Integer.parseInt(two[1]), Integer.parseInt(two[2]));
					timestamp = new Timestamp(calendar.getTimeInMillis());
				}
				preparedStatement.setTimestamp(7, timestamp);
				timestamp = new Timestamp(System.currentTimeMillis());
				temp = repoPO.getUpdate_at();
				if(temp!=null&&!temp.isEmpty()){
					String[] create = temp.substring(0, temp.length()-1).split("T");
					String[] one = create[0].split("-");
					String[] two = create[1].split(":");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(one[0]), Integer.parseInt(one[1]), Integer.parseInt(one[2]), Integer.parseInt(two[0]), Integer.parseInt(two[1]), Integer.parseInt(two[2]));
					timestamp = new Timestamp(calendar.getTimeInMillis());
				}
				preparedStatement.setTimestamp(8, timestamp);
				preparedStatement.setInt(9, repoPO.getNum_stars());
				preparedStatement.setInt(10, repoPO.getNum_forks());
				preparedStatement.setInt(11, repoPO.getNum_subscribers());
				preparedStatement.setInt(12, repoPO.getNum_contrbutors());
				preparedStatement.setInt(13, repoPO.getNum_collaborators());
				preparedStatement.setInt(14, repoPO.getNum_commits());
				preparedStatement.setInt(15, repoPO.getNum_pulls());
				preparedStatement.setInt(16, repoPO.getNum_issues());
				preparedStatement.executeUpdate();
				if(i%50==0){
					System.out.println("completed:"+i*1.0/repoPOs.size()*100+"%");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addUser(){
		String sql = "insert into tbl_user(login_name,name,avatar_url,blog,location,company,type,public_repo,public_gist,follower,following,email,bio,create_at,update_at) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<UserPO> userPOs = dataEncapsulation.allUserPOs;
		for(int i = 0; i < userPOs.size(); i++) {
			UserPO userPO = userPOs.get(i);
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userPO.getLogin());
				preparedStatement.setString(2, userPO.getName());
				preparedStatement.setString(3, userPO.getAvatar_url());
				preparedStatement.setString(4, userPO.getBlog());
				preparedStatement.setString(5, userPO.getLocation());
				preparedStatement.setString(6, userPO.getCompany());
				preparedStatement.setInt(7, userPO.getType().ordinal());
				preparedStatement.setInt(8, userPO.getPublic_repos());
				preparedStatement.setInt(9, userPO.getPublic_gists());
				preparedStatement.setInt(10, userPO.getFollowNum());
				preparedStatement.setInt(11, userPO.getFollowingNum());
				preparedStatement.setString(12, userPO.getEmail());
				preparedStatement.setString(13, userPO.getBio());
				//2010-06-29T18:39:32Z
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String temp = userPO.getCreate_at();
				if(temp!=null&&!temp.isEmpty()){
					String[] create = temp.substring(0, temp.length()-1).split("T");
					String[] one = create[0].split("-");
					String[] two = create[1].split(":");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(one[0]), Integer.parseInt(one[1]), Integer.parseInt(one[2]), Integer.parseInt(two[0]), Integer.parseInt(two[1]), Integer.parseInt(two[2]));
					timestamp = new Timestamp(calendar.getTimeInMillis());
				}
				preparedStatement.setTimestamp(14, timestamp);
				timestamp = new Timestamp(System.currentTimeMillis());
				String temp2 = userPO.getUpdate_at();
				if(temp2!=null&&!temp2.isEmpty()){
					String[] create = temp2.substring(0, temp2.length()-1).split("T");
					String[] one = create[0].split("-");
					String[] two = create[1].split(":");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(one[0]), Integer.parseInt(one[1]), Integer.parseInt(one[2]), Integer.parseInt(two[0]), Integer.parseInt(two[1]), Integer.parseInt(two[2]));
					timestamp = new Timestamp(calendar.getTimeInMillis());
				}
				preparedStatement.setTimestamp(15, timestamp);
				preparedStatement.executeUpdate();
				if(i%50==0){
					System.out.println("completed:"+i*1.0/userPOs.size()*100+"%");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}