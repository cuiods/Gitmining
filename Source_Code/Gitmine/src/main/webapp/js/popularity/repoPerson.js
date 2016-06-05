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
});