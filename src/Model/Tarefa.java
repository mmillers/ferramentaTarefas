package Model;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    
    private String nome;
    private String descricao;
    private String prioridade;
    private String previsao;
    private String conclusao;
    private String situacao;
    private List<Sub> sub;
    
    public Tarefa() {
        sub = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getPrevisao() {
        return previsao;
    }

    public void setPrevisao(String previsao) {
        this.previsao = previsao;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Sub> getSub() {
        return sub;
    }

    public void setSub(List<Sub> sub) {
        this.sub = sub;
    }
    
    public void addSub (Sub s) {
        sub.add(s);
    }

    public void rmSub (Sub s) {
        sub.remove(s);
    }

    @Override
    public String toString(){
        return nome;
    }
    
    public String ChamaAll(){
        return "Descrição: " + descricao + "\n" + "Fechar em: " + previsao + "\n" + "Prioridade: " + prioridade 
                + "\n" + "Status: " + situacao + "\n" + "Data de conclusão: " + conclusao;
    }

    public Sub encontraSub(String nome) {
        for(Sub t : sub){
            if(t.getName().equals(nome))
                return t;
        }
        return null;
    }
    
}
