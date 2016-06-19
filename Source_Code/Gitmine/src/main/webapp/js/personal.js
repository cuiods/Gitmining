/**
 * Created by lenovo on 2016/6/2.
 */

var currentRepoPage = 1;
var currentUserPage = 1;
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
                console.log("wrong!");
            }
        });

        RepoList.init();
        UserList.init();
        var repoPage = 0;
        var userPage = 0;
        $.ajax({
            type:'GET',
            url:'/favorite/repoPage',
            success:function(totalPage){
                repoPage=totalPage;
                console.log('repoPage is '+repoPage);
            },
            error:function(){
                console.log('get repoPage wrong!');
            }
        });
        $.ajax({
            type:'GET',
            url:'/favorite/userPage',
            success:function(totalPage){
                userPage = totalPage;
                console.log('userPage is '+userPage);
            },
            error:function(){
                console.log('get userPage wrong!');
            }
        })

        currentRepoPage = 1;
        currentUserPage = 1;
        $.ajax({
            type:'GET',
            url:'/favorite/repos',
            data:{},
            success:function(repoList){
                RepoList.updateList(repoList);
                $.jqPaginator('#pagination1', {
                    totalPages: repoPage,
                    visiblePages: 8,
                    currentPage: 1,
                    onPageChange: function (current) {
                        currentRepoPage = current;
                        jumpRepoPage(current);
                    }

                });
            },
            error:function(){
                console.log('get repoList wrong!');
            }
        });


        $.ajax({
            type:'GET',
            url:'/favorite/users',
            data:{},
            success:function(userList){
                UserList.updateList(userList);
                $.jqPaginator('#paginationUser', {
                    totalPages: userPage,
                    visiblePages: 8,
                    currentPage: 1,
                    onPageChange: function (current) {
                        currentUserPage = current;
                        jumpUserPage(current);
                    }

                });
            },
            error:function(){
                console.log('get userList wrong!');
            }
        })
    }
);

function jumpRepoPage(current){
    $.ajax({
        type:'GET',
        url:'/favorite/repos',
        data:{page:current},
        success:function(repoList){

            RepoList.updateList(repoList);
        },
        error:function(){
            console.log('jumpPage get repos wrong!');
        }
    })
};

function jumpUserPage(current){
    $.ajax({
        type:'GET',
        url:'/favorite/users',
        data:{page:current},
        success:function(userList){
            UserList.updateList(userList);
        },
        error:function(){
            console.log('jumpPage get users wrong!');
        }
    })
};

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

            var repoCherish = tempGrid.find('.repoCherish').eq(0);
            unCherishRepo(repoCherish,repo.ownerName,repo.reponame);

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
        this.clear = $("<div class=\"clearfix\"></div>" );
        this.hr = $('<hr>');
    },
    updateList:function(userList){
        this.gridsFather.empty();
        var _this = this;
        $.each(userList,function(i,user){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find('.userImg').eq(0).attr('src',user.avatarUrl);
            tempGrid.find('.userName').eq(0).text(user.login);
            tempGrid.find('.createTime').eq(0).text("created at "+user.createAt);
            tempGrid.find('.updateTime').eq(0).text("updated at "+user.updateAt);
            tempGrid.find('.repo').eq(0).text(user.publicRepo);
            tempGrid.find('.following').eq(0).text(user.following);
            tempGrid.find('.followed').eq(0).text(user.follower);
            tempGrid.find('.userDetail').eq(0).attr('href','userDetail.html?userName='+user.login);
            var userCherish = tempGrid.find('.userCherish').eq(0);

            unCherishUser(userCherish,user.login);
            _this.gridsFather.append(tempGrid);

            if(i%2==1){
                _this.gridsFather.append(_this.clear.clone(true));
                _this.gridsFather.append(_this.hr.clone(true));
            }
        });

    }
};

function unCherishRepo(obj,owner_name,repo_name){
    obj.unbind('click').click(function(){
        $.ajax({
            type:'GET',
            url:'/repo/unstar',
            data:{ownername:owner_name,reponame:repo_name},
            success:function(ok){
                if(ok){
                    $.ajax({
                        type:'GET',
                        url:'/favorite/repoPage',
                        success:function(total){
                            if(total>=currentRepoPage){
                                jumpRepoPage(currentRepoPage);
                            }else{
                                $.jqPaginator('#pagination1', {
                                    totalPages: total,
                                    visiblePages: 8,
                                    currentPage: total,
                                    onPageChange: function (current) {
                                        currentRepoPage = current;
                                        jumpRepoPage(current);
                                    }
                                });
                            }
                        }
                    })
                }else{
                    console.log("Do you login?");
                }
            }
        })

    })
};

function unCherishUser(obj,user_name){
    obj.unbind('click').click(function(){
        $.ajax({
            type:'GET',
            url:'/user/unstar',
            data:{username:user_name},
            success:function(ok){
                if(ok){
                    $.ajax({
                        type:'GET',
                        url:'/favorite/userPage',
                        success:function(total){
                            if(currentUserPage<=total){
                                jumpUserPage(currentUserPage);
                            }else{
                                $.jqPaginator('#paginationUser', {
                                    totalPages: total,
                                    visiblePages: 8,
                                    currentPage: total,
                                    onPageChange: function (current) {
                                        currentUserPage = current;
                                        jumpUserPage(current);
                                    }

                                });
                            }
                        }
                    })
                }else{
                    console.log("Do you really login ?");
                }
            }
        })
    })
}
