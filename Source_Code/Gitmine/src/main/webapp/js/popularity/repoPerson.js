/**
 * statistic of person popularity
 */
$(document).ready(function () {
    var person_follower = echarts.init(document.getElementById('gra-person-follower'));
    person_follower.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/personFollower",
        success:function (persondata) {
            option = {
                title: [
                    {
                        text: 'Follower number distribution of contributors to popular repositories',
                        left: 'center'
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
                        data: ['0~9','10~19','20~29','30~39','40~49','50~59','60~69','70~79','80~89','90~99','>=100']
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
                        data:persondata
                    }
                ]
            };
            person_follower.hideLoading();
            person_follower.setOption(option);
        }
    });

    var person_follower2 = echarts.init(document.getElementById('gra-person-compare'));
    person_follower2.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/personCompare",
        success:function (persondata) {
            option = {
                title: [
                    {
                        text: 'Folower number distribution of simple users',
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
                        data: ['0~9','10~19','20~29','30~39','40~49','50~59','60~69','70~79','80~89','90~99','>=100']
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
                        data:persondata
                    }
                ]
            };
            person_follower2.hideLoading();
            person_follower2.setOption(option);
        }
    });

    var person_rate = echarts.init(document.getElementById('gra-person-rate'));
    person_rate.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/personRate",
        success:function (persondata) {
            option = {
                title : {
                    text: 'Rate of user whose followers > 10',
                    subtext: 'Data from Github',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x : 'center',
                    y : 'bottom',
                    data:['follower<=10','follower>10']
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
                        name:'contributor of popular repositories',
                        type:'pie',
                        radius : [20, 110],
                        center : ['25%', 200],
                        data:[
                            {value:persondata[0][0], name:'follower<=10'},
                            {value:persondata[0][1], name:'follower>10'}
                        ]
                    },
                    {
                        name:'common users of Github',
                        type:'pie',
                        radius : [20, 110],
                        center : ['75%', 200],
                        data:[
                            {value:persondata[1][0], name:'follower<=10'},
                            {value:persondata[1][1], name:'follower>10'}
                        ]
                    }
                ]
            };
            person_rate.hideLoading();
            person_rate.setOption(option);
        }
    });

    var person_super = echarts.init(document.getElementById('gra-person-super'));
    person_super.showLoading();
    $.ajax({
        type:"GET",
        url:"/popularity/personSuper",
        success:function (persondata) {
            var littleData = persondata.little;
            var middleData = persondata.middle;
            var largeData = persondata.large;
            var dataMap = {};
            function dataFormatter(obj) {
                var pList = ['1000~2000star','2000~2000star','3000~4000star','4000~5000star','5000~6000star','>6000star'];
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

            dataMap.dataPI = dataFormatter({
                2008:littleData[0],
                2009:littleData[1],
                2010:littleData[2],
                2011:littleData[3],
                2012:littleData[4],
                2013:littleData[5],
                2014:littleData[6],
                2015:littleData[7],
                2016:littleData[8]
            });

            dataMap.dataSI = dataFormatter({
                2008:middleData[0],
                2009:middleData[1],
                2010:middleData[2],
                2011:middleData[3],
                2012:middleData[4],
                2013:middleData[5],
                2014:middleData[6],
                2015:middleData[7],
                2016:middleData[8]
            });

            dataMap.dataTI = dataFormatter({
                2008:largeData[0],
                2009:largeData[1],
                2010:largeData[2],
                2011:largeData[3],
                2012:largeData[4],
                2013:largeData[5],
                2014:largeData[6],
                2015:largeData[7],
                2016:largeData[8]
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
                            '2012-01-01', '2013-01-01','2014-01-01','2015-01-01','2016-01-01'
                        ],
                        label: {
                            formatter : function(s) {
                                return (new Date(s)).getFullYear();
                            }
                        }
                    },
                    title: {
                        subtext: 'x: the count of repositories\'s star;     y: the sum of contributors of these repositories in a certain range.'
                    },
                    tooltip: {},
                    legend: {
                        x: 'right',
                        data: ['0~10follower', '10~100follower', '>100follower']
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
                            'data':['1000~2000star','2000~2000star','3000~4000star','4000~5000star','5000~6000star','>6000star'],
                            splitLine: {show: false}
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: 'number of user'
                        }
                    ],
                    series: [
                        {name: '0~10follower', type: 'bar'},
                        {name: '10~100follower', type: 'bar'},
                        {name: '>100follower', type: 'bar'},
                        {
                            name: 'follower distribution',
                            type: 'pie',
                            center: ['50%', '25%'],
                            radius: '28%'
                        }
                    ]
                },
                options: [
                    {
                        title: {text: '2008:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2008']},
                            {data: dataMap.dataSI['2008']},
                            {data: dataMap.dataTI['2008']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2008sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2008sum']},
                                {name: '>100follower', value: dataMap.dataTI['2008sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2009:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2009']},
                            {data: dataMap.dataSI['2009']},
                            {data: dataMap.dataTI['2009']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2009sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2009sum']},
                                {name: '>100follower', value: dataMap.dataTI['2009sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2010:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2010']},
                            {data: dataMap.dataSI['2010']},
                            {data: dataMap.dataTI['2010']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2010sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2010sum']},
                                {name: '>100follower', value: dataMap.dataTI['2010sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2011:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2011']},
                            {data: dataMap.dataSI['2011']},
                            {data: dataMap.dataTI['2011']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2011sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2011sum']},
                                {name: '>100follower', value: dataMap.dataTI['2011sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2012:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2012']},
                            {data: dataMap.dataSI['2012']},
                            {data: dataMap.dataTI['2012']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2012sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2012sum']},
                                {name: '>100follower', value: dataMap.dataTI['2012sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2013:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2013']},
                            {data: dataMap.dataSI['2013']},
                            {data: dataMap.dataTI['2013']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2013sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2013sum']},
                                {name: '>100follower', value: dataMap.dataTI['2013sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2014:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2014']},
                            {data: dataMap.dataSI['2014']},
                            {data: dataMap.dataTI['2014']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2014sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2014sum']},
                                {name: '>100follower', value: dataMap.dataTI['2014sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2015:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2015']},
                            {data: dataMap.dataSI['2015']},
                            {data: dataMap.dataTI['2015']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2015sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2015sum']},
                                {name: '>100follower', value: dataMap.dataTI['2015sum']}
                            ]}
                        ]
                    },
                    {
                        title: {text: '2016:Contributor follower distribution of popular repositories'},
                        series: [
                            {data: dataMap.dataPI['2016']},
                            {data: dataMap.dataSI['2016']},
                            {data: dataMap.dataTI['2016']},
                            {data: [
                                {name: '0~10follower', value: dataMap.dataPI['2016sum']},
                                {name: '10~100follower', value: dataMap.dataSI['2016sum']},
                                {name: '>100follower', value: dataMap.dataTI['2016sum']}
                            ]}
                        ]
                    }
                ]
            };
            person_super.hideLoading();
            person_super.setOption(option);
        }
    });
});