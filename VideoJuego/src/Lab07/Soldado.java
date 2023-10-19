package Lab07;
public class Soldado {
    private String nombre;
    private int ejercito;
    private int fila;
    private int columna;
    private int puntosDeVida;
    
    public void setNombre( String n){
        nombre = n; 
    }
    public String getNombre(){
        return nombre;
    }
    public void setEjercito(int e){
        ejercito=e;
    }
    public int getEjercito(){
        return ejercito;
    }
    public void setFila(int f){
        fila = f;
    }
    public void setColumna(int c){
        columna = c;
    }
    public void setPuntosDeVida(int nv){
        puntosDeVida=nv;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }
    public int getPuntosDeVida(){
        return puntosDeVida;
    }
}
