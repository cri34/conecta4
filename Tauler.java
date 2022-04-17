import java.util.InputMismatchException;

public class Tauler implements InputConnecta4 {
    private final int tamVT = 7;
    private final int tamHT = 8;
    private char[][] tablero = new char[tamVT][tamHT];
    private int posicioV;
    private int posicioH;
    char tipusFicha;

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
        if (comprobarColumnaLibre()) {
            tipusFicha = (torn % 2 == 0) ? 'x' : 'o';
            tablero[posicioV][posicioH] = tipusFicha;
            mostrarInfoFicha();
            System.out.println("estado jugador: " + comprobaciones4enLinea());
            torn++;

        }
    }

    public void mostrarInfoFicha() {
        System.out.printf("ficha: '%c' insertada en posicion Vertical: [%d] posicion Horitzontal: [%d] \n", tablero[posicioV][posicioH], posicioV + 1, posicioH + 1);
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

        int[] dV = {0, 0, 0, 0};
        int[] dH = {0, 0, 0, 0};
        int indexDV = 0;
        int indexDH = 0;
        int contFichasCon = 0;
        int posV, posH;
        final int fichasConNecesaries = 4;
        for (int rec = -3; rec <= 3; ) {
            if (indexDV == 3) {
                dV[indexDV] = -rec;
            }
            if (indexDH != 1) {
                dH[indexDH] = rec;
            }
            if (indexDV != 0 && indexDV != 3) {
                dV[indexDV] = rec;
            }
            posV = posicioV + dV[indexDV];
            posH = posicioH + dH[indexDH];
            if (dentroTablero(posV, posH)) {
                contFichasCon = (tablero[posV][posH] == tipusFicha) ? ++contFichasCon : 0;
                if (contFichasCon == fichasConNecesaries) {
                    return true;
                }
            }
            if (indexDV != 3 && rec == 3) {
                rec = -3;
                indexDV++;
                indexDH = indexDV;
                contFichasCon = 0;
            } else {
                rec++;
            }
        }
        return false;
    }

    public boolean dentroTablero(int posV, int posH) {
        boolean rangoH = posH >= 0 && posH < tamHT;
        boolean rangoV = posV >= 0 && posV < tamVT;
        return rangoH && rangoV;
    }

    public boolean continuaPartida() {
        int tornMax=57;
        if (torn == tornMax) {
            System.out.println("PARTIDA EMPATADA");
            return false;
        }
        if (comprobaciones4enLinea()) {
            System.out.printf("PARTIDA GANADA POR '%c' en turno : %d", tablero[posicioV][posicioH],torn);
            return false;
        }
        return true;
    }

    public void comenzarPartida() {
        inicialitzarTablero();
        while (continuaPartida()) {
            insertarFicha();
            mostrarTablero();
        }
    }
}