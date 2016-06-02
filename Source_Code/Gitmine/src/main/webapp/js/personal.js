/**
 * Created by lenovo on 2016/6/2.
 */

$(document).ready(
    $.ajax({
        type:'GET',
        url:'/login/name',
        success:function(result){
            console.log($('#personalName').attr('href'));
            $('#personalName').text(result);
        },
        error:function(){
            alert("wrong!");
        }
    })


);

function updateCollectUsers(){
    
}