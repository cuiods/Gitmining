//package org.GitServer;
//
//import junit.framework.TestCase;
//import org.GitServer.cacheinit.DataEncapsulation;
//import org.GitServer.dataread.Reader;
//
//import java.util.List;
//
///**
// * Created by Harry on 2016/4/5.
// */
//public class DataEncapsulationTest extends TestCase {
//    public void testCommit() throws Exception {
//        DataEncapsulation dataEncapsulation = new Reader().excute();
//        List<String> commtis = dataEncapsulation.repoToCommit.get("jquery/jquery");
//        List<String> pulls = dataEncapsulation.repoToPull.get("jquery/jquery");
//        List<String> issues = dataEncapsulation.repoToIssue.get("jquery/jquery");
//        System.out.println("commits");
//        for (String s:commtis) {
//            System.out.println(s);
//        }
//        System.out.println("pulls");
//        for (String s:pulls) {
//            System.out.println(s);
//        }
//        System.out.println("issues");
//        for (String s:issues) {
//            System.out.println(s);
//        }
//    }
//}