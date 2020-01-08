package cn.wolfcode.lifecycle;

//模拟数据库连接池
public class MyDataSource {

	public MyDataSource() {
		System.out.println("创建对象..");
	}

	public void open() {
		System.out.println("开启资源");
	}

	public void close() {
		System.out.println("释放资源");
	}

	public void doWork() {
		System.out.println("工作.......");
	}
}
