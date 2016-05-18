/**
 * Created by yyy on 2016/5/18.
 */

$(document).ready(function() {
    var userName=document.getElementById("userName");
    userName.innerHTML=getQueryString('userName');
    $().UItoTop({ easingType: 'easeOutQuart' });

    $.ajax({
        type:'POST',
        url:'/user'+userName.innerHTML,
        success:function(data){
            document.getElementById("userDetail_repos").innerHTML=data.publicRepo;
            document.getElementById("userDetail_following").innerHTML=data.following;
            document.getElementById("userDetail_followed").innerHTML=data.follower;
            document.getElementById("userDetail_gists").innerHTML=data.publicGist;
            document.getElementById("blog").innerHTML=data.blog;
            document.getElementById("email").innerHTML=data.email;
            document.getElementById("company").innerHTML=data.company;
            document.getElementById("location").innerHTML=data.location;

        },
        error:function(){
            alert("controller return result unknown error!");
        }
    })



});



//to get the parameter transfered by url
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


