package org.GitClient;

import java.rmi.RemoteException;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.bl.service.UserChartBlService;
import edu.nju.git.rmi.RMIClientLauncher;
import junit.framework.TestCase;

public class UserChartBlServiceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testStatUserType() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserType();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatCompanyUser() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statCompanyUser();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserEmail() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserEmail();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserCreateTime() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserCreateTime();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserOwnRepo() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserOwnRepo();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserGist() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserGist();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserCollabRepo() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserCollabRepo();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserContriRepo() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserContriRepo();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserSubscrRepo() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserSubscrRepo();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatUserFolllowers() throws RemoteException {
		RMIClientLauncher.initRMI();
		UserChartBlService chartBl = UserChartBlImpl.instance();
		MyChartVO vo = chartBl.statUserFolllowers();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

}
