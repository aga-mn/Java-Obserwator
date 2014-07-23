/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obserwator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author niewega_local
 */
public class ConcreteFileOutputStreamObserver implements FileOutputStreamObserver{

    @Override
    public void updateOnCloseStream() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
//System.out.println(dateFormat.format(cal.getTime()));

       	String data = "\r\n"+dateFormat.format(cal.getTime())+" Zamknięcie strumienia\n";
 
    	try{File file =new File("c:\\outputFileObserver.txt");
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file,true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();}	
      catch(IOException e){
    		e.printStackTrace();
    	}
        

 
  
    
    }


    @Override
    public void updateOnWriteByte(int b, int in) {
 
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

       	String data = "\r\n"+dateFormat.format(cal.getTime())+" Zapisany bajt "+ b+" indeks "+in;
 
    	try{File file =new File("c:\\outputFileObserver.txt");
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file,true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();}	
      catch(IOException e){
    		e.printStackTrace();
    	}
    
    }

    @Override
    public void updateOnGroupByte(byte[] b, int beg, int end) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

          StringBuilder bld = new StringBuilder();
    
    for (int i=0;i<b.length;i++) {
        bld.append(String.valueOf(b[i]));        
       if(i<b.length-1) bld.append(", ");}
        bld.append(".");
        
       	String data = "\r\n"+dateFormat.format(cal.getTime())+" Zapisane bajty "+bld+ " indeks pocz. "+beg+" indeks końcowy "+end;
 
    	try{File file =new File("c:\\outputFileObserver.txt");
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file,true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();}	
      catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
}
