package model;

import dao.OrdinateurDao;
import model.Etat;

public class Ordinateur extends OrdinateurDao {
    Integer id_ordinateur;
    Integer id_modele;
    Integer ram;
    String processeur;
    Integer disque_dur;

    public Integer getId_ordinateur() {
        return id_ordinateur;
    }

    public void setId_ordinateur(Integer id_ordinateur) {
        this.id_ordinateur = id_ordinateur;
    }

    public Integer getId_modele() {
        return id_modele;
    }

    public void setId_modele(Integer id_modele) {
        this.id_modele = id_modele;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getProcesseur() {
        return processeur;
    }

    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    public Integer getDisque_dur() {
        return disque_dur;
    }

    public void setDisque_dur(Integer disque_dur) {
        this.disque_dur = disque_dur;
    }

    public void save() throws Exception {
        saveOrdinateur(this);
    }

    public void update() throws Exception {
        updateOrdinateur(this);
    }

    public void delete() throws Exception {
        deleteOrdinateur(this.id_ordinateur);
    }

    public void saveEtat(String etat_libelle, String date_etat, String disfonctionnement, Integer typePanneId) throws Exception {
        saveOrdinateurEtat(this.id_ordinateur, etat_libelle, date_etat, disfonctionnement, typePanneId);
    }

    public Etat getLastEtat() throws Exception {
        return getLastEtatByOrdiId(this.id_ordinateur);
    }
}
