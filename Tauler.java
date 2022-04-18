public class Tauler{
    private int tamVT ;
    private int tamHT ;
    private char[][] tablero;
    public void setTamVT(int tamVT){
        this.tamVT=tamVT;
    }
    public void setTamHT(int tamHT){
        this.tamHT=tamHT;
    }
    public void setTablero(char [][] tablero){
        this.tablero=tablero;
    }
    public void mostrarTablero() {
        int ultNum1chifra = 9;
        String espacios="  ";
        System.out.print( espacios );
        for (int numH = 1; numH <= tamHT; numH++ ){
            espacios=( numH > ultNum1chifra )?" ":"  ";
            System.out.print( espacios + numH );
        }
        System.out.println();
        for ( int recV = 0; recV < tamVT; recV++ ) {
            for ( int recH = 0; recH < tamHT; recH++ ) {
                if( recH==0 ){
                    espacios= ( recV+1 > ultNum1chifra )? "" : " ";
                    System.out.print( espacios + (recV+1) + " " );
                }
                System.out.print( "|" + tablero[recV][recH] + "|" );
            }
            System.out.println();
        }
        System.out.println("************************************************************");
    }
    public void inicialitzarTablero() {
        for (int recV = 0; recV < tamVT; recV++) {
            for (int recH = 0; recH < tamHT; recH++) {
                tablero[recV][recH] = '-';
            }
        }
    }
}