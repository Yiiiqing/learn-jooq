# learn-jooq
demo project for learning jooq

***
以下是blog 笔记,貌似有一点格式问题

#  jOOQ入门

公司的 ORM 框架用的是 jOOQ

之前为了学 spring 全家桶学习的 mybatis 和 mybatis plus 都白学了

好吧我又继续学习了

## 概述

官网: https://www.jooq.org/

官网对于为什么使用 jOOQ 的描述如下

> So why not just use SQL?
>
> SQL can be written as plain text and passed through the JDBC API. Over the years, people have become wary of this approach for many reasons:
>
> \- No typesafety  无类型安全
>
> \- No syntax safety 无语法安全
>
> \- No bind value index safety 无绑定值索引安全性
>
> \- Verbose SQL String concatenation 冗长的SQL字符串拼接
>
> \- Boring bind value indexing techniques 乏味的绑定值索引技术
>
> \- Verbose resource and exception handling in JDBC JDBC中的详细资源和异常处理
>
> \- A very "stateful", not very object-oriented JDBC API, which is hard to use 一个非常“有状态”的、不是非常面向对象的JDBC API，很难使用

因为以上这些原因,在过去,很多框架尝试以各种方式去抽象 JDBC. 但是不幸的是,很多框架把 SQL 也抽象掉了. jOOQ 可以将这一空白填充.

这样看起来,感觉口气很狂的样子,然后继续看一下官网的介绍

### jOOQ is different

> SQL was never meant to be abstracted. To be confined in the narrow boundaries of heavy mappers, hiding the beauty and simplicity of relational data. SQL was never meant to be object-oriented. SQL was never meant to be anything other than... SQL!

翻译过来就是说:

SQL 从来就不是抽象的.被限制在mappers 的狭窄的区域中,被隐藏了数据关联的美丽和简单

SQL 从来就不是面向对象的.

SQL 从来就不是任何东西, 除了 SQL!

> 好吧这么一看,喷了很多框架啊,哈哈哈开发者个性我喜欢

### jOOQ 的不同

> jOOQ has originally been created as a library for complete abstraction of JDBC and all database interaction. Various best practices that are frequently encountered in pre-existing software products are applied to this library. This includes:
>
> \- Typesafe database object referencing through generated schema, table, column, record, procedure, type, dao, pojo artefacts (see the chapter about code generation) 通过生成的模式、表、列、记录、过程、类型、dao和pojo构件引用类型安全数据库对象
>
> \- Typesafe SQL construction / SQL building through a complete querying DSL API modelling SQL as a domain specific language in Java (see the chapter about the query DSL API) 类型安全SQL构建/ SQL构建通过一个完整的查询DSL API建模的SQL
>
> \- Convenient query execution through an improved API for result fetching (see the chapters about the various types of data fetching) 通过改进的用于结果获取的API方便地执行查询
>
> \- SQL dialect abstraction and SQL clause emulation to improve cross-database compatibility and to enable missing features in simpler databases (see the chapter about SQL dialects)  SQL方言抽象和SQL子句模拟，以提高跨数据库兼容性，并在更简单的数据库中启用缺少的特性
>
> \- SQL logging and debugging using jOOQ as an integral part of your development process (see the chapters about logging) SQL日志记录和调试
>
> Effectively, jOOQ was originally designed to replace any other database abstraction framework short of the ones handling connection pooling (and more sophisticated transaction management) 实际上，jOOQ最初的设计是为了取代除处理连接池之外的任何其他数据库抽象框架

好,很棒

### 使用 jOOQ 的几种方式

> \- Using Hibernate for 70% of the queries (i.e. CRUD) and jOOQ for the remaining 30% where SQL is really needed
>
> \- Using jOOQ for SQL building and JDBC for SQL execution
>
> \- Using jOOQ for SQL building and Spring Data for SQL execution
>
> \- Using jOOQ without the source code generator to build the basis of a framework for dynamic
>
> SQL execution.

## 教程

