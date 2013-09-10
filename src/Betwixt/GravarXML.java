/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Betwixt;

import Model.Usuario;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.betwixt.strategy.HyphenatedNameMapper;
import org.xml.sax.SAXException;

/**
 *
 * @author Maximiller
 */
public class GravarXML {
    
    BeanWriter bean = new BeanWriter();
    
    public GravarXML(String arquivo, Usuario o) {
        try {
            // objeto que vai gravar o arquivo
            bean = new BeanWriter(new FileOutputStream(new File("src/Arquivos/" + arquivo+ ".xml")));
        } catch (FileNotFoundException ex) {
//            CriarLog.getSessionFactory();
            Logger.getLogger(GravarXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        bean.getXMLIntrospector().setAttributeNameMapper(new HyphenatedNameMapper());
        bean.enablePrettyPrint();
        bean.getXMLIntrospector().setAttributesForPrimitives(true);
        bean.setWriteIDs(false);
        try {
            bean.write(o);
        } catch (IOException | SAXException | IntrospectionException ex) {
            Logger.getLogger(GravarXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
