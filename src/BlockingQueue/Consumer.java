package BlockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	private BlockingQueue<String> queue;
	private static final int DEFAULT_RANGE_FOR_SLEEP = 800;
	private String name;
	
	 public Consumer(BlockingQueue<String> queue) {
	        this.queue = queue;
	    }
	 public Consumer(String name,BlockingQueue<String> queue) {
	        this.queue = queue;
	        this.name=name;
	    }

	public void run() {
		System.out.println("����<�������߳�>��  ���ƣ� "+name);
		Random r = new Random();
		boolean isRunning = true;
		try {
			while (isRunning) {
				System.out.println(name+" ���Ӷ��л�ȡ����...");
				String data = queue.poll(2, TimeUnit.SECONDS);
				if (null != data) {
					System.out.println(name+" �õ����ݣ�" + data);
					System.out.println(name+" �����������ݣ�" + data+ "      ����Ŀǰ��" + queue.size() + "������");
					Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				} else {
					// ����2s��û���ݣ���Ϊ���������̶߳��Ѿ��˳����Զ��˳������̡߳�
					isRunning = false;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("�˳��������̣߳� ���ƣ�"+name);
		}
	}
}
