
package movimento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.imageio.*; 
import java.io.*;
import java.awt.image.BufferedImage;

public class Shape extends JPanel implements ActionListener
{
    Timer t = new Timer(5,this);
    String path = "C:\\Users\\RicardoMachado\\Desktop\\imagem.png";
    BufferedImage image = null;
    
    public Shape(){
    
        try{
            FileInputStream FIS = new FileInputStream(path);
            this.image = ImageIO.read(FIS);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
      
    int x = 0, y = 0;
    int velx = 1, vely = 1;
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(this.image, x, y, this);
        t.start();
    }
    
    public void actionPerformed(ActionEvent e){
       if(x < 0 || x > 744) { velx = -velx; }
       // if(y < 0 || y > 380) {vely = -vely; }
        x += velx;
        //y += vely;
        repaint();
    }
}
