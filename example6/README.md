# 源码基于项目
example2

# 目标

- 远程更新应用
- 远程debug

# 添加远程更新支持

1.在pom.xml中修改spring-boot-maven-plugin配置添加 __excludeDevtools配置项__
```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludeDevtools>false</excludeDevtools>
				</configuration>
			</plugin>
		</plugins>
	</build>
```
