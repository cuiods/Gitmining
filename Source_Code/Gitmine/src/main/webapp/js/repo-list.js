/**
 * Created by darxan on 2016/5/15.
 */

var Repo = {

    initListeners: function () {

    },
    generateGrids: function () {
        var gridsFather =  $("#news-grids");
        var grids = gridsFather.find('.news-grid');
        var lastGrid = grids.eq(0);
        console.log(lastGrid);
        var count = grids.length;
        console.log(count);
        var clear = $("<div class=\"clearfix\"> </div>" );
        for (var i=count; i<16; i++){
            var tempGrid = lastGrid.clone(true);
            gridsFather.append(tempGrid);
            if(i%4==3){
                gridsFather.append(clear.clone());
            }
        }
    },


    updateData: function (object) {

    }
};

$(document).ready(
    function () {
        Repo.generateGrids();
        Repo.initListeners();
        Repo.updateData(1);
    }
);


function request( isPost,url,data, callback) {
    var json;
    if(isPost){
        json =  $.post(url,data,callback);
    }else{
        url += '?'+$.param(data,true);
        json =  $.get(url,callback);
    };
    return $.parseJSON(json);
}