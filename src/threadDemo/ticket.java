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
	                System.out.println(this.name+"��Ʊ---->"+(this.ticket--));  
	            }  
	        }  
	    }  

	public static class ticketDemo {  
	  
	      
	    public static void main(String[] args) {  
	        ticket mt1= new ticket("һ�Ŵ���");  
	        ticket mt2= new ticket("���Ŵ���");  
	        ticket mt3= new ticket("���Ŵ���");  
	        mt1.start();  
	        mt2.start();  
	        mt3.start();  
	    }  	  
	}  
	}

