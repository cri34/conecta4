import java.util.InputMismatchException;

public  class Partida implements InputConnecta4{
    Tauler t= new Tauler();
    public Partida(){
        selecTamTablero();
        t.setTamVT(tamVT);
        t.setTamHT(tamHT);
        tablero= new char[tamVT][tamHT];
        t.setTablero(tablero);
    }
    private int tamVT;
    private int tamHT;
    private char[][] tablero;
    private int posicioV;
    private int posicioH;
    private char tipusFicha;
    private int torn = 0;

    public void selecTamTablero(){
        int tamMinim=4;
        int tamMaxim=99;
        do {
            try {
                System.out.println("escribe el tamaño vertical del tablero ");
                tamVT = leerTeclado.nextInt();
                System.out.println("escribe el tamaño horizontal del tablero ");
                tamHT = leerTeclado.nextInt();
                if (tamHT>= tamMinim && tamVT>= tamMinim && tamHT<=tamMaxim && tamVT<=tamMaxim) {
                    break;
                }
            } catch (InputMismatchException e) {
                leerTeclado.next();
            }
            System.out.printf("¡ERROR!, tamaño insertado invalido, minimo aceptado: %d Vertical y %d de Horizontal | maximo aceptado: %d Horizontal y %d Vertical\n",tamMinim,tamMinim,tamMaxim,tamMaxim);
        } while (true);
    }
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
            System.out.printf("¡ERROR!, inserta un numero valido, Min[%d] y Max[%d] \n",minTamHT,tamHT);
        } while (true);
        return inJ;
    }
    public void insertarFicha() {
        posicioH = conseguirInputValid() - 1;
        if (comprobarColumnaLibre()) {
            tipusFicha = (torn % 2 == 0) ? 'x' : 'o';
            tablero[posicioV][posicioH] = tipusFicha;
            t.setTablero(tablero);
            torn++;
            mostrarInfoFicha();
        }
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
    public void mostrarInfoFicha() {
        System.out.printf("turno: %d ficha: '%c' insertada en posicion Vertical: [%d] posicion Horitzontal: [%d] \n", torn,tablero[posicioV][posicioH], posicioV + 1, posicioH + 1);
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
        int tornMax=tamHT*tamVT;
        if (torn == tornMax) {
            System.out.println("PARTIDA EMPATADA");
            return false;
        }
        if (comprobaciones4enLinea()) {
            System.out.printf("PARTIDA GANADA POR '%c' EN TURNO : %d | POSICION VERTICAL [%d] POSICION HORIZONTAL [%d]", tablero[posicioV][posicioH],torn,posicioV+1,posicioH+1);
            return false;
        }
        return true;
    }

    public void comenzarPartida() {
        t.inicialitzarTablero();
        while (continuaPartida()) {
            insertarFicha();
            t.mostrarTablero();
        }
    }

}