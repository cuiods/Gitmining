package edu.nju.git.tools;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;

/**
 * This class is a converter that convert a po to vo or vo to po.
 *
 * <p>Methods in this class should be static.All methods related to transforming po or vo share the same<br>
 * name but distinguished by polymorphism.
 *
 * @author benchaodong
 * @date 2016-03-08
 */
public class POVOConverter {

    public static RepoBriefVO convertToBriefVO(RepoPO po) {
        String updateTime = po.getUpdate_at().replace('T',' ').replace("Z","");
        return new RepoBriefVO(po.getOwnerName(), po.getName(), po.getDescription(), po.getNum_stars(),
                po.getNum_forks(), po.getNum_subscribers(), updateTime);
    }

    public static RepoVO convertToVO(RepoPO po) {
        String createTime = po.getCreate_at().replace('T',' ').replace("Z","");
        String updateTime = po.getUpdate_at().replace('T',' ').replace("Z","");
        RepoVO vo  = new RepoVO(po.getName(), po.getOwnerName(), po.getSize(), po.getLanguage(),po.getUrl(),
                po.getDescription(),createTime,updateTime,po.getNum_stars(),po.getNum_forks(),
                po.getNum_subscribers(),po.getNum_contrbutors(),po.getNum_collaborators(),po.getNum_commits(),
                po.getNum_issues(), po.getNum_pulls());
        return vo;
    }

    public static UserBriefVO convertToBriefVO(UserPO po) {
        String updateTime = po.getUpdate_at().replace('T',' ').replace("Z","");
        String createTime = po.getCreate_at().replace('T',' ').replace("Z","");
        return new UserBriefVO(po.getLogin(), po.getFollowNum(), po.getFollowingNum(), po.getPublic_repos(),
                createTime, updateTime, po.getCompany());
    }

    public static UserVO convertToVO(UserPO po) {
        String createTime = po.getCreate_at().replace('T',' ').replace("Z","");
        String updateTime = po.getUpdate_at().replace('T',' ').replace("Z","");
        return new UserVO(po.getLogin(), po.getType(), po.getName(), po.getCompany(), po.getBlog(), po.getLocation(),
                po.getEmail(), po.getBio(),po.getFollowNum(),po.getFollowingNum(),createTime,updateTime,
                po.getPublic_repos(),po.getPublic_gists(),po.getNum_subscribe(),po.getNum_contribute(),
                po.getNum_collaborate(),po.getAvatar_url());
    }
}
