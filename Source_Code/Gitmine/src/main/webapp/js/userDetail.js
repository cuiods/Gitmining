/**
 * Created by yyy on 2016/5/18.
 */


$(document).ready(function() {
    var userName=document.getElementById("userName");
    userName.innerHTML=getQueryString('userName');
    $().UItoTop({ easingType: 'easeOutQuart' });

    $.ajax({
        type:'GET',
        url:'/user/'+userName.innerHTML,
        success:function(data){
            document.getElementById("header_url").src=data.basicInfo.avatarUrl;
            document.getElementById("userDetail_repos").innerHTML=data.basicInfo.publicRepo;
            document.getElementById("userDetail_following").innerHTML=data.basicInfo.following;
            document.getElementById("userDetail_followed").innerHTML=data.basicInfo.follower;
            document.getElementById("userDetail_gists").innerHTML=data.basicInfo.publicGist;
            document.getElementById("blog").innerHTML=data.basicInfo.blog;
            document.getElementById("email").innerHTML=data.basicInfo.email;
            document.getElementById("company").innerHTML=data.basicInfo.company;
            document.getElementById("location").innerHTML=data.basicInfo.location;
            document.getElementById("bio").innerHTML=data.basicInfo.bio;
            document.getElementById("createTime").innerHTML=data.basicInfo.createAt;
            document.getElementById("updateTime").innerHTML=data.basicInfo.updateAt;
            var relatedRepo = document.getElementById("relatedRepo");
            $.each(data.relatedRepo,function(){
                var node = document.createElement("a");
                node.href = "userDetail.html?userName="+userName;
                node.innerHTML = this.ownerName+"/"+this.reponame;
                node.class="list-group-item";
                relatedRepo.appendChild(node);
            })


        },
        error:function(){
            alert("controller return result unknown error!");
        }
    })


});


//to cherish this user
function cherish(){

};

//to get the parameter transfered by url
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


