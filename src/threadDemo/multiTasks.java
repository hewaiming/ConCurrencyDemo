package threadDemo;

import java.util.concurrent.CountDownLatch;

public class multiTasks {
	static long result1 = 0;
	static long result2 = 0;
	static long result3 = 0;
	static long result4 = 0;
	static long result5 = 0;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(5);// 两个工人的协作
		long begin = System.currentTimeMillis();
		System.out.println("begin:" + begin);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("result1....");
				for (int i = 0; i < 100000000; i++) {
					result1 = result1 + i;
				}
				// System.out.println("result1="+result1);
				latch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("result2....");
				for (int i = 100000000; i < 200000000; i++) {
					result2 = result2 + i;
				}
				// System.out.println("result2="+result2);
				latch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("result3....");
				for (int i = 200000000; i < 300000000; i++) {
					result3 = result3 + i;
				}
				// System.out.println("result2="+result2);
				latch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("result4....");
				for (int i = 300000000; i < 400000000; i++) {
					result4 = result4 + i;
				}
				// System.out.println("result2="+result2);
				latch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("result5....");
				for (int i = 400000000; i < 500000000; i++) {
					result5 = result5 + i;
				}
				// System.out.println("result2="+result2);
				latch.countDown();
			}
		}).start();
      /*  Worker unit1=new Worker("unit1", 0,450000000, latch);   
        Worker unit2=new Worker("unit2", 450000000, 900000000, latch);
        unit1.start();
        unit2.start();*/
		latch.await();// 等待所有工人完成工作
		long end = System.currentTimeMillis();
		System.out.println("end:" + end);
		System.out.println("运行时间 (end-begin)：" + (end - begin));
		System.out.println("result=" + (result1 + result2+result3+result4+result5));
	}

	static class Worker extends Thread {
		String workerName;
		int begin,end;
		CountDownLatch latch;

		public Worker(String workerName, int begin,int end, CountDownLatch latch) {
			this.workerName = workerName;
			this.begin = begin;
			this.end=end;
			this.latch = latch;
		}

		@Override
		public void run() {
			for (int i = begin; i < end; i++) {
				result1 = result1 + i;
			}
			// System.out.println("result1="+result1);
			latch.countDown();
		}
	}
}
