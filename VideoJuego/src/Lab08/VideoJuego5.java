package Lab08;
import java.util.*;
public class VideoJuego5 {
    public static void main(String[] args){
        boolean continuar=true;
        while(continuar){
            ArrayList<ArrayList<Soldado>> misSoldadosTablero = new ArrayList<>();
            int numFilas=10;
            int numColumnas=10;
            for (int i=0;i<numFilas;i++) {  //Inicializa todas las posiciones como null
                ArrayList<Soldado> fila = new ArrayList<>();
                for (int j=0;j<numColumnas;j++) 
                    fila.add(null); 
                misSoldadosTablero.add(fila);
            }
            ArrayList<Soldado> orden=new ArrayList<>();
            HashMap<String,Soldado> misEjercitos=new HashMap<>();
            crearSoldados(misSoldadosTablero,orden,0,misEjercitos);
            crearSoldados(misSoldadosTablero,orden,1,misEjercitos);
            mostrarTablero(misSoldadosTablero,misEjercitos);
            System.out.println("DATOS DEL EJERCITO 0...");
            datosMayorVida(misSoldadosTablero,orden,misEjercitos);  
            System.out.println(" Promedio de nivel de vida del ejercito: "+promedioNivelVida(misSoldadosTablero,orden,0)+"\n");
            System.out.println("DATOS DEL EJERCITO 1...");
            datosMayorVida(misSoldadosTablero,orden,misEjercitos); 
            System.out.println(" Promedio de nivel de vida del ejercito: "+promedioNivelVida(misSoldadosTablero,orden,1)+"\n");
            mostrarDatosOrden(misSoldadosTablero,orden); 
            rankingPoderBurbuja(orden);
            rankingPoderSeleccion(orden);
            if (definirGanador(misSoldadosTablero,orden)==0) //Metrica explicada en los metodos/promedio Salud
                System.out.println("Ganador ejercito 0");
            else if (definirGanador(misSoldadosTablero,orden)==1)
                System.out.println("Ganador ejercito 0");
            else
                System.out.println("Hubo un empate entre ejercitos");
            continuar=continuar();
        }
        System.out.println("Videojuego terminado.");
        
   }
    public static int nCantidadSoldados(){
        return (int)(Math.random()*10)+1;
    }
    public static void crearSoldados(ArrayList<ArrayList<Soldado>> arrL,ArrayList orden,int ej,HashMap arrE){ //Almacenamiento en orden
        for (int i=0;i<nCantidadSoldados();i++ ){                                                   //y almacenar en HashMap
            int fil=(int)(Math.random()*10);
            int col=(int)(Math.random()*10);
            while (arrL.get(fil).get(col)!=null){
                fil=(int)(Math.random()*10);
                col=(int)(Math.random()*10);
            }
            Soldado nuevoSoldado=new Soldado();
            nuevoSoldado.setNombre("Soldado"+ej+"X"+i);
            nuevoSoldado.setFila(fil+1);
            nuevoSoldado.setColumna(col+1);
            nuevoSoldado.setPuntosDeVida((int)(Math.random()*5+1));
            arrL.get(fil).set(col, nuevoSoldado);
            arrE.put("Ejercito"+ej+" x"+i,nuevoSoldado);   //HashMap creado de ArrayList con datos <ejercito x nro,objeto>
            orden.add(arrL.get(fil).get(col)); //Arreglo que guardara el ejercito ordenado por creacion
        }
    }
    public static void mostrarTablero(ArrayList<ArrayList<Soldado>> arrL,HashMap<String,Soldado> hMap){
        for (int i=0;i<arrL.size();i++){
            for (int j=0;j<arrL.get(i).size();j++){
                Soldado posicion=arrL.get(i).get(j);
                if (posicion != null) {
                String key = getKeyFromValue(hMap, posicion); // Obtiene la clave del HashMap
                String color = key.startsWith("Ejercito0") ? "\u001B[31m" : "\u001B[34m";
                System.out.print(color + "|" + posicion.getPuntosDeVida() + "|" + "\u001B[0m");
                } 
                else {
                System.out.print("|_|");
                }
        }
        System.out.println();
        }
    }
    public static String getKeyFromValue(HashMap<String, Soldado> map, Soldado value) {
    for (Map.Entry<String, Soldado> entry : map.entrySet()) {
        if (entry.getValue() == value) {
            return entry.getKey();
        }
    }
    return null;
    }
    public static void mostrarDatos(ArrayList<ArrayList<Soldado>> arrL,int i,int j){
        Soldado posicion=arrL.get(i).get(j);
        System.out.println("  Nombre: "+ posicion.getNombre());
        System.out.println("  Ejercito: "+posicion.getEjercito());
        System.out.println("  Fila: "+ posicion.getFila());
        System.out.println("  Columna: "+ posicion.getColumna());
        System.out.println("  Puntos de vida: "+ posicion.getPuntosDeVida()+"\n");
    }
     public static void datosMayorVida(ArrayList<ArrayList<Soldado>> arrL,ArrayList<Soldado> orden,HashMap<String,Soldado> hMap){
        System.out.println("Datos de soldado con mayor vida:");
        int maxI = 0;
        int maxJ = 0;
        int mayorNivelV = 0;

        for (int i=0;i<orden.size();i++) {
            int fil=orden.get(i).getFila() - 1;
            int col=orden.get(i).getColumna() - 1;
            Soldado posicion = arrL.get(fil).get(col);
            // Obtener el ejército del soldado usando el nombre como clave en el HashMap
            String nombreSoldado = posicion.getNombre();
            String ejercitoClave = nombreSoldado.substring(0, nombreSoldado.indexOf("X") + 1); // Suponiendo "EjercitoX" en el nombre
            Soldado ejercito = hMap.get(ejercitoClave);

            if (mayorNivelV < posicion.getPuntosDeVida()) {
                mayorNivelV = posicion.getPuntosDeVida();
                maxI=fil;
                maxJ=col;
            }
        }
        mostrarDatos(arrL, maxI, maxJ);
    }
     public static double promedioNivelVida(ArrayList<ArrayList<Soldado>> arrL,ArrayList<Soldado> orden,int ejercito){
        double sumVida=0;
        for(int i=0;i<orden.size();i++){
            int fil=orden.get(i).getFila()-1;
            int col=orden.get(i).getColumna()-1;
            Soldado posicion=arrL.get(fil).get(col);
            if(posicion.getEjercito()==ejercito)
                sumVida+=posicion.getPuntosDeVida();
        }
        return sumVida/orden.size();
    }
    public static void mostrarDatosOrden(ArrayList<ArrayList<Soldado>> arrL,ArrayList<Soldado> orden){
        System.out.println("DATOS DEL EJERCITO EN EL ORDEN DE CREACION...");
        for(int i=0;i<orden.size();i++){
            if(orden.get(i).getEjercito()==0)
                System.out.println("-Ejercito 0...");
            else 
                System.out.println("-Ejercito 1...");
            if(orden.get(i)!=null)
                mostrarDatos(arrL,(orden.get(i).getFila())-1,(orden.get(i).getColumna())-1);
        }
    }
    public static void rankingPoderBurbuja(ArrayList<Soldado> orden){
        int temp;
        for (int i = 0; i < orden.size() - 1; i++) {
            for (int j = 0; j < orden.size() - i - 1; j++) {
                if(orden.get(i)!=null){
                    int ant=orden.get(j).getPuntosDeVida();
                    int pos=orden.get(j+1).getPuntosDeVida();
                    if (ant<pos){
                        temp=ant;
                        orden.get(j).setPuntosDeVida(pos);
                        orden.get(j+1).setPuntosDeVida(temp);
                    }   
                }
            }
        }
        System.out.println("RANKING DE PODER POR ORDENAMIENTO BURBUJA...");
        for(int i=0;i<orden.size();i++)
            System.out.println((i+1)+".- "+orden.get(i).getNombre()+"\t"+orden.get(i).getPuntosDeVida());
    }
    public static void rankingPoderSeleccion(ArrayList<Soldado> orden){
        int n=orden.size();
    for (int i=0;i<n-1;i++) {
        int mayor=i;
        for (int j=i+1;j<n;j++) {
            if (orden.get(j).getPuntosDeVida() > orden.get(mayor).getPuntosDeVida()) 
                mayor = j;     
        }
        Soldado temp = orden.get(i);
        orden.set(i, orden.get(mayor));
        orden.set(mayor, temp);
    }
        System.out.println("RANKING DE PODER POR ORDENAMIENTO SELECCION...");
        for(int i=0;i<orden.size();i++)
            System.out.println((i+1)+".- "+orden.get(i).getNombre()+"\t"+orden.get(i).getPuntosDeVida());
    }
    public static int definirGanador (ArrayList<ArrayList<Soldado>> arrL,ArrayList<Soldado> orden){//Tomando en cuenta el promedio de vida de c/ejercito
        double promedioEjercito0 = promedioNivelVida(arrL,orden, 0);
        double promedioEjercito1 = promedioNivelVida(arrL,orden, 1);
    if (promedioEjercito0 > promedioEjercito1) 
        return 0; // Ejército 0 gana
    else if (promedioEjercito1 > promedioEjercito0) 
        return 1; // Ejército 1 gana
    else 
        return -1; // Empate
    }
    public static boolean continuar(){
        Scanner sc=new Scanner (System.in);
        System.out.print("Desea simular otra batalla?(Y/N): ");
        if (sc.next().equals("N"))
            return false;
        return true;
    }
    
}
