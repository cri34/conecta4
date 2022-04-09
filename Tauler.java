import java.util.InputMismatchException;

public class Tauler implements InputConnecta4 {
    private final int tamVT = 7;
    private final int tamHT = 8;
    private char[][] tablero = new char[tamVT][tamHT];

    public void mostrarTablero() {
        int recH;
        for (int recV = 0; recV < tamVT; recV++) {
            for (recH = 0; recH < tamHT; recH++) {
                System.out.print("|" + tablero[recV][recH] + "|");
            }
            System.out.println();
        }
        System.out.println("*************************************");
    }

    public void inicialitzarTablero() {
        for (int recV = 0; recV < tamVT; recV++) {
            for (int recH = 0; recH < tamHT; recH++) {
                tablero[recV][recH] = '-';
            }
        }
    }

    private int torn = 1;

    public int conseguirInputValid() {
        final int minTamHT =1;
        int inJ;
        do{
            try {
                System.out.println("escribe la posicion horizontal para colocar la ficha en el tablero");
                inJ=leerTeclado.nextInt();
                if (inJ<=tamHT && inJ>=minTamHT){
                    break;
                }
            }catch (InputMismatchException e){
                leerTeclado.next();
            }
            System.out.println("¡ERROR!, inserta un numero valido [1-8]");
        }while(true);
        return inJ;
    }

    public void insertarFicha() {
        int posicio = conseguirInputValid()-1;
        int numColLibre = comprobarColumnaLibre(posicio);
        char tipusFicha;
        if (numColLibre == -1) {
            System.out.println("¡error!,columna llena, canvia de fila");
        } else {
            tipusFicha = (torn % 2 == 0) ? 'x' : 'o';
            mostrarInfoFicha(numColLibre, posicio);
            tablero[numColLibre][posicio] = tipusFicha;
            torn++;
        }
    }

    public void mostrarInfoFicha(int numColLibre, int posicio) {
        System.out.printf("ficha insertada en col: [%d] fila: [%d]'\n", numColLibre + 1, posicio + 1);
    }


    public int comprobarColumnaLibre(int posicio) {
        int col;
        for (col = tamVT - 1; col > 0; ) {
            if (tablero[col][posicio] == '-') {
                break;
            }
            col--;
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