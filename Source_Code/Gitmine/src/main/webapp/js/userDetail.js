/**
 * Created by yyy on 2016/5/18.
 */

$(document).ready(function() {
    var userName=document.getElementById("userName");
    userName.innerHTML=getQueryString('userName');
    $().UItoTop({ easingType: 'easeOutQuart' });
});

//to get the parameter transfered by url
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


