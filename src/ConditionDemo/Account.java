package ConditionDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����˻�
 */
public class Account {
	private String id; // �˺�
	private int cash; // �˻����
	private Lock lock = new ReentrantLock(); // �˻���
	private Condition _save = lock.newCondition(); // �������
	private Condition _draw = lock.newCondition(); // ȡ������

	Account(String id, int cash) {
		this.id = id;
		this.cash = cash;
	}

	/**
	 * ���
	 *
	 * @param x
	 *            �������
	 * @param name
	 *            ������
	 */
	public void saving(int x, String name) {
		lock.lock(); // ��ȡ��
		if (x > 0) {
			cash += x; // ���
			System.out.println("++++++++++++++");
			System.out.println(name + "���" + x + "���ʺţ�"+id+"  ��ǰ���:" + cash);
		}
		_draw.signalAll(); // �������еȴ���ȡ���̡߳�
		lock.unlock(); // �ͷ���
	}

	/**
	 * ȡ��
	 *
	 * @param x
	 *            �������
	 * @param name
	 *            ������
	 */
	public void drawing(int x, String name) {
		lock.lock(); // ��ȡ��
		try {
			System.out.println("----------------");
			if (cash - x < 0) {
				System.out.println(name + "ȡ��ʧ��[����]��ȡ��" + x + "���ʺţ�"+id+"  ��ǰ���:" + cash);
				_draw.await(); // ����ȡ�����
			} else {
				cash -= x; // ȡ��				
				System.out.println(name + "ȡ��" + x + "���ʺţ�"+id+"  ��ǰ���:" + cash);
			}
			_save.signalAll(); // �������еȴ��Ĵ�����
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // �ͷ���
		}
	}
}
