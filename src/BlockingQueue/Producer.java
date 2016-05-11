package BlockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
	private volatile boolean isRunning = true;
	private BlockingQueue queue;
	private static AtomicInteger count = new AtomicInteger();
	private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
    private String name;
	
	
	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	public Producer(String name,BlockingQueue queue) {
		this.queue = queue;
		this.name=name;
	}
	
	public void run() {
		String data = null;
		Random r = new Random();

		System.out.println("����<�������߳�>�� ���ƣ�"+name);
		try {
			while (isRunning) {
				System.out.println(name+" ������������...");
				Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));

				data = "data:" + count.incrementAndGet();
				System.out.println(name+" �����ݣ�" + data + "�������..."+ "    ����Ŀǰ��" + queue.size() + "������");
				
				if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
					System.out.println(name+" ��������ʧ�ܣ�" + data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("�˳��������̣߳�  ���ƣ�"+name);
		}
	}

	public void stop() {
		isRunning = false;
	}
}
