
function getPlan() {
    $.ajax({
        type: "post",
        url: "/api/chat/priceList",
        data: {},
        dataType: 'json',
        success: function(result) {
            for (let i = 0; i < result.rows.length; i++) {
                let experienced = result.rows[i].experiencedStr;
                let style = "";
                let disabled = "";
                let divStr = "";
                if(!experienced){
                    style="background-color: gray;cursor: not-allowed";
                    disabled = "disabled";
                }else{
                    style="background-color: rgb(73, 190, 240)";
                }

                if(result.rows[i].experienced==0){
                    divStr =`
                        <div>
                            <span style="color: red;font-size: 12px;">首充可用</span>
                        </div>
                    `;
                }

                $("#append").append(`
                    <div class="product__card mx-auto w-72 flex flex-col items-center px-5 py-4 rounded-lg bg-white shadow-lg ">    
                      <div class="w-40 h-40 ">    
                          <img class="w-full h-full rounded-full" src="${result.rows[i].logo}" alt="" srcset="">    
                      </div>    
                      <div class="py-2">    
                           ${result.rows[i].name} 
                      </div>    
                      <div class="h-16 flex items-center font-bold text-4xl text-sky-400">    
                          <div>￥</div>    
                          <div> ${result.rows[i].price} </div>    
                      </div>    
                      <div>    
                          <ul>    
                              <li>    
                                  <div>    
                                      ${result.rows[i].remark}
                                  </div>    
                              </li>    
                          </ul>    
                      </div>    
                      <button  ${disabled}  style=" ${style} " onclick="subscribe(${result.rows[i].id})" class="mt-4  px-6 py-2 rounded-3xl  border-solid border-blue-300  border text-white" >    
                          <div>
                            <div>
                                <span>立即订阅</span>
                            </div>
                            ${divStr}
                          </div>
                      </button>
                    </div>
                `)
            }
        },
        error: function(error) {
        }
    });

}

function subscribe(pId){
    $.ajax({
        type: "post",
        url: "/order/createOrder",
        data: {"pId":pId},
        dataType: 'json',
        success: function(result) {
            if(result.code==0){
                // alert("请输入对应的金额进行充值!!!，如果没有充值成功，请点击最下方联系客服");
                // window.location.href = result.msg
                $("#append").html(result.msg);
            }else{
                alert(result.msg);
            }
        },
        error: function(error) {
        }
    });
}

getPlan();