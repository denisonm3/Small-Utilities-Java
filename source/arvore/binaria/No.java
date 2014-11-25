package arvore.binaria;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author denison_usuario
 */
public class No {
    private No Direito = null;
    private No Esquerdo = null;
    private Integer valor = null;

    private No(int i) {
        valor = i;
    }

    No() {}

    public void inserirRaiz(int i) {
        //inserir raiz
        if(valor == null){
            valor = i;
        }else{
            //inserir folha
            if(valor <= i && Direito == null){
                No aux = new No(i);
                Direito = aux;
            }else if(valor <= i && Direito != null){
                Direito.inserir(i);
            }else if(valor > i && Esquerdo == null){
                No aux = new No(i);
                Esquerdo = aux;
            }else if(valor > i && Esquerdo != null){
                Esquerdo.inserir(i);
            }
            //após inserir ficou desequilibrada
            int hE = altura(Esquerdo,0);
            System.out.println("esq: "+hE);
            int hD = altura(Direito,0);
            System.out.println("dir: "+hD);
            if (hE - hD > 1 || hE - hD < -1 ) {
                System.out.println("Desequilibrado!");
            }
        }
    }

    public void inserir(int i) {
        //inserir raiz
        if(valor == null){
            valor = i;
        }else{
            //inserir folha
            if(valor <= i && Direito == null){
                No aux = new No(i);
                Direito = aux;
            }else if(valor <= i && Direito != null){
                Direito.inserir(i);
            }else if(valor > i && Esquerdo == null){
                No aux = new No(i);
                Esquerdo = aux;
            }else if(valor > i && Esquerdo != null){
                Esquerdo.inserir(i);
            }
        }
    }
    
    private int altura(No item, int nivel) {
        int esq, dir;
        if(item == null){
            return nivel;
        }else{
            esq = altura(item.Esquerdo,nivel+1);
            dir = altura(item.Direito,nivel+1);
            if (esq > dir)
                return esq;
            else
                return dir;
        }
    }

    void escrever() {
        ArrayList<Object[]> itens = new ArrayList<Object[]>();
        itens = getTexto(this, 0, 0);
        int maior = -1;
        for (Object[] objects : itens) {
            if(maior < (Integer)objects[1]){
                maior = (Integer)objects[1];
            }
        }
        ArrayList<String> linhas = new ArrayList<String>();
        for (int i = 0; i < maior+1; i++) {
            linhas.add("");
        }
        for (Object[] objects : itens) {
            linhas.set((Integer)objects[1], linhas.get((Integer)objects[1])+" "+objects[2]+" "+objects[0]);
        }
        for (String string : linhas) {
            System.out.println(string);
        }
    }
    
    private ArrayList<Object[]> getTexto(No item,int nivel, int lado) {
        if(item != null){
            ArrayList<Object[]> objs = new ArrayList<Object[]>();
            Object[] obj = new Object[3];
            obj[0] = item.getValor();
            obj[1] = nivel;
            obj[2] = lado;
            objs.add(obj);
            if(item.Esquerdo != null){
                objs.addAll(getTexto(item.Esquerdo, nivel+1, lado-1));
            }
            if(item.Direito != null){
                objs.addAll(getTexto(item.Direito, nivel+1, lado+1));
            }
            return objs;
        }
        return null;
    }

    private Integer getValor() {
        return valor;
    }
    
    @Override
    public String toString(){
        return "lol " + super.toString() + " DD";
    }
}
