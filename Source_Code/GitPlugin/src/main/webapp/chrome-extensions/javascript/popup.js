
/**
 * Created by Harry on 2016/8/9.
 */
function setNotify() {
    chrome.storage.sync.get(null, function (items) {
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
            //console.log("prepare to add click handler");
            $(".notify-item").click(function () {
                removeNotify($(this).data("repoId"));
            });
        }
    });
}

function removeNotify(itemKey) {
    //console.log("enter remove notify");
    //console.log(itemKey);
    var itemBody = {
        isUpdate: false,
        time: new Date().getTime()
    };
    var obj = {};
    obj[itemKey] = itemBody;
    chrome.storage.sync.set(obj);
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
    chrome.tabs.create({url: "https://github.com/"+itemKey}, function (tab) {
        //console.log("new tab is created");
    })
}

function queryNewsMotion(owner, name) {
    $.ajax({
        type: "GET",
        url: getServerIP()+"/meaning/news/motion",
        data: {
            owner: owner,
            name: name
        },
        success: function (number) {
            // //console.log("success");
            // //console.log(number);
            // $("#news-index>.index-value").text(number);
            var positivePercent = number.toFixed(4)*100+""+"%";
            var padLeft = number*350;
            $("#positivity").css("padding-left",padLeft+""+"px");
            $("#positivity").text(positivePercent);
            // //console.log(positivePercent);

            chrome.storage.local.get("news_motion", function (items) {
                var motion_obj;
                if (jQuery.isEmptyObject(items)){
                    motion_obj = {};
                }
                else {
                    motion_obj = items["news_motion"];
                }
                var id = owner+"/"+name;
                motion_obj[id] = number;
                var obj = {"news_motion": motion_obj};
                chrome.storage.local.set(obj);
            });
        },
        error:function (){
            $("#positivity").css("padding-left",0.65*350+""+"px");
            //console.log("positivity get wrong!");
        }
    });
}

function setNewsMotion(owner, name) {
    //console.log("enter news set motion");

    chrome.storage.local.get("news_motion", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var motion_map = items["news_motion"];
            var key = owner+"/"+name;
            var motion_val = motion_map[key];
            if (motion_val!=null){
                // //console.log("motion_val is: ");
                // //console.log(motion_val);
                var positivePercent = motion_val.toFixed(4)*100+""+"%";
                var padLeft = motion_val*350;
                $("#positivity").css("padding-left",padLeft+""+"px");
                $("#positivity").text(positivePercent);
            }
            else {
                queryNewsMotion(owner, name);
            }
        }
        else {
            queryNewsMotion(owner, name);
        }
    });

}

function setNewsKeyword(owner, name) {
    chrome.storage.local.get("news_keyword", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var keyword_obj = items["news_keyword"];
            var id = owner+"/"+name;
            var keyword_arr = keyword_obj[id];
            if (keyword_arr!=null && keyword_arr.length>0){
                var html = "Keywords:    ";
                for (var i = 0; i<keyword_arr.length;i++){
                    html = html+"<span class='label label-info'>"+keyword_arr[i]+"</span>";
                }
                $("#news-tag").html(html);
            }
            else {
                queryNewsKeyword(owner, name);
            }
        }
        else{
            queryNewsKeyword(owner, name);
        }
    });
}

function queryNewsKeyword(owner, name) {
    $.ajax(
        {
            type: "GET",
            url: getServerIP()+"/meaning/news/keyword",
            data: {
                owner: owner,
                name: name
            },
            success: function (keywords) {
                // //console.log("get news keywords success!!!!!!!!!!!");
                var html = "Keywords:    ";
                for (var i = 0; i<keywords.length;i++){
                    html = html+"<span class='label label-info'>"+keywords[i]+"</span>";
                }
                $("#news-tag").html(html);

                chrome.storage.local.get("news_keyword", function (items) {
                    var keyword_obj;
                    if (jQuery.isEmptyObject(items)){
                        keyword_obj = {};
                    }
                    else{
                        keyword_obj = items["news_keyword"];
                    }
                    var id = owner+"/"+name;
                    keyword_obj[id] = keywords;
                    var obj = {"news_keyword": keyword_obj};
                    chrome.storage.local.set(obj);
                });
            }
        }
    );
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
                    // $("#news-list").before("<div class='positive-index' id='news-index'>Positive index: <span class='index-value'>loading...</span></div>");
                    //set the positive number of news
                    setNewsKeyword(owner, name);

                    $("#news-list").before("<div class='rate-graph' ><span>Positivity:</span><div class='graph-scroller'><em id='positivity' >65.00%</em></div><ul class='graph-desc'><li>worse</li><li>weak</li><li>middle</li><li>good</li><li>best</li></ul><br><br></div>");

                    setNewsMotion(owner, name);
                    for (var i=0;i<entityList.length;i++){
                        var entity = entityList[i];
                        $("#news-list").append("<a class='list-group-item' href='http://www.oschina.net/news/"+entity.id+"' rel='external' target='_blank'>"+entity.title+"</a>");
                    }

                    $("#pageNoNews").text("1");
                    if(entityList.length == 10){
                        $("#newsNext").removeAttr("disabled");
                    }

                    //console.log($("#pageNoNews").var+" "+entityList.length);

                    setNewsPageButton(owner,name,1);
                }
            }
        }
    );
}

