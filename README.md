# 基于注解的MySQL数据库读写分离

## 主要技术
 1.Mybatis
 2.SpringAOP
 3.注解
 
 ## 实现思路
 
 - 创建注解`@RoutingDataSource`用来标识当前方法使用的DataSource
 - 使用`ThreadLocal` 维护每个线程需要访问的数据库对应的Key值
 - 继承Mybatis的`AbstractRoutingDataSource`抽象类，实现`determineCurrentLookupKey()`方法，该方法作用是：检索当前目标DataSource。确定当前查找键，在targetDataSources映射中执行查找，如有必要，则回退到指定的默认目标DataSource
 - 使用SpringAop对使用了`@RoutingDataSource`注解的service方法进行拦截，获取到`@RoutingDataSource`的value值，在方法运行之前，通过`ThreadLocal`设置当前线程datasource对应的key值，当前线程即会访问对应的datasource，方法运行之后，调用`ThreadLocal`的`remove()`方法，清除之前设置的key值

 
