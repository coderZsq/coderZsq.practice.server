package com.coderZsq._25_shoppingcart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 购物车对象
public class ShoppingCart {
    // 购物车的多个商品对象
    private List<CartItem> items = new ArrayList<>();
    // 购物车总价
    // private BigDecimal totalPrice;

    // 把商品添加进购物车
    public void save(CartItem newItem) {
        for (CartItem item : items) {
            // 如果该商品已经存在, 则叠加购买数量
            if (item.getId().equals(newItem.getId())) {
                item.setNum(item.getNum() + newItem.getNum());
                return;
            }
        }
        items.add(newItem);
    }

    // 从购物车中移除指定ID的商品
    public void delete (String id) {
        Iterator<CartItem> it = items.iterator();
        while (it.hasNext()) {
            CartItem item = it.next();
            if (item.getId().equals(id)) {
                // items.remove(item); // 错误的
                it.remove(); // YES
                break;
            }
        }
    }

    // 购物车中所有的商品
    public List<CartItem> getItems() {
        return items;
    }

    // 购物车总价
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getNum())));
        }
        return totalPrice;
    }
}
