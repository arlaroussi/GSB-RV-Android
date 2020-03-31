package models;

public class Mission {

    private long id;
    private String libelle;
    private int duree;
    private boolean isSelected;
    private long visiteurId;

    public Mission(long id, String libelle, int duree, long visiteurId) {
        this.id = id;
        this.libelle = libelle;
        this.duree = duree;
        this.visiteurId = visiteurId;
        this.isSelected = false;
    }

    // --- GETTER ---
    public long getId() { return id; }
    public String getLibelle() { return libelle; }
    public int getDuree() { return duree; }
    public Boolean getSelected() { return isSelected; }
    public long getVisiteurId() { return visiteurId; }

    // --- SETTER ---
    public void setId(long id) { this.id = id; }
    public void setLibelle(String libellle) { this.libelle = libellle; }
    public void setDuree(int duree) { this.duree = duree; }
    public void setSelected(Boolean selected) { isSelected = selected; }
    public void setUserId(long visiteurId) { this.visiteurId = visiteurId; }
}
