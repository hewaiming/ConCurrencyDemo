package ExchangerDemo;

import java.util.List;
import java.util.concurrent.Exchanger;

class ExchangerConsumer implements Runnable {
	private List<Fat> holder;
	private Exchanger<List<Fat>> exchanger;
	private volatile Fat value;
	private static int num = 0;

	public ExchangerConsumer(Exchanger<List<Fat>> exchanger, List<Fat> holder) {
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				
				holder = exchanger.exchange(holder);// �ȴ�����
				// ��ȡ�б��Ƴ�Ԫ��
				for (Fat x : holder) {
					num++;
					value = x;
					// ��ѭ����ɾ��Ԫ�أ������CopyOnWriteArrayList��û�������
					holder.remove(x);
				}
				if (num % 10000 == 0) {
					System.out.println("Exchanged count=" + num);
				}
			}
		} catch (InterruptedException e) {

		}
		System.out.println("Consumer stopped. Final value: " + value);
	}
}