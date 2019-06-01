package wsmgr.state;


import okhttp3.RequestBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketListener;
import wsmgr.entity.Pong;

import java.io.IOException;

import static okhttp3.ws.WebSocket.TEXT;


/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public class WsConnected extends ConnectedDispather implements State {
    public static final long ONCONNECT_SEND_PENDING_DELAY_MS = 2000L;
    private static final long PING_INTERVAL_MS = 10000L;
    private final long TIMEOUT_RECONNECT = 3000L;

    private final Object lock = new Object();
    private WsContext mContext;

    public WsConnected(WsContext context) {
        super(context);
        this.mContext = context;
        context.setWsListener(listener);
    }

    @Override
    public void connected() {

    }

    @Override
    public void disconnected() {
        try {
            this.pingIntervalTimer.cancel();
            this.pongWaitTimer.cancel();
        } finally {
            if (mContext.getWsClient() != null) {
                try {
                    mContext.getWsClient().dispatcher().cancelAll();
                } finally {

                }
            }
            mContext.setWsClient(null);
            mContext.setCurrentState(mContext.getDisconnected());
            mContext.disconnected();
        }
    }

    @Override
    public void sendMessage(String message) {
        sendCommand(message);
    }

    @Override
    void sendCommand(String messageJson) {
        synchronized (lock) {
            try {
                mContext.getWebSocket().sendMessage(RequestBody.create(TEXT, messageJson));
            } catch (IOException e) {
                mContext.disconnected();
            }
        }
    }

    @Override
    public void connecting() {

    }

    @Override
    public void ping() {
        this.pingIntervalTimer.start(PING_INTERVAL_MS);
    }


    private final WebSocketListener listener = new WebSocketListener() {

        @Override
        public void onOpen(WebSocket webSocket, okhttp3.Response response) {
            mContext.setCurrentState(WsConnected.this);
            mContext.setWebSocket(webSocket);
            ping();
        }

        @Override
        public void onFailure(IOException e, okhttp3.Response response) {
            disconnected();
            if (!mContext.isNeedReconnect()) return;

            mContext.connecting();

        }

        @Override
        public void onMessage(okhttp3.ResponseBody responseBody) throws IOException {
            mContext.getListenerMessage().onMessage(responseBody.toString());
        }

        @Override
        public void onPong(okio.Buffer buffer) {
            String response = buffer.readUtf8();
            buffer.close();
            Pong pong = gson.fromJson(response, Pong.class);

            if (WsConnected.this.pingId == pong.getId()) {
                pingId = 1L + pingId;
                pongWaitTimer.cancel();
                pingIntervalTimer.start(PING_INTERVAL_MS);
            } else {
            }
        }

        @Override
        public void onClose(int i, String s) {
            disconnected();

        }
    };
}
