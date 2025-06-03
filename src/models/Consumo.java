package models;

import java.util.Arrays;
import java.util.Random;

public class Consumo {

    /**
     * Arreglo de mes, dia, hora medido en KW/h
     */
    private int [][][] consumo;

    /**
     * Arreglo de mes, dia, hora medido en pesos
     */
    private int [][][] costo;

    /**
     * Un arreglo con la cantidad de dias que tiene cada mes
     */
    private int [] diasXMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** Clase consumo que va a tener todos los metodos sobre el consumod de los clientes
     *  se encarga de recorrer el arreglo, por la cantidad de meses y generar un consumo aleatorio
     */
    public Consumo() {
        consumo = new int[12][][];
        costo = new int[12][][];
        Random random = new Random();

        for (int mes = 0; mes < 12; mes++) {
            int dias = diasXMes[mes];
            consumo[mes] = new int[dias][24];
            costo[mes] = new int[dias][24];

            for (int dia = 0; dia < dias; dia++) {
                for (int hora = 0; hora < 24; hora++) {
                    int kw = 1 + random.nextInt(5); // consumo aleatorio entre 1 y 5 KW/h
                    consumo[mes][dia][hora] = kw;
                    costo[mes][dia][hora] = mCalcularCosto(hora, kw);
                }
            }
        }
    }

    /** Metodo para calcular el costo del consumo
     * @param hora Va guardar la hora del registro del consumo
     * @param kw los kilovatios, la cantidad de energia que se consume
     * @return retorna la cantidad de energia gastada por el precio segun la franja horario consumida
     */
    private int mCalcularCosto (int hora, int kw){
        Random random = new Random();
        int precioXKw;
        if (hora >= 0 && hora <= 6) {
            precioXKw = 10 + random.nextInt(91); // 10–100
        } else if (hora >= 7 && hora <= 17) {
            precioXKw = 500 + random.nextInt(1001); // 500–1500
        } else {
            precioXKw = 300 + random.nextInt(701); // 300–1000
        }
        return kw * precioXKw;
    }

    // Getters 
    public int[] getDiasXMes() { return diasXMes; }
    public int[] getConsumoXHora(int mes, int dia) { return consumo[mes - 1][dia]; }
    public int[] getCostoXHora(int mes, int dia) { return costo[mes - 1][dia]; }

    public int getConsumoXDia(int mes, int dia) {
        return Arrays.stream(consumo[mes - 1][dia]).sum();
    }

    public int getCostoXDia(int mes, int dia) {
        return Arrays.stream(costo[mes - 1][dia]).sum();
    }

    public int getConsumoXMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        int total = 0;
        for (int dia = 0; dia < consumo[mes - 1].length; dia++) {
            total += getConsumoXDia(mes, dia);
        }
        return total;
    }

    public int getCostoXMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        int total = 0;
        for (int dia = 0; dia < costo[mes - 1].length; dia++) {
            total += getCostoXDia(mes, dia);
        }
        return total;
    }

    public int getConsumoAnual() {
        int total = 0;
        for (int mes = 1; mes <= 12; mes++) {
            total += getConsumoXMes(mes);
        }
        return total;
    }

    public int getCostoAnual() {
        int total = 0;
        for (int mes = 1; mes <= 12; mes++) {
            total += getCostoXMes(mes);
        }
        return total;
    }

    public int getDiasDelMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        return diasXMes[mes - 1];
    }

    public int[][] getConsumoMensual(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes debe estar entre 1 y 12.");
        }
        return consumo[mes - 1]; // Retorna el consumo del mes (ajustado al índice 0)
    }

    /** Metodo para ver el consumo de un cliente por dia en un mes
     * @param mes numero de meses
     * @param dia numero de dias
     */
    public void mVerConsumoXDia(int mes, int dia) {
        for (int hora = 0; hora < 24; hora++) {
            System.out.printf("Hora %02d: %d kWh\n", hora, consumo[mes - 1][dia][hora]);
        }
    }


    /** Metodo que cambia un registro por hora del consumo
     * @param mes mes del consumo
     * @param dia dia del consumo
     * @param hora hora del consumo
     * @param nuevoKw variable que guarda el nuevo consumo en el dia, mes y hora especificada
     */
    public void mModificarConsumoXHora(int mes, int dia, int hora, int nuevoKw) {
        consumo[mes - 1][dia][hora] = nuevoKw;
        costo[mes - 1][dia][hora] = mCalcularCosto(hora, nuevoKw);
    }

    /**
     * Metodo que genera los datos del consumo de un cliente, genera un consumo aleatorio y segun eso calcula su costo
     */
    public void mGenerarDatos() {
        Random random = new Random();
        for (int mes = 0; mes < 12; mes++) {
            int dias = diasXMes[mes];
            for (int dia = 0; dia < dias; dia++) {
                for (int hora = 0; hora < 24; hora++) {
                    int kw = 1 + random.nextInt(5);
                    consumo[mes][dia][hora] = kw;
                    costo[mes][dia][hora] = mCalcularCosto(hora, kw);
                }
            }
        }
    }
}
