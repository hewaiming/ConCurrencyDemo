package ExchangerDemo;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerProducer implements Runnable {
	private List<Fat> holder;
	private Exchanger<List<Fat>> exchanger;

	public ExchangerProducer(Exchanger<List<Fat>> exchanger, List<Fat> holder) {
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// ����б�
				for (int i = 0; i < ExchangerDemo.size; i++) {
					holder.add(new Fat());
				}
				// �ȴ�����
				holder = exchanger.exchange(holder);
			}
		} catch (InterruptedException e) {
		}
		System.out.println("Producer stopped.");
	}
}
