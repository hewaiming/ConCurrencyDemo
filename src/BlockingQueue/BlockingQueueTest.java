package BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) throws Exception {
		 // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(6);
 
        Producer producer1 = new Producer("producer1",queue);
        Producer producer2 = new Producer("producer2",queue);
        Producer producer3 = new Producer("producer3",queue);
        Consumer consumer1 = new Consumer("consumer1",queue);
        Consumer consumer2 = new Consumer("consumer2",queue);
 
        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
 
        // 执行10s
        Thread.sleep(10 * 1000);
//        producer1.stop();
//        producer2.stop();
//        producer3.stop();
 
        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
	}
}
