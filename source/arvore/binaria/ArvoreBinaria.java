package arvore.binaria;

/**
 *
 * @author denison_usuario
 */
public class ArvoreBinaria {
    
    public static void main(String[] args)
    {
        No arvore = new No();
        System.out.println(arvore.toString());
        arvore.inserirRaiz(5);
        arvore.inserirRaiz(6);
        arvore.inserirRaiz(3);
        arvore.inserirRaiz(7);
        arvore.inserirRaiz(4);
        arvore.inserirRaiz(8);
        arvore.escrever();
    }
}