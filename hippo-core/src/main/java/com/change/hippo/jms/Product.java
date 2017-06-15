package com.change.hippo.jms;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:29
 */
@Data
@ToString(exclude = {"productId"})
@NoArgsConstructor
public class Product implements Serializable {


    private static final long serialVersionUID = -6838507810996029802L;

    private String productId;

    private String name;

    private int quantity;
}
