package com.example.tpandroid;

import java.io.Serializable;


public class CarteTresor implements Serializable {
    public int id;
    public String nom;
    public String localisation;
    public String difficulte;
    public int duree;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public CarteTresor(){

    }

    public CarteTresor(int id, String nom, String localisation, String difficulte, int duree) {
        this.id = id;
        this.nom = nom;
        this.localisation = localisation;
        this.difficulte = difficulte;
        this.duree = duree;
    }
}
