# multi-module: Tomcat + SSM 源码调试分析环境

## 项目简介
本项目旨在深入分析 **Apache Tomcat** 处理 HTTP 请求的源码流程，并结合 **SSM（Spring + Spring MVC + MyBatis）** 框架运行调试，从一次 HTTP 请求的入口到 Controller → Service → Mapper 层进行全链路跟踪。

核心思路：
1. 使用 **Tomcat 源码**（基于 8.5.57 版本），在 IDE 中进行源码级调试。
2. 将基于 **SSM XML 配置** 的 Web 项目打包并部署到 Tomcat 源码运行的 `webapps` 目录。
3. 在 Tomcat 源码与 SSM 项目中增加 `System.out` 日志输出，区分不同模块日志，便于观察调用链。

---

## 项目结构

```
multi-module/
 ├── apache-tomcat-8.5.57-src/   # Tomcat源码目录
 ├── ssm-xml/                    # SSM Web项目（Spring + Spring MVC + MyBatis配置）
 ├── .gitignore
 ├── README.md
 └── pom.xml 
```



---

## 环境准备

### 1. 必备工具
- JDK 8 或以上（建议 8）
- Maven 3.6+
- IDE（推荐 IntelliJ IDEA）
- MySQL 数据库（用于 MyBatis 数据访问测试）

### 2. Tomcat 源码编译
进入 `apache-tomcat-8.5.57-src` 目录，执行：
```bash
mvn clean install
```

### 3. 配置 SSM 项目

进入 `ssm-xml` 目录：

- 修改 `src/main/resources` 下的数据库连接配置（`jdbc.properties`）
- 确保 MyBatis Mapper XML、Spring 配置文件路径正确

## 运行方式

### 1. 在 IDE 中调试 Tomcat 源码

- 用 IDE 打开整个 `multi-module` 项目

- 配置 `apache-tomcat-8.5.57-src` 为主启动模块

- 配置启动参数

  ```
  -Dfile.encoding=UTF-8
  -Duser.lanaguage=en
  -Duser.region=US
  -Dcatalina.home=catalina-home
  -Dcatalina.base=catalina-home
  -Djava.endorsed.dirs=catalina-home/endorsed
  -Djava.io.tmpdir=catalina-home/temp
  -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
  -Djava.util.logging.config.file=catalina-home/conf/logging.properties
  ```

- 在 Tomcat `Bootstrap` 类处打断点，调试启动

### 2. 部署 SSM 项目到 Tomcat 源码运行环境

- 配置 `ssm-xml` 模块打包为copy到tomcat webapps目录下：

  ```xml
  <!-- 构建完成后 copy 到 tomcat webapps -->
  <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-antrun-plugin</artifactId>
      <version>1.8</version>
      <executions>
          <execution>
              <phase>package</phase>
              <goals>
                  <goal>run</goal>
              </goals>
              <configuration>
                  <target>
                      <!-- 删除原有解压目录 -->
                      <delete dir="../apache-tomcat-8.5.57-src/catalina-home/webapps/ssm-xml"/>
                      <delete dir="../apache-tomcat-8.5.57-src/catalina-home/webapps/ssm-xml.war"/>
  
                      <!-- 拷贝新的 war 文件 -->
                      <copy file="${project.build.directory}/${project.build.finalName}.war"
                            tofile="../apache-tomcat-8.5.57-src/catalina-home/webapps/ssm-xml.war"/>
                  </target>
              </configuration>
          </execution>
      </executions>
  </plugin>
  ```

- 将 `ssm-xml` 模块打包为 WAR：

  ```bash
  cd ssm-xml
  mvn clean package
  ```

- 启动 Tomcat，访问项目 URL 进行调试

## 调试目标

1. **请求入口**
    追踪 `org.apache.catalina.connector.Connector` → `org.apache.catalina.core.StandardEngineValve` 等类
2. **请求分发**
    调试 `org.apache.catalina.core.StandardWrapperValve` 调用 Servlet
3. **进入 SpringMVC DispatcherServlet**
    观察 HandlerMapping → Controller → Service → Mapper 调用链
4. **SQL 执行**
    跟踪 MyBatis 执行流程

------

## 联系方式

如有问题或建议，请通过 GitHub Issues 反馈：
 https://github.com/nitouge/multi-module/issues

------

## 版权与许可证

本项目包含 **Apache Tomcat 8.5.57** 源码（Apache License 2.0）及 **SSM** 示例代码。
 Tomcat 源码版权归 Apache Software Foundation 所有。
 你可自由修改和分发本项目，但需遵守相关开源协议。