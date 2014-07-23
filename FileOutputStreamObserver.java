/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obserwator;

/**
 *
 * @author niewega_local
 */
public interface FileOutputStreamObserver {
    
    public void updateOnCloseStream();
    //public void updateOnEndStream(long time);
    public void updateOnWriteByte(int b, int in);
    public void updateOnGroupByte(byte[] b, int beg, int end);
    
}
