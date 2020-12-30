package com.example.mydemo.bean;




public class ThreeBean {
    private Long id;//id
    private String name;//名称
    private String pic;//图片
    private float xian_price;//现价
    private boolean biao;
    private float zongjia;//总价
    private int count;
    public ThreeBean(Long id, String name, String pic, float xian_price,
                     boolean biao, float zongjia, int count) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.xian_price = xian_price;
        this.biao = biao;
        this.zongjia = zongjia;
        this.count = count;
    }

    public ThreeBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public float getXian_price() {
        return xian_price;
    }

    public void setXian_price(float xian_price) {
        this.xian_price = xian_price;
    }

    public boolean isBiao() {
        return biao;
    }

    public void setBiao(boolean biao) {
        this.biao = biao;
    }

    public float getZongjia() {
        return zongjia;
    }

    public void setZongjia(float zongjia) {
        this.zongjia = zongjia;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getBiao() {
        return this.biao;
    }
}
