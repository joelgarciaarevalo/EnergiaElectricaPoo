package models;

public class Registrador {
    private String idReg;
    private String ubicacion;
    private Consumo consumo;

    /** Constructor de mi clase registrador
     * @param idReg numero unico de identificacion del registrador
     * @param ubicacion ubicacion del registrador
     */
    public Registrador (String idReg, String ubicacion) {
        this.idReg = idReg;
        this.ubicacion = ubicacion;
        this.consumo = new Consumo();
    }

    // Getters
    public String getId() { return idReg; }
    public String getUbicacion() { return ubicacion;}
    public Consumo getConsumo() { return consumo; }
    
    // Setters
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    
}
