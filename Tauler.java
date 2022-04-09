import java.util.InputMismatchException;
public class Tauler implements InputConnecta4 {
    private final int tamVT = 7;
    private final int tamHT = 8;
    private char[][] tablero = new char[tamVT][tamHT];
    private int posicioV;
    private int posicioH;
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
        final int minTamHT = 1;
        int inJ;
        do {
            try {
                System.out.println("escribe la posicion horizontal para colocar la ficha en el tablero");
                inJ = leerTeclado.nextInt();
                if (inJ <= tamHT && inJ >= minTamHT) {
                    break;
                }
            } catch (InputMismatchException e) {
                leerTeclado.next();
            }
            System.out.println("¡ERROR!, inserta un numero valido [1-8]");
        } while (true);
        return inJ;
    }
    public void insertarFicha() {
        posicioH = conseguirInputValid() - 1;
        char tipusFicha;
        if (comprobarColumnaLibre()) {
            tipusFicha = (torn % 2 == 0) ? 'x' : 'o';
            tablero[posicioV][posicioH] = tipusFicha;
            mostrarInfoFicha();
            System.out.println("estado jugador: " + comprobaciones4enLinea());
            torn++;

        }
    }

    public void mostrarInfoFicha() {
        System.out.printf("ficha: '%c' insertada en posicion Vertical: [%d] posicion Horitzontal: [%d] \n",tablero[posicioV][posicioH], posicioV + 1, posicioH+ 1);
    }

    public boolean comprobarColumnaLibre() {
        for (posicioV = tamVT - 1; posicioV >= 0; --posicioV) {
            if (tablero[posicioV][posicioH] == '-') {
                return true;
            }
        }
        System.out.println("¡error!,columna llena, canvia de fila");
        return false;
    }

    public boolean comprobaciones4enLinea() {
        return comprobacionHorizontal() || comprobacionVertical();
    }
    private boolean comprobacionHorizontal() {
        final char tipusFicha = tablero[posicioV][posicioH ];
        int contFichasCon = 0;
        final int fihasConNecesaries = 4;
        for ( int recH = 0; recH < tamHT; recH++) {
            if (tablero[posicioV][recH] == tipusFicha) {
                contFichasCon++;
                if (contFichasCon == fihasConNecesaries) {
                    return true;
                }
            } else {
                contFichasCon = 0;
            }
        }
        return false;
    }

    private boolean comprobacionVertical() {
        final char tipusFicha = tablero[posicioV][posicioH];
        int contFichasCon = 0;
        final int fihasConNecesaries = 4;
        for (int recV = 0; recV < tamVT; recV++) {
            if (tablero[recV][posicioH ] == tipusFicha) {
                contFichasCon++;
                if (contFichasCon == fihasConNecesaries) {
                    return true;
                }
            } else {
                contFichasCon = 0;
            }
        }
        return false;
    }
    public void comenzarPartida() {
        inicialitzarTablero();
        while (torn != 57) {
            insertarFicha();
            mostrarTablero();
        }
    }
}