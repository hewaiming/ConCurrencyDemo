package threadDemo;

public class ticket extends Thread {
		      
	    private int ticket = 10;  
	    private String name;  
	    public ticket(String name){  
	        this.name =name;  
	    }  
	      
	    public void run(){  
	        for(int i =0;i<500;i++){  
	            if(this.ticket>0){  
	                System.out.println(this.name+"卖票---->"+(this.ticket--));  
	            }  
	        }  
	    }  

	public static class ticketDemo {  
	  
	      
	    public static void main(String[] args) {  
	        ticket mt1= new ticket("一号窗口");  
	        ticket mt2= new ticket("二号窗口");  
	        ticket mt3= new ticket("三号窗口");  
	        mt1.start();  
	        mt2.start();  
	        mt3.start();  
	    }  	  
	}  
	}

