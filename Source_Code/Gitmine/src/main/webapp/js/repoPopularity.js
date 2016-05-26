/**
 * Created by cuihao on 2016/5/25.
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
                    formatter: 'y = 0.5 * x + 3'
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
                    formatter: 'y = 0.5 * x + 3'
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

    var popu_language = echarts.init(document.getElementById('gra-popularity-language'));
    popu_language.showLoading();
    $.ajax({
        type:"GET",
        url: "/popularity/language",
        success:function (lan) {
            var data = echarts.dataTool.prepareBoxplotData([
                lan.lan0,
                lan.lan1,
                lan.lan2,
                lan.lan3,
                lan.lan4,
                lan.lan5,
                lan.lan6,
                lan.lan7,
                lan.lan8,
                lan.lan9
            ]);
            option = {
                title: [
                    {
                        text: 'Popularity with language: popular repositories star numbers',
                        left: 'center',
                    },
                    {
                        text: 'upper: Q3 + 1.5 * IRQ \nlower: Q1 - 1.5 * IRQ',
                        borderColor: '#999',
                        borderWidth: 1,
                        textStyle: {
                            fontSize: 14
                        },
                        left: '10%',
                        top: '90%'
                    }
                ],
                legend: {
                    data: ['JavaScript','Ruby','Python','CSS','PHP','Objective_C','C','Java','Go','Shell']
                },
                tooltip: {
                    trigger: 'item',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    left: '10%',
                    right: '10%',
                    bottom: '15%'
                },
                xAxis: {
                    type: 'category',
                    data: ['JavaScript','Ruby','Python','CSS','PHP','Objective_C','C','Java','Go','Shell'],
                    boundaryGap: true,
                    //nameGap: 30,
                    splitArea: {
                        show: false
                    },
                    axisLabel: {
                        formatter: '{value}'
                    },
                     splitLine: {
                         show: false
                     }
                },
                yAxis: {
                    type: 'value',
                    name: 'star number',
                    splitArea: {
                        show: true
                    }
                },
                series: [
                    {
                        name: 'boxplot',
                        type: 'boxplot',
                        data: data.boxData,
                        tooltip: {
                            formatter: function (param) {
                                return [
                                    'Language ' + param.name + ': ',
                                    'upper: ' + param.data[4],
                                    'Q1: ' + param.data[3],
                                    'median: ' + param.data[2],
                                    'Q3: ' + param.data[1],
                                    'lower: ' + param.data[0]
                                ].join('<br/>')
                            }
                        }
                    },
                    {
                        name: 'outlier',
                        type: 'scatter',
                        data: data.outliers
                    }
                ]
            };
            popu_language.hideLoading();
            popu_language.setOption(option);
        }
    });
});