function setNewsChange(owner,name,currentPage){
    if(currentPage == 1){
        $("#newsLast").attr("disabled","disabled");
    }else{
        $("#newsLast").removeAttr("disabled");
    }
    $.ajax(
        {
            type:"GET",
            url:getServerIP()+"/info/news",
            data:{
                owner:owner,
                name:name,
                page:currentPage
            },
            success:function(news){
                var entityList = news.entities;

                //console.log("pageNow: "+ currentPage + " "+entityList.length);
                if(entityList.length>0){
                    $("#news-list").remove();
                    $("#news-button").before('<div class="list-group" id="news-list"></div>');
                    for (var i=0;i<entityList.length;i++){
                        var entity = entityList[i];
                        $("#news-list").append("<a class='list-group-item news-item' href='http://www.oschina.net/news/"+entity.id+"' rel='external' target='_blank'>"+entity.title+"</a>");
                    }


                    if(entityList.length==10){
                        $("#newsNext").removeAttr("disabled");
                        setNewsPageButton(owner,name,currentPage);
                    }else{
                        $("#newsNext").attr("disabled","disabled");
                    }


                }
            }
        }
    )
}

function setNewsPageButton(owner,name,currentPage){
    $("#newsLast").unbind("click").click(function(){
        var pageNo = currentPage-1;
        console.log("lastClicked,now is :"+pageNo);
        $("#pageNoNews").text(pageNo);
        setNewsChange(owner,name,pageNo);
    });
    $("#newsNext").unbind("click").click(function(){
        var pageNo = currentPage + 1;
        console.log("nextClicked,now is :"+pageNo);
        $("#pageNoNews").text(pageNo);
        setNewsChange(owner,name,pageNo);
    })
}

function setCommentButton(owner,name,currentPage){
    $("#commentLast").unbind("click").click(function(){
        var pageNo = currentPage-1;
        $("#pageNoComment").text(pageNo);
        setCommentsChange(owner,name,pageNo);
    });
    $("#commentNext").unbind("click").click(function(){
        var pageNo = currentPage + 1;
        $("#pageNoComment").text(pageNo);
        setCommentsChange(owner,name,pageNo);
    })
}

function setCommentsChange(owner,name,currentPage){
    if(currentPage ==1 ){
        $("#commentLast").attr("disabled","disabled");
    }else{
        $("#commentLast").removeAttr("disabled");
    }
    $.ajax(
        {
            type:"GET",
            url:getServerIP()+"/info/comments",
            data:{
                owner:owner,
                name:name,
                page:currentPage,
            },
            success:function(comments){
                var commentsList = comments.entities;
                if(commentsList.length>0){
                    $("#comments-list").remove();
                    $("#comment-button").before('<ul class="list-group" id="comments-list"></ul>');
                    for (var i=0;i<commentsList.length;i++) {
                        var entity = commentsList[i];
                        $("#comments-list").append("<li class='list-group-item'>"+entity.comment+"</li>");
                    }
                    if(commentsList.length==10){
                        $("#commentNext").removeAttr("disabled");
                    }else{
                        $("#commentNext").attr("disabled","disabled");
                    }
                }
            }

        }
    )
}

function queryCommentMotion(owner, name) {
    $.ajax({
        type: "GET",
        url: getServerIP()+"/meaning/comment/motion",
        data: {
            owner: owner,
            name: name
        },
        success: function (number) {
            var positivePercent = number.toFixed(4)*100+""+"%";
            var padLeft = number*350;
            $("#comment_positivity").css("padding-left",padLeft+""+"px");
            $("#comment_positivity").text(positivePercent);

            chrome.storage.local.get("comment_motion", function (items) {
                var motion_obj;
                if (jQuery.isEmptyObject(items)){
                    motion_obj = {};
                }
                else {
                    motion_obj = items["comment_motion"];
                }
                var id = owner+"/"+name;
                motion_obj[id] = number;
                var obj = {"comment_motion": motion_obj};
                chrome.storage.local.set(obj);
            });
        }
    });
}

