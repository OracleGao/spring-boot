#Support actuator 
add actuator dependency into pom.xml

#Config actuator

#Access actuator interface 
##get
http://localhost:18080/actuator/health
http://localhost:18080/actuator/metrics
http://localhost:18080/actuator/mappings
http://localhost:18080/actuator/configprops
http://localhost:18080/actuator/env
http://localhost:18080/actuator/loggers
http://localhost:18080/actuator/beans
http://localhost:18080/actuator/dump
http://localhost:18080/actuator/trace

##post
http://localhost:18080/actuator/shutdown