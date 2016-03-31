package edu.nju.git.bl.TestRMI;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.rmi.RMIClientLauncher;

import java.rmi.RemoteException;

/**
 * Created by Harry on 2016/3/31.
 */
public class ChartTest {
    public static void main (String [] args) {
        RMIClientLauncher.initRMI();
        RepoChartBlImpl repobl = RepoChartBlImpl.instance();
        UserChartBlImpl userbl = UserChartBlImpl.instance();
        try {
            MyChartVO languageChart  = repobl.statLanguage();
            MyChartVO starChart  = repobl.statStars();
            MyChartVO repoCreateTime = repobl.statCreateTime();
            MyChartVO repoContributor = repobl.statContributors();
            MyChartVO repoColla = repobl.statCollaborators();
            MyChartVO repoSubscr  = repobl.statSubscriber();
            MyChartVO repoSize = repobl.statSize();
            MyChartVO repoFork = repobl.statForks();

            MyChartVO userType = userbl.statUserType();
            MyChartVO userOwnRepo  = userbl.statUserOwnRepo();
            MyChartVO userGist = userbl.statUserGist();
            MyChartVO userCreateTime = userbl.statUserCreateTime();
            MyChartVO userEmail = userbl.statUserEmail();
            MyChartVO userContribute = userbl.statUserContriRepo();
            MyChartVO userCollab = userbl.statUserCollabRepo();
            MyChartVO userSubscr = userbl.statUserSubscrRepo();
            MyChartVO userCompany = userbl.statCompanyUser();
            MyChartVO userFollower = userbl.statUserFolllowers();

            System.out.println("repolanguage: ");
            for (String field: languageChart.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: languageChart.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repostar: ");
            for (String field: starChart.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: starChart.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repoCreateTime: ");
            for (String field: repoCreateTime.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: repoCreateTime.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repoContributor: ");
            for (String field: repoContributor.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: repoContributor.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repoCollaborator: ");
            for (String field: repoColla.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: repoColla.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repoSubscriber: ");
            for (String field: repoSubscr.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: repoSubscr.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repoFork: ");
            for (String field: repoFork.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: repoFork.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("repoSize: ");
            for (String field: repoSize.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: repoSize.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");

//================================================================================================

            System.out.println("userOwnRepo: ");
            for (String field: userOwnRepo.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userOwnRepo.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userCreteTime: ");
            for (String field: userCreateTime.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userCreateTime.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userEmail: ");
            for (String field: userEmail.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userEmail.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userType: ");
            for (String field: userType.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userType.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userGist: ");
            for (String field: userGist.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userGist.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userContribute: ");
            for (String field: userContribute.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userContribute.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userCollaborate: ");
            for (String field: userCollab.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userCollab.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userSubscribe: ");
            for (String field: userSubscr.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userSubscr.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userCompany: ");
            for (String field: userCompany.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userCompany.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");
            System.out.println("userFollower: ");
            for (String field: userFollower.getFields()){
                System.out.print("  "+field);
            }
            System.out.print("\n");
            for (int value: userFollower.getValues()){
                System.out.print("  "+value);
            }
            System.out.print("\n");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
