package threadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaPhoreTest2 {

	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			myTask run = new myTask(index, semp);
			exec.execute(run);			
		}
		// 退出线程池
		exec.shutdown();
	}

	static class myTask extends Thread {
		int NO;
		Semaphore semp;

		public myTask(int no, Semaphore semp) {
			this.NO = no;
			this.semp = semp;
		}

		@Override
		public void run() {
			try {
				// 获取许可
				semp.acquire();
				System.out.println("获取许可: " + NO);
				Thread.sleep((long) (Math.random() * 6000));
				
				semp.release(); // 访问完后，释放
				System.out.println("-----释放: " + NO);

				System.out.println("剩余可用信号灯：" + semp.availablePermits()); // availablePermits()指的是当前信号灯库中有多少个可以被使用
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
