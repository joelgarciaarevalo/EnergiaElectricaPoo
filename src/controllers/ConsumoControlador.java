package controllers;

import models.Cliente;
import models.Consumo;
import models.GenerarFactura;
import models.Registrador;
import views.VistaConsola;

import java.util.Scanner;

public class ConsumoControlador {

    private VistaConsola vista = new VistaConsola();
    private SistemaEnergiaCliente sistemaEnergiaCliente;
    private Scanner scanner;

    /**
     * constructor de nuestro controlador, inicializamos el scanner
     */
    public ConsumoControlador (){
        sistemaEnergiaCliente = new SistemaEnergiaCliente();
        scanner = new Scanner(System.in);
    }
    /**
     *  Metodo que lee la opcion del menu de opciones de la vista y segun eso hace lo que el cliente necesita
     */
    public void iniciar(){
        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1: mCrearCliente();
                    break;
                case 2: mEditarCliente();
                    break;
                case 3: mCrearRegistrador();
                    break;
                case 4: mEditarRegistrador();
                    break;
                case 5: mCargarConsumoXCLiente();
                    break;
                case 6: mCargarConsumoTodosClientes();
                    break;
                case 7: mCambiarConsumoXHora();
                    break;
                case 8: mGenerarFactura();
                    break;
                case 9: mCargarConsumoXFranjas();
                    break;
                case 10: System.out.println("Saliendo del programa");
                    break;
                default: System.out.println("Opción invalida");
            }
        } while (opcion != 10);
        vista.cerrar();
    }

    /**
     * Metodo que pide los datos al usuario y crea un nuevo cliente
     */
    private void mCrearCliente(){
        System.out.println("Ingresa ID del cliente: ");
        String id = scanner.nextLine();
        System.out.println("Ingresa tipo de ID del cliente: ");
        String tipoId = scanner.nextLine();
        System.out.println("Ingresa nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingresa ciudad del cliente: ");
        String ciudad = scanner.nextLine();
        System.out.println("Ingresa Direccion del cliente: ");
        String direccion = scanner.nextLine();
        System.out.println("Ingresa Email del cliente: ");
        String email = scanner.nextLine();
        try {
            sistemaEnergiaCliente.mCrearCliente(new Cliente(id, tipoId, nombre, ciudad, direccion, email));
            System.out.println("Cliente creado");
        } catch (Exception e){
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }
    
    /**
     * Edita un cliente existente, primero revisa si existe y luego le da los nuevos valores hechos por el usuario
     */
    private void mEditarCliente(){
        boolean registrado = false;
        System.out.print("ID del cliente a editar: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese su Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su Nueva dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese su Nueva Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese su Nueva Correo: ");
        String email = scanner.nextLine();
        try {
            registrado = sistemaEnergiaCliente.mEditarCliente(id, nombre, direccion, ciudad, email);
        } catch (Exception e) {
            System.out.println("Error al editar cliente: " + e.getMessage());
        }
        if (registrado) {
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    /**
     * Metodo que crea un registrador asocioandolo a un cliente existente
     */
    private void mCrearRegistrador(){
        String ubicacion = "Desconocida";
        String direccion = "Desconocida";
        String ciudad = "Desconocida";
        System.out.print("ID del cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("ID del registrador: ");
        String idReg = scanner.nextLine();
        System.out.println("La ubicacion se asociara al cliente??");
        System.out.println("1. Si");
        System.out.println("2. No");
        int respuesta = scanner.nextInt();
        if (respuesta == 2) {
            System.out.print("Ubicación: ");
            ubicacion = scanner.nextLine();
        } else if (respuesta == 1) {
            Cliente cliente = sistemaEnergiaCliente.mBuscarCliente(idCliente);
            if (cliente != null) {
                direccion = cliente.getDireccion();
                ciudad = cliente.getCiudad();
                ubicacion = direccion + ", " + ciudad;
            } else {
                System.out.println("Cliente no encontrado. Se asignará ubicación por defecto.");
            }
        }
        boolean ok = sistemaEnergiaCliente.mAgregarRegistradorACliente(idCliente, new Registrador(idReg, ubicacion));
        System.out.println(ok ? "Registrador agregado." : "Cliente no encontrado.");
    }

    /**
     * Metodo que edita un registrador de un cliente, comprueba que ambos existan antes de darle los nuevos valores
     */
    private void mEditarRegistrador(){
        System.out.print("ID del cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("ID del registrador: ");
        String idReg = scanner.nextLine();
        System.out.print("Nueva ubicación: ");
        String ubicacion = scanner.nextLine();

        if (sistemaEnergiaCliente.mEditarRegistrador(idCliente, idReg, ubicacion)) {
            System.out.println("Registrador actualizado.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
    }

    /**
     * Metodo que carga el consumo de un cliente. comprueba si este cliente existe, y de ser asi, trae sus registros
     */
    private void mCargarConsumoXCLiente(){
        System.out.print("ID del cliente: ");
        String id = scanner.nextLine();
        Cliente cli = sistemaEnergiaCliente.mBuscarCliente(id);
        if (cli != null){
            System.out.println("Cliente encontrado.");
            for (Registrador res : cli.getRegistradores()) {
                res.getConsumo().mGenerarDatos();
            }
            System.out.println("Consumo generado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    /**
     * Metodo que carga todos los consumos de todos los clientes 
     */
    private void mCargarConsumoTodosClientes(){
        for (Cliente cli : sistemaEnergiaCliente.getClientes()) {
            for (Registrador res : cli.getRegistradores()) {
                res.getConsumo().mGenerarDatos();
            }
        }
        System.out.println("Consumo cargado de todos los clientes.");
    }

    /**Metodo que cambia el consumo en una horqa especifica
     * Comprueba si existe el cliente y el registrador, luego valida si el dia, mes y hora es valido, para modificar un registro
     */
    private void mCambiarConsumoXHora(){
        System.out.print("ID del cliente: ");
        String id = scanner.nextLine();
        Cliente cli = sistemaEnergiaCliente.mBuscarCliente(id);
        if (cli == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("ID del registrador: ");
        String idReg = scanner.nextLine();
        Registrador res = cli.mBuscarRegistrador(idReg);
        if (res == null) {
            System.out.println("Registrador no encontrado.");
            return;
        }

        System.out.print("Mes (1 = Enero ... | ... 12 = Diciembre): ");
        int mes = scanner.nextInt();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }

        int diasDelMes = res.getConsumo().getDiasDelMes(mes);
        int dia;
        do {
            System.out.print("Día (1 - " + diasDelMes + "): ");
            dia = scanner.nextInt();
            if (dia < 1 || dia > diasDelMes) {
                System.out.println("Día inválido para este mes.");
            }
        } while (dia < 1 || dia > diasDelMes);

        int hora;
        do {
            System.out.print("Hora (0-23): ");
            hora = scanner.nextInt();
            if (hora < 0 || hora > 23) {
                System.out.println("Hora inválida.");
            }
        } while (hora < 0 || hora > 23);

        System.out.print("Nuevo consumo (kW/h): ");
        int kw = scanner.nextInt();

        res.getConsumo().mModificarConsumoXHora(diasDelMes, dia, hora, kw);
    }

    private void mGenerarFactura(){
        System.out.print("ID del cliente: ");
        String id = scanner.nextLine();
        Cliente cli = sistemaEnergiaCliente.mBuscarCliente(id);
        if (cli == null) {
            System.out.println("Cliente no encontrado.");
            return;
        } else {
            System.out.println("Cliente encontrado.");
        }

        System.out.print("Mes (1 = Enero.. || ... 12 = Diciembre): ");
        int mes = scanner.nextInt();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }

        String[] nombresMes = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        String nombreMes = nombresMes[mes - 1];

        scanner.nextLine();
        System.out.print("Nombre archivo PDF (sin .pdf): ");
        String nombreArchivo = scanner.nextLine();

        int diaMenorConsumo = -1;
        int horaMenorConsumo = -1;
        int diaMayorConsumo = -1;
        int horaMayorConsumo = -1;
        int menorConsumo = Integer.MAX_VALUE;
        int mayorConsumo = Integer.MIN_VALUE;

        for (Registrador res : cli.getRegistradores()) {
            int[][] consumoMensual = res.getConsumo().getConsumoMensual(mes);
            for (int dia = 0; dia < consumoMensual.length; dia++) {
                for (int hora = 0; hora < consumoMensual[dia].length; hora++) {
                    if (consumoMensual[dia][hora] < menorConsumo) {
                        menorConsumo = consumoMensual[dia][hora];
                        diaMenorConsumo = dia + 1;
                        horaMenorConsumo = hora;
                    }
                    if (consumoMensual[dia][hora] > mayorConsumo) {
                        mayorConsumo = consumoMensual[dia][hora];
                        diaMayorConsumo = dia + 1;
                        horaMayorConsumo = hora;
                    }
                }
            }
        }

        GenerarFactura.generarFactura(cli, mes, nombreArchivo + ".pdf", diaMenorConsumo, horaMenorConsumo, menorConsumo,
                diaMayorConsumo, horaMayorConsumo, mayorConsumo);
        System.out.println("Factura generada: " + nombreArchivo + ".pdf");
    }
    

    /**
     * Carga el consumo por franja horaria de un registrador
     */
    private void mCargarConsumoXFranjas(){
        System.out.println("Ingrese el Id del cliente :");
        String id = "0";
        try {
            id = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error al ingresar el id del cliente error: " + e.getMessage());
        }
        Cliente cli = sistemaEnergiaCliente.mBuscarCliente(id);
        if (cli == null) {
            System.out.println("Cliente no encontrado.");
            return;
        } else {
            System.out.println("Cliente encontrado.");
        }
        System.out.println("Ingrese el mes (1-12):");
        int mes = 0;
        try {
            mes = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error al ingresar el mes error: " + e.getMessage());
        }
        int diasDelMes = cli.getRegistradores().get(0).getConsumo().getDiasDelMes(mes);
        if (diasDelMes == 0) {
            System.out.println("Mes inválido.");
            return;
        }
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }
        System.out.println("Ingrese el dia (1 -" + diasDelMes + "): ");
        int dia = 0;
        try {
            dia = scanner.nextInt();
        } catch (Exception ex) {
            System.out.println("Error al ingresar el dia, error: " + ex.getMessage());
        }
        Consumo consumo = cli.getRegistradores().get(0).getConsumo();
        int[][] consumoMensual = consumo.getConsumoMensual(mes);
        int[] franjas = new int[consumoMensual[dia - 1].length];
        // Consumo por franja horaria
        System.out.println("Ahora haremos el Consumo por franjas horarias:");
        int HoraDeInicio = 0;
        int HoraDeFin = 0;
        try {
            System.out.println("Ingrese la hora de inicio (0-23):");
            HoraDeInicio = scanner.nextInt();
            System.out.println("Ingrese la hora de fin (0-23):");
            HoraDeFin = scanner.nextInt();
            for (int i = HoraDeInicio; i <= HoraDeFin; i++) {
                franjas[i] = consumoMensual[dia - 1][i];
            }
            int sumaDeHoras = 0;
            System.out.println("El consumo de la franja horaria es:");
            for (int i = HoraDeInicio; i <= HoraDeFin; i++) {
                System.out.println("Hora " + i + ": " + franjas[i] + " kWh");
                sumaDeHoras += franjas[i];
            }
            System.out.println("El consumo total de la franja horaria del mes " + mes + ", el dia " + dia
                    + ",entre las " + HoraDeInicio + ",y las " + HoraDeFin + " es: " + sumaDeHoras + " kWh");

        } catch (Exception ex) {
            System.out.println("Error al ingresar las horas, error: " + ex.getMessage());
        }
    }

}
