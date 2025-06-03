package models;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private final String id;
    private String TipoId;
    private String nombre;
    private String direccion;
    private List<Registrador> registradores;
    private String email;
    private String ciudad;

    public Cliente(String id, String nombre, String direccion, String TipoId, String email, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.TipoId = TipoId;
        this.email = email;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.registradores = new ArrayList<>();
    }

    // Getters
    public String getId() { return id; }
    public String getTipoId() { return TipoId; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getCiudad() { return ciudad; }
    public List<Registrador> getRegistradores() { return registradores; }
    public String getEmail() { return email; }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public void SetEmail(String email) {
        this.email = email;
    }
    
    /** Metodo que agrega un resgistrador a un cliente
     * @param res variable que guarda eñ nuevo registrador
     */
    public void mCrearRegistrador(Registrador res) {
        this.registradores.add(res);
    }

    /** Metodo que encuentra un registrador asociado a un cliente
     * @param idReg busca el id del registrador y lo compara con los id de resgistradores ya existentes
     * @return
     */
    public Registrador mBuscarRegistrador(String idReg) {
        for (Registrador res : registradores) {
            if (res.getId().equals(idReg))
                return res;
        }
        return null;
    }
}
