package threadDemo;  
class MyThread1 implements Runnable{  
    private int ticket =11;  
    private String name;  
    private boolean runFlag=true;
    public void run(){  
       while (runFlag){      	   
            if(this.ticket>0){              	
                System.out.println(Thread.currentThread().getName()+"��Ʊ---->"+(this.ticket--));  
            }else{
            	runFlag=false;
            }
            
        }  
    }  
}  
public class ticket2 {    
      
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        //��������߳�  
         MyThread1 mt = new MyThread1();  
         Thread t1 = new Thread(mt,"1�Ŵ���");  
         Thread t2 = new Thread(mt,"2�Ŵ���");  
         Thread t3 = new Thread(mt,"3�Ŵ���");  
//         MyThread1 mt2 = new MyThread1();  
//         MyThread1 mt3 = new MyThread1();  
         t1.start();  
         t2.start();  
         t3.start();  
    }  
  
}  