/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Instituicao;
import entities.SetorDePesquisa;
import entities.Usuario;
import entities.Usuario.TipoUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class SetorDePesquisaBean implements Serializable {    
    private SetorDePesquisa setorDePesquisa = new SetorDePesquisa(); 
    private SetorDePesquisa setorDePesquisaSelecionado;
    private List<Instituicao> instituicoes;
    private List<SetorDePesquisa> setoresDePesquisa;
    private List<SetorDePesquisa> listaFiltrada;
    
    // Getters e Setters
    public List<SetorDePesquisa> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<SetorDePesquisa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public SetorDePesquisa getSetorDePesquisa() {
        return this.setorDePesquisa;
    }

    public void setSetorDePesquisa(SetorDePesquisa setorDePesquisa) {
        this.setorDePesquisa = setorDePesquisa;
    }

    public void setSetorDePesquisaSelecionado(SetorDePesquisa setorDePesquisa) {
        this.setorDePesquisaSelecionado = setorDePesquisa;
    }
    
    public List<SetorDePesquisa> getSetoresDePesquisa(){
        this.setoresDePesquisa = new SetorDePesquisa().buscarTodosSetores();
        return this.setoresDePesquisa;
    }

    public List<Instituicao> getInstituicoes() {
        this.instituicoes = new Instituicao().buscarTodos();
        if(this.instituicoes == null){
            this.instituicoes = new ArrayList<>();  
        }
        return this.instituicoes;
    }
    
    // Ações
    public String detalhar(Long id){
        if(id != null)
            this.setorDePesquisaSelecionado = this.setorDePesquisa.buscarPeloId(id);

        if (this.setorDePesquisaSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Setor De Pesquisa!",
                                   "Erro ao localizar Setor De Pesquisa!"));
            return "/pages/listar/listarSetoresDePesquisa";
        }
        else {
            this.setorDePesquisa = this.setorDePesquisaSelecionado;
            return "/pages/detalhes/detalhesSetorDePesquisa?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.setorDePesquisaSelecionado = this.setorDePesquisa.buscarPeloId(id);

        if (this.setorDePesquisaSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Setor De Pesquisa!",
                                   "Erro ao localizar Setor De Pesquisa!"));
            return "/pages/listar/listarSetoresDePesquisa";
        }
        else {
            this.setorDePesquisa = this.setorDePesquisaSelecionado;
            return "/pages/editar/editarSetorDePesquisa?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.setorDePesquisa = new SetorDePesquisa();
        this.setorDePesquisaSelecionado = new SetorDePesquisa();
    }
    
    public String remover(Long id) {
        if(this.setorDePesquisa.remover(id))
            return "/pages/listar/listarSetoresDePesquisa?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Setor De Pesquisa!",
                                   "Erro ao excluir Setor De Pesquisa!"));
            return "/pages/listar/listarSetoresDePesquisa";
        }
    }
    
    public String salvar() {
        if(validarCpfUnico(this.setorDePesquisa.getCpf())){
            this.setorDePesquisa.setSenha("1234");
            this.setorDePesquisa.setTipo(TipoUsuario.SetorDePesquisa);
            if(this.setorDePesquisa.salvar())
                return "/pages/listar/listarSetoresDePesquisa?faces-redirect=true";
            else {
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Setor De Pesquisa!",
                                       "Erro ao salvar Setor De Pesquisa!"));
                return "/pages/cadastrar/cadastrarSetorDePesquisa";
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Coordenador!",
                                   "Já existe um Usuário cadastrado com este CPF!"));
            return "/pages/cadastrar/cadastrarCoordenador";
        }
    }
    
    public boolean validarCpfUnico(String cpf){
        Usuario user = new Usuario().buscarPeloCpf(cpf);
        if(user == null)
            return true;
        return false;
    }
}
