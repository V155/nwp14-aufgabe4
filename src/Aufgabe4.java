package de.reichwald.fh.nwp14;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

public class Aufgabe4{

    public Aufgabe4(){
    }

    public static void main(String args[]){
	try{
	    BufferedReader br = new BufferedReader(new FileReader("tcpdump.dump"));
	    String line;
	    while ((line = br.readLine()) != null) {
		// process the line.
		if(Pattern.matches("^([0-9][0-9]:){2}[0-9]{2}.[0-9]{6} IP", line)){
		    System.out.println(line);
		    
	    }
	    br.close();
	}catch(FileNotFoundException e){
	}catch(IOException e){
	}
	
    }
}
    
