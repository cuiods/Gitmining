package edu.nju.git.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dom4j.Element;

import edu.nju.git.VO.UserVO;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;

/**
 * User panel
 * @author cuihao
 * @date 2016-03-10 20:28:45
 */
public class UserPanel extends JPanel{
	
	private static final long serialVersionUID = -1800716181939686012L;
	private String userName;
	private UserController service;
	
	public UserPanel(Element element, UIController controller){
		setLayout(null);
		userName = controller.getID();
		int w = Integer.parseInt(element.attributeValue("w"));
		int h = Integer.parseInt(element.attributeValue("h"));
		int x = Integer.parseInt(element.attributeValue("x"));
		int y = Integer.parseInt(element.attributeValue("y"));
		setBounds(x, y, w, h);
		setOpaque(false);
		service = controller.getUserController();
		initialize();
	}
	
	/**
	 * initialize components
	 */
	private void initialize() {
		/**
		 * create and add components
		 */
		UserVO userVO = service.getDetailedUser(userName);
		MyLabel login = new MyLabel(userVO.getLogin()); 
	    login.setBounds(490,36,200,30);add(login);
		MyLabel name = new MyLabel(userVO.getName());
		name.setBounds(490,80,200,30);add(name);
		MyLabel company = new MyLabel(userVO.getCompany());
		company.setBounds(490,124,220,30);add(company);
		MyLabel blog = new MyLabel(userVO.getBlog());
		blog.setBounds(420,225,300,30);add(blog);
		MyLabel email = new MyLabel(userVO.getEmail());
		email.setBounds(425,275,300,30);add(email);
		MyLabel location = new MyLabel(userVO.getLocation());
		location.setBounds(425,325,300,30);add(location);
		MyLabel bio = new MyLabel(userVO.getBio());
		bio.setBounds(425,375,300,30);add(bio);
		Font font = new Font("微软雅黑", Font.PLAIN, 20);
		JLabel follower = new JLabel(userVO.getFollowNum()+"",JLabel.CENTER);
		follower.setFont(font);
		follower.setBounds(45,180,60,30);add(follower);
		JLabel following = new JLabel(userVO.getFollowingNum()+"",JLabel.CENTER);
		following.setFont(font);
		following.setBounds(165,180,60,30);add(following);
		JLabel reponum = new JLabel(userVO.getPublic_repos()+"",JLabel.CENTER);
		reponum.setFont(font);
		reponum.setBounds(165,280,60,30);add(reponum);
		JLabel gistnum = new JLabel(userVO.getPublic_gists()+"",JLabel.CENTER);
		gistnum.setFont(font);
		gistnum.setBounds(45,275,60,30);add(gistnum);
		MyLabel create = new MyLabel(userVO.getCreate_at());
		bio.setBounds(130,380,200,30);add(create);
		MyLabel update = new MyLabel(userVO.getBio());
		update.setBounds(130,425,200,30);add(update);
		/**
		 * set listener
		 */
		blog.addMouseListener(new URLListener(blog));
	}
	
	
	class URLListener implements MouseListener{

		private JLabel label;
		
		public URLListener(JLabel label) {
			this.label = label;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			String url = label.getText().replace("\"","");
			openWeb(url);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			label.setForeground(Color.RED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			label.setForeground(Color.BLACK);
		}
		
		private void openWeb(String url) {
			if (java.awt.Desktop.isDesktopSupported()) {  
	            try {  
	                java.net.URI uri = java.net.URI.create(url);  
	                java.awt.Desktop dp = java.awt.Desktop.getDesktop() ;   
	                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {  
	                    dp.browse( uri ) ;  
	                }              
	            } catch (Exception e) {   
	                e.printStackTrace() ;  
	            }  
	        } 
		}
		
	}

}


