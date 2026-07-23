package model;

import dao.ModelDao;

public class Model extends ModelDao {
    Integer id_model;
    String libelle;

    public Integer getId_model() {
        return id_model;
    }
    public void setId_model(Integer id_model) {
        this.id_model = id_model;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    

}

