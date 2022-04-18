import java.util.InputMismatchException;

public  class Partida{
    Tauler t= new Tauler();
    public Partida(){
        t.setTamVT(tamVT);
        t.setTamHT(tamHT);
        tablero= new char[tamVT][tamHT];
        t.setTablero(tablero);
    }
    private int tamVT=7 ;
    private int tamHT=8 ;
    private char[][] tablero;
    private int posicioV;
    private int posicioH;

}