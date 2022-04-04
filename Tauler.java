public class Tauler {
    private final int tamVT = 7;
    private final int tamHT = 8;
    private char[][] posFiches = new char[tamVT][tamHT];
    public void mostrarTablero() {
        int recH;
        for (int recV = 0; recV < tamVT; recV++) {
            for (recH = 0; recH < tamHT - 1; recH++) {
                System.out.print("|" + posFiches[recV][recH] + "|");
            }
            System.out.println("|" + posFiches[recV][recH] + "|");
        }
        System.out.println("*************************************");
    }
}