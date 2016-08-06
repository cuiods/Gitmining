/**
 * Created by Harry on 2016/8/5.
 */
var watchCompare = -1.0;
var starCompare = -1.0;
var forkCompare = -1.0;

function getServerAddr() {
    return "http://127.0.0.1";
}

function getLoadSvg() {
    return '<div class="loading_icon"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"width="40px" height="40px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve"> <path fill="#000" d="M25.251,6.461c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615V6.461z"> <animateTransform attributeType="xml"attributeName="transform"type="rotate"from="0 25 25"to="360 25 25"dur="0.6s"repeatCount="indefinite"/> </path> </svg></div>>';
}

function addPopupToPage() {
    var repohead = $(".repohead-details-container");
    //this is not a repository!!!
    if (repohead.length == 0) {
        return;
    }
    //the page is a github repository
    else {
        console.log("find a github repository!");
        var lis = $(".repohead-details-container ul.pagehead-actions li");
        var watch = lis[0];
        var star = lis[1];
        var fork = lis[2];
        $(watch).hover(
            function (e) {
                $("<div class='popup' id='watch_tip'> <div>watch count beat</div></div>")
                    .css("top", e.pageY + 20)
                    .css("left", e.pageX)
                    .appendTo("body");
                $(getLoadSvg()).appendTo($(".popup"));
                console.log("watch type: "+ watch);
                var watch_count = watch.getElementsByClassName("social-count")[0].innerHTML;
                $.ajax(
                    {
                        url: getServerAddr()+"/compare/watch",
                        data: {
                            num: watch_count
                        },
                        type: "GET",
                        dataType: "json",
                        success: function(rateData){
                            $(".loading_icon").innerHTML = rateData;
                        }
                    }
                )
            },
            function () {
                $("#watch_tip").remove();
            }
        );
        $(star).hover(
            function (e) {
                $("<div class='popup' id='star_tip'> <div>star count beat</div></div>")
                    .css("top", e.pageY + 20)
                    .css("left", e.pageX)
                    .appendTo("body");
                $(getLoadSvg()).appendTo($(".popup"));
                var star_count = star.getElementsByClassName("social-count")[0].innerHTML;
                $.ajax(
                    {
                        url: getServerAddr()+"/compare/watch",
                        data: {
                            num: star_count
                        },
                        type: "GET",
                        dataType: "json",
                        success: function(rateData){
                            $(".loading_icon").innerHTML = rateData;
                        }
                    }
                )
            },
            function () {
                $("#star_tip").remove();
            }

        );
        $(fork).hover(
            function (e) {
                $("<div class='popup' id='fork_tip'> <div>fork count beat</div></div>")
                    .css("top", e.pageY + 20)
                    .css("left", e.pageX)
                    .appendTo("body");
                $(getLoadSvg()).appendTo($(".popup"));
                var fork_count = fork.getElementsByClassName("social-count")[0].innerHTML;
                $.ajax(
                    {
                        url: getServerAddr()+"/compare/watch/watch",
                        data: {
                            num: fork_count
                        },
                        type: "GET",
                        dataType: "json",
                        success: function(rateData){
                            $(".loading_icon").innerHTML = rateData;
                        }
                    }
                )
            },
            function () {
                $("#fork_tip").remove();
            }
        );

    }
}

$(function () {
    addPopupToPage();
});