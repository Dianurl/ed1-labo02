package ed.lab;


import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Comparator<Integer> comparator = Integer::compare;
        E03AVLTree<Integer> tree = new E03AVLTree<>(comparator);

        System.out.println("Size: " + tree.size());
        System.out.println("Height: " + tree.height());
        tree.insert(5); // almacena 5 en el árbol AVL
        tree.insert(3); // almacena 3 en el árbol AVL
        tree.insert(1); // almacena 1 en el árbol AVL y lo rebalancea
        System.out.println("Size: " + tree.size());
        System.out.println("Size after delete: " + tree.search(5)); // retorna 5
        System.out.println("Size after delete: " + tree.search(1)); // retorna 1
        System.out.println("Size after delete: " + tree.size()); // retorna 3
        System.out.println("Height: " + tree.height()); // retorna 2
        tree.delete(3); // elimina 3
        System.out.println("Size after delete: " + tree.size());
        System.out.println("search: " + tree.search(3));
        tree.insert(4); // almacena 4 y rebalancea el árbol AVL
        System.out.println("Size: " + tree.size());
    }
}