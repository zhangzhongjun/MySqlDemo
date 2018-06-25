# MySql数据库操作实例
本项目是使用c3p0连接池的一个mysql实例
![数据库的ER图](imgs/数据库的ER图.png)
## 引入依赖
```xml
<dependency>
	<groupId>c3p0</groupId>
	<artifactId>c3p0</artifactId>
	<version>0.9.1.2</version>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>6.0.6</version>
</dependency>
```

## 建库建表
对应关系
* id: 如果使用uuid作为id，数据库中使用 CHAR(36)，在sql 文件中使用函数uuid()获得一个随机的uuid
* 时间戳：在sql文件中使用函数now()获得一个时间戳
* 时间：数据库中使用timestamp，bean中使用java.utils.Date，dao中首先使用java.sql.Timestamp获得数据库中的数据，然后java.utils.Date date = new java.utils.Date(timestamp.getTime())获得java.utils.Date对象
* 对象：数据库中使用blob，bean中是任意的Object，dao中使用MyUtils类中序列化和反序列化的方法

进入mysql命令行
```bash
mysql> source 指向项目下的create_database_table.sql的路径
```
## 插入数据
```bash
mvn test -Dtest=MysqlHelperTest#saveTaskTest
mvn test -Dtest=MysqlHelperTest#batchSaveTaskOBJTest
```

## 查询数据
```bash
mvn test -Dtest=MysqlHelperTest#getTaskTest
mvn test -Dtest=MysqlHelperTest#getTaskObjTest
```

# 编码问题

MySQL的“utf8”实际上不是真正的UTF-8。“utf8”只支持每个字符最多三个字节，而真正的UTF-8是每个字符最多四个字节。

MySQL一直没有修复这个bug，他们在2010年发布了一个叫作“utf8mb4”的字符集，绕过了这个问题。

当然，他们并没有对新的字符集广而告之（可能是因为这个bug让他们觉得很尴尬），以致于现在网络上仍然在建议开发者使用“utf8”，但这些建议都是错误的。

简单概括如下：
* MySQL的 “utf8mb4” 是真正的“UTF-8”。
* MySQL的 “utf8” 是一种 “专属的编码”，它能够编码的Unicode字符并不多。


> 我要在这里澄清一下：所有在使用“utf8”的MySQL和MariaDB用户都应该改用“utf8mb4”，永远都不要再使用“utf8”。