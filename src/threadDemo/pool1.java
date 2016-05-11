package threadDemo;

public class pool1 {
	public synchronized static void inpool(String INname) {
		if (current < hight_level) {
			try {
				Thread.sleep(200);
				current = current + 1;
		        System.out.println(INname + "   当前水位  +：" + current);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public synchronized static void outpool(String outname) {
		if (current > low_level) {
			try {
				Thread.sleep(200);
				current = current - 1;
			    System.out.println(outname + "   当前水位  -：" + current);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static class Inwater extends Thread {
		private String name;

		public Inwater(String name) {
			this.name = name;
		}

		public void run() {
			while (true) {
				inpool(this.name);				
			}

		}
	}

	public static class Outwater extends Thread {
		private String name;

		public Outwater(String name) {
			this.name = name;
		}

		public void run() {
			while (true) {
				outpool(this.name);				
			}

		}
	}

	private static int hight_level = 20;
	private static int low_level = 1;
	private static int current = 10;

	public static void main(String[] args) {
		Inwater inwater1 = new Inwater("抽水机1");
		// inwater1.setPriority(5);
		inwater1.start();
		Inwater inwater2 = new Inwater("抽水机2");
		inwater2.start();
		Inwater inwater3 = new Inwater("抽水机3");
		inwater3.start();
		Outwater outwater1 = new Outwater("放水机1");
		// outwater1.setPriority(5);
		outwater1.start();
		Outwater outwater2 = new Outwater("放水机2");
		// outwater2.setPriority(1);
		outwater2.start();
	}

}
