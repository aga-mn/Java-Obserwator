/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obserwator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author niewega_local
 */
public class ObservableFileOutputStream extends FileOutputStream{

    private final ArrayList<FileOutputStreamObserver> writeByteObservers;
    private final ArrayList<FileOutputStreamObserver> endStreamObservers;
    private final ArrayList<FileOutputStreamObserver> closeStreamObservers;
    private final ArrayList<FileOutputStreamObserver> writeGroupByteObservers;
   private int index;
    
    
    public ObservableFileOutputStream(String file) throws FileNotFoundException {
        super(file);
        this.closeStreamObservers = new ArrayList<>();
        this.endStreamObservers = new ArrayList<>();
        this.writeByteObservers = new ArrayList<>();
        this.writeGroupByteObservers = new ArrayList<>();
    
    }
    
    public void addWriteByteObservers (FileOutputStreamObserver foso)
{
    if(foso==null) throw new IllegalArgumentException();
    if(this.writeByteObservers.contains(foso)) throw new IllegalStateException();
       writeByteObservers.add(foso);
}
    
    public void addWriteGroupByteObservers (FileOutputStreamObserver foso)
{
    if(foso==null) throw new IllegalArgumentException();
    if(this.writeGroupByteObservers.contains(foso)) throw new IllegalStateException();
    writeGroupByteObservers.add(foso);
}

    public void addCloseStreamObservers (FileOutputStreamObserver foso)
{
    if(foso==null) throw new IllegalArgumentException();
    if(this.closeStreamObservers.contains(foso)) throw new IllegalStateException();
    closeStreamObservers.add(foso);
}
    
  public void addEndStreamObservers (FileOutputStreamObserver foso)
{
    if(foso==null) throw new IllegalArgumentException();
    if(this.endStreamObservers.contains(foso)) throw new IllegalStateException();
    endStreamObservers.add(foso);
}
    
    public void removeWriteByteObservers (FileOutputStreamObserver foso)
{
    if(foso==null) throw new IllegalArgumentException();
    if(!this.writeByteObservers.contains(foso)) throw new IllegalStateException();
    writeByteObservers.remove(foso);
}

    public void removeWriteGroupByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(!this.writeGroupByteObservers.contains(fiso)) throw new IllegalStateException();
    writeGroupByteObservers.remove(fiso);
}

    public void removeCloseStreamObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(!this.closeStreamObservers.contains(fiso)) throw new IllegalStateException();
    closeStreamObservers.remove(fiso);
}
    public void removeEndStreamObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(!this.endStreamObservers.contains(fiso)) throw new IllegalStateException();
    endStreamObservers.remove(fiso);
}    
    
private void notifyWriteByteObservers(int b, int in)
    {
        for(FileOutputStreamObserver fio:writeByteObservers)
            fio.updateOnWriteByte(b,in);
    }
    
    private void notifyWriteGroupByteObservers(byte[] b, int beg, int end)
    {   
        for(FileOutputStreamObserver fio:writeGroupByteObservers)
            fio.updateOnGroupByte(b,beg,end);
    }
    
        
    private void notifyCloseStreamObservers()
    {   for(FileOutputStreamObserver fio:closeStreamObservers)
                fio.updateOnCloseStream();
    }
    
     
    
    @Override public void close() throws IOException 
    {
            super.close();
            notifyCloseStreamObservers();            
    }
    
    @Override public void write(int b) throws IOException 
    {
         super.write(b); 
         this.index++;
         notifyWriteByteObservers(b,this.index) ; 
         
     }
    
    @Override public void write (byte[] b) throws IOException 
    {
        super.write(b); 
        this.index+=b.length;
        notifyWriteGroupByteObservers(b,this.index-b.length,this.index-1); 
   
    }
    
    @Override public void write(byte[] b, int off, int len) throws IOException 
    {
        super.write(b,off,len); 
        this.index+=b.length;
        notifyWriteGroupByteObservers(b,this.index-b.length,this.index-1); 
        
    }
    
}
