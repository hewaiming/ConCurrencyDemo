package threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableFutureTaskTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		myTask task = new myTask();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		
		myTask_List task_list = new myTask_List();
		FutureTask<List<Integer>> futureTask_list = new FutureTask<List<Integer>>(task_list);
		
		executor.submit(futureTask);
		executor.submit(futureTask_list);	
		executor.shutdown();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("���߳���ִ������");

		try {
			System.out.println("task���н��" + futureTask.get());
			System.out.println("task_list���н��" + futureTask_list.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("��������ִ�����");
	}
}

class myTask implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("���߳��ڽ��м���");
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		return sum;
	}
}

class myTask_List implements Callable<List<Integer>> {

	@Override
	public List<Integer> call() throws Exception {
		System.out.println("����ֵΪLIST���߳��ڽ��м���");
		Thread.sleep(3000);
		List list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		return list;
	}

}
