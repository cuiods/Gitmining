/**
 * Created by Harry on 2016/8/4.
 */

function getServerIP() {
    return "http://127.0.0.1";
}

function compareWatch(watchCount, port) {

    chrome.storage.local.get("watch_compare", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var compare_obj = items["watch_compare"];
            var ratio = compare_obj[watchCount.toString()];
            if (ratio!=null){
                port.postMessage({
                    theme: "back_compare",
                    dataType: "watch",
                    ratio: ratio
                });
            }
            else {
                queryWatch(watchCount, port);
            }
        }
        else {
            queryWatch(watchCount, port);
        }
    });

}

function queryWatch(watchCount, port) {
    $.ajax(
        {
            type: "GET",
            url: getServerIP()+"/compare/watch",
            data: {num: watchCount},
            success: function (ratio) {
                port.postMessage({
                    theme: "back_compare",
                    dataType: "watch",
                    ratio: ratio
                });
                chrome.storage.local.get("watch_compare", function (items) {
                    var compare_obj;
                    if (jQuery.isEmptyObject(items)){
                        compare_obj = {};
                    }
                    else {
                        compare_obj = items["watch_compare"];
                    }
                    compare_obj[watchCount.toString()] = ratio;
                    var obj = {"watch_compare": compare_obj};
                    chrome.storage.local.set(obj);
                });
            }
        }
    );
}

function compareStar(starCount, port) {
    chrome.storage.local.get("star_compare", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var compare_obj = items["star_compare"];
            var ratio = compare_obj[starCount.toString()];
            if (ratio!=null){
                port.postMessage({
                    theme: "back_compare",
                    dataType: "star",
                    ratio: ratio
                });
            }
            else {
                queryStar(starCount, port);
            }
        }
        else {
            queryStar(starCount, port);
        }
    });
}

function queryStar(starCount, port) {
    $.ajax(
        {
            type: "GET",
            url: getServerIP()+"/compare/star",
            data: {num: starCount},
            success: function (ratio) {
                port.postMessage({
                    theme: "back_compare",
                    dataType: "star",
                    ratio: ratio
                });
                chrome.storage.local.get("star_compare", function (items) {
                    var compare_obj;
                    if (jQuery.isEmptyObject(items)){
                        compare_obj = {};
                    }
                    else {
                        compare_obj = items["star_compare"];
                    }
                    compare_obj[starCount.toString()] = ratio;
                    var obj = {"star_compare": compare_obj};
                    chrome.storage.local.set(obj);
                });
            }
        }
    );
}

function compareFork(forkCount, port) {
    chrome.storage.local.get("fork_compare", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var compare_obj = items["fork_compare"];
            var ratio = compare_obj[forkCount.toString()];
            if (ratio!=null){
                port.postMessage({
                    theme: "back_compare",
                    dataType: "fork",
                    ratio: ratio
                });
            }
            else {
                queryFork(forkCount, port);
            }
        }
        else {
            queryFork(forkCount, port);
        }
    });
}

function queryFork(forkCount, port) {
    $.ajax(
        {
            type: "GET",
            url: getServerIP()+"/compare/fork",
            data: {num: forkCount},
            success: function (ratio) {
                port.postMessage({
                    theme: "back_compare",
                    dataType: "fork",
                    ratio: ratio
                });
                chrome.storage.local.get("fork_compare", function (items) {
                    var compare_obj;
                    if (jQuery.isEmptyObject(items)){
                        compare_obj = {};
                    }
                    else {
                        compare_obj = items["fork_compare"];
                    }
                    compare_obj[forkCount.toString()] = ratio;
                    var obj = {"fork_compare": compare_obj};
                    chrome.storage.local.set(obj);
                });
            }
        }
    );
}

function queryNewNews() {
    // var unReadCount = 0;
    chrome.storage.sync.get(null, function (items) {
        // console.log(items);
        for (var itemKey in items) {
            var itemBody = items[itemKey];
            if (!itemBody.isUpdate) {
                var arr = itemKey.split("/");
                var owner = arr[0];
                var name = arr[1];
                $.ajax({
                    type: "GET",
                    url: getServerIP()+"/info/newstime",
                    data: {owner: owner, name: name},
                    success: function (lastTime) {
                        if (lastTime > itemBody.time) {
                            itemBody.isUpdate = true;
                            var obj = {};
                            obj[itemKey] = itemBody;
                            chrome.storage.sync.set(obj);
                            var newsNumStr = chrome.browserAction.getBadgeText({}, function (result) {
                                var newsNum = 0;
                                if ((newsNumStr!=null)&&(newsNumStr.length>0)){
                                    newsNum = parseInt(newsNumStr);
                                }
                                newsNum = newsNum+1;
                                chrome.browserAction.setBadgeText({
                                    text: newsNum.toString()});
                            });
                        }
                        else {
                            console.log("item time less than ..."+lastTime);
                        }
                    }
                });
            }
        }
    })
}

//建立一个缓冲区用于存储一些运算得到的ratio，避免重复计算
//todo

//监听来自content scripts的连接请求
chrome.runtime.onConnect.addListener(function (port) {
    console.log("port " + port.name + " connet to background.");
    console.log("the sender url is: " + port.sender.url);
    port.onMessage.addListener(function (msg) { //msg is a json

        if (msg.theme == "compare") {
            compareWatch(msg.watch, port);
            compareStar(msg.star, port);
            compareFork(msg.fork, port);
        }

    });
});

chrome.tabs.onUpdated.addListener(function (tabId, info, tab) {
    if ( (info.status == "complete") ) {
        console.log("test tabs.onUpdated");
        chrome.tabs.sendMessage(
            tabId,
            {theme: "reinsert tip window"}
        );
    }
});

// console.log("set alarm start");
//todo set delayInMinutes to 1 min
chrome.alarms.create("gitplugin_alarm", {
    delayInMinutes: 0.3,
    periodInMinutes: 3
});
// console.log("set alarm end");

chrome.alarms.onAlarm.addListener(function (alarm) {
    // console.log("catch alarm event");
    if (alarm.name == "gitplugin_alarm") {
        queryNewNews();
    }
});

// chrome.storage.sync.get("firebug/firebug", function (items) {
//     console.log("get firebug/firebug");
//     console.log(items);
// })

// chrome.webNavigation.onHistoryStateUpdated.addListener(function (details) {
//     console.log("prepare to reinsert content script");
//     chrome.tabs.executeScript(
//         details.tabId,
//         {
//             file: "javascript/content_scripts.js"
//         },
//         function () {
//             console.log("reinsert content script success!");
//         }
//     );
//     chrome.tabs.insertCSS(
//         details.tabId,
//         {
//             file: "css/tip_window.css"
//         },
//         function () {
//             console.log("reinsert css success!");
//         }
//     );
// });

