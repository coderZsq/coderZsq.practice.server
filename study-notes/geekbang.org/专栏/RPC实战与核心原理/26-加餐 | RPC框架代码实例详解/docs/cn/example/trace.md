分布式跟踪
===

内置了分布式跟踪过滤器，目前集成了skywalking和jaeger的插件实现

全局参数

| 参数 | 名称 | 类型 | 默认值 | 说明 |
| :--- | :---- | :---- |:---- |:---- |
| trace.open | 启用标识 |  boolean | false |   |
| trace.type | 跟踪实现类型 | String |  | 会根据当前环境选择分布式跟踪环境实现<br/>如果当前环境能找到skywalking则优先启用<br/>否则如果当前环境能找到jaeger则启用<br/>当存在多种分布式跟踪实现的时候可以手动指定  |

配置样例如下

  ```xml
  <beans>
    <joyrpc:parameter key="trace.open" value="true" />
    <joyrpc:parameter key="trace.type" value="jaeger" />
  </beans>
  ```

## Skywalking

参考[官方文档](https://skywalking.apache.org)

## Jaeger

 引入依赖

  ```xml
  <dependency>
      <groupId>io.jaegertracing</groupId>
      <artifactId>jaeger-core</artifactId>
      <version>${jaeger.version}</version>
  </dependency>
  ```
两种配置方式

- 使用spring配置jaeger，注入全局上下文参数

  ```xml
  <beans>
    <joyrpc:parameter key="jaeger" ref="jaeger" />
  </beans>
  ```

- 配置全局上下文参数或系统环境，系统会内部会根据配置创建一个jaeger实例

| 参数 | 名称 | 类型 | 默认值 | 说明 |
| :--- | :---- | :---- |:---- |:---- |
| JAEGER_SAMPLER_TYPE | 取样方式 |  String | probabilistic |   |
| JAEGER_SAMPLER_MANAGER_HOST_PORT |   | int |  |   |
| JAEGER_SAMPLER_PARAM |   | double | 0.001D |   |
| JAEGER_AGENT_HOST |   | String |  |   |
| JAEGER_AGENT_PORT |   | int |  |   |
| JAEGER_ENDPOINT |   | String |  |   |
| JAEGER_USER |   | String |  |   |
| JAEGER_PASSWORD |   | String |  |   |
| JAEGER_AUTH_TOKEN |   | String |  |   |

```xml
  <beans>
    <joyrpc:parameter key="JAEGER_SAMPLER_TYPE" value="probabilistic" />
  </beans>
  ```
