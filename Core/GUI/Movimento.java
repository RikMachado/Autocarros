
package movimento;
import javax.swing.JFrame;

/**
 *
 * @author 20160746
 */
public class Movimento
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Shape s = new Shape();
        JFrame jf = new JFrame();
        
        jf.add(s);
        jf.setVisible(true);
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
