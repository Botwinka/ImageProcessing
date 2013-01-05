package imagep;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Asia
 */
public class ParentFrame extends JFrame{
    public JDesktopPane tableDisplay;
    //ChildFrame cf;  //Active child
    public enum fileOptions {SAVE, OPEN, IMPORT, EXPORT};
    private JFileChooser fileDialog;
    private static final int DEFAULT_WINDOW_WIDTH=1200;
    private static final int DEFAULT_WINDOW_HEIGH=800;
    private JMenu file;
    private JMenu lab2;
    private JMenu lab3;
    private JMenu lab1;
    private JMenu lab5;
    private JMenu lab6;
    private JMenu lab7;
    private JMenu lab4;
        
    /*
     * Build Main Frame witch welcome us
     */
    public ParentFrame() {
        setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGH);
        tableDisplay = new JDesktopPane();
        this.add(tableDisplay);
        drawMenuBar();
    }
    
    /*
     * Build MenuBar in the MainFrame
     */
    private void drawMenuBar() {
        JMenuBar menuBar = new JMenuBar();
      
        //ArrayList<JMenu> menuElementsList= new ArrayList<>();
        Map<String,JMenu> mainMenuElementsMap = new HashMap<String,JMenu>();
       
        file = new JMenu("Plik");
        lab1 = new JMenu("Lab1");
        lab2 = new JMenu("Lab2");
        lab3 = new JMenu("Lab3");
        lab4 = new JMenu("Lab4");
        lab5 = new JMenu("Lab5");
        lab6 = new JMenu("Lab6");
        lab7 = new JMenu("Lab7");
        
        menuBar.add(file);
        menuBar.add(lab1);
        menuBar.add(lab2);
        menuBar.add(lab3);
        menuBar.add(lab4);
        menuBar.add(lab5);
        menuBar.add(lab6);
        menuBar.add(lab7);
        
        file.add(saveFile());
        file.add(openFile());
        file.addSeparator();
        file.add(exportFile());
        file.add(importFile());
        file.addSeparator();
        file.add(exitFile());
        
        lab1.add(lab1GrayscaleFile());
        
        this.setJMenuBar(menuBar);
    }
    
    private void choseFile(fileOptions fileOptionsValue) throws PropertyVetoException {
        fileDialog = new JFileChooser();
        File file;
        
        FileNameExtensionFilter ft;
        if(fileOptionsValue.equals(ParentFrame.fileOptions.SAVE) || fileOptionsValue.equals(ParentFrame.fileOptions.OPEN)) {
            ft = new FileNameExtensionFilter("Pliki Graficzne | *jpg *bmp *png", "jpg", "bmp", "png");
        }
        else { 
            ft = new FileNameExtensionFilter("Pliki csv | *csv", "csv");
        }
        fileDialog.addChoosableFileFilter(ft);
        fileDialog.setAcceptAllFileFilterUsed(false);
        fileDialog.setFileFilter(ft);
        
        int returnVal;
        switch(fileOptionsValue)
        {
            case SAVE:
                returnVal = fileDialog.showSaveDialog(this);
                break;
            case OPEN:
                returnVal = fileDialog.showOpenDialog(this);
                break;
            case IMPORT:
                returnVal = fileDialog.showOpenDialog(this);
                break;
            case EXPORT:
                returnVal = fileDialog.showSaveDialog(this);
                break;
            default:
                returnVal = javax.swing.JFileChooser.CANCEL_OPTION;
                break;
        }
        
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            file = fileDialog.getSelectedFile();
            if((fileOptionsValue.equals(ParentFrame.fileOptions.OPEN) || fileOptionsValue.equals(ParentFrame.fileOptions.IMPORT)))
            {
                JOptionPane.showMessageDialog(this, file.toString());
                ChildFrame newChild = new ChildFrame(file, tableDisplay);
            }
            else if(fileOptionsValue.equals(ParentFrame.fileOptions.SAVE) || fileOptionsValue.equals(ParentFrame.fileOptions.EXPORT))
            {
                try {
                    ChildFrame cf = getActiveMDIChild();
                    BufferedImage bi = cf.getImg();
                    ImageIO.write(bi, "png", file);
                } catch (IOException e) {
                }
            }
        }
    }
    
    /*
     * New Child
     */
    
    
    
    
    /*
     * Returns active frame
     */
    private ChildFrame getActiveMDIChild()
    {
        JInternalFrame frames = tableDisplay.getSelectedFrame();
        ChildFrame cf;
        cf = (ChildFrame) frames;
        return cf;
        //cf.convertToGrayscale();
        //repaint();
        //frames.repaint();       
    }
    
    
    /*
     * Returns an object
     */
    private ParentFrame getParentFrame(){
        return this;
    }
    
    /*
     * Saves a file
     */
    private Action saveFile() {
        return new AbstractAction("Zapisz") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    choseFile(fileOptions.SAVE);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ParentFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }
    
    private Action openFile()
    {
        return new AbstractAction("Otwórz") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    choseFile(fileOptions.OPEN);
                    //choseFile("graphic" , true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ParentFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
    }
    
    private Action importFile()
    {
        return new AbstractAction("Import") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    choseFile(fileOptions.IMPORT);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ParentFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }
    
    private Action exportFile()
    {
        return new AbstractAction("Export") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    choseFile(fileOptions.EXPORT);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(ParentFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }
    
    private Action exitFile()
    {
        return new AbstractAction("Zakoncz") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }
    
    
    private Action lab1GrayscaleFile()
    {
        return new AbstractAction("Czarno-Biały") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildFrame cf = getActiveMDIChild();
                cf.convertToGrayscale();
            }
        };
    }
    
    
    
}