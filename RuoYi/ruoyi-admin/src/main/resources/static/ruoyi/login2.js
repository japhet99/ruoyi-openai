const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

let prefix = window.location.hostname;

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

let common = /^[a-zA-Z0-9]{5,12}$/
// let passwordReg = `^[a-zA-Z0-9]{5,12}$`
// 登录提交
$("#signSubmit").click(function () {
  $(".userName").eq(0).hide()
  $(".password").eq(0).hide()
  let username =  $("input[name='username']").eq(0).val();
  let password = $("input[name='password']").eq(0).val()
  let code = $("input[name='code']").eq(0).val()

  if (!common.test(username)) {
    $(".userName").eq(0).show()
    $(".userName").eq(0).text("您的账号格式有误!")
    return 
  }
  if (!common.test(password)) {
    $(".password").eq(0).show()
    $(".password").eq(0).text("您的密码格式有误!")
    return 
  }
  
  let param = {
    username
    ,password
    ,rememberMe:false
  }
 let formData = `username=${param.username}&password=${param.password}&rememberMe=false&validateCode=${code}&prefix=${prefix}`
  login(formData)
  // 账号密码进行校验
})

// init code
refreshCode()
// code click..
$("#codeImg").click(function () {
  refreshCode()
})
 
// refresh code
function refreshCode(){

  var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
  $("#codeImg").attr("src", url);

}
// login 
function login (param){
  loadingShow()
  fetch(ctx + `login`,{
    method:"post",
    headers:{
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body:param
  }).then(response=>{
    if(response.ok){
      return response.json()
      }else{
      return Promise.reject({
          status: response.status,
          statusText: response.statusText
      })
      loadingHide()
  }
    // register successful,jump to login page
  }).then(data=>{
    if (data.code == web_status.SUCCESS) {
    // location.href = ctx + 'index';
    location.href = ctx + 'chat';
    } else {
      refreshCode()
      $.modal.msg(data.msg);
    }
    loadingHide()
  }).catch(err=>{
    loadingHide()
    layer.msg(err)
  })
}

$("#register").click(function () {
  register()
})

// register function
function register() {

  $(".account").eq(0).hide()
  $(".password").eq(1).hide()
  $(".repassword").eq(0).hide()
  $(".repassword").eq(0).hide()
  let username =  $("input[name='account']").eq(0).val();
  let password = $("input[name='password']").eq(1).val();
  let repassword = $("input[name='repassword']").eq(0).val();
  let inviteCode = $("input[name='inviteCode']").val()
  if (inviteCode.trim()=="") {
    inviteCode = 123456
  }
  // console.log(username,password,repassword,inviteCode);
  if (!common.test(username)) {
    $(".account").eq(0).show()
    $(".account").eq(0).text("账号由数字字母数字组成5-12位")
    return 
  }
  if (!common.test(password)) {
    $(".password").eq(1).show()
    $(".password").eq(1).text("密码由数字字母数字组成5-12位")
    return 
  }
  if (!common.test(repassword)) {
    $(".repassword").eq(0).show()
    $(".repassword").eq(0).text("密码由数字字母数字组成5-12位")
    return 
  }
  let isRead = $("#agreement").prop("checked");
  if (!isRead) {
    layer.msg("请勾选用户隐私协议")
    return
  }
  if (password!=repassword) {
    $(".repassword").eq(0).show()
    $(".repassword").eq(0).text("密码不一致")
    return
  }
let param = `loginName=${username}&password=${password}&code=${inviteCode}&isRead=true&prefix=${prefix}`

registerFetch(param)
}

function registerFetch(param){
  loadingShow()
   fetch(ctx + `register`,{
    method:"post",
    headers:{
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body:param
  }).then(response=>{
    if(response.ok){
      return response.json()
      }else{
      return Promise.reject({
          status: response.status,
          statusText: response.statusText
      })
  }
     loadingHide()
  }).then(data=>{
    if (data.code == web_status.SUCCESS) {
      layer.msg("<span color='red'>恭喜您，账号注册成功!</span>");
      container.classList.remove("sign-up-mode");
    } else {
      layer.msg(data.msg)
    }
     loadingHide()
  }).catch(err=>{
     loadingHide()
    layer.msg(err)
  })
  refreshCode()
}




function checkResult(str,common){
  return common.test(str)
}

function GetQueryString(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r!=null)return  unescape(r[2]); return null;
}

var pathSearch = window.location.search.substr(1);
if(pathSearch){
  $("input[name='inviteCode']").val(GetQueryString("code"));
  container.classList.add("sign-up-mode");
}


