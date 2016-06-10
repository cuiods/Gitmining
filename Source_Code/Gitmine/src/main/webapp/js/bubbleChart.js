/**
 * Created by lenovo on 2016/6/10.
 */
$(document).ready(function () {

    var languageData = new Array();
    var languageChart = null;
    console.log($('#userName').text());
    $.ajax({
        type:'GET',
        url:'/user/'+$('#userName').text()+'/basic',
        success:function(data){
            languageChart = data.languageChart;

            console.log(languageChart.field);
            console.log(languageChart.value);
            $.each(languageChart.field,function(i,field){
                if(i<9) {
                    languageData.push(
                        {
                            text: languageChart.field[i], count: languageChart.value[i].toString(),
                        }
                    );
                }
            });

            console.log(languageData);
            console.log([
                {text: languageChart.field[0], count: languageChart.value[0]},
                {text: languageChart.field[1], count: languageChart.value[1]},
                {text: languageChart.field[2], count: languageChart.value[2]},
                {text: languageChart.field[3], count: languageChart.value[3]},
                {text: languageChart.field[4], count: languageChart.value[4]},
                {text: languageChart.field[5], count: languageChart.value[5]},
                {text: languageChart.field[6], count: languageChart.value[6]},
                {text: languageChart.field[7], count: languageChart.value[7]},
                {text: languageChart.field[8], count: languageChart.value[8]},
            ]);
        }
    })
    var bubbleChart = new d3.svg.BubbleChart({
        supportResponsive: true,
        //container: => use @default
        size: 600,
        //viewBoxSize: => use @default
        innerRadius: 600 / 3.5,
        //outerRadius: => use @default
        radiusMin: 50,
        //radiusMax: use @default
        //intersectDelta: use @default
        //intersectInc: use @default
        //circleColor: use @default

        data: {
            items: 
                [
                    {text: languageChart.field[0], count: languageChart.value[0].toString()},
                    {text: languageChart.field[1], count: languageChart.value[1].toString()},
                    {text: languageChart.field[2], count: languageChart.value[2].toString()},
                    {text: languageChart.field[3], count: languageChart.value[3].toString()},
                    {text: languageChart.field[4], count: languageChart.value[4].toString()},
                    {text: languageChart.field[5], count: languageChart.value[5].toString()},
                    {text: languageChart.field[6], count: languageChart.value[6].toString()},
                    {text: languageChart.field[7], count: languageChart.value[7].toString()},
                    {text: languageChart.field[8], count: languageChart.value[8].toString()},
                ],
            eval: function (item) {return item.count;},
            classed: function (item) {return item.text.split(" ").join("");}
        },
        plugins: [
            {
                name: "central-click",
                options: {
                    // text: "(See more detail)",
                    style: {
                        "font-size": "12px",
                        "font-style": "italic",
                        "font-family": "Source Sans Pro, sans-serif",
                        //"font-weight": "700",
                        "text-anchor": "middle",
                        "fill": "white"
                    },
                    attr: {dy: "65px"},
                    centralClick: function(item) {
                        // alert("Here is more details: " + item.text + "!!");
                    }
                }
            },
            {
                name: "lines",
                options: {
                    format: [
                        {// Line #0
                            textField: "count",
                            classed: {count: true},
                            style: {
                                "font-size": "28px",
                                "font-family": "Source Sans Pro, sans-serif",
                                "text-anchor": "middle",
                                fill: "white"
                            },
                            attr: {
                                dy: "0px",
                                x: function (d) {return d.cx;},
                                y: function (d) {return d.cy;}
                            }
                        },
                        {// Line #1
                            textField: "text",
                            classed: {text: true},
                            style: {
                                "font-size": "14px",
                                "font-family": "Source Sans Pro, sans-serif",
                                "text-anchor": "middle",
                                fill: "white"
                            },
                            attr: {
                                dy: "20px",
                                x: function (d) {return d.cx;},
                                y: function (d) {return d.cy;}
                            }
                        }
                    ],
                    centralFormat: [
                        {// Line #0
                            style: {"font-size": "50px"},
                            attr: {}
                        },
                        {// Line #1
                            style: {"font-size": "30px"},
                            attr: {dy: "40px"}
                        }
                    ]
                }
            }]
    });
});
