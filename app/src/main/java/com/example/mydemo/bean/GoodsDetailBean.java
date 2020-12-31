package com.example.mydemo.bean;

import java.util.List;

public class GoodsDetailBean {
    private int categoryId;
    private String goodsBanner;
    private String goodsCode;
    private String goodsDefaultIcon;
    private String goodsDefaultPrice;
    private String goodsDefaultSku;
    private String goodsDesc;
    private String goodsDetailOne;
    private String goodsDetailTwo;
    private int goodsSalesCount;
    private List<GoodsSku> goodsSku;
    private int goodsStockCount;
    private int id;

    public GoodsDetailBean(int categoryId, String goodsBanner, String goodsCode, String goodsDefaultIcon, String goodsDefaultPrice, String goodsDefaultSku, String goodsDesc, String goodsDetailOne, String goodsDetailTwo, int goodsSalesCount, List<GoodsSku> goodsSku, int goodsStockCount, int id) {
        this.categoryId = categoryId;
        this.goodsBanner = goodsBanner;
        this.goodsCode = goodsCode;
        this.goodsDefaultIcon = goodsDefaultIcon;
        this.goodsDefaultPrice = goodsDefaultPrice;
        this.goodsDefaultSku = goodsDefaultSku;
        this.goodsDesc = goodsDesc;
        this.goodsDetailOne = goodsDetailOne;
        this.goodsDetailTwo = goodsDetailTwo;
        this.goodsSalesCount = goodsSalesCount;
        this.goodsSku = goodsSku;
        this.goodsStockCount = goodsStockCount;
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsBanner() {
        return goodsBanner;
    }

    public void setGoodsBanner(String goodsBanner) {
        this.goodsBanner = goodsBanner;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsDefaultIcon() {
        return goodsDefaultIcon;
    }

    public void setGoodsDefaultIcon(String goodsDefaultIcon) {
        this.goodsDefaultIcon = goodsDefaultIcon;
    }

    public String getGoodsDefaultPrice() {
        return goodsDefaultPrice;
    }

    public void setGoodsDefaultPrice(String goodsDefaultPrice) {
        this.goodsDefaultPrice = goodsDefaultPrice;
    }

    public String getGoodsDefaultSku() {
        return goodsDefaultSku;
    }

    public void setGoodsDefaultSku(String goodsDefaultSku) {
        this.goodsDefaultSku = goodsDefaultSku;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDetailOne() {
        return goodsDetailOne;
    }

    public void setGoodsDetailOne(String goodsDetailOne) {
        this.goodsDetailOne = goodsDetailOne;
    }

    public String getGoodsDetailTwo() {
        return goodsDetailTwo;
    }

    public void setGoodsDetailTwo(String goodsDetailTwo) {
        this.goodsDetailTwo = goodsDetailTwo;
    }

    public int getGoodsSalesCount() {
        return goodsSalesCount;
    }

    public void setGoodsSalesCount(int goodsSalesCount) {
        this.goodsSalesCount = goodsSalesCount;
    }

    public List<GoodsSku> getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(List<GoodsSku> goodsSku) {
        this.goodsSku = goodsSku;
    }

    public int getGoodsStockCount() {
        return goodsStockCount;
    }

    public void setGoodsStockCount(int goodsStockCount) {
        this.goodsStockCount = goodsStockCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    class GoodsSku{
        private int goodsId;
        private String goodsSkuContent;
        private String goodsSkuTitle;
        private int id;
        private List<String> skuContent;
        private String skuTitle;

        public GoodsSku(int goodsId, String goodsSkuContent, String goodsSkuTitle, int id, List<String> skuContent, String skuTitle) {
            this.goodsId = goodsId;
            this.goodsSkuContent = goodsSkuContent;
            this.goodsSkuTitle = goodsSkuTitle;
            this.id = id;
            this.skuContent = skuContent;
            this.skuTitle = skuTitle;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsSkuContent() {
            return goodsSkuContent;
        }

        public void setGoodsSkuContent(String goodsSkuContent) {
            this.goodsSkuContent = goodsSkuContent;
        }

        public String getGoodsSkuTitle() {
            return goodsSkuTitle;
        }

        public void setGoodsSkuTitle(String goodsSkuTitle) {
            this.goodsSkuTitle = goodsSkuTitle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getSkuContent() {
            return skuContent;
        }

        public void setSkuContent(List<String> skuContent) {
            this.skuContent = skuContent;
        }

        public String getSkuTitle() {
            return skuTitle;
        }

        public void setSkuTitle(String skuTitle) {
            this.skuTitle = skuTitle;
        }
    }
}
