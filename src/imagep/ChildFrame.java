package imagep;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.WritableRaster;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Asia
 */
public class ChildFrame extends JInternalFrame{
    private BufferedImage img;
    private int childHeight;
    private int childWidth;
    private int[] pixelsOfTheImage;
    
    /*
     * Constructor
     * @param file - Path of the image
     * @param mainframe - Parent
     */
    public ChildFrame(File file, JDesktopPane mainframe) throws PropertyVetoException {
        super("File", true, true, true, true);
        //tableDisplay = new JDesktopPane();
        //mainFrame=mainframe;
        try {
            img = ImageIO.read(file);
            this.childHeight = img.getHeight();
            this.childWidth = img.getWidth();
            this.setSize(this.childWidth, this.childHeight);
            this.setContentPane(new setBackgroundPanel(getImg()));
            this.setSelected(true);
            this.toFront();
            this.setSize(this.childWidth, this.childHeight);
            this.setVisible(true);
            //tableDisplay.add(internalFrame, JDesktopPane.POPUP_LAYER);
            mainframe.add(this, JDesktopPane.POPUP_LAYER);
           // tableDisplay.add(internalFrame, JDesktopPane.POPUP_LAYER);
        } catch (IOException e) { 
            System.out.println("Image could not be read");
            System.exit(1); 
        }
        
        //PixelValues pixels= new PixelValues(img);
        //mainFrame.setContentPane(tableDisplay); 
    }
    
    
    public void getPixelValues()
    {
        WritableRaster raster = getImg().getRaster();
        
        int[] sample = new int [4 * this.childHeight * this.childWidth];
        
        pixelsOfTheImage = raster.getPixels(0, 0, this.childWidth, this.childHeight, sample);
        int J=0;
        for(int eachSample = 0 ; eachSample < pixelsOfTheImage.length; eachSample++)
        {
            for(int i=0; i<4; i++)
            {
                System.out.print(pixelsOfTheImage[eachSample] + " ");
                eachSample++;
            }
            System.out.println();   
        }
        System.out.println(sample.length + " " + pixelsOfTheImage.length);
        BufferedImage image = new BufferedImage(childWidth, childHeight, BufferedImage.TYPE_INT_ARGB);
        //WritableRaster rastertemp = image.getRaster();
        ColorModel colorModel = image.getColorModel();
  /*      
        for(int i=0; i<childWidth; i++)
        {
            for(int j=0; j<childWidth; j++)
            {
                Object data = raster.getDataElements(i, j, null);
                WritableRaster wraster = (WritableRaster) image.getData();
                wraster.setPixels(0,0,childWidth,childHeight,pixelsOfTheImage);
            }
        }
    */    
            //image.setRGB(0, 0, childWidth, childHeight, pixelsOfTheImage, 0, childWidth);
        //image.setRGB(0, 0, childWidth, childHeight, sample, 0, 0);
            
        //for(int x = 0; x < childHeight; x++) {
          //  for(int y = 0; y < childHeight; y++) {
               // image.setRGB(x, y, );
            //}
            
//}
            
        JFrame tempFrame = new JFrame();
        
            tempFrame.setSize(this.childWidth, this.childHeight);
            tempFrame.setContentPane(new setBackgroundPanel(image));
            tempFrame.toFront();
            tempFrame.setSize(this.childWidth, this.childHeight);
            tempFrame.setVisible(true);
        //return pixelsOfTheImage;
    }

    public void convertToGrayscale()
    {
        BufferedImage grayscaleImage = new BufferedImage(childWidth, childHeight,  
            BufferedImage.TYPE_BYTE_GRAY);  
        Graphics g = grayscaleImage.getGraphics();  
        g.drawImage(img, 0, 0, null);  
        g.dispose();
        this.setContentPane(new setBackgroundPanel(grayscaleImage));
        getPixelValues();
    }
    
    
    /**
     * @return the img
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * @return the childHeight
     */
    public int getChildHeight() {
        return childHeight;
    }

    /**
     * @param childHeight the childHeight to set
     * 
     */
    public void setChildHeight(int childHeight) {
        this.childHeight = childHeight;
    }
    
    /**
     * @return the childWidth
     */
    public int getChildWidth() {
        return childWidth;
    }

    /**
     * @param childWidth the childWidth to set
     */
    public void setChildWidth(int childWidth) {
        this.childWidth = childWidth;
    }

    /**
     * @return the ChildFrame 
     */
    public ChildFrame getChildFrame(){
        return this;
    }   
}
/*
 * Sets Background image
 * @param image - Path of the image
 */
class setBackgroundPanel extends JComponent{
    private Image img;
    
    public setBackgroundPanel(Image image) {
        this.img = image;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,0,0,null);
}}






/*
class Main {
    static final int X = 380, Y = 250;
    static BufferedImage I = new BufferedImage(X, Y, BufferedImage.TYPE_INT_RGB);
 
    static public void main(String[] args){
        for (int i = 0; i<X; ++i)
        {
            for(int j=0; j<Y; ++j)
            {
                I.setRGB(i, j, 0xffc068);
            }
        } // Tan packground
        
        for (int j = 0; j<45; ++j){
            I.setRGB(j, j, 0);
        } // Scratch upper left corner
        
        WritableRaster wr = I.getRaster();
        
        if(1>0)
        {
            int x = X-40, y = Y-40;
            int[] a = new int[y*x*3]; // 96 bit pixels
            for(int j = 0; j<y; ++j)
            {
                for(int i = 0; i<x; ++i)
                {
                    int z = 3*(j*x + i);
                }
                a[z] = i; a[z+1] = j; a[z+2] = 128;
            }
          wr.setPixels(20, 20, x, y, a);}
        else {int[] a = new int[3]; a[2] = 128;
           for(int j = 0; j<Y-40; ++j) {a[1] = j;
           for(int i = 0; i<X-40; ++i) {a[0] = i;
              wr.setPixel(20+i, 20+j, a);}}}
        Frame f = new Frame( "paint Example" );
        f.add("Center", new MainCanvas());
        f.setSize(new Dimension(X,Y+22));
        f.setVisible(true);
        
        for(int q=0; q < args.length; ++q) {
           int z = args[q].lastIndexOf('.');
           if (z > 0) try {ImageIO.write(
                I, args[q].substring(z+1),
                new File(args[q]));}
              catch (IOException e) {System.err.println("image not saved.");}}
   }
}
class MainCanvas extends Canvas
{
    public void paint(Graphics g)
    {
        g.drawImage(Main.I, 0, 0, Color.red, null);
        Dimension s = getSize();
        g.drawOval(0, 0, s.width, s.height);
    }
}*/