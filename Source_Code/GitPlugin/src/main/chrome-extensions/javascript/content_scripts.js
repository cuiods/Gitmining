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
        $(watch).append("<div class='popup' id='watch_tip'> <div>Hello guys !</div> <svg height='50' width='100'> <path d='M0 50 L40 0 L65 0 Z' /> Sorry, your browser does not support inline SVG. </svg> </div>");
        $(star).append("<div class='popup' id='star_tip'> <div>Hello guys !</div> <svg height='50' width='100'> <path d='M0 50 L40 0 L65 0 Z' /> Sorry, your browser does not support inline SVG. </svg> </div>");
        $(fork).append("<div class='popup' id='fork_tip'> <div>Hello guys !</div> <svg height='50' width='100'> <path d='M0 50 L40 0 L65 0 Z' /> Sorry, your browser does not support inline SVG. </svg> </div>");

    }
}

$(function () {
    addPopupToPage();
});