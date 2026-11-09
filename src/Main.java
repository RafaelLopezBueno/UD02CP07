import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        Random random = new Random();
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lista.add(random.nextInt(100));
        }
        System.out.println("Lista original:  " + lista);

        Nodo raiz = new Nodo(lista);
        raiz.start();
        raiz.join();

        System.out.println("Lista ordenada:  " + raiz.getLista());
    }
}
