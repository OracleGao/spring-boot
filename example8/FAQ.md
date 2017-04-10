# failed to get node info for {#transport#-1}{xxx.xxx.xxx.xxx}{xxx.xxx.xxx.xxx:9300}, disconnecting...
## 问题特征
server log: Received message from unsupported version: [2.0.0] minimal compatible version is: [5.0.0]
## 解决办法
目前spring data elasticsearch暂不支持2.4.4+版本
降低elasticsearch版本到2.4.4
[下载2.4.4版本](https://www.elastic.co/downloads/past-releases/elasticsearch-2-4-4)
