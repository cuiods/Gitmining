package edu.nju.model.imp;

import edu.nju.dao.service.SecUserDaoService;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.UserStatsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/16.
 * implementation of statistic part of user
 */
@Service
public class UserStatsImpl implements UserStatsService {

    @Resource
    private SecUserDaoService secUserDaoService;

    @Override
    public SimpleChart statsUserType() {
        List<Object[]> list = secUserDaoService.getStatsUserType();
        int size = list.size();
        String [] field = new String[size];
        long [] value = new long[size];
        for (int i=0;i<size;i++){
            Object[] item = list.get(i);
            field[i] = item[0].toString();
            value[i] = Long.valueOf(item[1].toString());
        }
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserRepo() {
        String [] field = {"0~9","10~19","20~29","30~39","40~49","50~59","60~69","70~79","80~89","90~99",">=100"};
        long [] value = secUserDaoService.getStatsUserOwnRepo();
//        int step = 10;
//        for (int i=0;i<value.length-1;i++){
//            value[i] = secUserDaoService.getStatsUserOwnRepo(i*step, step*(i+1)-1);
//        }
//        value[value.length-1] = secUserDaoService.getStatsUserOwnRepo(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserGist() {
        String [] field = {"0~1","2~3","4~5","6~7","8~9","10~11","12~13",
                "14~15","16~17","18~19",">=20"};
        long [] value = secUserDaoService.getStatsUserFollower();
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserFollower() {
        String [] field = {"0~4","5~9","10~14","15~19","20~24","25~29","30~34",
                "35~39","40~44","45~49",">=50"};
        long [] value = secUserDaoService.getStatsUserFollower();
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserCreateTime() {
        List<Object[]> list = secUserDaoService.getStatsCreateTime();
        int years = list.size();
        String [] field = new String[years];
        long [] value = new long[years];
        for (int i=0;i<years;i++){
            Object [] year = list.get(i);
            field[i] = year[0].toString();
            value[i] = Long.valueOf(year[1].toString());
        }
        return new SimpleChart(field,value);
    }

    @Override
    public SimpleChart statsUserEmail() {
        int maxResults = 10;
        List list = secUserDaoService.getStatsEmail(maxResults);
        int actualResults = list.size();
        String [] field = new String[actualResults];
        long [] value = new long[actualResults];
        for (int i=0;i<actualResults;i++){
            Object[] item = (Object[]) list.get(i);
            field[i] = item[0].toString();
            value[i] = Long.valueOf(item[1].toString());
        }
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserCompany() {
        int maxResults = 10;
        List list = secUserDaoService.getStatsCompany(maxResults);
        int actualResults = list.size();
        String [] field = new String[actualResults];
        long [] value = new long[actualResults];
        for (int i=0;i<actualResults;i++){
            Object[] item = (Object[]) list.get(i);
            field[i] = item[0].toString();
            value[i] = Long.valueOf(item[1].toString());
        }
        return new SimpleChart(field, value);
    }
}
