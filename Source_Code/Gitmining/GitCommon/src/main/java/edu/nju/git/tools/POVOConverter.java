package edu.nju.git.tools;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;

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

    public static UserBriefVO convert(UserBriefPO po) {
        return new UserBriefVO(po.getLogin(), po.getFollowers(), po.getPublic_repos());
    }

    public static RepoBriefVO convert(RepoBriefPO po) {
        return new RepoBriefVO(po.getOwner(), po.getName(), po.getDescription(), po.getNum_stars(),
                po.getNum_forks(), po.getNum_subscribers(), po.getLastUpdate());
    }
}
