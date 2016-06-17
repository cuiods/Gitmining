/**
 * show statistic
 */
$(document).ready(function () {
    var popu_star = echarts.init(document.getElementById('gra-popularity-star'));
    popu_star.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/star",
        success:function (stardata) {
            var dataAll = [
                stardata.fork,
                stardata.watcher
            ];
            var statdata = stardata.stat;

            var markLineOpt = {
                animation: false,
                label: {
                    normal: {
                        formatter: statdata[0],
                        textStyle: {
                            align: 'right'
                        }
                    }
                },
                lineStyle: {
                    normal: {
                        type: 'solid'
                    }
                },
                tooltip: {
                    formatter: statdata[0]
                },
                data: [[{
                    coord: [0, statdata[1]],
                    symbol: 'none'
                }, {
                    coord: [1000, statdata[2]],
                    symbol: 'none'
                }]]
            };

            var markLineOpt2 = {
                animation: false,
                label: {
                    normal: {
                        formatter: statdata[3],
                        textStyle: {
                            align: 'right'
                        }
                    }
                },
                lineStyle: {
                    normal: {
                        type: 'solid'
                    }
                },
                tooltip: {
                    formatter: statdata[3]
                },
                data: [[{
                    coord: [0, statdata[4]],
                    symbol: 'none'
                }, {
                    coord: [1000, statdata[5]],
                    symbol: 'none'
                }]]
            };

            option = {
                title: {
                    text: 'Star with fork and watcher number',
                    x: 'center',
                    y: 0
                },
                grid: [
                    {x: '7%', y: '7%', width: '38%', height: '70%'},
                    {x2: '7%', y: '7%', width: '38%', height: '70%'}
                ],
                tooltip: {
                    formatter: 'Group {a}: ({c})'
                },
                xAxis: [
                    {gridIndex: 0, min:0, max:1000},
                    {gridIndex: 1, min:0, max:1000}
                ],
                yAxis: [
                    {gridIndex: 0, min:0, max:200},
                    {gridIndex: 1, min:0, max:1000}
                ],
                series: [
                    {
                        name: 'fork',
                        type: 'scatter',
                        xAxisIndex: [0],
                        yAxisIndex: [0],
                        data: dataAll[0],
                        markLine: markLineOpt
                    },
                    {
                        name: 'watcher',
                        type: 'scatter',
                        xAxisIndex: [1],
                        yAxisIndex: [1],
                        data: dataAll[1],
                        markLine: markLineOpt2
                    }
                ]
            };

            popu_star.hideLoading();
            popu_star.setOption(option);
        }
    });

    var popu_follower = echarts.init(document.getElementById('gra-popularity-follower'));
    popu_follower.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/followerRegression",
        success:function (stardata2) {
            var mydata = stardata2;

            option = {
                title: {
                    text: 'Star with sum of followers of contributors',
                    x: 'center',
                    y: 0
                },
                grid: [
                    {x: '7%', y: '7%', width: '76%', height: '70%'}
                ],
                tooltip: {
                    formatter: 'Group {a}: ({c})'
                },
                xAxis: [
                    {gridIndex: 0, min:0, max:5000}
                ],
                yAxis: [
                    {gridIndex: 0, min:0, max:200}
                ],
                series: [
                    {
                        name: 'fork',
                        type: 'scatter',
                        xAxisIndex: [0],
                        yAxisIndex: [0],
                        data: mydata
                    }
                ]
            };

            popu_follower.hideLoading();
            popu_follower.setOption(option);
        }
    });

    $.ajax({
        type:"GET",
        url:"/popularity/vaLanguage",
        success:function(data){
            $("#one-way-language").html("F value: "+data[0]);
        }
    });

    $.ajax({
        type:"GET",
        url:"/popularity/vaField",
        success:function(data){
            $("#one-way-field").html("F value: "+data[0]);
        }
    });

    $.ajax({
        type:"GET",
        url:"/popularity/vaPerson",
        success:function(data){
            $("#one-way-person").html("F value: "+data[0]);
        }
    });

    $.ajax({
        type:"GET",
        url:"/popularity/vaLanguageData",
        success:function(ldata){
            $('#regression-language').highcharts({

                chart: {
                    type: 'boxplot'
                },

                title: {
                    text: 'Star Distribution with Different Languages'
                },

                legend: {
                    enabled: false
                },

                xAxis: {
                    categories: ['JavaScript','Ruby','Python','CSS','PHP','Objective_C','C','Java'],
                    title: {
                        text: 'Language No.'
                    }
                },

                yAxis: {
                    title: {
                        text: 'Observations'
                    }
                },

                series: [{
                    name: 'Observations',
                    data: ldata,
                    tooltip: {
                        headerFormat: '<em>Language {point.key}</em><br/>'
                    }
                }, {
                    name: 'Outlier',
                    color: Highcharts.getOptions().colors[0],
                    type: 'scatter',
                    data: [ // x, y positions where 0 is the first category
                        [0, 644],
                        [4, 718],
                        [4, 951],
                        [4, 969]
                    ],
                    marker: {
                        fillColor: 'white',
                        lineWidth: 1,
                        lineColor: Highcharts.getOptions().colors[0]
                    },
                    tooltip: {
                        pointFormat: 'Observation: {point.y}'
                    }
                }]

            });
        }
    });

    $.ajax({
        type:"GET",
        url:"/popularity/vaFieldData",
        success:function(ldata){
            $('#regression-field').highcharts({

                chart: {
                    type: 'boxplot'
                },

                title: {
                    text: 'Star Distribution with Different Fields'
                },

                legend: {
                    enabled: false
                },

                xAxis: {
                    categories: ['node.js','javascript','library','ruby','web','api','vim','plugin'],
                    title: {
                        text: 'Field No.'
                    }
                },

                yAxis: {
                    title: {
                        text: 'Observations'
                    }
                },

                series: [{
                    name: 'Observations',
                    data: ldata,
                    tooltip: {
                        headerFormat: '<em>Language {point.key}</em><br/>'
                    }
                }, {
                    name: 'Outlier',
                    color: Highcharts.getOptions().colors[0],
                    type: 'scatter',
                    data: [ // x, y positions where 0 is the first category
                        [0, 644],
                        [4, 718],
                        [4, 951],
                        [4, 969]
                    ],
                    marker: {
                        fillColor: 'white',
                        lineWidth: 1,
                        lineColor: Highcharts.getOptions().colors[0]
                    },
                    tooltip: {
                        pointFormat: 'Observation: {point.y}'
                    }
                }]

            });
        }
    });

    
})