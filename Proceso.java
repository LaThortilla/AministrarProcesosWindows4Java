public class Proceso {

        private int PID;
        private String Usomemoria;
        private String Nombre;
        private String NombreSession;
        private String numeroSession;
        private String Estado;
        private String TiempoCPU;
        private String nombreUsuario;
        private String TituloVentana;

        private Proceso() {
        }

       public boolean kill(){
            boolean res = false;
            String cmd = "cmd /c taskkill /pid "+this.PID+" /F /T"; // (/F) para forzar cierre (/T) Cerrar los procesos asociados 

            Runtime rt = Runtime.getRuntime();
            Process p = null;
            try {
                p = rt.exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            try {
                while ((line = brError.readLine()) != null) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while ((line = brInput.readLine()) != null) {
                    if (line.contains("Correcto") || line.contains("CORRECTO")) {
                        res = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.destroy();
            return res;
   
       }
        
        public Proceso(String Nombre, int PID, String NombreSession, String numeroSession, String Usomemoria, String Estado, String nombreUsuario, String TiempoCPU, String TituloVentana) {
            this.Nombre = Nombre;
            this.PID = PID;
            this.NombreSession = NombreSession;
            this.numeroSession = numeroSession;
            this.Usomemoria = Usomemoria;
            this.Estado = Estado;
            this.nombreUsuario = nombreUsuario;
            this.TiempoCPU = TiempoCPU;
            this.TituloVentana =TituloVentana;
        }

        public String getNumeroSession() {
            return numeroSession;
        }

        public String getEstado() {
            return Estado;
        }

        public String getTiempoCPU() {
            return TiempoCPU;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public String getTituloVentana() {
            return TituloVentana;
        }

        public int getPID() {
            return PID;
        }

        public String getUsomemoria() {
            return Usomemoria;
        }

        public String getNombre() {
            return Nombre;
        }

        public String getNombreSession() {
            return NombreSession;
        }

        public static Proceso getProcesoByPID(String PID) {
            Proceso res = null;
            String cmd = "cmd /c tasklist  /fi \"pid eq " + PID + "\" /v /fo csv";

            Runtime rt = Runtime.getRuntime();
            Process p = null;
            try {
                p = rt.exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }

    
            BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            try {
                while ((line = brError.readLine()) != null) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                while ((line = brInput.readLine()) != null) {

                    if (line.contains(PID)) {
                        String[] spl = line.split("\",\"");
                        if (spl.length == 9) {
                            res = new Proceso(spl[0].replace("\"",""),Integer.parseInt(spl[1]),spl[2],spl[3],spl[4].replace("\"",""),spl[5],spl[6],spl[7],spl[8]);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            p.destroy();


            return res;
        }
    }
