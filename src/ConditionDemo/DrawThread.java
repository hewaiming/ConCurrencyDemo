package ConditionDemo;
/**
	 * ȡ���߳���
	 */
	public class DrawThread extends Thread {
		private String name; // ������
		private Account account; // �˻�
		private int x; // �����

		DrawThread(String name, Account account, int x) {
			this.name = name;
			this.account = account;
			this.x = x;
		}

		public void run() {
			account.drawing(x, name);
		}
	}
