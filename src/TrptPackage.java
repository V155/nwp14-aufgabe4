package de.reichwald.fh.nwp14;
public class TrptPackage{

    String nr;
    String dump;
    String seqNr;
    String ackNr;
    String rto;

    public TrptPackage(String nr, String dump, String seqNr, String rto, String ackNr){
	this.nr = nr;
	this.dump = dump;
	this.seqNr = seqNr;
	this.rto = rto;
	this.ackNr = ackNr;
    }

    public String getNr(){
	return this.nr;
    }

    public String getDump(){
	return this.dump;
    }

    public String getSeqNr(){
	return this.seqNr;
    }

    public String getRto(){
	return this.rto;
    }

    public String getAckNr(){
	return this.ackNr;
    }
}
