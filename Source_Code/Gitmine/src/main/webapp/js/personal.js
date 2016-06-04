/**
 * Created by lenovo on 2016/6/2.
 */

$(document).ready(
    function(){
        $.ajax({
            type:'GET',
            url:'/login/name',
            success:function(result){
                console.log($('#personalName').attr('href'));
                $('#personalName').text(result);
            },
            error:function(){
                alert("wrong!");
            }
        });

        RepoList.init();
        UserList.init();

        $.ajax({
            type:'GET',
            url:'/favorite/repos',
            data:{},
            success:function(repoList){
                RepoList.updateList(repoList);
            },
            error:function(){
                alert('wrong');
            }
        });
        $.ajax({
            type:'GET',
            url:'/favorite/users',
            data:{},
            success:function(userList){
                UserList.updateList(userList);
            },
            error:function(){
                alert('wrong');
            }
        })
    }
);

var RepoList={
    init:function(){
        this.gridsFather = $('#repoFather');
        this.lastGrid = $('.cherished_repo').eq(0);
        this.clear = $("<div class=\"clearfix\"> </div>" );
        this.hr = $('<hr>');
    },
    updateList:function(repoList){
        this.gridsFather.empty();
        var _this = this;
        $.each(repoList,function(i,repo){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find('.repoImg').eq(0).attr('src',repo.ownerAvatarUrl);
            tempGrid.find('.ownRepo').eq(0).text(repo.ownerName+'/'+repo.reponame);
            tempGrid.find('.updateTime').eq(0).text('updated at '+repo.updateAt);
            tempGrid.find('.description').eq(0).text(repo.description);
            tempGrid.find('.subscribe').eq(0).text(repo.numWatcher);
            tempGrid.find('.fork').eq(0).text(repo.numFork);
            tempGrid.find('.star').eq(0).text(repo.numStar);
            tempGrid.find('.repoDetail').eq(0).attr('href','repo.html?'+repo.ownerName+'/'+repo.reponame);
            _this.gridsFather.append(tempGrid);
            _this.gridsFather.append(_this.clear.clone(true));
            _this.gridsFather.append(_this.hr.clone(true));
        });
    }
};

var UserList={
    init:function(){
        this.gridsFather = $('#cherished_user');
        this.lastGrid = $('.userItem').eq(0);
        this.clear = $("<div class=\"clearfix\"> </div>" );
        this.hr = $('<hr>');
    },
    updateList:function(userList){
        this.gridsFather.empty();
        var _this = this;
        $.each(userList,function(i,user){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find('.userImg').eq(0).attr('src',user.avatarUrl);
            tempGrid.find('.userName').eq(0).text(user.login);
            tempGrid.find('.createTime').eq(0).text(user.createAt);
            tempGrid.find('.updateTime').eq(0).text(user.updateAt);
            tempGrid.find('.repo').eq(0).text(user.publicRepo);
            tempGrid.find('.following').eq(0).text(user.following);
            tempGrid.find('.followed').eq(0).text(user.follower);
            tempGrid.find('.userDetail').eq(0).attr('href','userDetail.html?'+user.login);
            _this.gridsFather.append(tempGrid);
            if(i%2==1){
                _this.gridsFather.append(_this.clear.clone(true));
                _this.gridsFather.append(_this.hr.clone(true));
            }
        });

    }
}