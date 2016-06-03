package edu.nju.common;

import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleRepoVO;
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
    private SecUserDaoService userDaoImpl;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public SimpleRepoVO simpleConvert(SecRepoEntity repoEntity){
        if (repoEntity == null){
            return null;
        }
        String createAt = dateFormat.format(repoEntity.getCreateAt());
        String updateAt = dateFormat.format(repoEntity.getUpdateAt());
        return new SimpleRepoVO(repoEntity.getOwner(),repoEntity.getName(),repoEntity.getSize(),repoEntity.getDescription(),
                repoEntity.getLanguage(),createAt,updateAt,repoEntity.getStarCount(),repoEntity.getForkCount(),repoEntity.getWatchersCount());
    }

    public RepoVO convert(SecRepoEntity repoEntity){
        if (repoEntity == null){
            return null;
        }
        String avatar = userDaoImpl.getUserAvatar(repoEntity.getOwner());
        //String createAt = timeTranslator.transUnixTime(tblRepo.getCreateAt().getTime());
        //String updateAt = timeTranslator.transUnixTime(tblRepo.getUpdateAt().getTime());
        String createAt = dateFormat.format(repoEntity.getCreateAt());
        String updateAt = dateFormat.format(repoEntity.getUpdateAt());
        RepoVO vo = new RepoVO(repoEntity.getOwner(),avatar,repoEntity.getName(),repoEntity.getSize(),repoEntity.getDescription(),
                repoEntity.getLanguage(),repoEntity.getHtmlUrl(),createAt,updateAt,repoEntity.getStarCount(),repoEntity.getForkCount(),
                repoEntity.getWatchersCount());
        return vo;
    }

    public UserVO convert(SecUserEntity userEntity){
        if (userEntity == null){
            return null;
        }
//        String createAt = timeTranslator.transUnixTime(tblUser.getCreateAt().getTime());
//        String updateAt = timeTranslator.transUnixTime(tblUser.getUpdateAt().getTime());
        String createAt = dateFormat.format(userEntity.getCreateAt());
        String updateAt = dateFormat.format(userEntity.getUpdateAt());

        UserVO vo = new UserVO(userEntity.getLogin(),userEntity.getName(),userEntity.getAvatarUrl(),userEntity.getBlog(),
                userEntity.getEmail(),userEntity.getLocation(),userEntity.getCompany(),userEntity.getBio(),userEntity.getType(),userEntity.getPublicRepos(),
                userEntity.getPublicGists(),userEntity.getFollowers(),userEntity.getFollowing(),createAt,updateAt);

        return vo;
    }
}
