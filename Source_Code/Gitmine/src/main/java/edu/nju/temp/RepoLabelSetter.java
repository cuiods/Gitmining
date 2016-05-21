package edu.nju.temp;

import edu.nju.dao.impl.RepoDaoImp;
import edu.nju.dao.impl.RepoLabelUpdater;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/20.
 */
@Service
public class RepoLabelSetter {

    @Resource
    private RepoLabelUpdater updater;

    @Resource
    private RepoDaoImp repoDaoImp;

    public void setLabel(){
        List<Object[]> list = repoDaoImp.getAllRepoDescription();
        int count=0;
        for (Object[] objects:list){
            updater.saveOrUpdateLabel((String)objects[0],(String)objects[1],(String)objects[2]);
            count++;
            if (count%50 == 0){
                System.out.println("has update "+count+" repo labels");
            }
        }
    }

}
