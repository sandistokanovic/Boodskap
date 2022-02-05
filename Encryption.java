package boodskap;

public class Encryption {
	
	private String message;
	
	public Encryption(String content)
	{
		this.message=content;
	}
	
	public String encrypt()
	{
		int key=3;
		String encrypted="";
	    for(int i=0;i<this.message.length();i++)
	    {
	        int ch=this.message.charAt(i);
	        if(Character.isUpperCase(ch))
	        {
	            ch=ch+(key%26);	            
	            if(ch>'Z')
	                ch=ch-26;
	        }
	        else if(Character.isLowerCase(ch))
	        {
	             ch=ch+(key%26);
	                ch=ch-26;
	        }
	     
	        encrypted=encrypted+(char)ch;
	    }
	    return encrypted;
	}
	

}
