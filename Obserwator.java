/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obserwator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author molda-ni
 */
public class Obserwator {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        
         ConcreteFileInputStreamObserver obs1 = new ConcreteFileInputStreamObserver();
        ConcreteFileInputStreamObserver obs2 = new ConcreteFileInputStreamObserver();
        ConcreteFileInputStreamObserver obs3 = new ConcreteFileInputStreamObserver();
        ConcreteFileInputStreamObserver obs4 = new ConcreteFileInputStreamObserver();
        ConcreteFileInputStreamObserver obs5 = new ConcreteFileInputStreamObserver();
        
ObservableFileInputStream ofis = new ObservableFileInputStream("c:\\Big Bang.txt");

        ofis.addCloseStreamObservers(obs1);
        ofis.addEndStreamObservers(obs2);
        ofis.addReadByteObservers(obs3);
        ofis.addReadGroupByteObservers(obs4);
        ofis.addSkipByteObservers(obs5);
      
      while(ofis.read()!=-1){}
       // byte[] buffer=new byte[5];
    //while ((ofis.read(buffer)) != -1) {} 
      // ofis.skip(24);
        
      // byte[] buffer=new byte[5];
       //ofis.read(buffer);
       
      //  System.out.println("Zostało bajtów: "+ ofis.available());
    
         
//ofis.close();

FileOutputStreamObserver outObs1 = new ConcreteFileOutputStreamObserver();
FileOutputStreamObserver outObs2 = new ConcreteFileOutputStreamObserver();
FileOutputStreamObserver outObs3 = new ConcreteFileOutputStreamObserver();

 File afile = new File("c:\\Big Bang.txt");
       // File bfile = new File("c:\\ofos.txt");

       InputStream inStream = new FileInputStream(afile);
        //outStream = new FileOutputStream(bfile);
    ObservableFileOutputStream outStream = new ObservableFileOutputStream("c:\\ofos.txt");
outStream.addCloseStreamObservers(outObs1);
outStream.addWriteByteObservers(outObs2);
outStream.addWriteGroupByteObservers(outObs3);

    int b;
    byte[] buffer=new byte[5];
while ((b = inStream.read( buffer))  != -1) {outStream.write(buffer);  }		
     
             

        //ofis.read();
        outStream.close();

       
}
}
