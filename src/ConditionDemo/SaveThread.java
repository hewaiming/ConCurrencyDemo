package ConditionDemo;
/**
	 * ����߳���
	 */
	public class SaveThread extends Thread {
		private String name; // ������
		private Account account; // �˻�
		private int x; // �����

		SaveThread(String name, Account account, int x) {
			this.name = name;
			this.account = account;
			this.x = x;
		}

		public void run() {
			account.saving(x, name);
		}
	}