/**
 * Created by darxan on 2016/5/19.
 */
var location_port = "127.0.0.1:8080";
$(document).ready(
    function () {
        var fullName = location.search;
        var index = location.search.indexOf("?");
        if(index!=-1){
          wrongUrl();
        }else{
            var params = fullName.substr(1);
            var names = params.split("/");
            if(names.length<2){
                wrongUrl();
            }else{
                var url = location_port+params;
                $.get(url,function (object) {
                    setBasicInfo(object);
                });
            }

        }
    }
);

function setBasicInfo(basicInfo) {
    var mainInfo = $('#main>#single-page>#single-page>#container> #main-info');
    var numbers = mainInfo.find("#numbers");
    var descriptions = mainInfo.find("#descriptions");
    var header = mainInfo.find("#header_url");
    
    header.attr("href",basicInfo.avatar);
}

function wrongUrl() {
    alert("wrong url");
}