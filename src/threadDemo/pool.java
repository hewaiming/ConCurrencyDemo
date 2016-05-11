package threadDemo;

public class pool {
	
	private static int level = 200;
	private static int current = 10;

	public static void main(String[] args) {

		new Thread() {
			public void run() {
				while (true) {
					if (current < level) {
						try {
							sleep(180);
							System.out.println("当前水位+：" + current);
							current = current + 3;
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

					}
				}
			}
		}.start();
		new Thread() {
			public void run() {
				while (true) {
					if (current > 0) {
						if (current > 6 && current < 12) {
							try {
								sleep(350);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							try {
								sleep(120);

							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						System.out.println("当前水位-：" + current);
						current=current-2;
					}
				}
			}
		}.start();

	}
}
