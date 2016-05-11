package threadDemo;

import java.util.concurrent.CountDownLatch;

public class multiTasks_countDown {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(4);// 两个工人的协作
		long begin = System.currentTimeMillis();
		System.out.println("begin:" + begin);
		Worker unit1 = new Worker("unit1", 0, 200000000, latch, 0);
		Worker unit2 = new Worker("unit2", 200000000, 400000000, latch, 0);
		Worker unit3 = new Worker("unit3", 400000000, 650000000, latch, 0);
		Worker unit4 = new Worker("unit4", 650000000, 900000000, latch, 0);
		/*Worker unit5 = new Worker("unit5", 400000000, 500000000, latch, 0);
		Worker unit6 = new Worker("unit6", 500000000, 600000000, latch, 0);
		Worker unit7 = new Worker("unit7", 600000000, 700000000, latch, 0);
		Worker unit8 = new Worker("unit8", 700000000, 800000000, latch, 0);
		Worker unit9 = new Worker("unit9", 800000000, 900000000, latch, 0);
		unit9.start();
		unit8.start();
		unit7.start();
		unit6.start();
		unit5.start();*/
		unit4.start();
		unit3.start();
		unit2.start();
		unit1.start();
		latch.await();// 等待所有工人完成工作
		long end = System.currentTimeMillis();
		System.out.println("end:" + end);
		System.out.println("运行时间 (end-begin)：" + (end - begin));
		System.out.println("result=" + (unit1.result + unit2.result + unit3.result + unit4.result));
//		System.out.println("result=" + (unit1.result + unit2.result + unit3.result + unit4.result + unit5.result
//				+ unit6.result + unit7.result + unit8.result+ unit9.result));
	}

	static class Worker extends Thread {
		String workerName;
		int begin, end;
		CountDownLatch latch;
		long result;

		public Worker(String workerName, int begin, int end, CountDownLatch latch, long int_result) {
			this.workerName = workerName;
			this.begin = begin;
			this.end = end;
			this.latch = latch;
			this.result = int_result;
			System.out.println(workerName + "....");
		}

		@Override
		public void run() {
			for (int i = begin; i < end; i++) {
				result = result + i;
			}
			latch.countDown();
		}
	}
}
