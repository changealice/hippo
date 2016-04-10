package com.change.hippo.rpc.frame;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午6:10
 */
public interface Constants {
    String ZK_REGISTRY_PATH = "/change";
    String ZK_REGISTRY_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider";
    String ZK_CONNECT_STRING = "localhost:2181,localhost:2182,localhost:2183";
    String ZK_DATA_PATH="/change/data";

    int TIMEOUT = 5000;
}
