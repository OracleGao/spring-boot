#Application.properties vs application.yml

#Add json message support
add jackson dependency into pom.xml

#Add xml message support
add jackson xml dependency into pom.xml

#Develop a new restful api with json message support
http://localhost:18080/message
post
content-type: text/xml
<xml>
<fromUserName>Clyne</fromUserName>
<toUserName>MessageServer</toUserName>
<content>hello world!</content>
</xml>