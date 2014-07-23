/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obserwator;

/**
 *
 * @author molda-ni
 */
public interface FileInputStreamObserver {
    
    public void updateOnCloseStream();
    public void updateOnEndStream(long time);
    public void updateOnReadByte(int b, int in);
    public void updateOnGroupByte(byte[] b, int beg, int end);
    public void updateOnSkipByte(long n,int in);
}