function setCommentMotion(owner, name) {

    chrome.storage.local.get("comment_motion", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var motion_map = items["comment_motion"];
            var id = owner+"/"+name;
            var motion_val = motion_map[id];
            if (motion_val!=null){
                var positivePercent = motion_val.toFixed(4)*100+""+"%";
                var padLeft = motion_val*350;
                $("#comment_positivity").css("padding-left",padLeft+""+"px");
                $("#comment_positivity").text(positivePercent);
                //console.log("positivity: "+positivePercent+" "+motion_val);
            }
            else {
                queryCommentMotion(owner, name);
            }
        }
        else {
            queryCommentMotion(owner, name);
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
                    //console.log("comment legth: "+commentList.length);
                    $("#comments-prompt").remove();
                    $("#comments-list").before("<div class='rate-graph' ><span>Positivity:</span><div class='graph-scroller'><em id='comment_positivity' >65.00%</em></div><ul class='graph-desc'><li>worse</li><li>weak</li><li>middle</li><li>good</li><li>best</li></ul><br><br></div>");
                    setCommentMotion(owner, name);
                    for (var i=0;i<commentList.length;i++) {
                        var entity = commentList[i];
                        $("#comments-list").append("<li class='list-group-item'>"+entity.comment+"</li>");
                    }
                    $("#pageNoComment").text(1);
                    if(commentList.length == 10){
                        $("#commentNext").removeAttr("disabled");
                    }
                    setCommentButton(owner,name,1);
                }
            }
        }
    );
}

function getAuth() {
    return '?client_id=a933b93042f032033396&client_secret=39ce7e6532fed6803bcb4ca56b4b66779a89c8b7';
}

function setLanguageChart(owner, repo) {
    //console.log("enter set language chart");
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

            $("#language-loader").remove();

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

function setPeoplePopularChart(popularVal, contriVal) {

    $("#popular-loader").remove();
    Highcharts.chart('popular_contributor', {

            chart: {
                type: 'solidgauge',
                marginTop: 50
            },

            title: {
                text: 'Popularity & Contributor Rank',
                style: {
                    fontSize: '18px'
                }
            },

            tooltip: {
                borderWidth: 0,
                backgroundColor: 'none',
                shadow: false,
                style: {
                    fontSize: '16px'
                },
                pointFormat: '{series.name}<br><span style="font-size:2em; color: {point.color}; font-weight: bold">{point.y}%</span>',
                positioner: function (labelWidth) {
                    return {
                        x: 240 - labelWidth / 2,
                        y: 190
                    };
                }
            },

            pane: {
                startAngle: 0,
                endAngle: 360,
                background: [{ // Track for Move
                    outerRadius: '112%',
                    innerRadius: '88%',
                    backgroundColor: Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0.3).get(),
                    borderWidth: 0
                }, { // Track for Exercise
                    outerRadius: '87%',
                    innerRadius: '63%',
                    backgroundColor: Highcharts.Color(Highcharts.getOptions().colors[2]).setOpacity(0.3).get(),
                    borderWidth: 0
                }]
            },

            yAxis: {
                min: 0,
                max: 100,
                lineWidth: 0,
                tickPositions: []
            },

            plotOptions: {
                solidgauge: {
                    borderWidth: '34px',
                    dataLabels: {
                        enabled: false
                    },
                    linecap: 'round',
                    stickyTracking: false
                }
            },

            series: [{
                name: 'Popularity',
                borderColor: Highcharts.getOptions().colors[0],
                data: [{
                    color: Highcharts.getOptions().colors[0],
                    radius: '100%',
                    innerRadius: '100%',
                    y: popularVal
                }]
            }, {
                name: 'Contributor Rank',
                borderColor: Highcharts.getOptions().colors[2],
                data: [{
                    color: Highcharts.getOptions().colors[2],
                    radius: '75%',
                    innerRadius: '75%',
                    y: contriVal
                }]
            }]
        },

        /**
         * In the chart load callback, add icons on top of the circular shapes
         */
        function callback() {

            // Move icon
            this.renderer.path(['M', -8, 0, 'L', 8, 0, 'M', 0, -8, 'L', 8, 0, 0, 8])
                .attr({
                    'stroke': '#303030',
                    'stroke-linecap': 'round',
                    'stroke-linejoin': 'round',
                    'stroke-width': 2,
                    'zIndex': 10
                })
                .translate(190, 26)
                .add(this.series[1].group);

            // Exercise icon
            this.renderer.path(['M', -8, 0, 'L', 8, 0, 'M', 0, -8, 'L', 8, 0, 0, 8, 'M', 8, -8, 'L', 16, 0, 8, 8])
                .attr({
                    'stroke': '#303030',
                    'stroke-linecap': 'round',
                    'stroke-linejoin': 'round',
                    'stroke-width': 2,
                    'zIndex': 10
                })
                .translate(190, 61)
                .add(this.series[1].group);

        });

}

