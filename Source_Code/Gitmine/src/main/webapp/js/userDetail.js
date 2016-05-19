/**
 * Created by yyy on 2016/5/18.
 */


$(document).ready(function() {
    var userName=document.getElementById("userName");
    userName.innerHTML=getQueryString('userName');
    $().UItoTop({ easingType: 'easeOutQuart' });


    $.ajax({
        type:'GET',
        url:'/user/'+userName.innerHTML,
        success:function(data){

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
            //add related repos
            var relatedRepo = document.getElementById("relatedRepo");
            $.each(data.relatedRepo,function(){
                var node = document.createElement("a");
                relatedRepo.appendChild(node);
                node.href = "repoDetail.html?repoName="+this.reponame;
                node.innerHTML = this.ownerName+"/"+this.reponame;
                $(node).addClass("list-group-item");

            });
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
            // alert(data.radarChart.field);


            radar_user.setOption(option);

            
            //add similar users
            /*
            wrap1_div
                image_div
                    image
                wrap2_div
                    updateLabel
                    br
                    name_cherish
                        name
                        cherish
                    clear
                    attr_div
                        repo_icon repo_num
                        following_icon following_num
                        followed_icon followed_num
                    br
                    detail
                clear
             */
            var similarUsers = document.getElementById("similarUsers");
            $.each(data.relatedUser,function(){
                var clear = $("<div class=\"clearfix\"> </div>" );
                var lineChange = $("<br>");

                var wrap1_div = document.createElement("div");
                wrap1_div.className="recent-posts-info";
                similarUsers.appendChild(wrap1_div);
                //header_image
                var image_div = document.createElement("div");
                $(image_div).addClass("posts-left");
                $(image_div).addClass("sngl-img");
                var image = document.createElement("img");
                image.src=this.basicInfo.avatarUrl;
                image_div.appendChild(image);
                wrap1_div.appendChild(image_div);
                //
                var wrap2_div = document.createElement("div");
                wrap2_div.className="posts-right";
                wrap1_div.appendChild(wrap2_div);

                var updateLabel = document.createElement("label");
                updateLabel.innerHTML = this.basicInfo.updateAt;
                wrap2_div.appendChild(updateLabel);
                wrap2_div.appendChild(lineChange.clone(true));

                var name_cherish = document.createElement("div");
                wrap2_div.appendChild(name_cherish);
                var name = document.createElement("h5");
                name.style.cssText="float:left;font-size:20px";
                name.innerHTML = this.basicInfo.login;
                name_cherish.appendChild(name);
                var cherish = document.createElement("button");
                $(cherish).addClass("glyphicon icon-heart-empty icon-large iconStyle");
                $(cherish).click(function(){
                    //to do after
                    
                    $(cherish).removeClass("icon-heart-empty").addClass("icon-heart");

                })
                name_cherish.appendChild(cherish);
                
                wrap2_div.appendChild(clear.clone(true));
                
                var attr_div = document.createElement("div");
                wrap2_div.appendChild(attr_div);

                var repo_icon = document.createElement("span");
                $(repo_icon).addClass("glyphicon glyphicon-apple iconStyle");
                var repo_num = document.createElement("span");
                repo_num.innerHTML = this.basicInfo.publicRepo;
                var following_icon = document.createElement("span");
                $(following_icon).addClass("glyphicon glyphicon-apple iconStyle");
                var following_num = document.createElement("span");
                following_num.innerHTML = this.basicInfo.following;
                var followed_icon = document.createElement("span");
                $(followed_icon).addClass("glyphicon glyphicon-apple iconStyle");
                var followed_num = document.createElement("span");
                followed_num.innerHTML = this.basicInfo.follower;

                attr_div.appendChild(repo_icon);
                attr_div.appendChild(repo_num);
                attr_div.appendChild(following_icon);
                attr_div.appendChild(following_num);
                attr_div.appendChild(followed_icon);
                attr_div.appendChild(followed_num);

                wrap2_div.appendChild(lineChange.clone(true));

                var detail = document.createElement("a");
                $(detail).addClass("btn btn-primary hvr-rectangle-in");
                detail.innerHTML = "Read Detail";
                wrap2_div.appendChild(detail);

                wrap1_div.appendChild(clear.clone(true));

                
            });


        },
        error:function(){
            alert("controller return result unknown error!");
        }
    })


});


//to cherish this user
function cherish(){

};

//to get the parameter transfered by url
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


