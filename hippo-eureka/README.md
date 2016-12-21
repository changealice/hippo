# 启动eureka server

com.change.spring.cloud.HippoEurekaApplication

#启动eureka客户端18081
-Dserver.port=18081 启动 com.change.SampleSimpleApplication


#启动eureka客户端18082
-Dserver.port=18082 启动 com.change.SampleSimpleApplication

#打开浏览器
http://localhost:8761 
输入用户名/密码 user/password123
#手动指定properties
$ java -jar myproject.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties

spring.profiles.active=dev,hsqldb

#consul mac install 
* brew install consul

`If consul was built with --with-web-ui, you can activate the UI by running
 consul with `-ui-dir /usr/local/Cellar/consul/0.7.1/share/consul/web-ui`.
 
 zsh completion has been installed to:
   /usr/local/share/zsh/site-functions
 
 To have launchd start consul now and restart at login:
   brew services start consul
 Or, if you don't want/need a background service you can just run:
   consul agent -dev -advertise 127.0.0.1
   `
* start consul
    consul agent -dev -advertise 127.0.0.1
* 打开UI
http://127.0.0.1:8500/ui/


