/**
 * Created by yyy on 2016/5/18.
 */

var locationPort = 'http://localhost'
var UserList={
    init:function(){
        this.gridsFather = $("#listStart");
        this.lastGrid = $(".news-grid").eq(0);
        this.clear = $("<div class=\"clearfix\"> </div>" );
    },
    updateData:function(userlist){
        this.gridsFather.empty();
        var _this = this;
        $.each(userlist,function(i,user){
            var tempGrid = _this.lastGrid.clone(true);
            var userName = tempGrid.find(".userName").eq(0);
            userName.text(user.name);
            userName.attr('href','userDetail.html?userName='+user.name);

            var imageUrl = tempGrid.find(".imageUrl").eq(0);
            imageUrl.attr('href','userDetail.html?userName='+user.name);
            var image = tempGrid.find(".header_user").eq(0);
            image.attr('src',user.avatarUrl);

            tempGrid.find('.create').eq(0).text('create at:  '+user.createAt);
            tempGrid.find('.update').eq(0).text('update at:  '+user.updateAt);
            tempGrid.find('.repo').eq(0).text(user.publicRepo);
            tempGrid.find('.following').eq(0).text(user.following);
            tempGrid.find('.followed').eq(0).text(user.follower);

            _this.gridsFather.append(tempGrid);
            if(i%4==3){
                _this.gridsFather.append(_this.clear.clone(true));
            }

        })
    },
};

$(document).ready(
    function () {
        UserList.init();
        var url = '/user/list'+"?pageNum=1";
        $.get(url,function (object) {

            UserList.updateData(object.userList);
        });
        // $.ajax({
        //     type:'GET',
        //     url:'/user/list',
        //     data:{pageNum:'1'};
        //     success:function(data){
        //         UserList.updateData(data.repoList);
        //     }
        //     error:function(){
        //         alert("unknown error in controller !");
        //     }
        // })
    }
);

function sort(obj){
    var originCheck = findCheckedSortType();
    var obj = $(obj);
    if(originCheck.attr("sortType")==obj.attr('sortType')){


        if(obj.attr("isReverse")=="true"){
            obj.attr("isReverse" , "false");
            obj.children("span").attr("class", "glyphicon glyphicon-arrow-down");
        }else{
            obj.attr("isReverse" , "true");
            obj.children("span").attr("class",  "glyphicon glyphicon-arrow-up");
        };

    }else{
        originCheck.attr("ischecked" ,"false");
        obj.attr("ischecked" , "true");
    }
    search();
}

//used to ensure which sortType is choosed
function findCheckedSortType() {
    var type = undefined;
    $('.nav>li>a').each(
        function (i,n){
            var jq =  $(n);
            if(jq.attr("ischecked")=="true"){
                type =  jq;
            }
        }
    );
    if(type==undefined){
        type = $('.nav>li>a');
    }
    return type;
}

function search(){
    var keyword = $('#keyword');
    var sortElement= findCheckedSortType();
    var sortBy = sortElement.attr("sortType");
    var isReverse = sortElement.attr("isReverse");
    var page = 1;
    $.ajax({
        type:'GET',
        url:'/user/search',
        data:{
            keyword:keyword,
            sortType:sortBy,
            reverse:isReverse,
            pageNum:page,
        },
        success:function(searchList){
            UserList.updateData(searchList);
        }
    })
    
    
}