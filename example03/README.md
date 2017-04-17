# project base on
example2

# 使用内存数据库h2，利用mybatis增加接收到的消息持久化和查询功能
## 添加h2数据支持
- 配置maven
- 配置数据源
- 初始化数据库和数据

## 添加mybatis支持
- 配置maven
- 配置mybatis
- 添加事物支持
- 开发mapper和dao(data access object)

## 开发业务功能


## 测试查询
## url
http://localhost:18080/message
## method
get

## 测试新增持久化
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
