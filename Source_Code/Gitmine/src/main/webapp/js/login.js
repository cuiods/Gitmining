var interestLabel = {
	registerLogin:$('#login-href').text(),nodeJs:0, javascript:0, library:0, ruby:0, web:0, api:0, vim:0,
	plugin:0, rust:0, app:0, client:0, server:0, json:0, framework:0,
	python:0, browser:0, rails:0, css:0, android:0, jquery:0, html:0,
	test:0, php:0, command:0, tool:0, demo:0, wrapper:0, ios:0,
	linux:0, windows:0, osX:0, django:0, google:0, generator:0, docker:0,
	image:0, template:0
};
// var labelFather = $('#interestLabel');



$(function(){
	
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'-8px',width:'70px'});
		$('#qlogin').css('display','none');    <!--隐藏属性-->
		$('#web_qr_login').css('display','block');
		
		});
	$('#switch_login').click(function(){
		
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'140px',width:'70px'});
		
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});
		if(getParam("a")=='0')
		{
			$('#switch_login').trigger('click');
		}

	});
	
function logintab(){
	scrollTo(0);
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'154px',width:'96px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	
}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    } 
    else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}  


var pwdmin = 6;

$(document).ready(function() {
	$.ajax({
		type:"GET",
		url:"/login/name",
		success:function (result) {
			if (result.length>2) {
				$("#login-href-text").html(result);
				// $("#interestModal").modal();
				$('#login-href-text').attr('href','html/html/personal.html');
				// $('#login-href').attr('href','#interestModal');
			}
		}
	});

	$('#login-href').click(function(){
		if($('#login-href-text').text()=='Login'){
			$('#myModal').modal();
		}
	})

	$('#personal').click(function(){
		if($('#login-href-text').text()=='Login'){
			$('#myModal').modal();
			// $('personal').attr('href','');
		}else{
			$('#personal').attr('href','/html/html/personal.html');
		}
	})

	$('#reg').click(function() {

		if ($('#user').val() == "") {
			$('#user').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×User name cannot be empty.</b></font>");
			return false;
		}



		if ($('#user').val().length < 4 || $('#user').val().length > 16) {

			$('#user').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×username should have 4-16 characters.</b></font>");
			return false;

		}
		
		if ($('#passwd').val().length < pwdmin) {
			$('#passwd').focus();
			$('#userCue').html("<font color='red'><b>×the password cannot be less than " + pwdmin + "bits</b></font>");
			return false;
		}
		if ($('#passwd2').val() != $('#passwd').val()) {
			$('#passwd2').focus();
			$('#userCue').html("<font color='red'><b>×the two passwords are not same！</b></font>");
			return false;
		}

		$.ajax({
			type: "GET",
			url: "/login/register",
			data: { username:$("#user").val(),password:$("#passwd").val(),email:$("#email_input").val()}, <!--要传递的数据--> 			<!--接受数据的格式-->
			success: function(result) {
				if(result){
					$('#myModal').modal('hide');
					$('#login-href-text').text($('#user').val());
					$('#interestModal').modal();
				}else{
					alert("please try again!");
				}
			},
			error: function () {
				alert("unknown error.");
			}
		});

		// var sqq = /^[1-9]{1}[0-9]{4,9}$/;
		// if (!sqq.test($('#qq').val()) || $('#qq').val().length < 5 || $('#qq').val().length > 12) {
		// 	$('#qq').focus().css({
		// 		border: "1px solid red",
		// 		boxShadow: "0 0 2px red"
		// 	});
		// 	$('#userCue').html("<font color='red'><b>×the format of email is not correct!</b></font>");return false;
		// } else {
		// 	$('#qq').css({
		// 		border: "1px solid #D7D7D7",
		// 		boxShadow: "none"
		// 	});
		//
		// }

	})
	
	$("#login_btn").click(function () {
		$.ajax({
			type:"GET",
			url:"/login/login",
			data: { username:$("#u").val(),password:$("#p").val()},
			success:function (result) {
				location.href = "";
			},
			error:function () {
				alert("error!");
			}
		});
	});
	
	$('#login_out').click(function(){
		$.ajax({
			type:'GET',
			url:'/login/logout',
			success:function(result){
				if(result){
					location.href = "";
				}
			}
		})
	})


	//for interest labels
	initInterestLabel();
	
	$('#finishInterest').click(function(){
		// var checkList = $('#interestLabel').find('.checkLabel');
		var i=0;
		$.each(interestLabel,function(key,value){
			if(i==0){

			}else{
				var checkbox = $('#interestLabel').find('.checkLabel').eq(i-1);
				if(checkbox.is(":checked")){
					interestLabel[key] = 1;
				}
			}
			i++;
		});

		console.log(interestLabel);
		var hobby = $.toJSON(interestLabel);
		console.log(hobby);
		$.ajax({
			type:'POST',
			url:'/login/hobby',
			data: hobby,
			dataType:'json',
			contentType:'application/json; charset=gbk',
			error:function(){
				alert("failed!");
			}
		})

	});

});

//initailize the interest Labels



function initInterestLabel(){
	var i=0;

	$.each(interestLabel,function(key,value){
		if(i==0){
			interestLabel[key] = $('#login-href-text').text();
			console.log($('#login-href-text').text());
			i++;
		}else {
			interestLabel[key] = 0;
			// console.log(interestLabel[key]);

			var check = "<input type=\"checkbox\" id=\"" + key + "\" class=\"checkLabel\" value=\"false\">";
			var label = "<label class=\"label\" for=\"" + key + "\">" + key + "</label>";

			$("#interestLabel").append(check);
			$("#interestLabel").append(label);

		}

	})
};

$(function() {
	/**
	 * for each menu element, on mouseenter,
	 * we enlarge the image, and show both sdt_active span and
	 * sdt_wrap span. If the element has a sub menu (sdt_box),
	 * then we slide it - if the element is the last one in the menu
	 * we slide it to the left, otherwise to the right
	 */
	$('#sdt_menu > li').bind('mouseenter',function(){
		var $elem = $(this);
		$elem.find('img')
			.stop(true)
			.animate({
				'width':'120px',
				'height':'40px',
				'left':'0px'
			},400,'easeOutBack')
			.andSelf()
			.find('.sdt_wrap')
			.stop(true)
			.animate({'top':'80px'},500,'easeOutBack')
			.andSelf()
			.find('.sdt_active')
			.stop(true)
			.animate({'height':'120px'},300,function(){
				var $sub_menu = $elem.find('.sdt_box');
				if($sub_menu.length){
					var left = '130px';
					if($elem.parent().children().length == $elem.index()+1)
						left = '-130px';
					$sub_menu.show().animate({'left':left},200);
				}
			});
	}).bind('mouseleave',function(){
		var $elem = $(this);
		var $sub_menu = $elem.find('.sdt_box');
		if($sub_menu.length)
			$sub_menu.hide().css('left','0px');

		$elem.find('.sdt_active')
			.stop(true)
			.animate({'height':'0px'},300)
			.andSelf().find('img')
			.stop(true)
			.animate({
				'width':'0px',
				'height':'0px',
				'left':'85px'},400)
			.andSelf()
			.find('.sdt_wrap')
			.stop(true)
			.animate({'top':'10px'},500);
	});
});
