package com.example.dto;

public class PromotionsDTO {
    private String promotionName;
    private String promotionLink;

    public PromotionsDTO(String promotionName, String promotionLink) {
        this.promotionName = promotionName;
        this.promotionLink = promotionLink;
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
}
