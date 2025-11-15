import ged.Ged;

public class Main {
    static Ged ged = new Ged();
    
    static {
        ged.limparConsole();
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2}, {3, 4}};
        ged.printArray(arr);
    }
}
