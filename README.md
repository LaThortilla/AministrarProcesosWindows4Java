# AministrarProcesosWindows4Java

 1.0 -  Permite la gestion de procesos en windows mediante el uso de taskkill, tasklist.
 
  
## Uso
  
 - Obtener Proceso
  
  ```java
    Proceso miProceso = Proceso.getProcesoByPID("5236");
  ```
 - Matar Proceso
   ```java
     miProceso.kill();
  ```
  
 - Obtener Información de proceso 
   ```java
     miProceso.getNombre(); // Nombre del proceso
     miProceso.getEAstado(); // Estado actual del proceso
     miProceso.getTiempoCPU(); // Es una la cantidad de tiempo de CPU (ciclos) que se han utilizado desde el inicio del proceso
     miProceso.getNombreUsuario(); //Nombre de usuario que inicio el proceso (Propietario del proceso)
     miProceso.getTituloVentana(); //Nombre del titulo de la ventana del proceso
     miProceso.getPID(); // Numero de Identificación del proceso (PID)
     miProceso.getUsomemoria(); // Uso de RAM que consume el proceso
     miProceso.getNumeroSession(); // Numero de session
     miProceso.getNombreSession(); //Nombre de la session
  ```
  
  

    
