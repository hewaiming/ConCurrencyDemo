package ExchangerDemo;

public class Fat {
	private volatile double d;
	private static int counter = 1;
	private final int id = counter++;

	public Fat() {
		// 执行一段耗时的操作
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
