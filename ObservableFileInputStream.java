
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obserwator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author molda-ni
 */
public class ObservableFileInputStream extends FileInputStream {
    
    private final ArrayList<FileInputStreamObserver> readByteObservers;
    private final ArrayList<FileInputStreamObserver> endStreamObservers;
    private final ArrayList<FileInputStreamObserver> closeStreamObservers;
    private final ArrayList<FileInputStreamObserver> readGroupByteObservers;
    private final ArrayList<FileInputStreamObserver> skipByteObservers;
    private int index;
    private long startTime;
    private long elapsedTime;
         
    public ObservableFileInputStream(String file) throws FileNotFoundException {
        super(file);
        this.closeStreamObservers = new ArrayList<>();
        this.endStreamObservers = new ArrayList<>();
        this.readByteObservers = new ArrayList<>();
        this.readGroupByteObservers = new ArrayList<>();
        this.skipByteObservers = new ArrayList<>();
        this.startTime=System.currentTimeMillis();
    }
        
    public void addReadByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(this.readByteObservers.contains(fiso)) throw new IllegalStateException();
       readByteObservers.add(fiso);
}
    
    public void addReadGroupByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(this.readGroupByteObservers.contains(fiso)) throw new IllegalStateException();
    readGroupByteObservers.add(fiso);
}
    
    public void addSkipByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(this.skipByteObservers.contains(fiso)) throw new IllegalStateException();
    skipByteObservers.add(fiso);
}

    public void addCloseStreamObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(this.closeStreamObservers.contains(fiso)) throw new IllegalStateException();
    closeStreamObservers.add(fiso);
}

    public void addEndStreamObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(this.endStreamObservers.contains(fiso)) throw new IllegalStateException();
    endStreamObservers.add(fiso);
}
    
    public void removeReadByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(!this.readByteObservers.contains(fiso)) throw new IllegalStateException();
    readByteObservers.remove(fiso);
}

    public void removeReadGroupByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(!this.readGroupByteObservers.contains(fiso)) throw new IllegalStateException();
    readGroupByteObservers.remove(fiso);
}

    public void removeSkipByteObservers (FileInputStreamObserver fiso)
{
    if(fiso==null) throw new IllegalArgumentException();
    if(!this.skipByteObservers.contains(fiso)) throw new IllegalStateException();
    skipByteObservers.remove(fiso);
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
    
    private void notifyReadByteObservers(int b, int in)
    {
        for(FileInputStreamObserver fio:readByteObservers)
            fio.updateOnReadByte(b,in);
    }
    
    private void notifyReadGroupByteObservers(byte[] b, int beg, int end)
    {   
        for(FileInputStreamObserver fio:readGroupByteObservers)
            fio.updateOnGroupByte(b,beg,end);
    }
    
    private void notifySkipByteObservers(long n,int in)
    {   
        for(FileInputStreamObserver fio:skipByteObservers) 
            fio.updateOnSkipByte(n,in);
    }
        
    private void notifyCloseStreamObservers()
    {   for(FileInputStreamObserver fio:closeStreamObservers)
                fio.updateOnCloseStream();
    }
    
    private void notifyEndStreamObservers(long time)
    {   for(FileInputStreamObserver fio:endStreamObservers) 
        fio.updateOnEndStream(time);
    }
   
    @Override public void close() throws IOException 
    {
            super.close();
            notifyCloseStreamObservers();
            this.elapsedTime=System.currentTimeMillis()-this.startTime;
    }
    
    @Override public int read() throws IOException 
    {
         int b=super.read(); this.index++;
         
         if (b<0){
                    this.elapsedTime=System.currentTimeMillis()-this.startTime;
                    notifyEndStreamObservers(this.elapsedTime);
                }
        
         notifyReadByteObservers(b,this.index) ; 
   
         return b;
     }
    
    @Override public int read(byte[] b) throws IOException 
    {
        int g=super.read(b); 
        this.index+=b.length;
        if (g<b.length) 
        {
            this.elapsedTime=System.currentTimeMillis()-this.startTime;
            notifyEndStreamObservers(this.elapsedTime);
        }
            notifyReadGroupByteObservers(b,this.index-b.length,this.index-1); 
        
        return g;
        }
    
    @Override public int read(byte[] b, int off, int len) throws IOException 
    {
        int g=super.read(b,off,len); 
        this.index+=b.length;
        if (g<len)
        {
            this.elapsedTime=System.currentTimeMillis()-this.startTime;
            notifyEndStreamObservers(this.elapsedTime);
        }
        notifyReadGroupByteObservers(b,this.index-b.length,this.index-1); 
        
        return g;
    }
    
    @Override public long skip(long n) throws IOException 
    {
        long b=super.skip(n); 
        this.index+=n;
        notifySkipByteObservers(n,this.index); 
        return b;
    }
    
 
}

