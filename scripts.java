/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/echo")
public class EchoSocket {
	public EchoSocket() {
		System.out.println("多例");
	}
	//静态变量，用来记录当前在线连接数。
	private static int onlineCount = 0;
	//打开连接时事件
	@OnOpen
	public void open(Session session) {
		//一个session就代表一个通信的会话
		System.out.println("session id:"+session.getId());
		onlineCount++;
		System.out.println("新用户加入，当前在线人数： "+onlineCount);
	}
	
	//关闭连接时事件
	@OnClose
	public void close(Session session) {
		//一个session就代表一个通信的会话
		System.out.println("session id:"+session.getId()+" close");
		onlineCount--;
		System.out.println("有用户退出，当前在线人数： "+onlineCount);
	}
	
	//发送消息事件
	@OnMessage
	public void message(Session session,String msg) {
		//打印客户端消息
		System.out.println("session id:"+msg);
		//服务端向客户端发消息
		try {
			session.getBasicRemote().sendText("server: "+msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
