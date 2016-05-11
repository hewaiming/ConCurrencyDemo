package threadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaPhoreTest1 {

	public static void main(String[] args) {
		// �̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		// ֻ��5���߳�ͬʱ����
		final Semaphore semp = new Semaphore(4);
		// ģ��20���ͻ��˷���
		for (int index = 0; index < 50; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// ��ȡ���
						semp.acquire();
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 6000));
						
						semp.release();  // ��������ͷ�
						System.out.println("-----releaseing: "+ NO);
					
						System.out.println("ʣ������źŵƣ�" + semp.availablePermits());	// availablePermits()ָ���ǵ�ǰ�źŵƿ����ж��ٸ����Ա�ʹ��
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		// �˳��̳߳�
		exec.shutdown();
	}

}
