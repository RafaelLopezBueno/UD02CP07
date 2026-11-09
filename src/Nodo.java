import java.util.ArrayList;
import java.util.List;

public class Nodo extends Thread {
    private final List<Integer> lista;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    public List<Integer> getLista() {
        return lista;
    }

    @Override
    public void run() {
        // Con 0 o 1 elementos la lista ya esta ordenada
        if (lista.size() <= 1) {
            return;
        }

        int mitad = lista.size() / 2;
        List<Integer> izquierda = new ArrayList<>();
        List<Integer> derecha = new ArrayList<>();
        anade(izquierda, 0, mitad);
        anade(derecha, mitad, lista.size());

        Nodo hijoIzq = new Nodo(izquierda);
        Nodo hijoDer = new Nodo(derecha);
        hijoIzq.start();
        hijoDer.start();

        try {
            hijoIzq.join();
            hijoDer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        mezcla(izquierda, derecha);
    }

    // Anade a l los elementos de lista desde la posicion d (incluida) hasta h (excluida)
    private void anade(List<Integer> l, int d, int h) {
        l.addAll(lista.subList(d, h));
    }

    // Mezcla de forma ordenada l1 y l2 dejando el resultado en lista
    private void mezcla(List<Integer> l1, List<Integer> l2) {
        lista.clear();
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                lista.add(l1.get(i++));
            } else {
                lista.add(l2.get(j++));
            }
        }
        while (i < l1.size()) {
            lista.add(l1.get(i++));
        }
        while (j < l2.size()) {
            lista.add(l2.get(j++));
        }
    }
}
