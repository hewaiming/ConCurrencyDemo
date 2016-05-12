package ExchangerDemo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {
	static int size = 10;
	static int delay = 5; // √Î

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Fat> producerList = new CopyOnWriteArrayList<>();
		List<Fat> consumerList = new CopyOnWriteArrayList<>();
		Exchanger<List<Fat>> exchanger = new Exchanger<>();
		exec.execute(new ExchangerProducer(exchanger, producerList));
		exec.execute(new ExchangerConsumer(exchanger, consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}
}