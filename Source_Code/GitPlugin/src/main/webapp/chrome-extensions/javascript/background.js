/**
 * Created by Harry on 2016/8/4.
 */

function getServerIP() {
    return "http://127.0.0.1";
}

function compareWatch(watchCount, port) {
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
            }
        }
    );
}

function compareStar(starCount, port) {
    $.ajax({
        type: "GET",
        url: getServerIP()+"/compare/star",
        data: {num: starCount},
        success: function (ratio) {
            port.postMessage({
                theme: "back_compare",
                dataType: "star",
                ratio: ratio
            });
        }
    });
}

function compareFork(forkCount, port) {
    $.ajax({
        type: "GET",
        url: getServerIP()+"/compare/watch",
        data: {num: forkCount},
        success: function (ratio) {
            port.postMessage({
                theme: "back_compare",
                dataType: "fork",
                ratio: ratio
            });
        }
    });
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

