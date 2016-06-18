/**
 * Created by darxan on 2016/5/15.
 */
var location_port = '';
var descriptionLength = 60;
var descriptionLengthEachLine = 20;
var nameLength = 10;

var RepoList = {

    init: function () {
        this.gridsFather =  $("#news-grids");
        this.lastGrid = this.gridsFather.children(".news-grid").eq(0);
        this.clear = $("<div class=\"clearfix\"> </div>" );
        this.totalPages = 0;
        // this.gridsFather.empty();
    },
    updateData: function (object) {
        this.gridsFather.empty();
        var _this = this;
        _this.checkboxes = new Array(object.length);
        $.each(object.repoList, function (i, n)
        {
            var tempGrid = _this.lastGrid.clone(true);
            var owner = tempGrid.find('.ownerName').eq(0);
            owner.text (n.ownerName.substr(0,nameLength));
            owner.attr ('href','/html/html/userDetail.html?userName='+n.ownerName);

            var repo = tempGrid.find('.reponame').eq(0);
            repo.text (n.reponame);

            var repoHref = '/html/html/repo-detail.html?'+n.ownerName+"/"+n.reponame;
            repo.attr ('href',repoHref);
            tempGrid.find('.ownerAvatarUrl').eq(0).attr  ( 'src',n.ownerAvatarUrl);
            tempGrid.find('.mask').eq(0).attr  ( 'href',repoHref);

            var description = n.description;
            var line1 = description.substr(0,descriptionLengthEachLine);
            var line2 = description.substr(descriptionLengthEachLine,descriptionLengthEachLine);
            if(line2.replace(/(^\s*)|(\s*$)/g, "").length==0){
                description = line1+"<br/><br/>";
            }else {
                description = line1+"<br/>"+line2;
            }
            // if(description.length>descriptionLength){
            //     description = description.substr(0,descriptionLength)+"...";
            // }else if(description.length<descriptionLengthEachLine){
            //     description = description+"<br><br>";
            // }

            console.log(n.isStared);

            var cherish = tempGrid.find('.cherish');
            cherishPresent(n.isStared,cherish);

            tempGrid.find('.repoDescription').eq(0).html (description);
            tempGrid.find('.createAt').eq(0).text (n.createAt);
            tempGrid.find('.updateAt').eq(0).text (n.updateAt);

            tempGrid.find('.numSubscriber').eq(0).text  ( n.numWatcher);
            tempGrid.find('.numFork').eq(0).text  ( n.numFork);
            tempGrid.find('.numStar').eq(0).text   ( n.numStar);

            var cherishRepo = tempGrid.find('.cherish').eq(0);
            onRepoClick_(cherishRepo,n.ownerName,n.reponame,'/repo/star','/repo/unstar');

            _this.gridsFather.append(tempGrid);

            var checkbox = tempGrid.find(".checkbox").eq(0);
            checkbox.attr("name",n.ownerName+"/"+n.reponame);
             _this.checkboxes[i] = (checkbox);
            if(i%4==3){
                _this.gridsFather.append(_this.clear.clone(true));
            }

        });
        
        console.log(_this.checkboxes);
        
        if(object.totalPage>0){

            RepoList.totalPages = object.totalPage;
            $('#paginator').jqPaginator('option', {
                totalPages: object.totalPage,
                currentPage:object.currentPage
            });
        }
    },

};

var RecommendList = {
    init : function () {
        this.recommendsFather = $('#recommends');
        this.recommendReserved = this.recommendsFather.find('.recommend').eq(0);
    },
    generateRecommends : function (objects) {
        var _this = this;
        _this.recommendsFather.empty();
        $.each(objects,function (i,element) {
            var recommendNode = _this.recommendReserved.clone(true);
            var userHref = '/html/html/userDetail.html?userName='+element.ownerName;
            var repoHref = '/html/html/repo-detail.html?'+element.ownerName+"/"+element.reponame;

            var repoName = recommendNode.find(".repoName");
            repoName.attr("href",repoHref);
            repoName.text(element.reponame);

            var owner = recommendNode.find('.ownerName').eq(0);
            owner.text (element.ownerName.substr(0,nameLength));
            owner.attr ('href','/html/html/userDetail.html?userName='+element.ownerName);

            recommendNode.find('.createAt').text (element.createAt);
            recommendNode.find('.updateAt').text (element.updateAt);
            
            recommendNode.find('.ownerAvatarUrl').attr ("src",element.ownerAvatarUrl);
            recommendNode.find('.repoDescription').eq(0).text (element.description);

            recommendNode.find('.numSubscriber').text  ( element.numWatcher);
            recommendNode.find('.numFork').text  ( element.numFork);
            recommendNode.find('.numStar').text   ( element.numStar);

            var cherish_rec = recommendNode.find('.cherish').eq(0);
            cherishPresent(element.isStared,cherish_rec);
            onRepoClick_(cherish_rec,element.ownerName,element.reponame,'/repo/star','/repo/unstar');
            _this.recommendsFather.append(recommendNode);
        });
    },
}


