/**
 * Created by lenovo on 2016/6/2.
 */

//obj: this cherish object
//fatherType:最高父节点
//dataStr : username
//urlStr: /user/star
//oppoUrl: /user/unStar

function onCherishClick(obj,fatherType,dataStr,starUrl,unStarUrl){
    var objJQ = $(obj);
    if(objJQ.hasClass('icon-heart-empty')){
        cherish(obj,fatherType,dataStr,starUrl,unStarUrl);
    }else{
        unCherish(obj,fatherType,dataStr,unStarUrl,starUrl);
    }
}
function cherish(obj,fatherType,dataStr,starUrl,unStarUrl){
    var objJQ = $(obj);
    var fatherGrid = objJQ.parents(fatherType).eq(0);
    var dataObj = fatherGrid.find(dataStr).eq(0);
    // console.log(dataObj.text());
    $.ajax({
        type:'POST',
        url:starUrl,
        data:{username:dataObj.text()},
        success:function(){
            objJQ.removeClass('icon-heart-empty').addClass('icon-heart');
            objJQ.attr('title','click to unCherish');
            objJQ.unbind('click').click(function(){
                unCherish(obj,fatherType,dataStr,unStarUrl,starUrl);
            })
            // obj.addEventListener('click',unCherish(obj,fatherType,dataStr),false);
        }

    })
}

function unCherish(obj,fatherType,dataStr,unStarUrl,starUrl){
    var objJQ = $(obj);
    var fatherGrid = objJQ.parents(fatherType).eq(0);
    var dataObj = fatherGrid.find(dataStr).eq(0);
    $.ajax({
        type:'POST',
        url:unStarUrl,
        data:{username:dataObj.text()},
        success:function(){
            objJQ.removeClass('icon-heart').addClass('icon-heart-empty');
            objJQ.attr('title','click to cherish');
            objJQ.unbind('click').click(function(){
                cherish(obj,fatherType,dataStr,starUrl,unStarUrl);
            })
            // obj.addEventListener('click',cherish(obj,fatherType,dataStr),false);
        }

    })
}