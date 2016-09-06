package edu.nju.data;

import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cuiods
 */
@Repository
public class UpdateStat {
    @Resource
    private SessionFactory sessionFactory;

    public void updateStat(int start, int max) {
        List<SecRepoEntity> repoEntities = getRepos(start, max);
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT U.followers,Contri.contributions FROM sec_repo AS Repo " +
                "LEFT JOIN sec_contributor AS Contri ON Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name " +
                "LEFT JOIN sec_user AS U ON U.login = Contri.contributor WHERE Repo.owner =:owner AND Repo.name =:name ");
        Query query1 = session.createQuery("update SecRepoEntity set sumFollower=:sumFollower, avgFollower=:avgFollower, weightFollower=:weightFollower " +
                "where owner=:owner and name=:name ");
        for (int j = 0; j < repoEntities.size(); j++) {
            SecRepoEntity secRepoEntity = repoEntities.get(j);
            query.setString("owner",secRepoEntity.getOwner());
            query.setString("name", secRepoEntity.getName());
            List result = query.list();
            if (result!=null && result.size()>0) {
                int sum_follower = 0;
                int sum_contribution = 0;
                int sum_production = 0;
                for (int i = 0; i < result.size(); i++) {
                    Object[] one = (Object[]) result.get(i);
                    if (one.length>1 && one[0]!=null && one[1]!=null) {
                        Integer follower = (Integer) one[0];
                        int contribution = (int) one[1];
                        sum_follower += follower;
                        sum_contribution += contribution;
                        sum_production += follower * contribution;
                    }
                }
                query1.setString("owner",secRepoEntity.getOwner());
                query1.setString("name", secRepoEntity.getName());
                query1.setInteger("sumFollower",sum_follower);
                query1.setFloat("avgFollower",sum_follower*1.0f/result.size());
                if (sum_contribution==0) {
                    sum_contribution = 1;
                }
                query1.setFloat("weightFollower",sum_production*1.0f/sum_contribution);
                query1.executeUpdate();
            }
            if (j%20==0) {
                System.out.println("completed:" + j*1.0f/max*100);
            }
        }
        session.close();
    }

    private List<SecRepoEntity> getRepos(int start, int max) {
        Session session = sessionFactory.openSession();
        List<SecRepoEntity> entities = null;
        Query query = session.createQuery("from SecRepoEntity ");
        query.setFirstResult(start);
        query.setMaxResults(max);
        entities = query.list();
        session.close();
        return entities;
    }
}
