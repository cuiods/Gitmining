package edu.nju.model.imp;

import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoStatsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        String [] field = {"0~1","2~3","4~5","6~7","8~9","10~11","12~13",
                "14~15",">=16"};
        long [] value = new long[field.length];

        int step = 2;
        for (int i=0;i<field.length-1;i++){
            value[i] = secRepoDaoService.getStatsFork(step*i, step*(i+1)-1);
        }
        value[value.length-1] = secRepoDaoService.getStatsFork(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }

    @Override
    public SimpleChart statsStarCount() {
        String [] field = {"0~9","10~19","20~29","30~39","40~49","50~59",
                "60~69","70~79","80~89","90~99",">=100"};
        long [] value = new long[field.length];

        int step = 10;
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
                "1200~1400","1400~1600","1600~1800","1800~2000","2000~2200","2200~2400",
                "2400~2600","2600~2800","2800~3000",">=3000"};
        long [] value = new long[field.length];

        int step = 200;
        for (int i=0;i<field.length-1;i++){
            value[i] = secRepoDaoService.getStatsSize(step*i, step*(i+1)-1);
        }
        value[value.length-1] = secRepoDaoService.getStatsSize(step*(value.length-1), Integer.MAX_VALUE);
        return new SimpleChart(field, value);
    }
}
