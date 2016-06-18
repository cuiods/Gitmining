/**
 * Created by yyy on 2016/5/18.
 */


var isMapEverInited =false;
var userInfoData = undefined;
function loadMapScenario(){
    
    var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
        credentials: 'Ak84CuIJYo828Ii3n714qYMPp6T1PDOGd2mVCqGZyle9KKLtytu7B_D8atBRtRoj'
    });
    Microsoft.Maps.loadModule('Microsoft.Maps.Search', function () {

        if(userInfoData==undefined){
            return;
        }
        var currentLooation = userInfoData.basicInfo.location;
        if(currentLooation=="" || currentLooation==null || currentLooation==undefined){
            currentLooation = "nanjing";
        }
        var searchManager = new Microsoft.Maps.Search.SearchManager(map);
        var requestOptions = new Microsoft.Maps.Search.GeocodeRequestOptions();
        requestOptions.bounds = map.getBounds();
        requestOptions.where =                 currentLooation;
        requestOptions.callback = function (answer, userData) {
            console.log(answer);
            map.setView({ bounds: answer.results[0].bestView });
            map.entities.push(new Microsoft.Maps.Pushpin(answer.results[0].location));


            
            function addNeighbors(neighbors) {
                $.each(neighbors,function (i,neighbor) {
                    
                    var pin = new Microsoft.Maps.Pushpin(
                        new Microsoft.Maps.Location(neighbor.latitude ,neighbor.longitude),
                        { text: 'U', title: neighbor.login }
                    );
                    map.entities.push(pin);
                    console.log(pin);
                    Microsoft.Maps.Events.addHandler(
                        pin,
                        'click',
                        function (args) {
                            window.location.href = '/html/html/userDetail.html?userName='+neighbor.login;
                        });
                });
            }

            if(userInfoData.neighbors!=null){
                 addNeighbors(userInfoData.neighbors);
            }else{
                var name = userInfoData.basicInfo.login;
                var longitude = answer.results[0].location.longitude;
                var latitude = answer.results[0].location.latitude;
                var url = '/user/neighbors';
                var data = {
                    name : name,
                    longitude : longitude,
                    latitude : latitude,
                }
                $.get(url,data,function (object) {
                    addNeighbors(object);
                });
            }
            isMapEverInited = true;

        };
        searchManager.geocode(requestOptions);
    });

}
    
$(document).ready(function() {
    var userName=document.getElementById("userName");
    userName.innerHTML=getQueryString('userName');
    $().UItoTop({ easingType: 'easeOutQuart' });


    $.ajax({
        type:'GET',
        url:'/user/'+userName.innerHTML+'/basic',
        success:function(data){
            userInfoData = data;
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

            var language = document.getElementById('userLanguage');
            var chart = data.languageChart;
            var dataArray= new Array();
            $.each(chart.field,function(i,n){
                dataArray.push(
                    {
                        value:chart.value[i],name:chart.field[i]
                    }
                )
            })
            drawLanguageChart(language,dataArray);


            if(!isMapEverInited){
                loadMapScenario();
            }
            console.log(data.basicInfo.isStared);
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
        },
        
        
            
    })
    
}

//to get the parameter transfered by url
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function drawLanguageChart(obj,data){
    var languageChart = echarts.init(obj);
    option = {
        backgroundColor: '#2c343c',

        title: {
            text: '',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#ccc'
            }
        },

        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series : [
            {
                name:'language using',
                type:'pie',
                radius : '55%',
                center: ['50%', '50%'],
                data:data.sort(function (a, b) { return a.value - b.value}),
                roseType: 'angle',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    languageChart.hideLoading();
    languageChart.setOption(option);
    return languageChart;
}


