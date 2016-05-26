/**
 * Created by cuihao on 2016/5/25.
 */
$(document).ready(function () {
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
                        text: 'Popularity with language',
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
                    // nameGap: 30,
                    // splitArea: {
                    //     show: false
                    // },
                    // // axisLabel: {
                    // //     formatter: 'expr {value}'
                    // // },
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
                                    'Experiment ' + param.name + ': ',
                                    'upper: ' + param.data[0],
                                    'Q1: ' + param.data[1],
                                    'median: ' + param.data[2],
                                    'Q3: ' + param.data[3],
                                    'lower: ' + param.data[4]
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