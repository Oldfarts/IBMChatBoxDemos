package application;

public class Messages {

    private String uid; 
    private String msg; 
    // Parameterized Constructor to set Student 
    // name, age, course enrolled in. 
    public Messages(String uid, String msg) 
    { 
        this.uid = uid; 
        this.msg = msg; 
    } 
    // Setter Methods to set table data to be 
    // displayed 
    public String getUid() 
    { 
    	return uid; 
    } 
    public String getMsg() 
    { 
    	return msg; 
    } 
} 

