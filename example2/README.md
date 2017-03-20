# 添加utf-8字符集支持
添加application

# 添加跨域支持
添加application配置

# 添加json消息协议支持
默认支持

# 添加xml消息格式支持
添加maven依赖

# 开发Restful消息接口
- 接收客户端发送的xml格式消息,解析消息内容content，
- 调换发送者fromUserName和接收者toUserName,
- 根据配置，给收到的消息加前缀并
- 以json格式返回处理过的消息

## 处理配置项example.message.resprefix

##开发restful接口和相应功能

## 测试
### url
http://localhost:18080/message
### method
post
### Content-Type
text/xml
### body
```xml
<xml>
<fromUserName>Clyne</fromUserName>
<toUserName>MessageServer</toUserName>
<content>好好学习，天天向上</content>
</xml>
```
