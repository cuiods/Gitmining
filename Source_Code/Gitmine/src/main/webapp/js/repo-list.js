/**
 * Created by darxan on 2016/5/15.
 */
var location_port = 'http://localhost:8080';
var Repo = {


    // gridsFatherNode: $("#news-grids"),
    // gridNode: this.gridsFatherNode.find(".news-grid").eq(0),
    initListeners: function () {
    },

    init: function () {
        this.gridsFather =  $("#news-grids");
        this.lastGrid = this.gridsFather.children(".news-grid");
        this.clear = $("<div class=\"clearfix\"> </div>" );
        this.gridsFather.empty();
        this.url = location_port+"/search";

        for (var i=0; i<16; i++){
            var tempGrid = this.lastGrid.clone(true);
            this.gridsFather.append(tempGrid);
            if(i%4==3){
                this.gridsFather.append(this.clear.clone());
            }
        }
    },


    updateData: function (object) {


        // var object = $.parseJSON(data);

        this.gridsFather.empty();
        $.each(object, function (i, n)
        {
            console.log(n);
            var element = this.lastGrid.clone();
            this.gridsFather.append(element);
            if(i%4==3){
                this.gridsFather.append(this.clear.clone());
            }

        });
    },

    updateList: function (pageNum) {
        var url = location_port+'/repo/list'+"?pageNum="+pageNum;
        var oAjax = null;
        if(window.XMLHttpRequest){
            oAjax = new XMLHttpRequest();
        }else{
            oAjax = new ActiveXObject('Microsoft.XMLHTTP');
        }
        oAjax.open('GET', url, true);
        oAjax.send();

        oAjax.onreadystatechange=function(){
            if(oAjax.readyState==4){
                if(oAjax.status==200){
                    console.log(oAjax.responseText);
                }else{
                    console.log('filed');
                }
            }
        };
    }
};

$(document).ready(
    function () {
        Repo.init();
        Repo.initListeners();
        Repo.updateList(1);
        

    }
);

