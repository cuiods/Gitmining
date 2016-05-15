/**
 * Created by darxan on 2016/5/15.
 */
$(document).ready(
    function () {
       var gridsFather =  $("#news-grids");
        var grids = gridsFather.find('.news-grid');
       var lastGrid = grids.eq(0);
        console.log(lastGrid);
        var count = grids.length;
        console.log(count);

        for (var i=count; i<16; i++){
            var tempGrid = lastGrid.clone(true);
            gridsFather.append(tempGrid);
        }

    }
);