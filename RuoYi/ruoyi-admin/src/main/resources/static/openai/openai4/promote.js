getMyInfo();
function getMyInfo(){
    $.ajax({
        url: "/api/chat/getMyPromoteInfo",
        type: 'post',
        data: {},
        dataType: 'json',
        success: function (data) {
            $("#freezeMoney").text(data.data.freezeMoney);
            $("#money").text(data.data.money);
            $("#myPromoteCount").text(data.data.myPromoteCount);
            $("#registerInvitePayMoney").text(data.data.registerInvitePayMoney);
            $("#totalMoney").text(data.data.totalMoney);
            $("#promoteCode").text(data.data.promoteCode);
            $("#buyCount").text(data.data.buyCount);
            $("#inviteMoneyTime").text(data.data.inviteMoneyTime);
        },error: function (xhr, textstatus, thrown) {

        }
    });
}

function getMoney(){
    $.ajax({
        url: "/api/chat/getMoney",
        type: 'post',
        data: {},
        success: function (data) {
            alert(data.msg);
        },error: function (xhr, textstatus, thrown) {

        }
    });
}
function copyUrl(){
    let text = document.createElement('textarea');
    text.value = $("#promoteCode").text();
    document.body.appendChild(text)
// 选中文本
    text.select()
// 调用剪切板的复制
    document.execCommand('copy');
// 移除textarea元素
    document.body.removeChild(text);
    alert("复制成功");
}