## Creators

Joel Santiago Garcia Arevalo - 910154 - Universidad Minuto de Dios | joel.garcia@uniminuto.edu.co
Laura Valentina Giraldo Vargas - 974754 - Universidad Minuto de Dios | laura.giraldo-va@uniminuto.edu.co

## Descripción

Este es un proye4cto desarrollado en java. En el analizamos, creamos y gestionamos datos sobre los clientes en el sector de la energia electrica de Bogota. Los clientes tienen asociados registradores, cada cliente puede tener mas de un resgistrador. Estos registradores a su vez tienen varios registros de consumo de energia. El costo de este consumo de energia va a depender de una franja horaria, segun la hora el costo cambia. 
Este proyecto cuenta con:

- Creacion y actualizacion de clientes.
- Creacion y actualizacion de registradores asociados a los clientes.
- Cargar consumos de un usuario por hora o por mes.
- Cargar todos los consumos de todos los usuarios.
- Generar factura tipo .pdf con los datos del cliente de un mes en especifico.

La creacion del pdf se hizo mediante la libreria itext pdf, para esta fue necesario crear el archivo pom.xml, en el que hay dependencias y configuraciones. Tambien fue necesario añadir a nuestra carpeta lib, el archivo .jar para que pueda leer la libreria.

## Estructura

EnergiaElectricaPoo/
    .vscode/
        settings.json
    bin/
        controllers/
            ConsumoControlador.class
            SistemaEnergiaCliente.class
        documents/
            InformeEnergiaElectrica.pdf
        models/
            Cliente.class
            Consumo.class
            GenerarFactura.class
            Registrador.class
        views/
            VistaConsola.class
        App.class
    lib/
        itextpdf-5.5.13.3.jar
    src/
        controllers/
            ConsumoControlador.java
            SistemaEnergiaCliente.java
        documents/
            InformeEnergiaElectrica.pdf
        models/
            Cliente.java
            Consumo.java
            GenerarFactura.java
            Registrador.java
        views/
            VistaConsola.java
        App.java
    pom.xml
    README.md

En el **controllers** estan los controladores que gestionan la logica del proyecto.
En el **models** estan los modelos que son las clases que contienen los datos necesarios del proyecto.
En el **controllers** esta la clase que me permite ejecutar la aplicacion

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Instalacion

Clona el proyecto a traves de git:

https://github.com/joelgarciaarevalo/EnergiaElectricaPoo


## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
