/**
 * repository statistic charts.
 */
// 基于准备好的dom，初始化echarts实例
// var myChart = echarts.init(document.getElementById('main'));
//
// // 指定图表的配置项和数据
// var option = {
//     title: {
//         text: 'ECharts 入门示例'
//     },
//     tooltip: {},
//     legend: {
//         data:['销量']
//     },
//     xAxis: {
//         data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
//     },
//     yAxis: {},
//     series: [{
//         name: '销量',
//         type: 'bar',
//         data: [5, 20, 36, 10, 10, 20]
//     }]
// };
//
// // 使用刚指定的配置项和数据显示图表。
// myChart.setOption(option);

$(document).ready(function () {
    var myChart_create = echarts.init(document.getElementById('gra-create-time'));
    myChart_create.showLoading();
    $.ajax({
        type:"GET",
        url:"/repo/statistic/create_at",
        success:function (data) {
            option = {
                title : {
                    text: 'Repository create time',
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
                    data: data.field
                },
                series : [
                    {
                        name: 'Create at',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:data.value[0], name:data.field[0]},
                            {value:data.value[1], name:data.field[1]},
                            {value:data.value[2], name:data.field[2]},
                            {value:data.value[3], name:data.field[3]},
                            {value:data.value[4], name:data.field[4]}
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
            myChart_create.hideLoading();
            myChart_create.setOption(option);
            window.onresize = myChart_create.resize;

        }
    });

    var myChart_size = echarts.init(document.getElementById('gra-size'));
    myChart_size.showLoading();
    $.ajax({
        type: "GET",
        url: "/repo/statistic/size",
        success: function (size) {
            option = {
                title : {
                    text: 'Repository size',
                    subtext: 'source from Github'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:size.field
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
                        data : size.field
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
                        data:size.value,
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
            myChart_size.hideLoading();
            myChart_size.setOption(option);
            window.onresize = myChart_size.resize;
        }
    });

    var myChart_language = echarts.init(document.getElementById('gra-language'));
    myChart_language.showLoading();
    $.ajax({
        type: "GET",
        url: "/repo/statistic/language",
        success: function (language) {
            option = {
                title : {
                    text: 'Repository Language',
                    subtext: 'source from Github',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x : 'center',
                    y : 'bottom',
                    data: language.field
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel']
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                series : [
                    {
                        name:'size',
                        type:'pie',
                        radius : [30, 180],
                        roseType : 'radius',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        lableLine: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:[
                            {value:language.value[0], name:language.field[0]},
                            {value:language.value[1], name:language.field[1]},
                            {value:language.value[2], name:language.field[2]},
                            {value:language.value[3], name:language.field[3]},
                            {value:language.value[4], name:language.field[4]},
                            {value:language.value[5], name:language.field[5]},
                            {value:language.value[6], name:language.field[6]},
                            {value:language.value[7], name:language.field[7]},
                            {value:language.value[8], name:language.field[8]},
                            {value:language.value[9], name:language.field[9]}
                        ]
                    }
                ]
            };
            myChart_language.hideLoading();
            myChart_language.setOption(option);
            window.onresize = myChart_language.resize;
        }
    });

    var myChart_star = echarts.init(document.getElementById('gra-star'));
    myChart_star.showLoading();
    $.ajax({
        type:"GET",
        url:"/repo/statistic/star",
        success:function (star) {
            option = {
                title : {
                    text: 'Repository star',
                    subtext: 'source from Github'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:star.field
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
                        data : star.field
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'star',
                        type:'bar',
                        data:star.value,
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
            myChart_star.hideLoading();
            myChart_star.setOption(option);
            window.resize = myChart_star.resize;
        }
    });

    var myChart_fork = echarts.init(document.getElementById('gra-fork'));
    myChart_fork.showLoading();
    $.ajax({
        type:"GET",
        url:"/repo/statistic/fork",
        success:function (fork) {
            option = {
                title : {
                    text: 'Repository fork',
                    subtext: 'source from Github'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:fork.field
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
                        data : fork.field
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
                        data:fork.value,
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
            myChart_fork.hideLoading();
            myChart_fork.setOption(option);
            window.onresize = myChart_fork.resize;
        }
    });
});