#Դ�����
example2

#Ŀ��
- Զ�̸���Ӧ��
- Զ��debug

#���Զ�̸���֧��
1.���maven����
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