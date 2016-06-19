/**
 * popularity field gra statistic
 */
$(document).ready(function () {
    var field_number = echarts.init(document.getElementById('gra-field-number'));
    field_number.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/fieldNumber",
        success:function (stardata) {
            option = {
                title: [
                    {
                        text: 'Repository number of some popular fields',
                        subtext:'',
                        left: 'center',
                    }
                ],
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data:['repository number']
                },
                xAxis: [
                    {
                        type: 'category',
                        data: ['node.js','javascript','library','ruby','web','api','vim','plugin','rust','app','server','json']
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: 'number',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: [
                    {
                        name:'number',
                        type:'bar',
                        data:stardata
                    }
                ]
            };
            field_number.hideLoading();
            field_number.setOption(option);
        }
    });

    var field_box = echarts.init(document.getElementById('gra-field-boxplot'));
    field_box.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/fieldNumberBox",
        success:function (stardata) {
            var data = echarts.dataTool.prepareBoxplotData(stardata);
            option = {
                title: [
                    {
                        text: 'Popularity with fields: popular repositories star numbers',
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
                    data: ['node.js','javascript','library','ruby','web','api','vim','plugin','rust','app','server','json']
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
                    data: ['node.js','javascript','library','ruby','web','api','vim','plugin','rust','app','server','json'],
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
            field_box.hideLoading();
            field_box.setOption(option);
        }
    });

    var field_create = echarts.init(document.getElementById('gra-field-create'));
    field_create.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/fieldCreate",
        success:function (stardata) {
            option = {
                title: {
                    subtext: 'Number of Repository Created with Different Fields: repositories created in mainly language since 2007',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    left: 'left',
                    data: ['node.js','javascript','library','ruby','web','api','vim','plugin','rust','app','server','json']
                },
                xAxis: {
                    type: 'category',
                    name: 'x',
                    splitLine: {show: false},
                    data: ['2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                yAxis: {
                    type: 'value',
                    name: 'y'
                },
                series: [
                    {
                        name: 'node.js',
                        type: 'line',
                        data:  stardata[0]
                    },
                    {
                        name: 'javascript',
                        type: 'line',
                        data: stardata[1]
                    },
                    {
                        name: 'library',
                        type: 'line',
                        data: stardata[2]
                    },
                    {
                        name: 'ruby',
                        type: 'line',
                        data: stardata[3]
                    },
                    {
                        name: 'web',
                        type: 'line',
                        data: stardata[4]
                    },
                    {
                        name: 'api',
                        type: 'line',
                        data: stardata[5]
                    },
                    {
                        name: 'vim',
                        type: 'line',
                        data: stardata[6]
                    },
                    {
                        name: 'plugin',
                        type: 'line',
                        data: stardata[7]
                    },
                    {
                        name: 'rust',
                        type: 'line',
                        data: stardata[8]
                    },
                    {
                        name: 'app',
                        type: 'line',
                        data: stardata[9]
                    },
                    {
                        name: 'server',
                        type: 'line',
                        data: stardata[10]
                    },
                    {
                        name: 'json',
                        type: 'line',
                        data: stardata[11]
                    }
                ]
            };

            field_create.hideLoading();
            field_create.setOption(option);
        }
    });
})
