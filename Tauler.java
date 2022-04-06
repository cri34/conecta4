public class Tauler implements InputConnecta4 {
    private final int tamVT = 7;
    private final int tamHT = 8;
    private char[][] tablero = new char[tamVT][tamHT];

    public void mostrarTablero() {
        int recH;
        for (int recV = 0; recV < tamVT; recV++) {
            for (recH = 0; recH < tamHT ; recH++) {
                System.out.print("|" + tablero[recV][recH] + "|");
            }
            System.out.println();
        }
        System.out.println("*************************************");
    }
    public void inicialitzarTablero(){
        for (int recV = 0; recV < tamVT; recV++) {
            for (int recH = 0; recH < tamHT ; recH++) {
                tablero[recV][recH]='-';
            }
        }
    }
}