function jumpPage(pageNum) {
    search(pageNum,RepoList.totalPages==0);
}

/**
 * @param obj
 * @param legendArea
 * @param field
 * @param data
 * @returns {*}
 */
function drawRadarChart(obj,legendArea,field,data){
    //radar chart really begins!
    var radar_compare =echarts.init(obj);
    var lineStyle = {
        normal: {
            width: 1,
            opacity: 0.5,
            color:'#F9713C'
        }
    }
    option = {
        backgroundColor: '#161627',
        legend:{
            data: legendArea,
        },
        tooltip:{

        },
        radar:{
            indicator:[
                {name: field[0], max:1},
                {name: field[1], max:1},
                {name: field[2], max:1},
                {name: field[3], max:1},
                {name: field[4], max:1}

            ],
            shape: "circle",
            splitNumber:5,
            name:{
                textStyle:{
                    color:'rgb(238,197,102)'
                }
            },
            splitLine:{
                lineStyle:{
                    color:[
                        'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
                        'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                        'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
                    ].reverse()
                }
            },
            splitArea:{
                show:false
            },
            axisLine:{
                lineStyle:{
                    color:'rgba(238,197,102,0.5)'
                }
            }
        },
        series:[
            {
                name: 'userRadar',
                type: 'radar',
                lineStyle: lineStyle,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: data,
                symbol: 'none',
                // itemStyle: {
                //     normal: {
                //         color: '#F9713C'
                //     }
                // },
                // areaStyle: {
                //     normal: {
                //         opacity: 0.3
                //     }
                // }
            },
        ]
    };

    radar_compare.hideLoading();
    radar_compare.setOption(option);
    return radar_compare;

    //radar chart ends!
}

function jsonToArray(object) {
    var result = new Array(object.length)
    result[0] = object.reponame
    result[1] = object.size
    result[2] = object.description
    result[3] = object.language
    result[4] = object.createAt
    result[5] = object.updateAt
    result[6] = object.numStar
    result[7] = object.numFork
    result[8] = object.numWatcher
    return result;
};


var CoapareTable = {
    init: function () {
        this.compareTableFather = $("#compareTableFather");
        this.compareTableBodeReserve = $(".compareTable").clone(true);
    },
    reInit: function () {
        this.compareTableFather.empty();
        this.compareTableFather.append(this.compareTableBodeReserve.clone(true));
    },
};

function compare() {
    //find the checked boxes, saved in selected array
    var selected = new Array();
    console.log(RepoList.checkboxes);
    for(var index in RepoList.checkboxes){
       
       var item = RepoList.checkboxes[index];
        console.log(index);
        console.log(item);
        if(item.prop("checked")){
           selected.push(item.attr("name"));
       }
    }
    if(selected.length<2){
        alert("no selected");
        return;
    }
    var field = null;
    var compList = new Array();
    var compInfo = new Array();
    CoapareTable.reInit();
    $.each(selected,function (i,repoFullName) {
        var url = "/repo/"+repoFullName;
        $.ajax({url:url,async:false,success:function (info) {
            field = info.radarChart.field;
            compList.push(repoFullName);
            compInfo.push({
                name: repoFullName,
                    value:info.radarChart.value,
            }
            );
            addColCommon($('.compareTable tr'),jsonToArray(info.basicInfo));
        }});
    });
    var obj = document.getElementById('compareRadar');
    drawRadarChart(obj,compList,field,compInfo);
    $('#compareModal').modal();
}


function addColCommon(obj,dataList){
    obj.each(function(j,attr){
        var trHtml = $(this).html();
        if(j==0){
            trHtml += '<th class="newCol">'+dataList[j]+'</th>'
        }else {
            trHtml += '<td class="newCol">'+dataList[j]+'</td>';
        }
        $(this).html(trHtml);
    });
}

function search(page,isKeyChanged) {

    var key = $('#key').val();
    var label_language = findCheckedRadio($('.radio-toolbar-language').eq(0));
    var label_year = findCheckedRadio($('.radio-toolbar-year').eq(0));
    var label_filterType = findCheckedRadio($('.radio-toolbar-filter').eq(0));
    var sort = findCheckedSortType();
    var sort_by = sort.attr("sortType");
    console.log(sort_by);
    var isReverse = sort.attr("isReverse");

    var data = {
        filterType:label_filterType,
        language:label_language,
        createYear:label_year,
        keyword:key,
        pageNum:page,
        sortType:sort_by,
        reverse:isReverse,
        isKeyChanged:isKeyChanged
    }
    console.log("para");
    console.log(data);
    var url = location_port+"/repo/search";
    $.get(url,data,function (object) {
        console.log(object);
        RepoList.totalPages = isKeyChanged?0:RepoList.totalPages;
        RepoList.updateData(object);
    });
}


