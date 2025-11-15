import ged.Dados;
import ged.Ged;

public class Main {
    static Ged ged = new Ged();
    
    static {
        ged.limparConsole();
    }

    public static void main(String[] args) {
        Dados dados = new Dados();

        dados.atribuir(new String[][] {
            {"a", "b"},
            {"a", "b"},
        });

        dados.print();
    }
}
