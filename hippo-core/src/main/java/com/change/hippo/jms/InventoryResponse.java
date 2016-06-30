package com.change.hippo.jms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:36
 */
@Data
@NoArgsConstructor
public class InventoryResponse implements Serializable {

    private static final long serialVersionUID = -6152616640408108911L;
    private String productId;
    private int returnCode;
    private String comment;
}
