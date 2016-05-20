package edu.nju.common;

import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * Created by Harry on 2016/5/17.
 * this is a tool class to a <tt>TblRepo</tt> or <tt>TblUser</tt> to vo when transfer on the internet
 */
@Service
public class VOConvertor {

    @Resource
    private UserDaoService userDaoImpl;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public RepoVO convert(TblRepo tblRepo){
        if (tblRepo == null){
            return null;
        }
        String avatar = userDaoImpl.getUserAvatar(tblRepo.getOwnerName());
        //String createAt = timeTranslator.transUnixTime(tblRepo.getCreateAt().getTime());
        //String updateAt = timeTranslator.transUnixTime(tblRepo.getUpdateAt().getTime());
        String createAt = dateFormat.format(tblRepo.getCreateAt());
        String updateAt = dateFormat.format(tblRepo.getUpdateAt());
        RepoVO vo = new RepoVO(tblRepo.getOwnerName(),avatar,tblRepo.getName(),tblRepo.getSize(),tblRepo.getDescription(),
                tblRepo.getLanguage(),tblRepo.getUrl(),createAt,updateAt,tblRepo.getNumStar(),tblRepo.getNumFork(),
                tblRepo.getNumSubscriber());
        return vo;
    }

    public UserVO convert(TblUser tblUser){
        if (tblUser == null){
            return null;
        }
//        String createAt = timeTranslator.transUnixTime(tblUser.getCreateAt().getTime());
//        String updateAt = timeTranslator.transUnixTime(tblUser.getUpdateAt().getTime());
        String createAt = dateFormat.format(tblUser.getCreateAt());
        String updateAt = dateFormat.format(tblUser.getUpdateAt());
        String type = (tblUser.getType()==0)?"User":"Organization";
        UserVO vo = new UserVO(tblUser.getLoginName(),tblUser.getName(),tblUser.getAvatarUrl(),tblUser.getBlog(),
                tblUser.getEmail(),tblUser.getLocation(),tblUser.getCompany(),tblUser.getBio(),type,tblUser.getPublicRepo(),
                tblUser.getPublicGist(),tblUser.getFollower(),tblUser.getFollowing(),createAt,updateAt);

        return vo;
    }
}
