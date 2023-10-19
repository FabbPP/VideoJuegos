package Lab09;
public class Soldado {
    private String nombre;
    private int ejercito;
    private int fila;
    private int columna; //Usada solo para reconocer posicion
    private String columnaStr; 
    private int nivelAtaque;
    private int nivelDefensa;
    private int vidaActual;
    private int velocidad;
    private String actitud;
    private boolean vive;
    //Metodos constructores sobrecargados
    public Soldado(String n){  //Simula un setNombre y es el principal
        nombre=n;
    }
    public void Soldado(int e,int f){ //Simula un setEjercito y setFila
        ejercito=e;
        fila=f;
    }
    public void Soldado(int nA,int nD,int v,String a,boolean vi){
        nivelAtaque=nA;   //Inicializa por defecto los valores predeterminados
        nivelDefensa=nD;
        velocidad=v;
        actitud=a;
        vive=vi;
    }
    //Get y set de atributos 
     //Metodos de atributos solo accesores, ya definidos en clase Soldado
    public String getNombre(){
        return nombre;
    }
    public int getEjercito(){
        return ejercito;
    }    
    public int getFila(){
        return fila;
    }
    
    public void setColumna(int c){  //Debido a que columna es una letra 
        columna = c;
        switch (columna){
            case 0: 
                columnaStr="A";
                break;
            case 1: 
                columnaStr="B";
                break;
            case 2:
                columnaStr="C";
                break;
            case 3: 
                columnaStr="D";
                break;
            case 4: 
                columnaStr="E";
                break;
            case 5: 
                columnaStr="F"; 
                break;
            case 6: 
                columnaStr="G"; 
                break;
            case 7: 
                columnaStr="H"; 
                break;
            case 8: 
                columnaStr="I"; 
                break;
            case 9: 
                columnaStr="J"; 
                break;
        }
    }
    public int getColumna(){ //Para usar la posicion
        return columna;
    }
    public String getColumnaStr(){
        return columnaStr;
    }
    public void setVidaActual(int v){
        vidaActual=v;
    }
    public int getVidaActual(){
        return vidaActual;
    }    
    public void setVive(boolean v){
        vive=v;
    }
    public boolean getVive(){
        return vive;
    }
    
    //Solo metodos accesores para estos atributos
    public int getNivelDefensa(){
        return nivelDefensa;
    }
    public int getNivelAtaque(){
        return nivelAtaque;
    } 
    public int getVelocidad(){
        return velocidad;
    }
    public String getActitud(){
        return actitud;
    }
    //Acciones
    public void avanzar(){
        velocidad+=1;
    }
    public void atacar(){
        actitud="ofensiva";
        avanzar();
    }
    public void defender(){
        velocidad=0;
    }
    public void actitud(String a){
        actitud=a;
    }
    public void retroceder(){
        if(velocidad>0){
            velocidad=0;
            actitud="defensiva";
        }
        else
            velocidad-=1;
    }
    public void serAtacado(){
        vidaActual-=1;
        if(vidaActual==0)
            morir();
    }
    public void morir(){
        vive=false;
    }
    public void huir(){
        actitud="fuga";
        velocidad+=2;
    }
}


