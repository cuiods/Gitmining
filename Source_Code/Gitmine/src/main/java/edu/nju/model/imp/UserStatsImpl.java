package edu.nju.model.imp;

import edu.nju.dao.service.SecUserDaoService;
import edu.nju.dao.service.UserDaoService;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.UserStatsService;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
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
        String [] field = {"0~20","20~40","40~60","60~80","80~100",">=100"};
        long [] value = new long[field.length];
        int step = 20;
        for (int i=0;i<value.length-1;i++){
            value[i] = secUserDaoService.getStatsUserOwnRepo(i*step, step*(i+1)-1);
        }
        value[value.length-1] = secUserDaoService.getStatsUserOwnRepo(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserGist() {
        String [] field = {"0~10","10~20","20~30","30~40","40~50","50~60","60~70",
                "70~80","80~90","90~100",">=100"};
        long [] value = new long[field.length];
        int step = 10;
        for (int i=0;i<value.length-1;i++){
            value[i] = secUserDaoService.getStatsUserGist(i*step, step*(i+1)-1);
        }
        value[value.length-1] = secUserDaoService.getStatsUserGist(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserFollower() {
        String [] field = {"0~10","10~20","20~30","30~40","40~50","50~60","60~70",
                "70~80","80~90","90~100",">=100"};
        long [] value = new long[field.length];
        int step = 10;
        for (int i=0;i<value.length-1;i++){
            value[i] = secUserDaoService.getStatsUserFollower(i*step, step*(i+1)-1);
        }
        value[value.length-1] = secUserDaoService.getStatsUserFollower(step*(value.length-1), Integer.MAX_VALUE);
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
