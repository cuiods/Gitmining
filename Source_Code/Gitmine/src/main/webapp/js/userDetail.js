/**
 * Created by yyy on 2016/5/18.
 */


$(document).ready(function() {
    var userName=document.getElementById("userName");
    userName.innerHTML=getQueryString('userName');
    $().UItoTop({ easingType: 'easeOutQuart' });


    $.ajax({
        type:'GET',
        url:'/user/'+userName.innerHTML+'/basic',
        success:function(data){
            console.log(data.basicInfo.isStared);
            cherishPresent(data.basicInfo.isStared,$('#cherish'));
            onIdCherishClick($('#cherish'),userName.innerHTML,'/user/star','/user/unstar');
            document.getElementById("header_url").src=data.basicInfo.avatarUrl;
            document.getElementById("userDetail_repos").innerHTML=data.basicInfo.publicRepo;
            document.getElementById("userDetail_following").innerHTML=data.basicInfo.following;
            document.getElementById("userDetail_followed").innerHTML=data.basicInfo.follower;
            document.getElementById("userDetail_gists").innerHTML=data.basicInfo.publicGist;
            document.getElementById("blog").innerHTML=data.basicInfo.blog;
            document.getElementById("email").innerHTML=data.basicInfo.email;
            document.getElementById("company").innerHTML=data.basicInfo.company;
            document.getElementById("location").innerHTML=data.basicInfo.location;
            document.getElementById("bio").innerHTML=data.basicInfo.bio;
            document.getElementById("createTime").innerHTML=data.basicInfo.createAt;
            document.getElementById("updateTime").innerHTML=data.basicInfo.updateAt;
            //add contribute repos
            
            var contributeObj = $('#contributeRepo');
            var contributeLast = contributeObj.find('.new-group-item').eq(0);
            addRelatedRepo(contributeObj,contributeLast,userName.innerHTML,'contribute');

            var subscribeObj = $('#subscribeRepo');
            var subscribeLast = subscribeObj.find('.new-group-item').eq(0);
            addRelatedRepo(subscribeObj,subscribeLast,userName.innerHTML,'subscribe');


            
            //add radar graph
            var radar_user =echarts.init(document.getElementById("userRadar"));
            var lineStyle = {
                normal: {
                    width: 1,
                    opacity: 0.5
                }
            }
            option = {
                backgroundColor: '#161627',
                tooltip:{

                },
                radar:{
                    indicator:[
                        {name: data.radarChart.field[0], max:1},
                        {name: data.radarChart.field[1], max:1},
                        {name: data.radarChart.field[2], max:1},
                        {name: data.radarChart.field[3], max:1},
                        {name: data.radarChart.field[4], max:1}

                    ],
                    shape: "circle",
                    splitNumber:5,
                    name:{
                        textStyle:{
                            color:'rgb(238,197,102)'
                        }
                    },
                    splitLine:{
                        lineStyle:{
                            color:[
                                'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
                                'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                                'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
                            ].reverse()
                        }
                    },
                    splitArea:{
                        show:false
                    },
                    axisLine:{
                        lineStyle:{
                            color:'rgba(238,197,102,0.5)'
                        }
                    }
                },
                series:[
                    {
                        name: 'userRadar',
                        type: 'radar',
                        lineStyle: lineStyle,
                        data: [data.radarChart.value],
                        symbol: 'none',
                        itemStyle: {
                            normal: {
                                color: '#F9713C'
                            }
                        },
                        areaStyle: {
                            normal: {
                                opacity: 0.3
                            }
                        }
                    },
                ]
            };

            radar_user.setOption(option);


        },
        error:function(){
            alert("controller return result unknown error!");
        }
    })


});


//to cherish this user
function cherish(){

};

function addRelatedRepo(fatherGrid,lastGrid,userName,relateType){
    fatherGrid.empty();
    console.log('/user/'+userName+'/'+relateType);
    $.ajax({
        type:'GET',
        url:'/user/'+userName+'/'+relateType,
        success:function(repoList){
            $.each(repoList,function(i,repo){
                var tempGrid = lastGrid.clone(true);
                tempGrid.find('.repo_name').eq(0).text(repo.reponame);
                tempGrid.find('.repo_description').eq(0).text(repo.description);
                tempGrid.find('.repo_fork').eq(0).text(repo.numFork);
                tempGrid.find('.repo_star').eq(0).text(repo.numStar);
                tempGrid.find('.repo_detail').eq(0).attr('href','repo-detail.html?'+repo.ownerName+'/'+repo.reponame);

                var repo_cherish = tempGrid.find('.repo_cherish').eq(0);
                console.log(repo.isStared);
                console.log(repo.ownerName+' '+repo.reponame);
                cherishPresent(repo.isStared,repo_cherish);
                onRepoClick_(repo_cherish,repo.ownerName,repo.reponame,'/repo/star','/repo/unstar');
                fatherGrid.append(tempGrid);
                fatherGrid.append('<br>');
            })
        },
        error:function(){
            alert("wrong!");
        }
        
            
    })
    
}

//to get the parameter transfered by url
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


