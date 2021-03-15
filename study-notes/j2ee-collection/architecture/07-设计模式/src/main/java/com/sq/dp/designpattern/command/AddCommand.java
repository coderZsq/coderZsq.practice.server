package com.sq.dp.designpattern.command;

public class AddCommand implements Command {
    private int num1;
    private int num2;
    private Processor processor;

    public AddCommand(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Command setNum(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
        return this;
    }

    @Override
    public void execute() {
        this.processor.add(this.num1, this.num2);
    }
}
