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
				
				holder = exchanger.exchange(holder);// 等待交换
				// 读取列表并移除元素
				for (Fat x : holder) {
					num++;
					value = x;
					// 在循环内删除元素，这对于CopyOnWriteArrayList是没有问题的
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