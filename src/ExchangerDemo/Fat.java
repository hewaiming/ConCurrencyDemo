package ExchangerDemo;

public class Fat {
	private volatile double d;
	private static int counter = 1;
	private final int id = counter++;

	public Fat() {
		// ִ��һ�κ�ʱ�Ĳ���
		for (int i = 1; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}

	public void print() {
		System.out.println(this);
	}

	public String toString() {
		return "Fat id=" + id;
	}
}
