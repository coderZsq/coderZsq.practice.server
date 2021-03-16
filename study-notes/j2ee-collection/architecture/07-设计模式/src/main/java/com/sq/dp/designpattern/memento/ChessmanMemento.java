package com.sq.dp.designpattern.memento;

/**
 * 备忘录对象
 */
public class ChessmanMemento {
    private String label;
    private int x;
    private int y;

    public ChessmanMemento(Chessman chessman) {
        this.label = chessman.getLabel();
        this.x = chessman.getX();
        this.y = chessman.getY();
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
