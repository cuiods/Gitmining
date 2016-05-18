package edu.nju.model.imp;

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
    private UserDaoService userDaoImpl;

    @Override
    public SimpleChart statsUserType() {
        String [] field = {"User", "Organization"};
        long [] value = new long[field.length];
        value[0] = userDaoImpl.getStatsUserType((byte)0);
        value[1] = userDaoImpl.getStatsUserType((byte)1);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserRepo() {
        String [] field = {"0~20","20~40","40~60","60~80","80~100",">=100"};
        long [] value = new long[field.length];
        int step = 20;
        for (int i=0;i<value.length-1;i++){
            value[i] = userDaoImpl.getStatsUserOwnRepo(i*step, step*(i+1)-1);
        }
        value[value.length-1] = userDaoImpl.getStatsUserOwnRepo(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserGist() {
        String [] field = {"0~10","10~20","20~30","30~40","40~50","50~60","60~70",
                "70~80","80~90","90~100",">=100"};
        long [] value = new long[field.length];
        int step = 10;
        for (int i=0;i<value.length-1;i++){
            value[i] = userDaoImpl.getStatsUserGist(i*step, step*(i+1)-1);
        }
        value[value.length-1] = userDaoImpl.getStatsUserGist(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserFollower() {
        String [] field = {"0~10","10~20","20~30","30~40","40~50","50~60","60~70",
                "70~80","80~90","90~100",">=100"};
        long [] value = new long[field.length];
        int step = 10;
        for (int i=0;i<value.length-1;i++){
            value[i] = userDaoImpl.getStatsUserFollower(i*step, step*(i+1)-1);
        }
        value[value.length-1] = userDaoImpl.getStatsUserFollower(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsUserCreateTime() {
        String [] years = {"before 2008","2008","2009","2010","2011","2012","2013","after 2013"};
        long [] value = new long[years.length];
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.set(1000, Calendar.JANUARY, 1, 0, 0, 0);
        to.set(2008, Calendar.DECEMBER, 31, 23, 59,59);
        value[0] = userDaoImpl.getStatsCreateTime(from, to);
        for (int i = 2008;i<=2013;i++){
            from.set(Calendar.YEAR, i);
            to.set(Calendar.YEAR, i);
            value[i-2007] = userDaoImpl.getStatsCreateTime(from, to);
        }
        from.set(Calendar.YEAR, 2014);
        to.setTime(new Date());
        value[value.length-1] = userDaoImpl.getStatsCreateTime(from, to);
        return new SimpleChart(years, value);
    }

    @Override
    public SimpleChart statsUserEmail() {
        int maxResults = 10;
        List list = userDaoImpl.getStatsEmail(maxResults);
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
        List list = userDaoImpl.getStatsCompany(maxResults);
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
