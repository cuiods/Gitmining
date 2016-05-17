/**
 * Created by darxan on 2016/5/15.
 */
var location_port = '127.0.0.1:8080/';
var Repo = {


    // gridsFatherNode: $("#news-grids"),
    // gridNode: this.gridsFatherNode.find(".news-grid").eq(0),

    initListeners: function () {



    },
    generateGrids: function () {
        this.gridsFather =  $("#news-grids");
        this.lastGrid = this.gridsFather.children(".news-grid");
        this.clear = $("<div class=\"clearfix\"> </div>" );
        this.gridsFather.empty();
        for (var i=0; i<16; i++){
            var tempGrid = this.lastGrid.clone(true);
            this.gridsFather.append(tempGrid);
            if(i%4==3){
                this.gridsFather.append(this.clear.clone());
            }
        }
    },


    updateData: function (object) {

        this.gridsFather.empty();
        $.each(object, function (i, n)
        {
            var element = this.lastGrid.clone();

        });
    }
};

$(document).ready(
    function () {
        Repo.generateGrids();
        Repo.initListeners();
        Repo.updateData(request(1,1,1,1));
    }
);


function request( isPost,url,data, callback) {
    var json;
    if(isPost){
        json =  $.post(url,data,callback);
    }else{
        url += location_port+'?'+$.param(data,true);
        json =  $.get(url,callback);
    };

    var object = $.parseJSON(json);
    console.log(object);
    return object;
}