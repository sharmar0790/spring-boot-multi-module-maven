package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Promotions implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String promotionName;
    private String promotionLink;

    public Promotions(String promotionName, String promotionLink) {
        this.promotionName = promotionName;
        this.promotionLink = promotionLink;
    }

    public Promotions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionLink() {
        return promotionLink;
    }

    public void setPromotionLink(String promotionLink) {
        this.promotionLink = promotionLink;
    }

    @Override
    public String toString() {
        return "Promotions{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", promotionLink='" + promotionLink + '\'' +
                '}';
    }
}
