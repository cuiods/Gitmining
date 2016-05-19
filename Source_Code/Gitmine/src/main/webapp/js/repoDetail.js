/**
 * Created by darxan on 2016/5/19.
 */
var location_port = 'http://localhost:8080';
$(document).ready(
    function () {
        var fullName = location.search;
        var index = location.search.indexOf("?");

        if(index==-1){
            wrongUrl();
        }else{
            var params = fullName.substr(index+1);
            var names = params.split("/");

            if(names.length<2){
                wrongUrl();
            }else{
                var url = location_port+"/repo/"+params;
                console.log(url);
                $.get(url,function (object) {
                    setBasicInfo(object.basicInfo);
                });
            }

        }
    }
);

function setBasicInfo(basicInfo) {
    var mainInfo = $('#main>#single-page>#single-page>#container> #main-info');
    var numbers = mainInfo.find("#numbers");
    var descriptions = mainInfo.find("#descriptions");
    var header = mainInfo.find("#ownerAvatarUrl");

    descriptions.children("reponame").text(basicInfo.reponame);
    descriptions.children("ownerName").text(basicInfo.ownerName);
    descriptions.children("description").text(basicInfo.description);
    descriptions.children("language").text(basicInfo.language);
    descriptions.children("url").text(basicInfo.url);

    numbers.children("#numStar").text(basicInfo.numStar);
    numbers.children("#numFork").text(basicInfo.numFork);
    numbers.children("#numSubscriber").text(basicInfo.numSubscriber);
    numbers.children("#size").text(basicInfo.size);

    header.attr("src",basicInfo.ownerAvatarUrl);
}

function wrongUrl() {
    alert("wrong url");
}