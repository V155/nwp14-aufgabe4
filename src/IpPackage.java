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
    String rseqNr;
    String ackIn;
    
    public IpPackage(String tcpdumpNr, String dump, String seqNr, String rtt, String rto, String timestamp, String rseqNr, String ackNr){
	this.tcpdumpNr = tcpdumpNr;
	this.dump = dump;
	this.seqNr = seqNr;
	this.rtt = rtt;
	this.rto = rto;
	this.timestamp = timestamp;
	this.rseqNr = rseqNr;
	this.ackNr = ackNr;
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

    public String getrseqNr(){
	return this.rseqNr;
    }

    public String getAckIn(){
	return this.ackIn;
    }

    public void setAckIn(String ackIn){
	this.ackIn = ackIn;
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
