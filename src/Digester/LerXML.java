/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Digester;

import Model.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.xml.sax.SAXException;

/**
 *
 * @author Maximiller
 */
public class LerXML {
    
    private Usuario retorno;
    
    public LerXML(String arquivo) {
        DigesterLoader regra = DigesterLoader.newLoader(new FromXmlRulesModule() {
        @Override
        protected void loadRules() {
            loadXMLRules(new File("src/ModeloXML/ArquivoModelo.xml"));
        }
    });
    
    Digester leitor = regra.newDigester();
    ArrayList<Usuario> resultado = new ArrayList<>();
    leitor.push(resultado);
        try {
            ArrayList<Usuario> user = leitor.parse(new File("src/Arquivos/" + arquivo));
            retorno = user.get(0);
        } catch (IOException | SAXException ex) {
            Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getRetorno() {
        return retorno;
    }
}
