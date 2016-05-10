package org.GitServer.dataread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.GitServer.cacheinit.DataEncapsulation;

import edu.nju.git.PO.UserPO;
import edu.nju.git.type.OwnerType;

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
		dataTransport.addUser();
	}
	private void init() {
		Reader reader = new Reader();
		dataEncapsulation = reader.excute();
		try {
			connection = DriverManager.getConnection(url, user, password);
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