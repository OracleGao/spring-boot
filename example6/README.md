#Դ�����
example2

#Ŀ��
- Զ�̸���Ӧ��
- Զ��debug

#���Զ�̸���֧��

1.��pom.xml���޸�spring-boot-maven-plugin�������excludeDevtools������
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