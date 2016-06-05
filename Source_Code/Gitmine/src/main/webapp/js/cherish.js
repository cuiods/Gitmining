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

function onRepoClick(obj,fatherType,dataStr,starUrl,unStarUrl){
    var objJQ = $(obj);
    if(objJQ.hasClass('icon-heart-empty')){
        cherish_repo(obj,fatherType,dataStr,starUrl,unStarUrl);
    }else{
        unCherish_repo(obj,fatherType,dataStr,unStarUrl,starUrl);
    }
}
function cherish_repo(obj,fatherType,dataStr,starUrl,unStarUrl){
    var objJQ = $(obj);
    var fatherGrid = objJQ.parents(fatherType).eq(0);
    var strlist = dataStr.split(' ');
    var dataObj1 = fatherGrid.find(strlist[0]).eq(0);
    var dataObj2 = fatherGrid.find(strlist[1]).eq(0);
    console.log(dataObj1.text()+' '+dataObj2.text());
    $.ajax({
        type:'GET',
        url:starUrl,
        data:{ownername:dataObj1.text(),reponame:dataObj2.text()},
        dataType:'json',
        contentType:'application/json; charset=gbk',
        success:function(ok) {
            console.log(ok);
            if (ok) {
                objJQ.removeClass('icon-heart-empty').addClass('icon-heart');
                objJQ.attr('title', 'click to unCherish');
                objJQ.unbind('click').click(function () {
                    unCherish_repo(obj, fatherType, dataStr, unStarUrl, starUrl);
                })
            }else{
                alert('Please try again!');
            }
            // obj.addEventListener('click',unCherish(obj,fatherType,dataStr),false);
        },
        error:function(){
            alert('wrong!');
        }

    })
}

function unCherish_repo(obj,fatherType,dataStr,unStarUrl,starUrl){
    var objJQ = $(obj);
    var fatherGrid = objJQ.parents(fatherType).eq(0);
    var strlist = dataStr.split(' ');
    var dataObj1 = fatherGrid.find(strlist[0]).eq(0);
    var dataObj2 = fatherGrid.find(strlist[1]).eq(0);
    $.ajax({
        type:'GET',
        url:unStarUrl,
        data:{ownername:dataObj1.text(),reponame:dataObj2.text()},
        // dataType:'json',
        // contentType:'application/json; charset=gbk',
        success:function(){
            objJQ.removeClass('icon-heart').addClass('icon-heart-empty');
            objJQ.attr('title','click to cherish');
            objJQ.unbind('click').click(function(){
                cherish_repo(obj,fatherType,dataStr,starUrl,unStarUrl);
            })
            // obj.addEventListener('click',cherish(obj,fatherType,dataStr),false);
        }

    })
}