好记性不如烂笔头,跟着官网的 7 步成 jOOQ 教程走一波

### 准备

#### 下载jOOQ 或者通过 maven 引入

官网的话https://www.jooq.org/download/

我使用的是 maven 方式

首先建了一个空白的 maven 项目,在 pom 中引入:

```xml
<dependency>
  <groupId>org.jooq</groupId>
  <artifactId>jooq</artifactId>
</dependency>
<dependency>
  <groupId>org.jooq</groupId>
  <artifactId>jooq-meta</artifactId>
</dependency>
<dependency>
  <groupId>org.jooq</groupId>
  <artifactId>jooq-codegen</artifactId>
</dependency>
```

#### 数据库数据准备

```mysql
CREATE DATABASE `library`;
USE `library`;
CREATE TABLE `author` (
`id` int NOT NULL,
`first_name` varchar(255) DEFAULT NULL, `last_name` varchar(255) DEFAULT NULL, PRIMARY KEY (`id`)
);
```

### 代码生成

在 resources 中创建一个 xml 文件, 使用官网给出的模板

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<configuration xmlns="http://www.jooq.org/xsd/jooq-codegen-3.9.2.xsd">
  <!-- Configure the database connection here -->
  <jdbc>
    <driver>com.mysql.jdbc.Driver</driver>
    <!-- 数据库url -->
    <url>jdbc:mysql://localhost:3306/library?useUnicode=true&amp;characterEncoding=UTF-8</url>
    <!-- 数据库账号 -->
    <user>root</user>
    <!-- 数据库账号密码 -->
    <password>123456</password>
  </jdbc>

  <generator>
    <!-- The default code generator. You can override this one, to generate your own code style.
         Supported generators:
         - org.jooq.util.JavaGenerator
         - org.jooq.util.ScalaGenerator
         Defaults to org.jooq.util.JavaGenerator -->
    <name>org.jooq.util.JavaGenerator</name>

    <database>
      <!-- The database type. The format here is:
           org.util.[database].[database]Database -->
      <name>org.jooq.util.mysql.MySQLDatabase</name>

      <!-- The database schema (or in the absence of schema support, in your RDBMS this
           can be the owner, user, database name) to be generated -->
      <inputSchema>library</inputSchema>

      <!-- All elements that are generated from your schema
           (A Java regular expression. Use the pipe to separate several expressions)
           Watch out for case-sensitivity. Depending on your database, this might be important! -->
      <includes>.*</includes>

      <!-- All elements that are excluded from your schema
           (A Java regular expression. Use the pipe to separate several expressions).
           Excludes match before includes, i.e. excludes have a higher priority -->
      <excludes></excludes>
    </database>

    <target>
      <!-- The destination package of your generated classes (within the destination directory) -->
      <!-- 生成的包名，生成的类在此包下 -->
      <packageName>zone.yiqing.learnjooq.generated</packageName>

      <!-- The destination directory of your generated classes. Using Maven directory layout here -->
      <!-- 输出的目录 -->
      <directory>/Users/yiqing/Documents/programming/projects/learn-jooq/src/main/java</directory>
    </target>
  </generator>
