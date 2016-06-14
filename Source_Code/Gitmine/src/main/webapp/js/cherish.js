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
        success:function(ok){
            if(ok) {
                objJQ.removeClass('icon-heart-empty').addClass('icon-heart');
                objJQ.attr('title', 'click to unCherish');
                objJQ.unbind('click').click(function () {
                    unCherish(obj, fatherType, dataStr, unStarUrl, starUrl);
                })
            }else{
                userlogin();
            }
            // obj.addEventListener('click',unCherish(obj,fatherType,dataStr),false);
        },
        error:function(){
            alert('cherish wrong');
        }

    })
}

function unCherish(obj,fatherType,dataStr,unStarUrl,starUrl){
    var objJQ = $(obj);
    var fatherGrid = objJQ.parents(fatherType).eq(0);
    var dataObj = fatherGrid.find(dataStr).eq(0);
    $.ajax({
        type:'GET',
        url:unStarUrl,
        data:{username:dataObj.text()},
        success:function(ok){
            if(ok) {
                objJQ.removeClass('icon-heart').addClass('icon-heart-empty');
                objJQ.attr('title', 'click to cherish');
                objJQ.unbind('click').click(function () {
                    cherish(obj, fatherType, dataStr, starUrl, unStarUrl);
                });
            }else{
                userlogin();
            }
            // obj.addEventListener('click',cherish(obj,fatherType,dataStr),false);
        },
        error:function(){
            alert('unCherish wrong');
        }

    })
}

function onRepoClick_(obj,user_name,repo_name,starUrl,unStarUrl){
    obj.unbind('click').click(function(){
        if(obj.hasClass('icon-heart-empty')){
            cherish_repo_(obj,user_name,repo_name,starUrl,unStarUrl);
        }else{
            unCherish_repo_(obj,user_name,repo_name,unStarUrl,starUrl);
        }
    })

};
function cherish_repo_(obj,user_name,repo_name,starUrl,unStarUrl){
    $.ajax({
        type:'GET',
        url:starUrl,
        data:{ownername:user_name,reponame:repo_name},
        success:function(ok){
            if(ok){
                console.log(user_name + ' '+repo_name);
                obj.removeClass('icon-heart-empty').addClass('icon-heart');
                obj.attr('title','click to cancel cherish');
                obj.unbind('click').click(function(){
                    unCherish_repo_(obj,user_name,repo_name,unStarUrl,starUrl);
                })
            }else{
                userlogin();
            }
        }
    })
};

function unCherish_repo_(obj,user_name,repo_name,unStarUrl,starUrl){
    $.ajax({
        type:'GET',
        url:unStarUrl,
        data:{ownername:user_name,reponame:repo_name},
        success:function(ok){
            console.log(user_name + ' '+repo_name);
            if(ok){
                obj.removeClass('icon-heart').addClass('icon-heart-empty');
                obj.attr('title','click to cherish');
                obj.unbind('click').click(function(){
                    cherish_repo_(obj,user_name,repo_name,starUrl,unStarUrl);
                })
            }else{
                userlogin();
            }
        }
    })
}

