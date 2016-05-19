package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.entity.UserLabel;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * user data impl
 * @author cuihao
 */
@Repository
public class UserDaoImp implements UserDaoService {
    @Resource
    private SessionFactory sessionFactory;
    /**
     * get user info
     *
     * @param loginName
     * @return user info entity
     */
    public TblUser findUserByLoginName(String loginName) {
        Session session =sessionFactory.openSession();
        TblUser user = null;
        Query query = session.createQuery("from TblUser as u where u.loginName=?");
        query.setString(0, loginName);
        List<TblUser> users = query.list();
        if (users.size() > 0) {
            user = users.get(0);
        }
        else {
//            System.out.println("miss for find user====================" + loginName+"=========================");
        }
        session.close();
        return user;
    }

    @Override
    public String getUserAvatar(String username) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT avatar_url FROM tbl_user WHERE login_name = :username ");
        query.setString("username",username);
        List list = query.list();
        String avatar = "";
        if (list.size() > 0){
            avatar = (String)list.get(0);
        }
        session.close();
        return avatar;
    }

    /**
     * search users by login name(keyword)
     *
     * @param keyword
     * @return list of users
     */
    public List<TblUser> searchUserByLoginName(String keyword, SortType sortType, boolean isDesc, int offset, int maxNum) {
        Session session =sessionFactory.openSession();

        String sql = "from TblUser as u where u.loginName like ? order by ";
        if (sortType == null){
            sql+="loginName ";
        }
        else {
            switch (sortType){
                case User_Follored:sql+="follower ";break;
                case User_Folloring:sql+="following ";break;
                case User_Repos:sql+="publicRepo ";break;
                default:sql+="loginName ";break;
            }
        }

        if (isDesc){
            sql+="desc ";
        }
        else {
            sql+="asc";
        }
        Query query = session.createQuery(sql);
        query.setString(0, "%"+keyword+"%");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblUser> users = query.list();
        session.close();
        return users;
    }

    /**
     * get Total count of user.
     *
     * @return number of user
     */
    public long getUserTotalCount() {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from TblUser");
        Long result = (Long) query.list().get(0);
        session.close();
        return result;
    }


    /**
     * get sorted user list.
     *
     * @param offset
     * @param maxNum
     * @return list of sorted user.
     */
    @Deprecated
    public List<TblUser> getUsers(int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblUser");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblUser> list = query.list();
        session.close();
        return list;
    }

    /**
     * get sorted user list.
     *
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<TblUser> getUsers(SortType sortType, boolean isDesc,  int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = null;
        String order = isDesc?"desc":"asc";
        switch (sortType) {
            case User_Follored:query = session.createQuery("from TblUser order by follower "+order);break;
            case User_Folloring:query = session.createQuery("from TblUser order by following "+order);break;
            case User_Repos:query = session.createQuery("from TblUser order by publicRepo "+order);break;
            default:query = session.createQuery("from TblUser order by loginName "+order);break;
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblUser> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<TblRepo> getUserOwnRepos(String userLoginName,SortType sortType,int offset,int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblRepo where ownerName = :owner order by numStar desc ");
        query.setString("owner", userLoginName);
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblRepo> list = query.list();
        session.close();
        return list;
    }


    /**
     * get related repositorys
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserSubscribeRepos(String userLoginName,int offset,int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new list(repoOwner,repo) from TblSubscriber where subscriber=?");
        query.setString(0,userLoginName);
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<List> list = query.list();
        session.close();
        return list;
    }

    /**
     * get related collaborator repositories
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserCollaboratorRepos(String userLoginName,int offset,int maxNum) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select new list(repoOwner,repo) from TblCollabrator where collabrator=?");
        query.setString(0,userLoginName);
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<List> list = query.list();
        session.close();
        return list;
    }

    /**
     * get related contributor
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserContributorRepos(String userLoginName,int offset,int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new list(ownerName,repo) from TblContributor where contributor=?");
        query.setString(0,userLoginName);
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<List> list = query.list();
        session.close();
        return list;
    }

    /**
     * get user interest labels
     *
     * @param userName
     * @return user label
     * {@link UserLabel}
     */
    public UserLabel getUserInterest(String userName) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("from UserLabel where userLogin=?");
        query.setString(0,userName);
        List<UserLabel> userLabels = query.list();
        session.close();
        if(userLabels.size()>0) {
            return userLabels.get(0);
        }
        return null;
    }

    /**
     * save userLabel
     *
     * @param userLabel
     * @return is succeed
     */
    public boolean saveUserInterest(UserLabel userLabel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userLabel);
        session.flush();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public long getStatsUserType(byte isOrg) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where type = ?");
        query.setByte(0, isOrg);
        List list = query.list();
        session.close();
        return ((Long)list.get(0));
    }

    @Override
    public long getStatsUserOwnRepo(int min, int max) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where publicRepo >= " +
                "? and publicRepo <= ?");
        query.setInteger(0, min);
        query.setInteger(1, max);
        List list = query.list();
        session.close();
        return (Long)list.get(0);
    }

    @Override
    public long getStatsUserGist(int min, int max) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where publicGist >= " +
                "? and publicGist <= ?");
        query.setInteger(0, min);
        query.setInteger(1, max);
        List list = query.list();
        session.close();
        return (Long)list.get(0);
    }

    @Override
    public long getStatsUserFollower(int min, int max) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where follower >= " +
                "? and follower <= ?");
        query.setInteger(0, min);
        query.setInteger(1, max);
        List list = query.list();
        session.close();
        return (Long)list.get(0);
    }

    @Override
    public long getStatsCreateTime(Calendar fromTime, Calendar toTime) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where createAt " +
                ">= :fT and createAt <= :tT");
        query.setTimestamp("fT", fromTime.getTime());
        query.setTimestamp("tT", toTime.getTime());
        List list = query.list();
        long result = (Long)list.get(0);
        session.close();
        return result;
    }

    @Override
    public List getStatsEmail(int maxResults) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT email_suffix, count(*) AS num FROM tbl_user WHERE email_suffix <> '' GROUP BY email_suffix ORDER BY num DESC ");
        query.setMaxResults(maxResults);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List getStatsCompany(int maxResults) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select company, count (*) as num from TblUser " +
                "where company <> '' group by company order by num desc ");
        query.setMaxResults(maxResults);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public double getMaxRepos() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(publicRepo) from TblUser");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxGists() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(publicGist) from TblUser");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxFollower() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(follower) from TblUser");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxUserContribute() {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(A.num) FROM (SELECT COUNT(*) AS num " +
                "FROM tbl_contributor GROUP BY contributor) AS A ");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMaxUserValue() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(A.val) FROM (SELECT avg(num_star*0.2+num_fork*0.3+num_subscriber*0.5) " +
                "AS val FROM tbl_repo GROUP BY owner_name) AS A");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getUserContribute(String username) {
        double contri = 0;
        Session session = sessionFactory.openSession();

        Query query2 = session.createQuery("select count(*) from TblContributor where contributor = ?");
        query2.setString(0,username);
        List list2 = query2.list();
        if (list2.size()>0){
            contri = Double.valueOf(list2.get(0).toString());
        }

        session.close();

        return contri;
    }

    @Override
    public double getUserValue(String username) {
        Session session = sessionFactory.openSession();
        SQLQuery query  = session.createSQLQuery("SELECT avg(num_star*0.2+num_fork*0.3+num_subscriber*0.5) FROM tbl_repo WHERE owner_name = :un GROUP BY owner_name");
        query.setString("un", username);
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        return 0;
    }

    /*

    private String getSortTypeHql(SortType sortType, boolean isDesc){
        StringBuilder builder = new StringBuilder();
        switch (sortType){
            case User_Repos:builder.append(" order by publicRepo ");break;
            case User_Follored:builder.append(" order by follower ");break;
            case User_Folloring:builder.append(" order by following ");break;
            case Repo_Star:builder.append(" order by numStar ");break;
            default:builder.append(" order by loginName ");break;
        }
        if (isDesc){
            builder.append("desc ");
        }
        else {
            builder.append("asc ");
        }
        return builder.toString();
    }

    private String getSortTypeSql(SortType sortType, boolean isDesc){
        StringBuilder builder = new StringBuilder();
        switch (sortType){
            case User_Repos:builder.append(" order by public_repo ");break;
            case User_Follored:builder.append(" order by follower ");break;
            case User_Folloring:builder.append(" order by following ");break;
            case Repo_Star:builder.append(" order by num_star ");break;
            default:builder.append(" order by login_name ");break;
        }
        if (isDesc){
            builder.append("desc ");
        }
        else {
            builder.append("asc ");
        }
        return builder.toString();
    }
*/
}
