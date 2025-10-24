package org.tricol.model;

import jakarta.persistence.*;


@Entity
@Table(name = "suppliers")
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "companyName", nullable = false)
    private String societe;
    private String adresse;
    private String contact;
    private String telephone;
    private String email;
    private String ville;
    @Column(unique = true, nullable = true)
    private String ice;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getIce() { return ice; }
    public void setIce(String ice) { this.ice = ice; }
}
