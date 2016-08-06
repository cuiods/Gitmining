/**
 * Created by Harry on 2016/8/5.
 */
function addPopupToPage() {
    var repohead = $(".repohead-details-container");
    //this is not a repository!!!
    if (repohead.length == 0) {
        return;
    }
    //the page is a github repository
    else {
        console.log("find a github repository!");
        var watch = $(".repohead-details-container ul.pagehead-actions li:nth-child(1)");
        var star = $(".repohead-details-container ul.pagehead-actions li:nth-child(2)");
        var fork = $(".repohead-details-container ul.pagehead-actions li:nth-child(3)");
        watch.hover(
            function (e) {
                $("<div class='popup' id='watch_tip'> <div>Hello guys !</div> <svg height='50' width='100'> <path d='M0 50 L40 0 L65 0 Z' /> Sorry, your browser does not support inline SVG. </svg> </div>")
                    .css("top", e.pageX + 20)
                    .css("left", e.pageY + 10)
                    .appendTo(watch);
            },
            function () {
                $("#watch_tip").remove();
            }
        );
        star.hover(
            function (e) {
                $("<div class='popup' id='star_tip'> <div>Hello guys !</div> <svg height='50' width='100'> <path d='M0 50 L40 0 L65 0 Z' /> Sorry, your browser does not support inline SVG. </svg> </div>")
                    .css("top", e.pageX + 20)
                    .css("left", e.pageY + 10)
                    .appendTo(star);
            },
            function () {
                $("#star_tip").remove();
            }
        );
        fork.hover(
            function (e) {
                $("<div class='popup' id='fork_tip'> <div>Hello guys !</div> <svg height='50' width='100'> <path d='M0 50 L40 0 L65 0 Z' /> Sorry, your browser does not support inline SVG. </svg> </div>")
                    .css("top", e.pageX + 20)
                    .css("left", e.pageY + 10)
                    .appendTo(fork);
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