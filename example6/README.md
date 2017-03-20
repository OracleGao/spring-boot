#源码基于
example2

#目标
- 远程更新应用
- 远程debug

#添加远程更新支持

1.在pom.xml中修改spring-boot-maven-plugin配置添加excludeDevtools配置项
```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				__<configuration>__
					__<excludeDevtools>false</excludeDevtools>__
				__</configuration>__
			</plugin>
		</plugins>
	</build>
```