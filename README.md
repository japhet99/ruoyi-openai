# 介绍
   RuoYi-OpenAI  是基于不分离应用若依框架拓展出来的一款AI-Chat产品， RuoYi + OpenAI  [演示地址](https://git.byabstudio.com) 
   
   账号自己注册，如果提示“注册失败!请联系管理员或切换网络重试”  可以切换wifi或者流量，重新注册，因为每一个ip只能注册一个账号

# 功能
  1. 支持多种大模型：
     + [OpenAI ChatGPT 系列模型](https://platform.openai.com/docs/guides/gpt/chat-completions-api)（支持 [Azure OpenAI API](https://learn.microsoft.com/en-us/azure/ai-services/openai/reference)）
     + [Anthropic Claude 系列模型](https://anthropic.com)
     + [Google PaLM2/Gemini 系列模型](https://developers.generativeai.google)
     + [百度文心一言系列模型](https://cloud.baidu.com/doc/WENXINWORKSHOP/index.html)
     + [阿里通义千问系列模型](https://help.aliyun.com/document_detail/2400395.html)
     + [讯飞星火认知大模型](https://www.xfyun.cn/doc/spark/Web.html)
   
  2、支持开设分站
  
  3、支持配置第三方代理服务
  
  4、支持 stream 流式输出文字
  
  5、支持用户邀请奖励、分佣
  
  6、支持发布公告等

  7、midjourney绘画接入

  8、DALL.E 绘画接入

  9、配置易支付收款，收款无忧

  10、AI联网、AI语音、AI-PPT、AI-PDF等

# 获取key

   [获取api 秘钥 ](https://api.byabstudio.com/)

# 运行
 1. 下载项目（安装maven、jdk、mysql）
 2. 项目导入idea
 3. 导入mysql数据(创建数据库)
 4. 修改配置文件：（端口、mysql密码等）
 5. 数据配置：（1、sys_config表 2、配置域名：sys_dict_data表）
 6. 启动项目
 7. 打开浏览器访问 http://localhost:1234
 8. 这个时候发送内容会报错，因为需要配置key，如果你有官方的key，就把对应的url 和 key粘贴到sys_config表中，仔细看表，有注释，如果没有的话，可以去我这里购买key，比官方要便宜 [获取api 秘钥 ](https://api.byabstudio.com/) 


# 地址(账号密码： admin   admin123)
   1. 后台：http://localhost:1234/japhets
   2. 前台：http://localhost:1234
   3. 合伙人： http://localhost:1234/partner
   4. 支付对接： https://ypay.byabstudio.com （请联系我，我教你）

# 打包
 1. 打包前修改配置内容是服务器上面的内容
 2. mvn clean package

# 部署
 1. 如果用的是我中转站的api就可以是国内的服务器，如果是用官方的api,就需要海外的服务器
 2. 服务器2c4g就够了
 3. 安装MySQL、jdk、运行jar包
 4. 解析域名
 5. .....

# 交流群

添加QQ进群，请备注 "ruoyi-openai"：
![image](https://github.com/japhet99/ruoyi-openai/assets/38454934/c58931a7-d135-4804-b350-c008a7a1c03e)

# 软件架构

  感谢若依框架   [若依官网](http://www.ruoyi.vip)
