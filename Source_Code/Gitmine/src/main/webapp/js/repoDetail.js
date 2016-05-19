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
    console.log(basicInfo);
    var mainInfo = $('#main>#single-page>#single-page>#container> #main-info');

    console.log( mainInfo.find("#numbers"));
    var numbers = $("#numbers");
    console.log( $("#descriptions"));
    var descriptions = $("#descriptions");
    var header = $("#ownerAvatarUrl");


    descriptions.find("#reponame").text(basicInfo.reponame);
    descriptions.find("#ownerName").text(basicInfo.ownerName);
    descriptions.find("#description").text(basicInfo.description);
    descriptions.find("#language").text(basicInfo.language);
    descriptions.find("#createAt").text(basicInfo.createAt);
    descriptions.find("#updateAt").text(basicInfo.updateAt);
    descriptions.find("#url").text(basicInfo.url);
    descriptions.find("#url").attr("href",basicInfo.url);

    numbers.find("#numStar").text(basicInfo.numStar);
    numbers.find("#numFork").text(basicInfo.numFork);
    numbers.find("#numSubscriber").text(basicInfo.numSubscriber);
    numbers.find("#size").text(basicInfo.size);

    header.attr("src",basicInfo.ownerAvatarUrl);
}

function wrongUrl() {
    alert("wrong url");
}