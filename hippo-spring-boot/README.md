## 打包运行
- 启动: java -jar target/simpleproject.jar
- debug端口开启：
    java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n  -jar target/myproject-0.0.1-SNAPSHOT.jar
    
## Maven插件运行
- mvn sprint-boot:run
- 设置环境变量 export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M -Djava.security.egd=file:/dev/./urandom
