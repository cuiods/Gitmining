/**
 * get popularity statistic chart
 * @author cuihao
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

    var popu_lanrate = echarts.init(document.getElementById('gra-popularity-lanrate'));
    popu_lanrate.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/languageRate",
        success:function (rate) {
            option = {
                title : {
                    text: 'Language rate of repositorys whose star number > 300',
                    subtext: 'Data from Github',
                    x:'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:rate.language
                },
                series: [
                    {
                        name:'language rate:',
                        type:'pie',
                        radius: ['50%', '70%'],
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
                            {value:rate.count[0], name:rate.language[0]},
                            {value:rate.count[1], name:rate.language[1]},
                            {value:rate.count[2], name:rate.language[2]},
                            {value:rate.count[3], name:rate.language[3]},
                            {value:rate.count[4], name:rate.language[4]},
                            {value:rate.count[5], name:rate.language[5]},
                            {value:rate.count[6], name:rate.language[6]},
                            {value:rate.count[7], name:rate.language[7]},
                            {value:rate.count[8], name:rate.language[8]},
                            {value:rate.count[9], name:rate.language[9]},
                            {value:rate.count[10], name:rate.language[10]},
                            {value:rate.count[11], name:rate.language[11]}
                        ]
                    }
                ]
            };
            popu_lanrate.hideLoading();
            popu_lanrate.setOption(option);
        }
    });

    var language_yearRate = echarts.init(document.getElementById('gra-popularity-rateyear'));
    language_yearRate.showLoading();
    $.ajax({
        type: "GET",
        url:"/popularity/languageYearRate",
        success:function (yeardata) {
            option = {
                title: {
                    text: '',
                    x: 'center'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:yeardata[0]
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
                        data : ['2008','2009','2010','2011','2012','2013','2014','2015','2016']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name: yeardata[0][0],
                        type:'line',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        areaStyle: {normal: {}},
                        data:yeardata[1]
                    },
                    {
                        name:yeardata[0][1],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[2]
                    },
                    {
                        name:yeardata[0][2],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[3]
                    },
                    {
                        name:yeardata[0][3],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[4]
                    },
                    {
                        name:yeardata[0][4],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[5]
                    },
                    {
                        name:yeardata[0][5],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[6]
                    },
                    {
                        name:yeardata[0][6],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[7]
                    },
                    {
                        name:yeardata[0][7],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[8]
                    },
                    {
                        name:yeardata[0][8],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[9]
                    },
                    {
                        name:yeardata[0][9],
                        type:'line',
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:yeardata[10]
                    }
                ]
            };
            language_yearRate.hideLoading();
            language_yearRate.setOption(option);
        }
    });

    var language_yearRate2 = echarts.init(document.getElementById('gra-popularity-rateyear2'));
    language_yearRate2.showLoading();
    $.ajax({
        type: "GET",
        url:"/popularity/languageRateYear",
        success:function (yeardata) {
            var dataMap = {};
            function dataFormatter(obj) {
                var pList = yeardata[0];
                var temp;
                for (var year = 2008; year <= 2016; year++) {
                    var max = 0;
                    var sum = 0;
                    temp = obj[year];
                    for (var i = 0, l = temp.length; i < l; i++) {
                        max = Math.max(max, temp[i]);
                        sum += temp[i];
                        obj[year][i] = {
                            name : pList[i],
                            value : temp[i]
                        }
                    }
                    obj[year + 'max'] = Math.floor(max / 100) * 100;
                    obj[year + 'sum'] = sum;
                }
                return obj;
            }
            dataMap.dataGDP = dataFormatter({
                2016:yeardata[9],
                2015:yeardata[8],
                2014:yeardata[7],
                2013:yeardata[6],
                2012:yeardata[5],
                2011:yeardata[4],
                2010:yeardata[3],
                2009:yeardata[2],
                2008:yeardata[1]
            });
            option = {
                baseOption: {
                    timeline: {
                        // y: 0,
                        axisType: 'category',
                        // realtime: false,
                        // loop: false,
                        autoPlay: true,
                        // currentIndex: 2,
                        playInterval: 1000,
                        // controlStyle: {
                        //     position: 'left'
                        // },
                        data: [
                            '2008-01-01','2009-01-01','2010-01-01','2011-01-01',
                            '2012-01-01','2013-01-01','2014-01-01','2015-01-01','2016-01-01'
                        ],
                        label: {
                            formatter : function(s) {
                                return (new Date(s)).getFullYear();
                            }
                        }
                    },
                    title: {
                        subtext: 'Data from Github'
                    },
                    tooltip: {},
                    legend: {
                        x: 'right',
                        data: ['create number']
                    },
                    calculable : true,
                    grid: {
                        top: 80,
                        bottom: 100
                    },
                    xAxis: [
                        {
                            'type':'category',
                            'axisLabel':{'interval':0},
                            'data':yeardata[0],
                            splitLine: {show: false}
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: 'num',
                            // max: 53500
                            max: 6000
                        }
                    ],
                    series: [
                        {name: 'create number', type: 'bar'},
                        {
                            name: 'create rate',
                            type: 'pie',
                            center: ['75%', '35%'],
                            radius: '25%'
                        }
                    ]
                },
                options: [
                    {
                        title: {text: 'create number of 2008'},
                        series: [
                            {data: dataMap.dataGDP['2008']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[1][0]+1},
                                {name: yeardata[0][1], value: yeardata[1][1]+1},
                                {name: yeardata[0][2], value: yeardata[1][2]+1},
                                {name: yeardata[0][3], value: yeardata[1][3]+1},
                                {name: yeardata[0][4], value: yeardata[1][4]+1},
                                {name: yeardata[0][5], value: yeardata[1][5]+1}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2009'},
                        series: [
                            {data: dataMap.dataGDP['2009']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[2][0]},
                                {name: yeardata[0][1], value: yeardata[2][1]},
                                {name: yeardata[0][2], value: yeardata[2][2]},
                                {name: yeardata[0][3], value: yeardata[2][3]},
                                {name: yeardata[0][4], value: yeardata[2][4]},
                                {name: yeardata[0][5], value: yeardata[2][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2010'},
                        series: [
                            {data: dataMap.dataGDP['2010']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[3][0]},
                                {name: yeardata[0][1], value: yeardata[3][1]},
                                {name: yeardata[0][2], value: yeardata[3][2]},
                                {name: yeardata[0][3], value: yeardata[3][3]},
                                {name: yeardata[0][4], value: yeardata[3][4]},
                                {name: yeardata[0][5], value: yeardata[3][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2011'},
                        series: [
                            {data: dataMap.dataGDP['2011']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[4][0]},
                                {name: yeardata[0][1], value: yeardata[4][1]},
                                {name: yeardata[0][2], value: yeardata[4][2]},
                                {name: yeardata[0][3], value: yeardata[4][3]},
                                {name: yeardata[0][4], value: yeardata[4][4]},
                                {name: yeardata[0][5], value: yeardata[4][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2012'},
                        series: [
                            {data: dataMap.dataGDP['2012']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[5][0]},
                                {name: yeardata[0][1], value: yeardata[5][1]},
                                {name: yeardata[0][2], value: yeardata[5][2]},
                                {name: yeardata[0][3], value: yeardata[5][3]},
                                {name: yeardata[0][4], value: yeardata[5][4]},
                                {name: yeardata[0][5], value: yeardata[5][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2013'},
                        series: [
                            {data: dataMap.dataGDP['2013']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[6][0]},
                                {name: yeardata[0][1], value: yeardata[6][1]},
                                {name: yeardata[0][2], value: yeardata[6][2]},
                                {name: yeardata[0][3], value: yeardata[6][3]},
                                {name: yeardata[0][4], value: yeardata[6][4]},
                                {name: yeardata[0][5], value: yeardata[6][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2014'},
                        series: [
                            {data: dataMap.dataGDP['2014']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[7][0]},
                                {name: yeardata[0][1], value: yeardata[7][1]},
                                {name: yeardata[0][2], value: yeardata[7][2]},
                                {name: yeardata[0][3], value: yeardata[7][3]},
                                {name: yeardata[0][4], value: yeardata[7][4]},
                                {name: yeardata[0][5], value: yeardata[7][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2015'},
                        series: [
                            {data: dataMap.dataGDP['2015']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[8][0]},
                                {name: yeardata[0][1], value: yeardata[8][1]},
                                {name: yeardata[0][2], value: yeardata[8][2]},
                                {name: yeardata[0][3], value: yeardata[8][3]},
                                {name: yeardata[0][4], value: yeardata[8][4]},
                                {name: yeardata[0][5], value: yeardata[8][5]}
                            ]}
                        ]
                    },
                    {
                        title: {text: 'create number of 2016'},
                        series: [
                            {data: dataMap.dataGDP['2016']},
                            {data: [
                                {name: yeardata[0][0], value: yeardata[9][0]},
                                {name: yeardata[0][1], value: yeardata[9][1]},
                                {name: yeardata[0][2], value: yeardata[9][2]},
                                {name: yeardata[0][3], value: yeardata[9][3]},
                                {name: yeardata[0][4], value: yeardata[9][4]},
                                {name: yeardata[0][5], value: yeardata[9][5]}
                            ]}
                        ]
                    }
                ]
            };
            language_yearRate2.hideLoading();
            language_yearRate2.setOption(option);
        }
    });
});