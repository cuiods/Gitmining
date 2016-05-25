package edu.nju.model.imp;

import edu.nju.dao.service.RepoDaoService;
import edu.nju.dao.service.SecRepoDaoService;
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
    private SecRepoDaoService secRepoDaoService;

    @Override
    public SimpleChart statsCreateTime() {
        List<Object[]> list = secRepoDaoService.getStatsCreateTime();
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
    public SimpleChart statsForkCount() {
        String [] field = {"0~10","10~20","20~30","30~40","40~50","50~60","60~70",
                "70~80","80~90","90~100",">=100"};
        long [] value = new long[field.length];

        int step = 10;
        for (int i=0;i<field.length-1;i++){
            value[i] = secRepoDaoService.getStatsFork(step*i, step*(i+1)-1);
        }
        value[value.length-1] = secRepoDaoService.getStatsFork(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsStarCount() {
        String [] field = {"0~50","50~100","100~150","150~200","200~250","250~300",
                "300~350","350~400",">=400"};
        long [] value = new long[field.length];

        int step = 50;
        for (int i=0;i<field.length-1;i++){
            value[i] = secRepoDaoService.getStatsStar(step*i, step*(i+1)-1);
        }
        value[value.length-1] = secRepoDaoService.getStatsStar(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsLanguage() {
        int maxResults = 10;
        List list = secRepoDaoService.getStatsLanguage(maxResults);
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
        String [] field = {"0~200","200~400","400~600","600~800","800~1000","1000~1200",
                "1200~1400","1400~1600","1600~1800","1800~2000",">=2000"};
        long [] value = new long[field.length];

        int step = 200;
        for (int i=0;i<field.length-1;i++){
            value[i] = secRepoDaoService.getStatsSize(step*i, step*(i+1)-1);
        }
        value[value.length-1] = secRepoDaoService.getStatsSize(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }
}
