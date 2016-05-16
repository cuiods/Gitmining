package edu.nju.model.imp;

import edu.nju.dao.service.RepoDaoService;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoStatsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 2016/5/16.
 * statistic for repo
 */
@Service
public class RepoStatsImpl implements RepoStatsService {

    @Resource
    private RepoDaoService repoDaoImpl;

    @Override
    public SimpleChart statsCreateTime() {
        String [] years = {"before 2008","2008","2009","2010","2011","2012","2013","after 2013"};
        long [] value = new long[years.length];
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.set(1000, Calendar.JANUARY, 1, 0, 0, 0);
        to.set(2008, Calendar.DECEMBER, 31, 23, 59,59);
        value[0] = repoDaoImpl.getStatsCreateTime(from, to);
        for (int i = 2008;i<=2013;i++){
            from.set(Calendar.YEAR, i);
            to.set(Calendar.YEAR, i);
            value[i-2007] = repoDaoImpl.getStatsCreateTime(from, to);
        }
        from.set(Calendar.YEAR, 2014);
        to.setTime(new Date());
        value[value.length-1] = repoDaoImpl.getStatsCreateTime(from, to);
        return new SimpleChart(years, value);
    }

    @Override
    public SimpleChart statsForkCount() {
        String [] field = {"0~50","50~100","100~150","150~200","200~250","250~300","300~350",
                "350~400","400~450","450~500",">=500"};
        long [] value = new long[field.length];

        int step = 50;
        for (int i=0;i<field.length-1;i++){
            value[i] = repoDaoImpl.getStatsFork(step*i, step*(i+1)-1);
        }
        value[value.length-1] = repoDaoImpl.getStatsFork(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsStarCount() {
        String [] field = {"0~500","500~1000","1000~1500","1500~2000","2000~2500","2500~3000",
                "3000~3500","3500~4000",">=4000"};
        long [] value = new long[field.length];

        int step = 500;
        for (int i=0;i<field.length-1;i++){
            value[i] = repoDaoImpl.getStatsStar(step*i, step*(i+1)-1);
        }
        value[value.length-1] = repoDaoImpl.getStatsStar(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsLanguage() {
        int maxResults = 10;
        List list = repoDaoImpl.getStatsLanguage(maxResults);
        int actualRsults = list.size();
        String [] field = new String[actualRsults];
        long [] value = new long[actualRsults];
        for (int i=0; i<actualRsults; i++){
            Object[] item = (Object[]) list.get(i);
            field[i] = item[0].toString();  //the language name
            value[i] = Long.valueOf(item[1].toString());
        }
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsSize(){
        String [] field = {"0~2000","2000~4000","4000~6000","6000~8000","8000~10000","10000~12000",
                "12000~14000","14000~16000","16000~18000","18000~20000",">=20000"};
        long [] value = new long[field.length];

        int step = 2000;
        for (int i=0;i<field.length-1;i++){
            value[i] = repoDaoImpl.getStatsSize(step*i, step*(i+1)-1);
        }
        value[value.length-1] = repoDaoImpl.getStatsSize(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }
}
