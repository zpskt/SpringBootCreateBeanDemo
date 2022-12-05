# 02-设置自定义配置项
在使用别人组件的时候，就拿Spring来说server.port这种的，直接yml文件就就会给你提示，这里就是要实现自定义配置项在编辑时自动提示。
# 添加配置依赖
需要添加配置依赖处理，不需要加版本
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <!-- 通常无需指定版本，会根据所使用SpringBoot版本自动匹配 -->
        </dependency>
```
# 创建properties类
新建属性类
```java
@Component
@ConfigurationProperties(prefix = "user.info") //配置前缀
public class MyProperties {
    private String name;
    private Integer age;

}
```
# 配置json文件
编译项目后生成target文件夹，在target/classes/META-INF/目录下会生成spring-configuration-metadata.json
原始文件张这样
```json
{
  "groups": [
    {
      "name": "myconfig",
      "type": "org.example.properties.MyConfig",
      "sourceType": "org.example.properties.MyConfig"
    },
    {
      "name": "user.info",
      "type": "org.example.properties.MyProperties",
      "sourceType": "org.example.properties.MyProperties"
    }
  ],
  "properties": [],
  "hints": []
}
```
我们需要把这个文档放到源目录下src/main/resources/META-INF/。
为了实现写配置的时候有属性值具体提示，我们可以设置一些默认值的选项。
```json
{
  "hints": [
    {
      "name": "user.info.age",
      "values": [
        {
          "value": 18
        },
        {
          "value": 20
        }
      ]
    }
  ],
  "groups": [
    {
      "sourceType": "org.example.properties.MyProperties",
      "name": "随便乱取",
      "type": "org.example.properties.MyProperties"
    }
  ],
  "properties": [
    {
      "sourceType": "org.example.properties.MyProperties",
      "name": "user.info.Name",
      "type": "java.lang.String"
    },
    {
      "sourceType": "org.example.properties.MyProperties",
      "name": "user.info.age",
      "type": "java.lang.Integer"
    }
  ]
}
```
# 重新编译项目（模块）
复制修改完json文件后，再重新编译项目，这样就可以再properties（yml）文件实现自定义配置项的提示了。
