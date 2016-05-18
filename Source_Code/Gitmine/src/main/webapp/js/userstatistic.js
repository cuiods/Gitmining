/**
 * user statistic
 */
$(document).ready(function () {
    var myChart_repos = echarts.init(document.getElementById('gra-repos'));
    myChart_repos.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/public_repo",
        success:function (repodata) {
            option = {
                title : {
                    text: 'user repositories',
                    subtext: 'source from Github'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:repodata.field
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : repodata.field
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'size',
                        type:'bar',
                        data:repodata.value,
                        markPoint : {
                            data : [
                                {type : 'max', name: 'max'},
                                {type : 'min', name: 'min'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: 'average'}
                            ]
                        }
                    }
                ]
            };
            myChart_repos.hideLoading();
            myChart_repos.setOption(option);
            window.onresize = myChart_repos.resize;
        }
    });
    var myChart_gists = echarts.init(document.getElementById('gra-gists'));
    myChart_gists.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/public_gist",
        success:function (repodata) {
            option = {
                title : {
                    text: 'user gists',
                    subtext: 'source from Github'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:repodata.field
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : repodata.field
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'size',
                        type:'bar',
                        data:repodata.value,
                        markPoint : {
                            data : [
                                {type : 'max', name: 'max'},
                                {type : 'min', name: 'min'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: 'average'}
                            ]
                        }
                    }
                ]
            };
            myChart_gists.hideLoading();
            myChart_gists.setOption(option);
            window.onresize = myChart_gists.resize;
        }
    });
    var myChart_follower = echarts.init(document.getElementById('gra-follower'));
    myChart_follower.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/follower",
        success:function (repodata) {
            option = {
                title : {
                    text: 'user follower statistic',
                    subtext: 'source from Github'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:repodata.field
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : repodata.field
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'size',
                        type:'bar',
                        data:repodata.value,
                        markPoint : {
                            data : [
                                {type : 'max', name: 'max'},
                                {type : 'min', name: 'min'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: 'average'}
                            ]
                        }
                    }
                ]
            };
            myChart_follower.hideLoading();
            myChart_follower.setOption(option);
            window.onresize = myChart_follower.resize;
        }
    });
    var myChart_create = echarts.init(document.getElementById('gra-create'));
    myChart_create.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/create_at",
        success:function (userdata) {
            option = {
                title: {
                    text: 'User Create Time'
                },
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : userdata.field
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'time',
                        type:'line',
                        data:userdata.value
                    }
                ]
            };
            myChart_create.hideLoading();
            myChart_create.setOption(option);
            window.onresize = myChart_create.resize;
        }
    });

    var myChart_email = echarts.init(document.getElementById('gra-email'));
    myChart_email.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/email",
        success:function (userdata) {
            option = {
                title : {
                    text: 'User Email Statistic',
                    subtext: 'source from Github',
                    x:'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:userdata.field
                },
                series: [
                    {
                        name:'user email',
                        type:'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:userdata.value[0], name:userdata.field[0]},
                            {value:userdata.value[1], name:userdata.field[1]},
                            {value:userdata.value[2], name:userdata.field[2]},
                            {value:userdata.value[3], name:userdata.field[3]},
                            {value:userdata.value[4], name:userdata.field[4]},
                            {value:userdata.value[5], name:userdata.field[5]},
                            {value:userdata.value[6], name:userdata.field[6]},
                            {value:userdata.value[7], name:userdata.field[7]},
                            {value:userdata.value[8], name:userdata.field[8]},
                            {value:userdata.value[9], name:userdata.field[9]}
                        ]
                    }
                ]
            };
            myChart_email.hideLoading();
            myChart_email.setOption(option);
            window.onresize = myChart_email.resize;
        }
    });

    var myChart_company = echarts.init(document.getElementById('gra-company'));
    myChart_company.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/company",
        success:function (userdata) {
            option = {
                title : {
                    text: 'User Company Statistic',
                    subtext: 'source from Github',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: userdata.field
                },
                series : [
                    {
                        name: 'company',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:userdata.value[0], name:userdata.field[0]},
                            {value:userdata.value[1], name:userdata.field[1]},
                            {value:userdata.value[2], name:userdata.field[2]},
                            {value:userdata.value[3], name:userdata.field[3]},
                            {value:userdata.value[4], name:userdata.field[4]},
                            {value:userdata.value[5], name:userdata.field[5]},
                            {value:userdata.value[6], name:userdata.field[6]},
                            {value:userdata.value[7], name:userdata.field[7]},
                            {value:userdata.value[8], name:userdata.field[8]},
                            {value:userdata.value[9], name:userdata.field[9]}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myChart_company.hideLoading();
            myChart_company.setOption(option);
            window.onresize = myChart_company.resize;
        }
    });

    var myChart_type = echarts.init(document.getElementById('gra-type'));
    myChart_type.showLoading();
    $.ajax({
        type:"GET",
        url:"/user/statistic/type",
        success:function (userdata) {
            option = {
                title : {
                    text: 'User Type Statistic',
                    subtext: 'source from Github',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: userdata.field
                },
                series : [
                    {
                        name: 'company',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:userdata.value[0], name:userdata.field[0]},
                            {value:userdata.value[1], name:userdata.field[1]}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myChart_type.hideLoading();
            myChart_type.setOption(option);
            window.onresize = myChart_type.resize;
        }
    });
});
