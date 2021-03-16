package com.sq.dp.designpattern.memento;

/**
 * 发起人
 */
public class Chessman {
    private String label;
    private int x;
    private int y;

    public Chessman(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    /**
     * 创建备忘录: 备份
     */
    public ChessmanMemento save() {
        return new ChessmanMemento(this);
    }

    /**
     * 还原备忘录
     */
    public void restore(ChessmanMemento memento) {
        this.label = memento.getLabel();
        this.x = memento.getX();
        this.y = memento.getY();
    }

    /**
     * 展示棋子当前状态
     */
    public void show() {
        System.out.println(String.format("棋子<%s>: 当前位置为: <%d, %d>", this.label, this.x, this.y));

    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
