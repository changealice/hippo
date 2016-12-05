package com.bill99.thunder.config;

import java.io.Serializable;

public class ReferenceConfig implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -4523443825400424113L;
    private String interfaceName;
    private String secretkey;
    private int timeout;
    public String getInterfaceName() {
        return interfaceName;
    }
    public String getSecretkey() {
        return secretkey;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
