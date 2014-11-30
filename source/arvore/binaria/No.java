package arvore.binaria;

import java.util.ArrayList;

/**
 * Arvore binária
 * @author Denison
 */
public class No {

    private final Boolean raiz;
    private No Direito = null;
    private No Esquerdo = null;
    private Comparable valor = null;

    /**
     * Create a leaf node of the tree
     * @param valor value of the node
     */
    public No(Object valor) {
        this.valor = (Comparable) valor;
        raiz = false;
    }

    /**
     * Create a root node of the tree
     */
    public No() {
        raiz = true;
    }

    /**
     * Insere um elemento na arvore e retorna a mensagem
     *
     * @param element
     * @return
     */
    public String msgAdd(Object element) {
        //inserir raiz
        if (valor == null) {
            valor = (Comparable) element;
            return "[raiz]\n";
        } else {
            //inserir folha
            if (valor.compareTo(element) <= 0 && Direito == null) {
                No aux = new No(element);
                Direito = aux;
            } else if (valor.compareTo(element) <= 0 && Direito != null) {
                Direito.add(element);
            } else if (valor.compareTo(element) > 0 && Esquerdo == null) {
                No aux = new No(element);
                Esquerdo = aux;
            } else if (valor.compareTo(element) > 0 && Esquerdo != null) {
                Esquerdo.add(element);
            }
            //após inserir ficou desequilibrada
            String retorno;
            int hE = altura(Esquerdo, 0);
            retorno = ("[esq: " + hE + "]");
            int hD = altura(Direito, 0);
            retorno += ("[dir: " + hD + "]");
            if (hE - hD > 1 || hE - hD < -1) {
                retorno += (" -> Desequilibrado!");
            }
            retorno += "\n";
            return retorno;
        }
    }

    public void add(No element) {
        //inserir raiz
        if (valor == null) {
            throw new IllegalArgumentException("não é possivel inserir nó na raiz!");
        } else if (element.equals(this) || element.Esquerdo != null || element.Direito != null) {
            throw new IllegalArgumentException("Elemento já foi conectado!");
        } else {
            //inserir folha
            if (valor.compareTo(element.valor) <= 0 && Direito == null) {
                Direito = element;
            } else if (valor.compareTo(element.valor) <= 0 && Direito != null) {
                Direito.add(element);
            } else if (valor.compareTo(element.valor) > 0 && Esquerdo == null) {
                Esquerdo = element;
            } else if (valor.compareTo(element.valor) > 0 && Esquerdo != null) {
                Esquerdo.add(element);
            }
        }
    }

    public void add(Object element) {
        //inserir raiz
        if (valor == null) {
            valor = (Comparable) element;
        } else {
            //inserir folha
            if (valor.compareTo(element) <= 0 && Direito == null) {
                No aux = new No(element);
                Direito = aux;
            } else if (valor.compareTo(element) <= 0 && Direito != null) {
                Direito.add(element);
            } else if (valor.compareTo(element) > 0 && Esquerdo == null) {
                No aux = new No(element);
                Esquerdo = aux;
            } else if (valor.compareTo(element) > 0 && Esquerdo != null) {
                Esquerdo.add(element);
            }
        }
    }

    private int altura(No item, int nivel) {
        int esq, dir;
        if (item == null) {
            return nivel;
        } else {
            esq = altura(item.Esquerdo, nivel + 1);
            dir = altura(item.Direito, nivel + 1);
            if (esq > dir) {
                return esq;
            } else {
                return dir;
            }
        }
    }

    public Object getValor() {
        return valor;
    }

    @Override
    public String toString() {
        if (raiz) {
            String arvore = "";
            int nivel = 0;
            ArrayList<No> linha;
            ArrayList<No> proxLinha = new ArrayList<>();
            proxLinha.add(this);
            do {
                linha = proxLinha;
                proxLinha = new ArrayList<>();
                for (No prox : linha) {
                    int hE = altura(prox, 0);
                    String espaco = "";
                    while (hE > 0) {
                        espaco += "\t";
                        hE--;
                    }
                    if (prox.Esquerdo != null) {
                        espaco += "<-";
                    }
                    arvore += espaco + "[" + prox.valor + "]";
                    if (prox.Esquerdo != null) {
                        proxLinha.add(prox.Esquerdo);
                    }
                    if (prox.Direito != null) {
                        arvore += "->";
                        proxLinha.add(prox.Direito);
                    }
                }
                arvore += "\n";
                nivel++;
            } while (proxLinha.size() > 0);
            return arvore;
        } else {
            return super.toString();
        }
    }
}
