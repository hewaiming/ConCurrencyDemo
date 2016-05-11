package threadDemo;

public class oneTask {

	public static void main(String[] args) {
		long result=0;
		long begin=System.currentTimeMillis();
		System.out.println("begin:"+begin);
		for(int i=0;i<900000000;i++){
			result=result+i;
		}
		long end=System.currentTimeMillis();
		System.out.println("end:"+end);
		System.out.println("运行时间 (end-begin)："+(end-begin));
		System.out.println("result="+result);
	}

}
