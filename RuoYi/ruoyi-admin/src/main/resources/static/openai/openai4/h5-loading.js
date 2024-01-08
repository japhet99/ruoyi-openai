
$("body").before(`
		<div class="potato_loading potato_toast hide">
			<div class="potato_loading1 potato_loading3">
				<p class="potato_loading1_title potato_loading3_title"></p>
			</div>
		</div>
		<div class="potato_loading potato_stre hide">
			<div class="potato_loading_zhe"></div>
			<div class="potato_loading1 potato_loading2">
				<div class="potato_loading1_Icon"></div>
				<p class="potato_loading1_title">加载中...</p>
			</div>
		</div>
	`)

/***************************loading加载******************************************/

// 开始显示loading
const loadingShow=()=>{
	$(".potato_stre").removeClass("hide");
	setTimeout(()=>{
		$(".potato_loading_zhe").addClass("potato_loading_zhe_tr");
		$(".potato_loading2").addClass("potato_loading_zhe_tr");
	},30)
}
// 隐藏loading
const loadingHide=()=>{
	$(".potato_loading_zhe").removeClass("potato_loading_zhe_tr");
	$(".potato_loading2").removeClass("potato_loading_zhe_tr");
	setTimeout(()=>{
		$(".potato_stre").addClass("hide");
	},360)
}
/*
	包含时间自动隐藏的loading
	time：加载的时间
*/ 
const loadingFun=(time)=>{
	loadingShow();
	setTimeout(()=>{
		loadingHide();
	},time)
}

/********************toast消息提示框************************************/

// toast消息提示框显示
const tispShow=()=>{
	$(".potato_toast").removeClass("hide");
	setTimeout(()=>{
		$(".potato_loading3").addClass("potato_loading_zhe_tr");
	},30)
}
// toast消息提示框隐藏
const tispHide=()=>{
	$(".potato_loading3").removeClass("potato_loading_zhe_tr");
	setTimeout(()=>{
		$(".potato_toast").addClass("hide");
	},360)
}
/*
	自定义消息提示框toast
	time：时间
	title：文案
*/
const tispFun=(title,time)=>{
	tispShow();
	$(".potato_loading3_title").html(title);
	setTimeout(()=>{
		tispHide();
	},time)
}