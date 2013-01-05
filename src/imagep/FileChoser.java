/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagep;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Asia
 */
public class FileChoser{
    private JFileChooser fileDialog;
    ParentFrame mainFrame;
    ChildFrame newChild;
    /*
     * Open a file and saves it path to te variable
     */
    public FileChoser(ParentFrame.fileOptions fileOptionsValue, ParentFrame mainFrame) {
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
                returnVal = fileDialog.showSaveDialog(mainFrame);
                break;
            case OPEN:
                returnVal = fileDialog.showOpenDialog(mainFrame);
                break;
            case IMPORT:
                returnVal = fileDialog.showOpenDialog(mainFrame);
                break;
            case EXPORT:
                returnVal = fileDialog.showSaveDialog(mainFrame);
                break;
            default:
                returnVal = javax.swing.JFileChooser.CANCEL_OPTION;
                break;
        }
        
        /*
        if(fileOptionsValue.equals(ParentFrame.fileOptions.SAVE))
            
        else
          */  
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            file= fileDialog.getSelectedFile();
             
            JOptionPane.showMessageDialog(mainFrame, file.toString());
            //newChild = new ChildFrame(file, mainFrame);
            //generateCsvFile();
            //newChild.convertToGrayscale();
        }
    }
    
    public int generateCsvFile()
    {
        //int [] pixelToCsvArray = newChild.getPixelValues();
        
        //for(int eachPixel : pixelToCsvArray)
         //   System.out.print(eachPixel + " ");
        
        return 0;
    }
}
