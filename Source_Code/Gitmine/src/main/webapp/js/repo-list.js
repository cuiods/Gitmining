/**
 * Created by darxan on 2016/5/15.
 */
var location_port = '';
var descritopmLength = 63;
var descritopmLengthEachLine = 39;
var nameLength = 10;

var RepoList = {

    init: function () {
        this.gridsFather =  $("#news-grids");
        this.lastGrid = this.gridsFather.children(".news-grid").eq(0);
        this.clear = $("<div class=\"clearfix\"> </div>" );
        // this.gridsFather.empty();
    },
    updateData: function (object) {
        this.gridsFather.empty();
        var _this = this;
        _this.checkboxes = new Array(object.length);
        $.each(object, function (i, n)
        {
            var tempGrid = _this.lastGrid.clone(true);
            var owner = tempGrid.find('.ownerName').eq(0);
            owner.text (n.ownerName.substr(0,descritopmLengthEachLine));
            owner.attr ('href','/html/html/userDetail.html?ownerName='+n.ownerName);

            var repo = tempGrid.find('.reponame').eq(0);
            repo.text (n.reponame.substr(0,nameLength));

            var repoHref = '/html/html/repo-detail.html?'+n.ownerName+"/"+n.reponame;
            repo.attr ('href',repoHref);
            tempGrid.find('.ownerAvatarUrl').eq(0).attr  ( 'src',n.ownerAvatarUrl);
            tempGrid.find('.mask').eq(0).attr  ( 'href',repoHref);
            var description = n.description;
            if(description.length>descritopmLength){
                description = description.substr(0,descritopmLength)+"...";
            }else if(description.length<descritopmLengthEachLine){
                description = description+"<br><br>";
            }
            tempGrid.find('.description').eq(0).html (description);
            tempGrid.find('.createAt').eq(0).text (n.createAt);
            tempGrid.find('.updateAt').eq(0).text (n.updateAt);

            tempGrid.find('.numSubscriber').eq(0).text  ( n.numSubscriber);
            tempGrid.find('.numFork').eq(0).text  ( n.numFork);
            tempGrid.find('.numStar').eq(0).text   ( n.numStar);

            _this.gridsFather.append(tempGrid);

            var checkbox = tempGrid.find(".checkbox").eq(0);
            checkbox.attr("name",n.ownerName+"/"+n.reponame);
             _this.checkboxes.push(checkbox);
            if(i%4==3){
                _this.gridsFather.append(_this.clear.clone(true));
            }

        });
        console.log(_this.checkboxes);
    },

};

$(document).ready(
    function () {
        RepoList.init();
        var url = location_port+'/repo/list'+"?pageNum=1";
        $.get(url,function (object) {
            RepoList.updateData(object.repoList);
        });
    }
);

function jumpPage() {

}

function compare() {
   console.log(RepoList.checkboxes);
    var selected = new Array();
   for(var index in RepoList.checkboxes){
       var item = RepoList.checkboxes[index];
       if(item.prop("checked")){
           selected.push(item.attr("name"));
       }
   }
   standardPost("",selected); 
}

function search(page) {

    var key = $('#key').val();
    var label_language = findCheckedRadio($('.radio-toolbar-language').eq(0));
    var label_year = findCheckedRadio($('.radio-toolbar-year').eq(0));
    var label_filterType = findCheckedRadio($('.radio-toolbar-filter').eq(0));
    var sort = findCheckedSortType();
    var sort_by = sort.attr("sortType");
    var isReverse = sort.attr("isReverse");

    var data = {
        // filterType:label_filterType,
        // language:label_language,
        // createYear:label_year,
        keyword:key,
        pageNum:page,
        sortType:sort_by,
        reverse:isReverse,
    }
    console.log(data);
    var url = location_port+"/repo/search";
    $.post(url,data,function (object) {
        console.log(object);
        RepoList.updateData(object);
    });
}


//$（'element'）.children('')返回的是JQuery对象还是DOM对象??
function findCheckedRadio(toolbar) {
    var radios = toolbar.children('input');
    var label = 'all';
    radios.each(
        function (i,n) {
            if(n.ischecked='true'){
                label =  n.value;
            }
        }
    );
    return label;
}

function onclickFunction(obj) {
    var originCheck = findCheckedSortType();
    var obj = $(obj);
    if(originCheck.attr("sortType")==obj.attr('sortType')){


        if(obj.attr("isReverse")=="true"){
            obj.attr("isReverse" , "false");
            obj.children("span").attr("class", "glyphicon glyphicon-arrow-down");
        }else{
            obj.attr("isReverse" , "true");
            obj.children("span").attr("class",  "glyphicon glyphicon-arrow-up");
        };

    }else{
        originCheck.attr("ischecked" ,"false");
        obj.attr("ischecked" , "true");
    }
    search(1);
}

/**
 * 
 */
function standardPost(url,args){
    var body = $(document.body),
        form = $("<form method='post'></form>"),
        input;
    form.attr({"action":url});
    $.each(args,function(key,value){
        input = $("<input type='hidden'>");
        input.attr({"name":key});
        input.val(value);
        form.append(input);
    });

    form.appendTo(document.body);
    form.submit();
    document.body.removeChild(form[0]);
}

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
    var list = new Array(
        "web",
        "app",
        "api",
        "framework",
        "cms",
        "django",
        "emacs",
        "mac",
        "management",
        "linux",
        "windows",
        "interFace",
        "os",
        "server",
        "tool",
        "plugin",
        "json",
        "tempLate",
        "library",
        "ui",
        "dataBase"
    );
    for(var i in list){
        var radio = " <input type=\"radio\" id=\"" +list[i] + "\" name=\"radios0\" value=\"false\">";
        var label = " <label class=\"label\" for=\"" +list[i] + "\">" +list[i] + "</label>";
        document.write(radio);
        document.write(label);
    }
}