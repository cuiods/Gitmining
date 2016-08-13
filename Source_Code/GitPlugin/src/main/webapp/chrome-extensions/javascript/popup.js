
/**
 * Created by Harry on 2016/8/9.
 */
function setNotify() {
    
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
                    $(".news-prompt").remove();
                    for (var i=0;i<entityList.length;i++){
                        var entity = entityList[i];
                        $(".news-list").append("<li class='news-item'><a href='"+entity.sourceUrl+"' rel='external' target='_blank'>"+entity.summary+"</a></li>");
                    }
                }
            }
        }
    );
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
                    $(".comments-prompt").remove();
                    for (var i=0;i<commentList.length;i++) {
                        var entity = commentList[i];
                        $(".comments-list").append("<li class='comment-item'>"+entity.comment+"</li>");
                    }
                }
            }
        }
    );
}

$(function () {
    //console.log("popup.js load");
    chrome.tabs.query({
        active: true,
        currentWindow: true
    }, function (tabs) {
        var url = tabs[0].url;
        var tabId = tabs[0].id;
        console.log("tabs.query success");
        console.log(url);
        if (new RegExp(/github.com\/[\w\-]+\/[\w\-]+/, "i").test(url)){
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