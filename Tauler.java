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
    private int torn = 1;

    public void insertarFicha() {
        System.out.println("escribe la posicion de la ficha");
        int posicio = leerTeclado.nextInt();
        int numColLibre = comprobarColumnaLibre(posicio);
        if (numColLibre == -1) {
            System.out.println("¡error!,columna llena, canvia de fila");
        } else {
            if (torn % 2 == 0) {
                System.out.printf("jugador2 a insertado ficha: 'x' en col: [%d] fila: [%d]'\n", numColLibre + 1, posicio);
                tablero[numColLibre][posicio - 1] = 'x';
            } else {
                System.out.printf("jugador1 a insertado ficha: 'o' col: [%d] fila: [%d]'\n", numColLibre + 1, posicio);
                tablero[numColLibre][posicio - 1] = 'o';
            }
            torn++;
        }
    }

    public int comprobarColumnaLibre(int posicio) {
        int col;
        for (col = tamVT - 1; col > 0; ) {
            if (tablero[col][posicio - 1] == 'x' || tablero[col][posicio - 1] == 'o') {
                col--;
            } else {
                break;
            }
        }
        if (tablero[col][posicio - 1] == 'x' || tablero[col][posicio - 1] == 'o') {
            col -= 1;
        }
        return col;
    }
    public void comenzarPartida() {
        inicialitzarTablero();
        while (torn != 57) {
            insertarFicha();
            mostrarTablero();
        }
    }


}