
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obserwator;

/**
 *
 * @author molda-ni
 */
public class ConcreteFileInputStreamObserver implements FileInputStreamObserver {

    @Override
    public void updateOnCloseStream() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    System.out.println("Zamknięcie strumienia\n");
    
    }

    @Override
    public void updateOnEndStream(long time) {
        System.out.print("\nKoniec strumienia\n\nCałkowity czas: "+time+" milisekund\n\n");
    }

    @Override
    public void updateOnReadByte(int  b, int index) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Odczytany bajt "+b+" "+ (char)b+" indeks bajtu: "+index);
    }

    @Override
    public void updateOnGroupByte(byte[] b, int beg, int end) {
        System.out.print("Odczytane bajty: ");
        for (int i=0;i<b.length;i++){System.out.print(" "+(char)b[i]);}
        System.out.print(" indeksy bajtów: "+beg+", "+end+"\n");
    }

    @Override
    public void updateOnSkipByte(long n,int in) {
        System.out.println("Opuszczone bajty "+ n +" indeks ostatniego: "+in);
    }
    
}