# ���utf-8�ַ���֧��
���application

# ��ӿ���֧��
���application����

# ���json��ϢЭ��֧��
Ĭ��֧��

# ���xml��Ϣ��ʽ֧��
���maven����

# ����Restful��Ϣ�ӿ�
- ���տͻ��˷��͵�xml��ʽ��Ϣ,������Ϣ����content��
- ����������fromUserName�ͽ�����toUserName,
- �������ã����յ�����Ϣ��ǰ׺��
- ��json��ʽ���ش��������Ϣ

## ����������example.message.resprefix

##����restful�ӿں���Ӧ����

## ����
### url
http://localhost:18080/message
### method
post
### Content-Type
text/xml
### body
<xml>
<fromUserName>Clyne</fromUserName>
<toUserName>MessageServer</toUserName>
<content>�ú�ѧϰ����������</content>
</xml>