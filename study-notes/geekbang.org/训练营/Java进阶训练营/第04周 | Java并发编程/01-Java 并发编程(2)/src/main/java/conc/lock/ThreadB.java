package conc.lock;

public class ThreadB extends Thread {
    private Count3 count3;

    public ThreadB(Count3 count3) {
        this.count3 = count3;
    }

    @Override
    public void run() {
        count3.lockMethod();
    }
}
