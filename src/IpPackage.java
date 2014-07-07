package de.reichwald.fh.nwp14;
public class IpPackage{

    String dump;
    String seqNr;
    String rtt;
    String rto;
    String timestamp;
    String tcpdumpNr;
    String ackNr;
    String ackTimestamp;
    String trptNr;
    
    public IpPackage(String tcpdumpNr, String dump, String seqNr, String rtt, String rto, String timestamp){
	this.tcpdumpNr = tcpdumpNr;
	this.dump = dump;
	this.seqNr = seqNr;
	this.rtt = rtt;
	this.rto = rto;
	this.timestamp = timestamp;
    }

    public String getDump(){
	return this.dump;
    }

    public String getSeqNr(){
	return this.seqNr;
    }

    public String getRtt(){
	return this.rtt;
    }

    public String getRto(){
	return this.rto;
    }

    public String getTimestamp(){
	return this.timestamp;
    }

    public String getTcpdumpNr(){
	return this.tcpdumpNr;
    }

    public String getAckNr(){
	return this.ackNr;
    }

    public String getAckTimestamp(){
	return this.ackTimestamp;
    }

    public String getTrptNr(){
	return this.trptNr;
    }

    public void setAckNr(String ackNr){
	this.ackNr = ackNr;
    }

    public void setAckTimestamp(String ackTimestamp){
	this.ackTimestamp = ackTimestamp;
    }

    public void setTrptNr(String trptNr){
	this.trptNr = trptNr;
    }
    
    
}
