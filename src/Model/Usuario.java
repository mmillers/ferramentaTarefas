/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiller
 */
public class Usuario {
    private String nome;
    private String senha;
    private List<Tarefa> lista;
    
    public Usuario() {
        lista = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Tarefa> getLista() {
        return lista;
    }

    public void setLista(List<Tarefa> lista) {
        this.lista = lista;
    }
    
    public void addTarefa(Tarefa t){
        lista.add(t);
    }
    
    public void rmTarefa (Tarefa t) {
        lista.remove(t);
    }
    
    public Tarefa encontraTarefa(String nome){
        for(Tarefa t : lista){
            if(t.getNome().equals(nome))
                return t;
        }
        return null;
    }
    
}
