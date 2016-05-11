package threadDemo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	static int n = 3;

	static int[] arr = new int[3];

	// 2������barrier����Ҫ���������߳�
	static CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {

		@Override
		public void run() {
			arr[2] = arr[0] + arr[1];
			for (int i = 0; i < arr.length; i++) {
				System.out.println("arr[" + i + "]=" + arr[i]);
			}
		}
	});

	public static void main(String[] args) {

		new Thread(new Component(1, arr, barrier)).start();
		new Thread(new Component(0, arr, barrier)).start();
	}

	public static class Component implements Runnable {
		int id;
		int[] arr;
		CyclicBarrier barrier;

		public Component(int id, int[] arr, CyclicBarrier barrier) {
			this.id = id;
			this.arr = arr;
			this.barrier = barrier;
		}

		@Override
		public void run() {           
			arr[id] = new Random().nextInt(100);
			try {
				System.out.println(id + "��ʼ��Ϣ");
				// ���е�barrier����˯�ŵȴ���������
				barrier.await();
				// barrier�򿪣������߳�
				System.out.println(id + "������");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

}
