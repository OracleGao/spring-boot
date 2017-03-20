# 目标

- 远程更新应用
- 远程debug

# 前提条件
项目中已经增加了devtools功能

# 远程更新应用
项目部署在远程服务器上，项目代码和配置在本地。通过修改本地项目的代码和配置，直接同步修改部署在远程的服务器上的该项目的编译后代码和配置并即时生效。

## 远程项目发布前准备
1. 在pom.xml中修改spring-boot-maven-plugin配置添加excludeDevtools配置项
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
