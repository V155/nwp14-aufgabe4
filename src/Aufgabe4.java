package de.reichwald.fh.nwp14;
import de.reichwald.fh.nwp14.IpPackage;
import de.reichwald.fh.nwp14.TrptPackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Aufgabe4
{

    public static void main(String args[])
    {
	ArrayList<IpPackage> list1 = readTcpdump();
	ArrayList<TrptPackage> list2 = readTrptdump();
	ArrayList<IpPackage> list3 = mergePackages(list1);
	ArrayList<IpPackage> list4 = calculateRtt(list3);
	ArrayList<IpPackage> list5 = mergeLists(list4, list2);
	printCsv(list5);
	
	
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
		if(line.matches("^[0-9]{3} (FIN_WAIT_1:input|ESTABLISHED:output|ESTABLISHED:input|SYN_SENT:input|SYN_SENT:output) .*")){
		    output = new String(line);
		    nr = line.split(" ")[0];
		    if(line.matches("^[0-9]{3} (ESTABLISHED:input|SYN_SENT:input|SYN_SENT:output) .*")){
			int index1 = line.indexOf(')')+1;
			seqNr = new String(line.substring(index1,index1+8));
		    }else if(line.matches("^[0-9]{3} (ESTABLISHED:output) .*")){
			int index1 = line.indexOf(')')+2;
			seqNr = new String(line.substring(index1,index1+8));
		    }else if(line.matches("^[0-9]{3} (FIN_WAIT_1:input) .*")){
			int index1 = line.indexOf('@')+1;
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
	    
	}
	catch(FileNotFoundException e){
	}
	catch(IOException e){
	}
	return trptPackageList;
    }
		    
    
    public static ArrayList<IpPackage> readTcpdump(){
	ArrayList<IpPackage> ipPackageList = new ArrayList();
	
	try{
	    BufferedReader br = new BufferedReader(new FileReader("tcpdump.dump"));
	    String line = "";
	    String output = "";
	    String seqNr = "";
	    int nr = 1;
	    String ackNr = "";
	    String rseqNr = "";
	    String timestamp = "";
	    
	    while ((line = br.readLine()) !=null) {
		if(line.matches("^([0-9][0-9]:){2}[0-9]{2}\\.[0-9]{6} IP .*"))
		    {
			output=new String(line);
			nr++;
			timestamp = line.split(" ")[0];
			for (int i=0; i < 4; i++)
			    {
				if ((line = br.readLine()) != null){
				    output = output + "\n" + line;
				    /*
				    if (i == 0 && line.matches(".*, ack [0-9]{2,12}, .*")){
					rseqNr = line.split(",")[2];
					rseqNr = rseqNr.substring(rseqNr.indexOf('q')+2);
					ackNr = line.split(",")[3];
					ackNr = ackNr.substring(ackNr.indexOf('k')+2);
				    } else if (i==0) {

					if (line.split(",")[1].matches(".*cksum .*")){
					    rseqNr = line.split(",")[2];
					    rseqNr = rseqNr.substring(rseqNr.indexOf('q')+2);
					} else {
					    rseqNr = line.split(",")[1];
					    rseqNr = rseqNr.substring(rseqNr.indexOf('q')+2).split(":")[1];
					}

					}*/

				    if (i == 3)
					{
					    //seqNr = new String(line.substring(25,29) + line.substring(30,34));
					    String[] splitline = line.split(" ");
					    seqNr = new String(splitline[5] + splitline[6]);
					    ackNr = new String(splitline[7] + splitline[8]);
					}
				}
			    }
			ipPackageList.add(new IpPackage(""+nr, output, seqNr, "rtt", "rto", timestamp, rseqNr, ackNr));
			ackNr = new String();
		    }
	    }
	    br.close();
	}catch(FileNotFoundException e){
	}
	catch(IOException e){
	}
	return ipPackageList;
    }

    public static ArrayList<IpPackage> mergePackages(ArrayList<IpPackage> iplist){


	ArrayList<IpPackage> deleteList = new ArrayList();

	long oneSeqNr = Long.parseLong(iplist.get(1).getSeqNr(), 16);
	oneSeqNr++;
	String refSeqNr = Long.toHexString(oneSeqNr);
	
	for (IpPackage ipkg : iplist){
	    if (ipkg.getSeqNr().equals(refSeqNr)){
		deleteList.add(ipkg);
	    }else {
		for (IpPackage ipkg2 : iplist){
		    if (ipkg.getSeqNr().equals(ipkg2.getAckNr())){
			ipkg.setAckIn(ipkg2.getTcpdumpNr());
			ipkg.setAckTimestamp(ipkg2.getTimestamp());
		    }
		}
	    }
	}
	for (IpPackage deleteObject : deleteList){
	    iplist.remove(deleteObject);
	}
	
	return iplist;
    }

    public static ArrayList<IpPackage> mergeLists(ArrayList<IpPackage> iplist, ArrayList<TrptPackage> trptlist){

	//bad code follows. I appreciate any hint to make it better
	for (IpPackage ipkg : iplist){
	    for (TrptPackage tpkg : trptlist){
		if(ipkg.getSeqNr().equals(tpkg.getSeqNr())){
		    ipkg.setTrptNr(tpkg.getNr());
		    ipkg.setRto(tpkg.getRto());
		}
	    }
	}
	return iplist;
    }

    public static ArrayList<IpPackage> calculateRtt(ArrayList<IpPackage> ipkglist){

	DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSSSSS");

	for (IpPackage ipkg : ipkglist){
	    if(ipkg.getTimestamp()!=null && ipkg.getAckTimestamp()!=null){
		try{
		    Date date1 = formatter.parse(ipkg.getTimestamp());
		    Date date2 = formatter.parse(ipkg.getAckTimestamp());
		    ipkg.setRtt("" + (((double)date2.getTime()-(double)date1.getTime())/1000000));
		}catch (ParseException e){
		    System.out.println("Parse Exception");
		}
	    }
	}
	return ipkglist;
    }

    public static void printCsv(ArrayList<IpPackage> ipkglist){
	System.out.println("TcpdumpNr, TrptNr, AckNr, Sendezeit, Empfangszeit, SeqNr, RTT, RTO");
	for (IpPackage pkg : ipkglist){
	    System.out.println(pkg.getTcpdumpNr() + ", " + pkg.getTrptNr() + ", " + pkg.getAckIn() + ", " + pkg.getTimestamp() + ", " + pkg.getAckTimestamp() + ", " + pkg.getSeqNr() + ", " + pkg.getRtt() + ", " + pkg.getRto());
	}
	
    }
    
}

