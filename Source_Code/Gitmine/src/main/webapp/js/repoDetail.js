/**
 * Created by darxan on 2016/5/19.
 */
var location_port = '';
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
                    setRecommend(object.relatedRepo);
                });
            }

        }
    }
);

function setBasicInfo(basicInfo) {
    var numbers = $("#numbers");
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

function setRecommend(recommend) {
    console.log(recommend);
    var fatherNode = $('#recommendRepos');
    var recommendNode = fatherNode.find('#recommend-item');
    fatherNode.empty();
    $.each(recommend,function (i,item) {
        var newNode = recommendNode.clone();
        newNode.find("#recommend_ownerAvatarUrl").attr('src',item.ownerAvatarUrl);
        newNode.find("#recommend_updateAt").attr('src',item.updateAt);
        newNode.find("#recommend_description").attr('src',item.description);
        newNode.find("#recommend_numStar").text(item.numStar);
        newNode.find("#recommend_numFork").text(item.numFork);
        newNode.find("#recommend_numSubscriber").text(item.numSubscriber);

        fatherNode.add(newNode);
    });
}

function wrongUrl() {
    alert("wrong url");
}