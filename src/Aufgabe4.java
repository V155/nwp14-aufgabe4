package de.reichwald.fh.nwp14;
import de.reichwald.fh.nwp14.IpPackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Aufgabe4
{

    public static void main(String args[])
    {
	ArrayList<IpPackage> packageList = new ArrayList();
	try{
	    BufferedReader br = new BufferedReader(new FileReader("tcpdump.dump"));
	    String line = "";
	    String output = "";
	    String seqNr = "";
	    boolean next = false;
	    boolean first = true;

	    while ((line = br.readLine()) !=null) {
		// process the line.
		// ^([0-9][0-9]:){2}[0-9]{2}.[0-9]{6} IP
		if(line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6}.* IPv4 .*"))
		    {
			output=new String(line);
			for (int i=0; i < 3; i++)
			    {
				if ((line = br.readLine()) != null){
				    output = output + "\n" + line;
				    if (i == 2)
					{
					     
					    seqNr = new String(line.substring(42,46) + line.substring(47,51));
					}
				}
			    }
			packageList.add(new IpPackage(output, seqNr, "rtt", "rto", "timestamp", "tcpdumpNr"));
			//System.out.println(output);
		    }
		//else if(line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6}.* IPv4 .*") && next){
		//  System.out.println(output);		    
		//  output=new String(line);
		//}
		
	    }
	    br.close();
	    for (IpPackage ippackage : packageList){
		System.out.println(ippackage.getSeqNr());
	    }

	}
	catch(FileNotFoundException e){
	}
	catch(IOException e){
	}
	
	
    }
}
