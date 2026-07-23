package model;

public class Etat {
    private Integer id;
    private String libelle;
    private String typeFonctionnel;
    private Integer etatdemarche;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTypeFonctionnel() {
        return typeFonctionnel;
    }

    public void setTypeFonctionnel(String typeFonctionnel) {
        this.typeFonctionnel = typeFonctionnel;
    }

    public Integer getEtatdemarche() {
        return etatdemarche;
    }

    public void setEtatdemarche(Integer etatdemarche) {
        this.etatdemarche = etatdemarche;
    }
}
