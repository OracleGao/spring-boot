# 目标

- 更新远程应用
- 远程debug

# project base on
example2

# 前提条件
项目中已经增加了devtools功能

# 更新远程应用
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
    # secret要复杂并且难以猜测，否则远程同步服务就变成后门了
    spring.devtools.remote.secret: secret
    #添加sync-trigger文件，当该文件被更新，并且其它代码或配置项修改才会触发远程更新操作
    spring.devtools.restart.trigger-file: sync-trigger
```
## 发布远程项目
   打包发布
## 启动本地服务同步代码和配置
### 要点
1. 使用devtools提供的启动类启动同步服务
   org.springframework.boot.devtools.RemoteSpringApplication
2. 远程服务入口作为启动参数
   http://${远程项目主机地址}:${远程项目端口}/${项目的contextpath}
3. 要在本地项目上下文中启动同步服务（classpath上要有与远程服务一一对应的完整的编译后的代码和配置）

# 远程debug
## 设置JAVA_OPTS参数
### linux
```shell
#!/usr/bin/env bash
export set JAVA_OPTS="${JAVA_OPTS} -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=8000"
./mvnw clean install spring-boot:run
```
参数说明
- server server模式
- transport 远程调试连接方式
- suspend 启动服务时是否等待调试服务连接
- address 调试端口

### windows
设置系统环境变量

# reference
http://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/#using-boot-devtools-restart