</configuration>
```

其中:

* 替换 sql 连接的账号密码
* packageName: 设置为用于生成代码的 classes 的 parent package
* directory: 输出生成 classes 的目录

### 生成代码

#### 使用 maven 插件

https://www.jooq.org/doc/3.0/manual/code-generation/codegen-configuration/

这个方法应该是最简单的方法

但是我在这个方法一直报错,最终尝试了其他方法

#### 使用生成器生成

将 jOOQ 的三个文件和 mysql connector的 jar 包复制到一个目录,在本例中是 generated.将之前创建的 library.xml 也复制进来

cd 到这个目录

执行:

```bash
java -classpath jooq-3.9.5.jar:jooq-meta-3.9.5.jar:jooq-codegen-3.9.5.jar:mysql-connector-java-5.1.30.jar: org.jooq.util.GenerationTool library.xml
```

注意: jar 包都要和本地的版本对应,mysql-connector 也要和 mysql 对应,我数据库是 5.7.24,使用 5.1.48 版本的 connector 报 ClassNotFound 错误,使用 5.1.30 就可以

运行命令看到输出

```bash
...
信息: Synthetic primary keys   : 0 (0 included, 0 excluded)
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Overriding primary keys  : 1 (0 included, 1 excluded)
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Generating table         : Author.java [input=author, output=author, pk=KEY_author_PRIMARY]
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Tables generated         : Total: 712.006ms
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Generating table references
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Table refs generated     : Total: 724.683ms, +12.676ms
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Generating Keys
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Keys generated           : Total: 728.338ms, +3.654ms
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Generating table records
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Generating record        : AuthorRecord.java
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Table records generated  : Total: 742.384ms, +14.046ms
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Domains fetched          : 0 (0 included, 0 excluded)
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Generation finished: library: Total: 743.337ms, +0.953ms
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息:
六月 29, 2021 11:16:00 上午 org.jooq.tools.JooqLogger info
信息: Removing excess files
```

### 连接数据库

创建一个测试类,一个标准的 JDBC MySQL 连接代码

```java
package zone.yiqing.learnjooq;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author yiqing.zhang
 * @date 2021-06-29.
 */
public class Main {

  public static void main(String[] args) {
    String userName = "root";
    String password = "123456";
    String url = "jdbc:mysql://localhost:3306/library";

    // Connection is the only JDBC resource that we need
    // PreparedStatement and ResultSet are handled by jOOQ, internally
    try (Connection conn = DriverManager.getConnection(url, userName, password)) {
      // ...
    }
    // For the sake of this tutorial, let's keep exception handling simple
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}

```

### 查询

使用 jOOQ 的 DSL 构建一个简单查询

```java
DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
Result<Record> result = create.select().from(AUTHOR).fetch();
```

传入`Connection连接对象`、`数据方言`得到一个`DSLContext`的实例，然后使用DSL对象查询得到一个Result对象。

> 注意：DSLContext不会主动关闭连接，需要我们手动关闭。

### 输出

得到结果后,使用迭代器打印结果

```java
      for (Record r : result) {
        Integer id = r.getValue(AUTHOR.ID);
        String firstName = r.getValue(AUTHOR.FIRST_NAME);
        String lastName = r.getValue(AUTHOR.LAST_NAME);
        System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
      }
```

结果我一看咋啥都没有,原来是没有插入数据

```mysql
INSERT INTO author(id,first_name,last_name) VALUES(1,'yiqing','zhang');
```

重新运行

```bash
六月 29, 2021 11:41:22 上午 org.jooq.tools.JooqLogger info
信息: 
                                      
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@  @@        @@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@        @@@@@@@@@@
@@@@@@@@@@@@@@@@  @@  @@    @@@@@@@@@@
@@@@@@@@@@  @@@@  @@  @@    @@@@@@@@@@
@@@@@@@@@@        @@        @@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@        @@        @@@@@@@@@@
@@@@@@@@@@    @@  @@  @@@@  @@@@@@@@@@
@@@@@@@@@@    @@  @@  @@@@  @@@@@@@@@@
@@@@@@@@@@        @@  @  @  @@@@@@@@@@
@@@@@@@@@@        @@        @@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@  @@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  Thank you for using jOOQ 3.9.5
                                      
ID: 1 first name: yiqing last name: zhang
```



成功!



### 探索

好吧这就是第七步,强行加的一步,附上第七步原文

jOOQ has grown to be a comprehensive SQL library. For more information, please consider the documentation:

https://www.jooq.org/learn

... explore the Javadoc:

https://www.jooq.org/javadoc/latest/

... or join the news group:

https://groups.google.com/forum/#!forum/jooq-user

This tutorial is the courtesy of Ikai Lan. See the original source here:

http://ikaisays.com/2011/11/01/getting-started-with-jooq-a-tutorial/



