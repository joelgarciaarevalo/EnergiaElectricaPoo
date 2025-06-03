package controllers;

import models.Cliente;
import models.Registrador;

import java.util.ArrayList;
import java.util.List;

public class SistemaEnergiaCliente {
    private List <Cliente> clientes;

    public SistemaEnergiaCliente(){
        clientes = new ArrayList<>();
    }
    
    // Getters
    public List<Cliente> getClientes() { return clientes; }

    /** Crea un cliente nuevo a la lista de clientes
     * @param cliente trae la informacion del cliente creado
     */
    public void mCrearCliente(Cliente cliente){
        clientes.add(cliente);
    }

    /**Metodo que busca un cliente segun su id
     * @param id numero unico de identificacion de un cliente
     * @return devuelve un nulo en caso de no encontrar en la lista de clientes el id buscado
     */
    public Cliente mBuscarCliente(String id) {
        for (Cliente cli : clientes) {
            if (cli.getId().equals(id)) return cli;
        }
        return null;
    }

    /** Metodo para editar un cliente, edita sus datos a excepcion de su numero unico de identificacion, porque este no debe actualizarse
     * @param id numero de identificacion del cliente
     * @param nuevoNombre variable que guarda el nuevo nombre del cliente
     * @param nuevaDireccion variable que guarda la nueva direccion del cliente
     * @param nuevoEmail variable que guarda el nuevo correo del cliente
     * @param nuevaCiudad variable que guarda la nueva ciudad del cliente
     * @return
     */
    public boolean mEditarCliente(String id, String nuevoNombre, String nuevaDireccion, String nuevoEmail, String nuevaCiudad) {
        Cliente cli = mBuscarCliente(id);
        if (cli != null) {
            cli.setNombre(nuevoNombre);
            cli.setDireccion(nuevaDireccion);
            cli.setCiudad(nuevaCiudad);
            cli.SetEmail(nuevoEmail);
            return true;
        }
        return false;
    }
    
    /** Metodo que crea un nuevo registrador a un cliente
     * @param idCliente identificacoin del cliente
     * @param registrador guarda el registrador que estamos creando
     * @return en caso de el cliente ser nulo devuelve un false, porque no se puede crear un registrador a un cliente no existente
     */
    public boolean mAgregarRegistradorACliente(String idCliente, Registrador registrador) {
        Cliente cli = mBuscarCliente(idCliente);
        if (cli != null) {
            cli.mCrearRegistrador(registrador);
            return true;
        }
        return false;
    }

    /** Metodo que edita los datos de un registrador, pero no edita su id ya que este no puede cambiar
     * @param idCliente identificacion del cliente al que esta asociado el resgistrador
     * @param idRegistrador identificacion del registrador que necesitamos actualizar
     * @param nuevaUbicacion variable que guarda la nueva ubicvacion del registrador
     * @return en caso de el cliente ser nulo devuelve un false, porque no se puede actualizar un registrador a un cliente no existente
     */
    public boolean mEditarRegistrador(String idCliente, String idRegistrador, String nuevaUbicacion) {
        Cliente cli = mBuscarCliente(idCliente);
        if (cli != null) {
            Registrador res = cli.mBuscarRegistrador(idRegistrador);
            if (res != null) {
                res.setUbicacion(nuevaUbicacion);
                return true;
            }
        }
        return false;
    }


}
