package com.n3.project_thoitrang.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {

    private Long order;
    private Long product;

    public OrderDetailId() {}

    public OrderDetailId(Long order, Long product) {
        this.order = order;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}