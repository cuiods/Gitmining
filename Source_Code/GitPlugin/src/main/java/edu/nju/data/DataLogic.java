package edu.nju.data;

import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuihao on 2016/8/29.
 */
@Repository
public class DataLogic {
    @Resource
    private GithubReader githubReader;

    @Resource
    private SessionFactory sessionFactory;

    private SecRepoEntity getRepo(long id){
        Session session = sessionFactory.openSession();
        SecRepoEntity secRepoEntity = null;
        Query query = session.createQuery("from SecRepoEntity where id=:id");
        query.setLong("id",id);
        List results = query.list();
        if (results.size()>0) {
            secRepoEntity = (SecRepoEntity) results.get(0);
        }
        session.close();
        return secRepoEntity;
    }

    public void startLogic(long start, long end) {
        for (long i = start; i < end; i++) {
            long total = end-start;
            SecRepoEntity secRepoEntity = getRepo(i);
            if (secRepoEntity!=null) {
                githubReader.updateActivity(secRepoEntity.getOwner(),secRepoEntity.getName());
                githubReader.updateWeekly(secRepoEntity.getOwner(),secRepoEntity.getName());
                githubReader.updatePunch(secRepoEntity.getOwner(),secRepoEntity.getName());
            }
            if (i%10==total%10) {
                System.out.println("Complete:"+i*1.0/total);
            }
        }
    }
}
