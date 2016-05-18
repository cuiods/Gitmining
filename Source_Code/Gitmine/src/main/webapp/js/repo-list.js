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
        this.lastGrid = this.gridsFather.children(".news-grid").eq(0);
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

        /**
         * {"totalPage":10,
         * repoList":[{"ownerName":null,"ownerAvatarUrl":null,"reponame":null,"size":0,"description":null,
         * "language":null,"url":null,"createAt":null,"updateAt":null,
         * "numStar":0,"numFork":0,"numSubscriber":0}],"currentPage":1}
         */
        this.gridsFather.empty();
        var _this = this;
        $.each(object.repoList, function (i, n)
        {


            var tempGrid = _this.lastGrid.clone();

            tempGrid.find('.ownerName').eq(0).text (n.ownerName);
            tempGrid.find('.reponame').eq(0).text (n.reponame);
            tempGrid.find('.description').eq(0).text (n.description);
            tempGrid.find('.createAt').eq(0).text (n.createAt);
            tempGrid.find('.updateAt').eq(0).text (n.updateAt);

            tempGrid.find('.ownerAvatarUrl').eq(0).attr  ( 'src',n.ownerAvatarUrl);
            tempGrid.find('.numSubscriber').eq(0).text  ( n.numSubscriber);
            tempGrid.find('.numFork').eq(0).text  ( n.numFork);
            tempGrid.find('.numStar').eq(0).text   ( n.numStar);

            _this.gridsFather.append(tempGrid);
            if(i%4==3){
                _this.gridsFather.append(_this.clear.clone());
            }

        });
    },

};

$(document).ready(
    function () {
        Repo.init();
        Repo.initListeners();
        var url = location_port+'/repo/list'+"?pageNum=1";
        $.get(url,function (object) {
            console.log(object);
            Repo.updateData(object);
        });
    }
);

