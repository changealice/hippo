package com.change.hippo.rpc.frame;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午8:46
 */
public class RpcResponse {

    private String requestId;
    private Throwable error;
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getError() {
        return error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isError() {
        return error != null;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
