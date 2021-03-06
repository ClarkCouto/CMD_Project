/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.CoordenadorDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 *
 * @author CristianoSilva
 */
@Entity
@DiscriminatorValue(value="Coordenador")
public class Coordenador extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private Area area;
    
    @OneToMany(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<GrupoDePesquisa> gruposDePesquisa;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String siape;

    public Coordenador() {
    }    

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<GrupoDePesquisa> getGruposDePesquisa() {
        return gruposDePesquisa;
    }

    public void setGruposDePesquisa(List<GrupoDePesquisa> gruposDePesquisa) {
        this.gruposDePesquisa = gruposDePesquisa;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    @Override
    public int hashCode() {
        int hash = 3;       
        hash = 53 * hash + Objects.hashCode(super.hashCode());
        hash = 53 * hash + Objects.hashCode(this.area);
        hash = 53 * hash + Objects.hashCode(this.gruposDePesquisa);
        hash = 53 * hash + Objects.hashCode(this.siape);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordenador other = (Coordenador) obj;
        if (!Objects.equals(this.siape, other.siape)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.gruposDePesquisa, other.gruposDePesquisa)) {
            return false;
        }
        return true;
    }
    
    @Override
    public Coordenador buscarPeloId(Long id){
        return new CoordenadorDAO().findById(id);
    }
    
    public List<Coordenador> buscarTodosCoordenadores() {
        return new CoordenadorDAO().findAll();
    }
}
