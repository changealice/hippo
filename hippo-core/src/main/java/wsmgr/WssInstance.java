package wsmgr;


import wsmgr.state.WsContext;

public class WssInstance {

    public static void main(String[] args) {
        WsContext wsContext = new WsContext("ws://echo.websocket.org");
        wsContext.setOnMessageListener(message -> {

        });

        wsContext.connect();
    }
}
