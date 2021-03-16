package com.sq.dp.designpattern.chainofresponsebility;

/**
 * 抽象处理者
 */
abstract public class Handler {
    protected String name;
    protected Handler next;

    public Handler(String name) {
        this.name = name;
    }

    abstract public boolean handle(Request request);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
