package com.change.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * User: change.long
 * Date: 16/5/19
 * Time: 下午5:10
 * 评论数据
 */
@Document
public class Comments {


    @Id
    private Long id;

    private String orderId;

    private String comment;

    private Long productId;

    @Field("locations")
    private Collection<Location> locations = new LinkedHashSet<Location>();


    public Comments(Long id, String orderId, String comment, Long productId) {
        this.id = id;
        this.orderId = orderId;
        this.comment = comment;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", comment='" + comment + '\'' +
                ", productId=" + productId +
                ", locations=" + locations +
                '}';
    }
}
