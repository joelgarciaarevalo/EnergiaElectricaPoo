package views;

import java.util.Scanner;

public class VistaConsola {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenuPrincipal(){
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Crear cliente");
        System.out.println("2. Editar cliente");
        System.out.println("3. Crear registrador");
        System.out.println("4. Editar registrador");
        System.out.println("5. Cargar consumo automáticamente de un cliente");
        System.out.println("6. Cargar consumo automáticamente de todos los clientes");
        System.out.println("7. Cambiar consumo de una hora");
        System.out.println("8. Generar factura (.pdf)");
        System.out.println("9. Consumo por franjas horarias");
        System.out.println("10. Salir");
        System.out.print("Seleccione opción: ");
        return scanner.nextInt();
    }
    
    public void cerrar() {
        scanner.close();
    }
}
