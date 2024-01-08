
let menuList;
getMenu('','','','');
function getMenu(title,mId,muuid,mType){
    let formData = `title=${title}`
    fetch('/api/chat/getMenu',{
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then((response) => response.json())
    .then((res) => {
        menuList = res.data.menu
        let result = getMenuList(menuList)
        $(".menuList").html(result)
        if(muuid.length==0){
            changeContent(menuList[0]["type"],menuList[0]["openaiPromptsList"][1]["id"],menuList[0]["openaiPromptsList"][1]["uuid"],menuList[0]["name"])
        }else{
            if(mType==1){
                changeContent(mType,mId,muuid,'ChatGPT3.5')
            }else if(mType==2){
                changeContent(mType,mId,muuid,'ChatGPT4.0')
            }else if(mType==3){
                changeContent(mType,mId,muuid,'ChatGPT-PDF')
            }else if(mType==5){
                changeContent(mType,mId,muuid,'ChatGPT-AI识图')
            }else if(mType==6){
                changeContent(mType,mId,muuid,'ChatGPT-AI联网')
            }else{
                changeContent(mType,mId,muuid,'欢迎使用chatgpt')
            }
        }
    })
}

function getMenuList(menuList) {
   let str = 
   `
        <ul class="menuItem">
        ${menuList.map(roleList=>{
            return `<li class="">
                 <div class="hover:text-gray-400">${roleList.name}</div>
                <ul>
                ${roleList.openaiPromptsList.map(item=>{
                    if(item.type != 1 && item.type != 2 && item.type != 3 && item.type != 5 && item.type != 6){
                        return `
                        <ul>
                          <li
                            class="menu-li flex mb-2 items-center dataMode h-12 px-1 py-1 border rounded-md border-white  hover:bg-white hover:text-black cursor-pointer" aa=${item.uuid} onClick="changeContent(${roleList.type},${item.id},'${item.uuid}','${item.title}')">
                              <img style="width: 32px;height: 32px; " class="shrink-0 mr-2 rounded-full" src="${item.icon}" alt="" srcset="">
                            <div style="flex: 1; " class="overflow-hidden text-xs relative">
                              <div class="truncate">${item.title}</div>
                              <div class="truncate ">${item.content}</div>
                              <div class="closeIcon absolute top-0 right-0 hidden" onClick="deleteTab(${item.id},'${item.uuid}')">
                               <svg t="1691401958145" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3059" width="25" height="25"><path d="M106.666667 213.333333h810.666666v42.666667H106.666667z" fill="#000000" p-id="3060"></path><path d="M640 128v42.666667h42.666667V128c0-23.573333-19.093333-42.666667-42.538667-42.666667H383.872A42.496 42.496 0 0 0 341.333333 128v42.666667h42.666667V128h256z" fill="#000000" p-id="3061"></path><path d="M213.333333 896V256H170.666667v639.957333C170.666667 919.552 189.653333 938.666667 213.376 938.666667h597.248C834.218667 938.666667 853.333333 919.68 853.333333 895.957333V256h-42.666666v640H213.333333z" fill="#000000" p-id="3062"></path><path d="M320 341.333333h42.666667v384h-42.666667zM490.666667 341.333333h42.666666v384h-42.666666zM661.333333 341.333333h42.666667v384h-42.666667z" fill="#000000" p-id="3063"></path></svg>
                              </div>
                            </div>
                          </li>
                        </ul>
                     `;
                    }else{
                        if("创建新会话"==`${item.title}`){
                            return `
                                <ul>
                                  <li
                                    class=" menu-li flex mb-2 items-center dataMode h-12 px-1 py-1 border rounded-md border-white hover:bg-white hover:text-black cursor-pointer " aa=${item.uuid} onClick="changeContent(${roleList.type},${item.id},'${item.uuid}','${item.title}')">
                                      <img style="width: 32px;height: 32px; " class="shrink-0 mr-2 rounded-full" src="${item.icon}" alt="" srcset="">
                                    <div style="flex: 1; " class="overflow-hidden text-xs">
                                      <div class="truncate">${item.title}</div>
                                      <div class="truncate ">${item.content}</div>
                                    </div>
                                  </li>
                                </ul>
                             `;
                        }else{
                            return `
                                <ul>
                                  <li
                                    class="menu-li flex mb-2 items-center dataMode h-12 px-1 py-1 border rounded-md border-white  hover:bg-white hover:text-black cursor-pointer" aa=${item.uuid} onClick="changeContent(${roleList.type},${item.id},'${item.uuid}','${item.title}')">
                                      <img style="width: 32px;height: 32px; " class="shrink-0 mr-2 rounded-full" src="${item.icon}" alt="" srcset="">
                                    <div style="flex: 1; " class="overflow-hidden text-xs relative">
                                      <div class="truncate">${item.title}</div>
                                      <div class="truncate ">${item.content}</div>
                                      <div class="closeIcon absolute top-0 right-0 hidden" onClick="deleteTab(${item.id},'${item.uuid}')">
                                       <svg t="1691401958145" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3059" width="25" height="25"><path d="M106.666667 213.333333h810.666666v42.666667H106.666667z" fill="#000000" p-id="3060"></path><path d="M640 128v42.666667h42.666667V128c0-23.573333-19.093333-42.666667-42.538667-42.666667H383.872A42.496 42.496 0 0 0 341.333333 128v42.666667h42.666667V128h256z" fill="#000000" p-id="3061"></path><path d="M213.333333 896V256H170.666667v639.957333C170.666667 919.552 189.653333 938.666667 213.376 938.666667h597.248C834.218667 938.666667 853.333333 919.68 853.333333 895.957333V256h-42.666666v640H213.333333z" fill="#000000" p-id="3062"></path><path d="M320 341.333333h42.666667v384h-42.666667zM490.666667 341.333333h42.666666v384h-42.666666zM661.333333 341.333333h42.666667v384h-42.666667z" fill="#000000" p-id="3063"></path></svg>
                                      </div>
                                    </div>
                                  </li>
                                </ul>
                             `;
                        }
                    }
                }).join("")}
                </ul>
            </li>`
        }).join("")}
        </ul>
    `
   return str
}


/**
 * 切换内容前面进行重置操作
 * */
function changeContent(mType,id,uuid,title) {

    pageNum = 1;
    pageSize = 10;
    count = 1;
    temp = true;
    messageId=id;
    mType=mType;
    uuids = uuid;
    let list =  document.getElementsByClassName("menu-li")
    for (let i = 0; i < list.length; i++) {
        list[i].classList.remove("selectActive")
    }
    for (let i = 0; i < list.length; i++) {
        if(list[i].getAttribute("aa")==uuid){
            list[i].classList.add("selectActive")
        }
    }
    hidePage()
    $("#messageContent").html("")
    $("#sendBottom").show();
    $(".content_title").html(title)
    getMessage(pageNum,pageSize,"init",id,uuid,mType)
}

function deleteTab(mId,uuid) {
    let str = "清空";
    if (mId == 1 || mId == 2) {
        str = "删除";
    } else {
        str = "清空";
    }
    var index = layer.confirm("确认要" + str + "内容吗?", {
        btn: ['确认', '取消'] //按钮
    }, function () {
        deleteChatList(mId,uuid)
        layer.close(index);
    }, function () {
        console.log("取消....")
    });
}

function deleteChatList(mId,uuid){
    let formData = `id=`+mId+`&uuid=`+uuid;
    fetch('/api/chat/deleteChatList',{
        method: "post",
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: formData
    })
    .then((response) => response.json())
    .then((res) => {
        if(res.code!=0){
            window.location.reload();
        }
        getMenu('','','','')
    })
}