// function onRepoClick(obj,fatherType,dataStr,starUrl,unStarUrl){
//     var objJQ = $(obj);
//     if(objJQ.hasClass('icon-heart-empty')){
//         cherish_repo(obj,fatherType,dataStr,starUrl,unStarUrl);
//     }else{
//         unCherish_repo(obj,fatherType,dataStr,unStarUrl,starUrl);
//     }
// }
// function cherish_repo(obj,fatherType,dataStr,starUrl,unStarUrl){
//     var objJQ = $(obj);
//     var fatherGrid = objJQ.parents(fatherType).eq(0);
//     var strlist = dataStr.split(' ');
//     var dataObj1 = fatherGrid.find(strlist[0]).eq(0);
//     var dataObj2 = fatherGrid.find(strlist[1]).eq(0);
//     console.log(dataObj1.text()+' '+dataObj2.text());
//     $.ajax({
//         type:'GET',
//         url:starUrl,
//         data:{ownername:dataObj1.text(),reponame:dataObj2.text()},
//         dataType:'json',
//         contentType:'application/json; charset=gbk',
//         success:function(ok) {
//             console.log(ok);
//             if (ok) {
//                 objJQ.removeClass('icon-heart-empty').addClass('icon-heart');
//                 objJQ.attr('title', 'click to unCherish');
//                 objJQ.unbind('click').click(function () {
//                     unCherish_repo(obj, fatherType, dataStr, unStarUrl, starUrl);
//                 })
//             }else{
//                 alert('Please try again!');
//             }
//             // obj.addEventListener('click',unCherish(obj,fatherType,dataStr),false);
//         },
//         error:function(){
//             alert('wrong!');
//         }
//
//     })
// }
//
// function unCherish_repo(obj,fatherType,dataStr,unStarUrl,starUrl){
//     var objJQ = $(obj);
//     var fatherGrid = objJQ.parents(fatherType).eq(0);
//     var strlist = dataStr.split(' ');
//     var dataObj1 = fatherGrid.find(strlist[0]).eq(0);
//     var dataObj2 = fatherGrid.find(strlist[1]).eq(0);
//     $.ajax({
//         type:'GET',
//         url:unStarUrl,
//         data:{ownername:dataObj1.text(),reponame:dataObj2.text()},
//         // dataType:'json',
//         // contentType:'application/json; charset=gbk',
//         success:function(){
//             objJQ.removeClass('icon-heart').addClass('icon-heart-empty');
//             objJQ.attr('title','click to cherish');
//             objJQ.unbind('click').click(function(){
//                 cherish_repo(obj,fatherType,dataStr,starUrl,unStarUrl);
//             })
//             // obj.addEventListener('click',cherish(obj,fatherType,dataStr),false);
//         }
//
//     })
// }

//used to show the heart is full or empty
function cherishPresent(isCherished,obj){
    if(isCherished){
        obj.removeClass('icon-heart-empty').addClass('icon-heart');
        obj.attr('title','click to cancel cherish');
    }else{
        obj.removeClass('icon-heart').addClass('icon-heart-empty');
        obj.attr('title','click to cherish');
    }
}

function onIdCherishClick(objJQ,data,starUrl,unStarUrl){
    if(objJQ.hasClass('icon-heart-empty')){
        objJQ.unbind('click').click(function(){
            IdCherish(objJQ,data,starUrl,unStarUrl);
        });

    }else{
        objJQ.unbind('click').click(function(){
            unIdCherish(objJQ,data,unStarUrl,starUrl);
        });

    }
}
function IdCherish(objJQ,data,starUrl,unStarUrl){
    $.ajax({
        type:'GET',
        url:starUrl,
        data:{username:data},
        success:function(ok){
            if(ok) {
                objJQ.removeClass('icon-heart-empty').addClass('icon-heart');
                objJQ.attr('title', 'click to unCherish');
                objJQ.unbind('click').click(function () {
                    unIdCherish(objJQ, data, unStarUrl, starUrl);
                })
            }else{
                userlogin();
            }
            // obj.addEventListener('click',unCherish(obj,fatherType,dataStr),false);
        },
        error:function(){
            alert('idCherish wrong!');
        }

    })
}

function unIdCherish(objJQ,data,unStarUrl,starUrl){
    $.ajax({
        type:'GET',
        url:unStarUrl,
        data:{username:data},
        success:function(ok){
            if(ok) {
                objJQ.removeClass('icon-heart').addClass('icon-heart-empty');
                objJQ.attr('title', 'click to cherish');
                objJQ.unbind('click').click(function () {
                    IdCherish(objJQ, data, starUrl, unStarUrl);
                })
            }else{
                userlogin();
            }
            // obj.addEventListener('click',cherish(obj,fatherType,dataStr),false);
        },
        error:function(){
            alert('unIdCherish wrong!');
        }

    })
};

function userlogin(){
    alert("please login in !");
}

