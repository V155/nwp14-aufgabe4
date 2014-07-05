package de.reichwald.fh.nwp14;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

public class Aufgabe4
{

    public Aufgabe4()
    {
    }

    public static void main(String args[])
    {
	try{
	    BufferedReader br = new BufferedReader(new FileReader("tcpdump.dump"));
	    String line = "";
	    String output = "";
	    boolean next = false;
	    boolean first = true;

	    while (line!=null) {
		// process the line.
		// ^([0-9][0-9]:){2}[0-9]{2}.[0-9]{6} IP
		if(line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6}.* IPv4 .*"))
		    {
			output=new String(line);
			while((line = br.readLine()) != null && line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6}.*")==false)
			    {
				output = output + "\n" + line;
			    }
			System.out.println(output);
		    }
		//else if(line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6}.* IPv4 .*") && next){
		//  System.out.println(output);		    
		//  output=new String(line);
		//}
		
	    }
	    System.out.println(output);
	    
	    br.close();
	}
	catch(FileNotFoundException e){
	}
	catch(IOException e){
	}
	
	
    }
}
