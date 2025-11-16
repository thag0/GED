import ged.Ged;

public class Main {
    static Ged ged = new Ged();
    
    static {
        ged.limparConsole();
    }

    public static void main(String[] args) {
        double[][] a = {
            {1, 1, 1, 1},
            {2, 2, 2, 2},
            {3, 3, 3, 3},
            {4, 4, 4, 4}
        };
        
        double[][][] sep = ged.separarTreinoTeste(a, 0.25);
        ged.printMatriz(sep[0], "treino");
        ged.printMatriz(sep[1], "teste");
    }
}
