# 添加 actuator支持 
- 添加maven依赖
- 添加配置
- 添加远程关闭服务

# 访问 actuator 接口
## url
http://localhost:18080/actuator/health
http://localhost:18080/actuator/configprops
http://localhost:18080/actuator/env
http://localhost:18080/actuator/trace
http://localhost:18080/actuator/loggers
http://localhost:18080/actuator/metrics
http://localhost:18080/actuator/autoconfig
http://localhost:18080/actuator/mappings
http://localhost:18080/actuator/beans
http://localhost:18080/actuator/dump
## method
get

# 远程关闭服务
## url
http://localhost:18080/actuator/shutdown
## method
post