var popular_val;
var contri_val;

function loadPopularAndPeople(owner, name) {
    var id = owner+"/"+name;

    chrome.storage.local.get("popular", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var popular_obj = items["popular"];
            popular_val = popular_obj[id];
            if (popular_val){
                // //console.log("exist popular!!!!!!!!!========================");
                //setPopulrData(popular_val);
                //do nothing
            }
            else{
                queryPopular(owner, name);
            }
        }
        else{
            queryPopular(owner, name);
        }

    });

    chrome.storage.local.get("contributor", function (items) {
        if (!jQuery.isEmptyObject(items)){
            var contri_obj = items["contributor"];
            contri_val = contri_obj[id];
            if (contri_val){
                // //console.log("contributor exist!===================================");
                // setContriData(contri_val);
                //do nothing
            }
            else{
                queryContributor(owner, name);
            }
        }
        else{
            queryContributor(owner, name);
        }
    });

    var intVal = setInterval(function () {
        if (popular_val && contri_val){
            //console.log("enter if for two vals");
            //console.log(popular_val, contri_val);
            setPeoplePopularChart(popular_val, contri_val);
            stopInt(intVal);
        }
    }, 1000);
}

function stopInt(intVal) {
    clearInterval(intVal);
}

function queryContributor(owner, name) {
    // //console.log("query contributor!========================");
    $.ajax({
        type: "GET",
        url: getServerIP()+"/compare/people",
        data: {
            owner: owner,
            name: name
        },
        // async: false,
        success: function (number) {
            // setContriData(number);
            contri_val = number;
            chrome.storage.local.get("contributor", function (items) {
                var contri_obj;
                if (jQuery.isEmptyObject(items)){
                    contri_obj = {};
                }
                else {
                    contri_obj = items["contributor"];
                }
                var id = owner+"/"+name;
                contri_obj[id] = number;
                var obj = {"contributor": contri_obj};
                chrome.storage.local.set(obj);
            });
        },
        error:function (){
            //console.log("compare people get wrong!");
        }
    });
}

function queryPopular(owner, name) {
    //console.log("query popular!===================");
    $.ajax({
        type: "GET",
        url: getServerIP()+"/analyse/popular",
        data: {
            owner: owner,
            name: name
        },
        // async: false,
        success: function (number) {
            popular_val = number*100;
            chrome.storage.local.get("popular", function (items) {
                var popular_obj;
                if (jQuery.isEmptyObject(items)){
                    popular_obj = {};
                }
                else {
                    popular_obj = items["popular"];
                }
                var id = owner+"/"+name;
                popular_obj[id] = number*100;
                var obj = {"popular": popular_obj};
                chrome.storage.local.set(obj);
            });
        },
        error:function (){
            //console.log("popular get wrong!");
        }
    });
}

$(function () {
    ////console.log("popup.js load");
    setNotify();

    chrome.tabs.query({
        active: true,
        currentWindow: true
    }, function (tabs) {
        var url = tabs[0].url;
        var tabId = tabs[0].id;
        //console.log("tabs.query success");
        //console.log(url);
        if (new RegExp(/https:\/\/github.com\/[\w\-]+\/[\w\-]+/, "i").test(url)){
            //console.log("match url");
            var port = chrome.tabs.connect(tabId, {name: "query owner and reponame"});
            port.onDisconnect.addListener(function (event) {
                //the other side doesn't listener for the onConnection
                //console.log("onDisconnect: "+event);
                //todo

            });
            port.onMessage.addListener(function (msg) {
                if (msg.theme == "back_owner_and_repo") {
                    //console.log("owner and reponame is back");
                    var owner = msg.owner;
                    var reponame = msg.name;
                    setNews(owner, reponame);
                    setComments(owner, reponame);
                    setLanguageChart(owner, reponame);
                    loadPopularAndPeople(owner, reponame);
                }
                else {

                }
            });
            port.postMessage({
                theme:"query_owner_and_repo"
            });
        }
        else {
            //console.log("not match url");
            $(".loading_area").remove();
        }
    });
});

