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
2. 在application配置中添加配置项
```yml
    spring.devtools.remote.secret: secret
```
## 发布远程项目

## 启动本地服务同步代码和配置
### 要点
1. 使用devtools提供的启动类启动同步服务
   org.springframework.boot.devtools.RemoteSpringApplication
2. 远程服务入口作为启动参数
   http://${远程项目主机地址}:${远程项目端口}/${项目的contextpath}
3. 要在本地项目上下文中启动同步服务（classpath上要有与远程服务一一对应的完整的编译后的代码和配置）

   
