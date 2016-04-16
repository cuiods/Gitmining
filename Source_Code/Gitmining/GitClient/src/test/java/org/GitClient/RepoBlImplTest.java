package org.GitClient;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.rmi.RMIClientLauncher;
import junit.framework.TestCase;

/**
 * Created by Harry on 2016/4/6.
 */
public class RepoBlImplTest extends TestCase {
    public void testGetRepoBasicInfo() throws Exception {

        RMIClientLauncher.initRMI();

        RepoBlImpl bl = RepoBlImpl.instance();
        RepoVO vo = bl.getRepoBasicInfo("Lexikos", "AutoHotkey_L");
        String [] lineChartFields = vo.getLineCharField();
        Integer[] lineChartValues = vo.getLineChartValue();
        for (String s : lineChartFields) {
            System.out.print(s+" ");
        }
        System.out.print("\n");
        for (int v : lineChartValues) {
            System.out.print(v+" ");
        }

        assertEquals(lineChartFields.length, lineChartValues.length);
    }

}