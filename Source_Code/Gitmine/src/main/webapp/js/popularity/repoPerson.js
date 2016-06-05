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
                        text: 'Folower number distribution of contributors to popular repositories',
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
});