package org.GitClient;

import java.rmi.RemoteException;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.service.RepoChartBlService;
import edu.nju.git.rmi.RMIClientLauncher;
import junit.framework.TestCase;

public class RepoChartBlServiceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testStatLanguage() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statLanguage();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatCreateTime() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statCreateTime();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatContributors() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statContributors();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatCollaborators() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statCollaborators();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatSize() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statSize();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatSubscriber() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statSubscriber();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatForks() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statForks();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

	public void testStatStars() throws RemoteException {
		RMIClientLauncher.initRMI();
		RepoChartBlService chartBl = RepoChartBlImpl.instance();
		MyChartVO vo = chartBl.statStars();
		String[] fields = vo.getFields();
		int[] values = vo.getValues();
		
		for(int i=0;i<fields.length;i++){
			assertTrue(fields[i]!=null&&values[i]>=0);
		}
	}

}
