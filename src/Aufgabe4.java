package de.reichwald.fh.nwp14;
import de.reichwald.fh.nwp14.IpPackage;
import de.reichwald.fh.nwp14.TrptPackage;
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
	ArrayList<IpPackage> list1 = readTcpdump();
	ArrayList<TrptPackage> list2 = readTrptdump();
    }

    public static ArrayList<TrptPackage> readTrptdump(){
	ArrayList<TrptPackage> trptPackageList = new ArrayList();

	try{
	    BufferedReader br = new BufferedReader(new FileReader("trptdump.dump"));
	    String line = "";
	    String output = "";
	    String seqNr = "";
	    String rto = "";
	    String nr = "";

	    while ((line = br.readLine()) != null) {
		if(line.matches("^[0-9]{3} (ESTABLISHED:output|ESTABLISHED:input|SYN_SENT:input) .*")){
		    output = new String(line);
		    nr = new String(line.substring(0, line.indexOf(' ')));
		    if(line.matches("^[0-9]{3} (ESTABLISHED:input|SYN_SENT:input) .*")){
			int index1 = line.indexOf(')')+1;
			seqNr = new String(line.substring(index1,index1+8));
		    }

		    if(line.matches("^[0-9]{3} (ESTABLISHED:output) .*")){
			int index1 = line.indexOf(')')+2;
			seqNr = new String(line.substring(index1,index1+8));
		    }
		    for(int i = 0; i < 4; i++){
			if ((line = br.readLine()) != null){
			    output = output + "\n" + line;
			    if (i==2){
				rto=line.substring(line.indexOf('=')+1,line.indexOf(','));
			    }
			}
		    }
		    trptPackageList.add(new TrptPackage(nr, output, seqNr, rto));
		}
	    }
	    br.close();
	    for (TrptPackage trptpackage : trptPackageList){
		System.out.println(trptpackage.getSeqNr() + ", " + trptpackage.getRto());
	    }
	}
	catch(FileNotFoundException e){
	}
	catch(IOException e){
	}
	return new ArrayList<TrptPackage>();
    }
		    
    
    public static ArrayList<IpPackage> readTcpdump(){
	ArrayList<IpPackage> ipPackageList = new ArrayList();
	
	try{
	    BufferedReader br = new BufferedReader(new FileReader("tcpdump.dump"));
	    String line = "";
	    String output = "";
	    String seqNr = "";
	    int nr = 1;
	    
	    while ((line = br.readLine()) !=null) {
		if(line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6} IP .*"))
		    {
			output=new String(line);
			nr++;
			for (int i=0; i < 4; i++)
			    {
				if ((line = br.readLine()) != null){
				    output = output + "\n" + line;
				    if (i == 3)
					{
					    seqNr = new String(line.substring(35,39) + line.substring(40,44));
					}
				}
			    }
			ipPackageList.add(new IpPackage(""+nr, output, seqNr, "rtt", "rto", "timestamp"));
		    }
	    }
	    br.close();
	    for (IpPackage ippackage : ipPackageList){
		System.out.println(ippackage.getSeqNr());
	    }
	    
	}
	catch(FileNotFoundException e){
	}
	catch(IOException e){
	}
	return new ArrayList<IpPackage>();
    }

    public static void calculateRtt(){
    }

    public static void printCsv(ArrayList<IpPackage> ippackagelist, ArrayList<TrptPackage> trptpackageList){
	System.out.println();
	
    }
    
}