//$（'element'）.children('')返回的是JQuery对象还是DOM对象??
function findCheckedRadio(toolbar) {
    var radios = toolbar.children('input');
    var label = '';
    radios.each(
        function (i,n) {
            console.log(n.checked)
            if(n.checked==true){
                label =  n.value;
            }
        }
    );
    return label;
}
/**
 * @param obj
 */
function onclickFunction(obj) {
    var originCheck = findCheckedSortType();
    var originCheckType = originCheck.attr("sortType");
    var obj = $(obj);
    var nowCheckTyep = obj.attr('sortType');
    if(originCheckType==nowCheckTyep){

        if(originCheckType!='hobby_match'){
            if(obj.attr("isReverse")=="true"){
                obj.attr("isReverse" , "false");
                obj.children("span").attr("class", "glyphicon glyphicon-arrow-up");
            }else{
                obj.attr("isReverse" , "true");
                obj.children("span").attr("class",  "glyphicon glyphicon-arrow-down");
            };
        }else {
            return;
        };

    }else{
        originCheck.attr("ischecked" ,"false");
        originCheck.children("span").attr("class",  "glyphicon glyphicon-minus");
        
        obj.attr("ischecked" , "true");
        obj.attr("isReverse" , "true");
        obj.children("span").attr("class",  "glyphicon glyphicon-arrow-down");
    }
    search(1,false);
}


// function standardPost(url,args){
//     var body = $(document.body),
//         form = $("<form method='post'></form>"),
//         input;
//     form.attr({"action":url});
//     $.each(args,function(key,value){
//         input = $("<input type='hidden'>");
//         input.attr({"name":key});
//         input.val(value);
//         form.append(input);
//     });
//
//     form.appendTo(document.body);
//     form.submit();
//     document.body.removeChild(form[0]);
// }

/**
 *
 * @returns JQuery
 */
function findCheckedSortType() {
    var type = undefined;
    $('.nav>li>a').each(
        function (i,n){
            var jq =  $(n);
            if(jq.attr("ischecked")=="true"){
                type =  jq;
            }
        }
    );
    if(type==undefined){
        type = $('.nav>li>a');
    }
    return type;
}

/**
 *
 */
function generateFilterList() {
    var list = [
        "node.js",
        "javascript",
        "library",
        "ruby",
        "web",
        "api",
        "vim",
        "plugin",
        "rust",
        "app",
        "client",
        "server",
        "json",
        "framework",
        "python",
        "browser",
        "rails",
        "css",
        "android",
        "jquery",
        "html",
        "test",
        "php",
        "command",
        "tool",
        "demo",
        "wrapper",
        "ios",
        "linux",
        "windows",
        "os_x",
        "django",
        "google",
        "generator",
        "docker",
        "image",
        "template"
    ];
    generateLabels(list,'filter');
}

function generateFilterYears() {
    var list = [
        '2011',
        '2012',
        '2013',
        '2014',
        '2015',
    ];
    generateLabels(list,'year');
}

function genetateFileterLanguage() {
    var list = [
        'java',
        'ruby',
        'python',
        'c',
        'javascript',
        'Perl',
        'lua',
        'Erlang',
        'PHP',
        'Lisp',
        'Scala',
    ];
    generateLabels(list,'language');
    
}

function generateLabels(list,name) {
    
    for(var i in list){
        var radio = " <input type=\"radio\" id=\"" +list[i] + "\" name=\""+name+"\" value=\""+list[i]+"\">";
        var label = " <label class=\"label\" for=\"" +list[i] + "\">" +list[i] + "</label>";
        document.write(radio);
        document.write(label);
    }
}

function reGenerateLabels(list,name, divClass) {
    var fatherNode = $(divClass);
    var node1 = fatherNode.children().eq(0);
    var node2 = fatherNode.children().eq(1);
    fatherNode.empty();

    fatherNode.append(node1);
    fatherNode.append(node2);
    for(var i in list){
        var radio = " <input type=\"radio\" id=\"" +list[i] + "\" name=\""+name+"\" value=\""+list[i]+"\">";
        var label = " <label class=\"label\" for=\"" +list[i] + "\">" +list[i] + "</label>";
        fatherNode.append($(radio));
        fatherNode.append($(label));
    }
}

function addOnChangeListener () {
    $('.radio-toolbar > input').change (function () {
       search(1,true);
    });
}

$(document).ready(
    function () {
        var languageURL = "/repo/statistic/language";
        $.get(languageURL,function (simplechart) {
            console.log(simplechart);
            reGenerateLabels(simplechart.field,'language','.radio-toolbar-language');
            addOnChangeListener();
        });

        RepoList.init();
        CoapareTable.init();
        RecommendList.init();
        addOnChangeListener();
        var recommendURL = '/repo/recommend'+"?pageNum="+0;
        $.get(recommendURL,function (data) {
           RecommendList.generateRecommends(data)
        });
        $.jqPaginator('#paginator', {
            totalPages: 10,
            visiblePages: 10,
            currentPage: 1,
            onPageChange: function (num, type) {
                jumpPage(num);
            }
        });
    }
);