package threadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaPhoreTest2 {

	public static void main(String[] args) {
		// �̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		// ֻ��5���߳�ͬʱ����
		final Semaphore semp = new Semaphore(5);
		
		// ģ��20���ͻ��˷���
		for (int index = 0; index < 20; index++) {
			myTask run = new myTask(index, semp);
			exec.execute(run);			
		}
		// �˳��̳߳�
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
				// ��ȡ���
				semp.acquire();
				System.out.println("��ȡ���: " + NO);
				Thread.sleep((long) (Math.random() * 6000));
				
				semp.release(); // ��������ͷ�
				System.out.println("-----�ͷ�: " + NO);

				System.out.println("ʣ������źŵƣ�" + semp.availablePermits()); // availablePermits()ָ���ǵ�ǰ�źŵƿ����ж��ٸ����Ա�ʹ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
