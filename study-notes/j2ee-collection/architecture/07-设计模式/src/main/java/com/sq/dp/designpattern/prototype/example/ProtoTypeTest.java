package com.sq.dp.designpattern.prototype.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProtoTypeTest {
    public static void main(String[] args) {
        List<ProductStock> psList = new ArrayList<>();
        Date now = new Date();
        ProductStock ps = new ProductStock(1L, "MacBook Pro", new BigDecimal("18999"), 0, BigDecimal.ZERO, 1L, "1号仓库", 0, now, now);
        psList.add(ps);

        for (int i = 2, len = 5; i <= len; i++) {
            ProductStock tmp = (ProductStock) ps.clone();
            tmp.setDepotId((long)i);
            tmp.setDepotName(i + "号仓库");

            psList.add(tmp);
        }

        psList.forEach(System.out::println);
    }
}

class ProductStock implements Cloneable {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stockNum;
    private BigDecimal amount;
    private Long depotId;
    private String depotName;
    private Integer status;
    private Date ctime;
    private Date utime;

    @Override
    protected Object clone()  {
        ProductStock obj = null;
        try {
            obj = (ProductStock) super.clone();
            // 自己申请深拷贝
            obj.setAmount(new BigDecimal(obj.getAmount().toString()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ProductStock(Long id, String name, BigDecimal price, Integer stockNum, BigDecimal amount, Long depotId, String depotName, Integer status, Date ctime, Date utime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockNum = stockNum;
        this.amount = amount;
        this.depotId = depotId;
        this.depotName = depotName;
        this.status = status;
        this.ctime = ctime;
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "ProductStock@" + super.hashCode() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockNum=" + stockNum +
                ", amount@" + amount.hashCode() + amount +
                ", depotId=" + depotId +
                ", depotName='" + depotName + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }
}
