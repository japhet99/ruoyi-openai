$.ajax({
 url: "/init",
 type: 'get',
 dataType: 'html',
success: function (data) {
 $("#messageContent").html(data);
 // element.init();
},
error: function (xhr, textstatus, thrown) {
//  return layer.msg('Status:' + xhr.status + '，' + xhr.statusText + '，请稍后再试！');
 }
 });