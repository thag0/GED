import ged.Ged;

public class Main {
    static Ged ged = new Ged();
    
    static {
        ged.limparConsole();
    }

    public static void main(String[] args) {
        Double[] arr = {1., 2., 3.};
        ged.printArray(arr);
    }
}
