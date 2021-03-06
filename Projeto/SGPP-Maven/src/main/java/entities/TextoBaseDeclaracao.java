/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.TextoBaseDeclaracaoDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class TextoBaseDeclaracao extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @Column(nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDesativacao;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(30)")
    private String identificador;
    
    @Column(nullable=false, columnDefinition = "TEXT(3000)")
    private String texto;

    public TextoBaseDeclaracao() {
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }  
    
    public TextoBaseDeclaracao buscarPeloId(Long id){
        return new TextoBaseDeclaracaoDAO().findById(id);
    }
    
    public List<TextoBaseDeclaracao> buscarTodos() {
        return new TextoBaseDeclaracaoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new TextoBaseDeclaracaoDAO().remove(id);
    }
    
    public boolean salvar(){
        return new TextoBaseDeclaracaoDAO().save(this);
    }
}