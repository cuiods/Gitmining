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
})