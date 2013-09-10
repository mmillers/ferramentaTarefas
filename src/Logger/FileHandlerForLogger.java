/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 *
 * @author Maximiller
 */
public class FileHandlerForLogger {
    
    private static FileHandler criaArquivo;
    
    public static FileHandler getFileHandler(String str) throws IOException{
        
        if(criaArquivo == null)
            criaArquivo = new FileHandler(str);
        return criaArquivo;
    }
}
