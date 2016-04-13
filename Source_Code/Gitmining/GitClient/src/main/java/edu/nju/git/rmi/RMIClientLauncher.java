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
		Thread t1 = showWarning();
		t1.start();
		do {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println("try to reconnect count: "+counts);
		}
		while ((!initRMI())&&(Consts.CONNECT_TIMES > ++counts));

		if (counts >= Consts.CONNECT_TIMES) {	//can not reconnect to server
			System.out.println("reconnect fail");
//			UtilDialog.hideDialog();
//			UtilDialog.ShowConfirm("无法连接至服务器，客户端将在3秒后关闭", new CloseOperation());

			Thread t2 = showExit();
			t2.start();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("thread sleep exception");
			}
			System.exit(-1);
		}
		else {
//			UtilDialog.hideDialog();
			t1.stop();
		}
	}

	public static Thread showWarning() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String message = "连接服务器失败，正在尝试重连，请稍后...";
				WarningFrame frame = new WarningFrame(message);

				frame.setVisible(true);
			}
		});

		return thread;
	}

	public static Thread showExit() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String message = "无法连接服务器，程序将在5秒后关闭...";
				WarningFrame frame = new WarningFrame(message);

				frame.setVisible(true);
			}
		});

		return thread;
	}

	protected static class WarningFrame extends JFrame {
		public WarningFrame(String message) {
			super("警告");
			this.setSize(400,300);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = this.getSize();

			if (frameSize.height > screenSize.height)
				frameSize.height = screenSize.height;
			if (frameSize.width > screenSize.width)
				frameSize.width = screenSize.width;

			this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

			JLabel label = new JLabel(message,SwingConstants.CENTER);
			label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
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
