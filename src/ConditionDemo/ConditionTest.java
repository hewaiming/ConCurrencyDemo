package ConditionDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	public static void main(String[] args) throws InterruptedException {
		ConditionTest test = new ConditionTest();

		// 创建并发访问的账户
		Account myAccount = new Account("95599200901215522", 10000);
		Account taAccount=new Account("6121988612564", 100);
		// 创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Thread t1 =new DrawThread("张三", myAccount, 11000);
		Thread t2 = new SaveThread("李四", myAccount, 3600);
		Thread t3 = new DrawThread("王五", myAccount, 2700);
		Thread t4 = new SaveThread("老张", myAccount, 600);
		Thread t5 = new DrawThread("老牛", myAccount, 1300);
		Thread t6 = new SaveThread("胖子", myAccount, 2000);
		
		Thread t11 =new DrawThread("张三", taAccount, 11000);
		Thread t12 = new SaveThread("李四", taAccount, 3600);
		Thread t13 = new DrawThread("王五", taAccount, 2700);
		Thread t14 = new SaveThread("老张", taAccount, 600);
		Thread t15 = new DrawThread("老牛", taAccount, 1300);
		Thread t16 = new SaveThread("胖子", taAccount, 2000);

		// 执行各个线程
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);			
		pool.execute(t11);
		pool.execute(t12);
		pool.execute(t13);		
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);
		pool.execute(t14);
		pool.execute(t15);
		pool.execute(t16);
		
		 Thread.sleep(2000);
		// 关闭线程池
		pool.shutdown();
	}
	

	

}