package com.sq.dp.designpattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录管理者
 */
public class MementoCaretaker {
    private List<ChessmanMemento> mementoList = new ArrayList<>();

    public ChessmanMemento getMemento(int index) {
        return mementoList.get(index);
    }

    public void addMemento(ChessmanMemento memento) {
        mementoList.add(memento);
    }
}
