# No property index found for type
## 问题描述
在同一个实体对象上同时声明@Entity和@Document属性时，启动出现上述异常
## 解决办法
将database respository与elasticserach respository分开到不同的包下，并在Application.java类上声明各自的包位置
@EnableJpaRepositories(basePackages = "example.repository")
@EnableElasticsearchRepositories(basePackages = "example.search")

## Reference
http://stackoverflow.com/questions/36252099/no-property-index-found-for-type-user