package edu.nju.git.rmi;

import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.constant.Consts;
import edu.nju.git.data.service.RepoChartDataService;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.data.service.UserChartDataService;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.ui.utils.CloseOperation;
import edu.nju.git.ui.utils.UtilDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * this class is for init rmi bounding
 */
public class RMIClientLauncher {

	public static boolean initRMI() {
		try {
			File file = new File("support_files/ServerIPAddress.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String ip = bufferedReader.readLine();

			RepoChartDataService repoChartDataService = (RepoChartDataService) Naming.lookup(ip+"/RepoChartDataService");
			UserChartDataService userChartDataService = (UserChartDataService) Naming.lookup(ip+"/UserChartDataService");
			RepoDataService repoDataService = (RepoDataService) Naming.lookup(ip+"/RepoDataService");
			UserDataService userDataService = (UserDataService) Naming.lookup(ip+"/UserDataService");

			RepoChartBlImpl.instance().setRepoChartDataService(repoChartDataService);
			UserChartBlImpl.instance().setUserChartDataService(userChartDataService);
			RepoBlImpl.instance().setRepoDataService(repoDataService);
			UserBlImpl.instance().setUserDataService(userDataService);
			return true;
		} catch (NotBoundException e) {
			//e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			//e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * this method is invoked when client has problem connecting to server
	 */
	public static void sendRMIWarning() {
		int counts = 0;
//		UtilDialog.ShowMessage("连接服务器失败，正在尝试重连，请稍后...");
		WarningFrame frame1 = showWarning("连接服务器失败，正在尝试重连，请稍后...");
		do {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("try to reconnect count: "+counts);
		}
		while ((!initRMI())&&(Consts.CONNECT_TIMES > ++counts));

		frame1.dispose();

		if (counts >= Consts.CONNECT_TIMES) {	//can not reconnect to server
			System.out.println("reconnect fail");
//			UtilDialog.hideDialog();
//			UtilDialog.ShowConfirm("无法连接至服务器，客户端将在3秒后关闭", new CloseOperation());

			WarningFrame frame2 = showWarning("无法连接至服务器，客户端将在5秒内关闭");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("thread sleep exception");
			}
			frame2.dispose();
			System.exit(-1);
		}
		else {
//			UtilDialog.hideDialog();
		}
	}

	public static WarningFrame showWarning(String message) {
		MyRunnable runnable = new MyRunnable(message);
		Thread thread = new Thread(runnable);
		thread.start();
		return runnable.getFrame();
	}

	protected static class MyRunnable implements Runnable {

		private WarningFrame frame;

		public MyRunnable(String message) {
			frame = new WarningFrame(message);
		}

		@Override
		public void run() {
			frame.setVisible(true);
		}

		public WarningFrame getFrame(){
			return frame;
		}
	}

	protected static class WarningFrame extends JFrame {
		public WarningFrame(String message) {
			super("警告");
			this.setSize(400,250);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = this.getSize();

			if (frameSize.height > screenSize.height)
				frameSize.height = screenSize.height;
			if (frameSize.width > screenSize.width)
				frameSize.width = screenSize.width;

			this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

			JLabel label = new JLabel(message,SwingConstants.CENTER);
			label.setFont(new Font("", Font.PLAIN, 20));
			this.getContentPane().add(label);

			this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		}

		@Override
		protected void processWindowEvent(WindowEvent e) {
			if (e.getID() == WindowEvent.WINDOW_CLOSING)
				return;
			super.processWindowEvent(e);
		}
	}
}
