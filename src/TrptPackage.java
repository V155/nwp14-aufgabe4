package de.reichwald.fh.nwp14;
public class TrptPackage{

    String nr;
    String dump;
    String seqNr;
    String rto;

    public TrptPackage(String nr, String dump, String seqNr, String rto){
	this.nr = nr;
	this.dump = dump;
	this.seqNr = seqNr;
	this.rto = rto;
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
}
