package ConditionDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	public static void main(String[] args) throws InterruptedException {
		ConditionTest test = new ConditionTest();

		// �����������ʵ��˻�
		Account myAccount = new Account("95599200901215522", 10000);
		Account taAccount=new Account("6121988612564", 100);
		// ����һ���̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Thread t1 =new DrawThread("����", myAccount, 11000);
		Thread t2 = new SaveThread("����", myAccount, 3600);
		Thread t3 = new DrawThread("����", myAccount, 2700);
		Thread t4 = new SaveThread("����", myAccount, 600);
		Thread t5 = new DrawThread("��ţ", myAccount, 1300);
		Thread t6 = new SaveThread("����", myAccount, 2000);
		
		Thread t11 =new DrawThread("����", taAccount, 11000);
		Thread t12 = new SaveThread("����", taAccount, 3600);
		Thread t13 = new DrawThread("����", taAccount, 2700);
		Thread t14 = new SaveThread("����", taAccount, 600);
		Thread t15 = new DrawThread("��ţ", taAccount, 1300);
		Thread t16 = new SaveThread("����", taAccount, 2000);

		// ִ�и����߳�
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
		// �ر��̳߳�
		pool.shutdown();
	}
	

	

}