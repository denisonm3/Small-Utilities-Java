package arvore.binaria;

/**
 *
 * @author denison_usuario
 */
public class ArvoreBinaria {
    
    public static void main(String[] args)
    {
        No arvore = new No();
        System.out.println(arvore.msgAdd(5));
        System.out.println(arvore.msgAdd(6));
        System.out.println(arvore.msgAdd(3));
        System.out.println(arvore.msgAdd(7));
        System.out.println(arvore.msgAdd(4));
        System.out.println(arvore.msgAdd(8));
        System.out.println(arvore.msgAdd(2));
        System.out.println(arvore.msgAdd(1));
        System.out.println(arvore.toString());
    }
}