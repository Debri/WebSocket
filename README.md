## WebSocket使用实例
---
####   WebSocket是在单个 TCP 连接上进行全双工通讯的网络协议，用作服务器向客户端推送消息 

> http协议是无状态的，在http1.0里面， HTTP的生命周期通过Request来界定，也就是一个Request 一个Response，那么在HTTP1.0中，这次HTTP请求就结束了。在HTTP1.1中进行了改进，使得有一个keep-alive，也就是说，在一个HTTP连接中，可以发送多个Request，接收多个Response。但是请记住 Request = Response ， 在HTTP中永远是这样，也就是说一个request只能有一个response。而且这个response也是被动的，不能主动发起。
> 使用websocket以后  最大的好处是减少了数据的传输，http的请求头里面没有其他多余的数据，并且实现了真正的由服务器发起的消息推送   