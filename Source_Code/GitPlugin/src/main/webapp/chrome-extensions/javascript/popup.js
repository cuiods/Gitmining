
/**
 * Created by Harry on 2016/8/9.
 */
function setNotify() {
    chrome.storage.local.get(null, function (items) {
        if ((items!=null)&&(!jQuery.isEmptyObject(items))) {
            for (var itemKey in items) {
                var itemBody = items[itemKey];
                if (itemBody.isUpdate == true) {
                    $("#nofity-prompt").remove();
                    $("#notification-list").append('<a class="list-group-item notify-item" href="#"> New news about '+itemKey+'</a>');
                    $(".notify-item:last").data("repoId", itemKey);
                }
            }
            //todo add on click listener
            console.log("prepare to add click handler");
            $(".notify-item").click(function () {
                removeNotify($(this).data("repoId"));
            });
        }
    });
}

function removeNotify(itemKey) {
    console.log("enter remove notify");
    console.log(itemKey);
    var itemBody = {
        isUpdate: false,
        time: new Date().getTime()
    };
    var obj = {};
    obj[itemKey] = itemBody;
    chrome.storage.local.set(obj);
    var newsNumStr = chrome.browserAction.getBadgeText({}, function (result) {
        var newsNum = 1;
        if ((newsNumStr!=null)&&(newsNumStr.length>0)){
            newsNum = parseInt(newsNumStr);
        }
        newsNum = newsNum-1;
        var newsNumStr = "";
        if (newsNum > 0 ){
            newsNumStr = newsNum.toString();
        }
        chrome.browserAction.setBadgeText({
            text: newsNumStr});
    });
    //todo open new tab
    chrome.tabs.create({url: "https://github.com/"+itemKey}, function (tab) {
        console.log("new tab is created");
    })
}

function setNewsMotion(owner, name) {
    console.log("enter news set motion");
    $.ajax({
        type: "GET",
        url: getServerIP()+"/meaning/news/motion",
        data: {
            owner: owner,
            name: name
        },
        success: function (number) {
            console.log("success");
            $("#news-index>.index-value").text(number);
        }
    });
}

function setNews(owner, name) {
    $.ajax(
        {
            type: "GET",
            url: getServerIP()+"/info/news",
            data: {
                owner: owner,
                name: name,
                page: 1
            },
            success: function (news) {
                var entityList = news.entities;
                if (entityList.length > 0) {
                    $("#news-prompt").remove();
                    $("#news-list").before("<div class='positive-index' id='news-index'>Positive index: <span class='index-value'>loading...</span></div>");
                    //set the positive number of news
                    setNewsMotion(owner, name);
                    for (var i=0;i<entityList.length;i++){
                        var entity = entityList[i];
                        $("#news-list").append("<a class='list-group-item' href='http://www.oschina.net/news/"+entity.id+"' rel='external' target='_blank'>"+entity.title+"</a>");
                    }

                }
            }
        }
    );
}

function setCommentMotion(owner, name) {
    $.ajax({
        type: "GET",
        url: getServerIP()+"/meaning/comment/motion",
        data: {
            owner: owner,
            name: name
        },
        success: function (number) {
            $("#comments-index>.index-value").text(number);
        }
    });
}

function setComments(owner, name) {
    $.ajax(
        {
            type: "GET",
            url: getServerIP()+"/info/comments",
            data: {
                owner:owner,
                name: name,
                page: 1
            },
            success: function (comments) {
                var commentList = comments.entities;
                if (commentList.length > 0) {
                    console.log("comment legth: "+commentList.length);
                    $("#comments-prompt").remove();
                    $("#comments-list").before("<div class='positive-index' id='comments-index'>Positive index: <span class='index-value'>  loading...</span></div>");
                    setCommentMotion(owner, name);
                    for (var i=0;i<commentList.length;i++) {
                        var entity = commentList[i];
                        $("#comments-list").append("<li class='list-group-item'>"+entity.comment+"</li>");
                    }
                }
            }
        }
    );
}

function getAuth() {
    return '?client_id=a933b93042f032033396&client_secret=39ce7e6532fed6803bcb4ca56b4b66779a89c8b7';
}

function setLanguageChart(owner, repo) {
    console.log("enter set language chart");
    var chart = {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    };
    var title = {
        text: 'language usage of this repository'
    };
    var tooltip = {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    };
    var plotOptions = {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: false
            },
            showInLegend: true
        }
    };

    var giturl = 'https://api.github.com/repos/'+owner+'/'+repo+'/languages'+getAuth();
    $.ajax({
        type: "GET",
        url: giturl,
        data: {},
        success: function (languageObj) {

            var dataArr = [];
            for (var key in languageObj) {
                dataArr.push({
                    name: key.toString(),
                    y: languageObj[key]
                });
            }

            var series = [
                {
                    name: 'language',
                    colorByPoint: true,
                    data: dataArr
                }
            ];
            $("#language-chart").highcharts({
                chart: chart,
                title: title,
                tooltip: tooltip,
                series: series,
                plotOptions: plotOptions
            });
        }
        
    });


}

$(function () {
    //console.log("popup.js load");
    setNotify();

    chrome.tabs.query({
        active: true,
        currentWindow: true
    }, function (tabs) {
        var url = tabs[0].url;
        var tabId = tabs[0].id;
        console.log("tabs.query success");
        console.log(url);
        if (new RegExp(/https:\/\/github.com\/[\w\-]+\/[\w\-]+/, "i").test(url)){
            console.log("match url");
            var port = chrome.tabs.connect(tabId, {name: "query owner and reponame"});
            port.onDisconnect.addListener(function (event) {
                //the other side doesn't listener for the onConnection
                console.log("onDisconnect: "+event);
                //todo

            });
            port.onMessage.addListener(function (msg) {
                if (msg.theme == "back_owner_and_repo") {
                    console.log("owner and reponame is back");
                    var owner = msg.owner;
                    var reponame = msg.name;
                    setNews(owner, reponame);
                    setComments(owner, reponame);
                    setLanguageChart(owner, reponame);
                }
                else {
                    //todo
                }
            });
            port.postMessage({
                theme:"query_owner_and_repo"
            });
        }
        else {
            console.log("not match url");
        }
    });
});
