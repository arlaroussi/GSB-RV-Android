package rli.lla.loginwindows;

public class Visiteur {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public Visiteur(String email, String nom, String prenom,  String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public Visiteur() { }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

