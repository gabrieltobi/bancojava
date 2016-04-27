public class ListaDeContaCorrenteEncadeada
{
    private Nodo primeiro = null;    
    private int quantidade = 0;

    public boolean estaVazia () {
        if (primeiro == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existePosicao(int posicao) {
        if (posicao < quantidade && posicao >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public Nodo get (int posicao) {
        if (existePosicao(posicao)) {
            Nodo nodo = this.primeiro;
            for (int i = 0; i < posicao; i++) {
                nodo = nodo.getProximo();
            }
            return nodo;    
        } else {
            return null;
        }
    }

    public ContaCorrente getConta (int numeroConta) {
        Nodo nodo = this.primeiro;
        for (int i = 0; i < quantidade; i++) {
            if (nodo.getInfo().getNumero() != numeroConta) {
                nodo = nodo.getProximo();
            } else {
                break;
            }
        }
        return nodo.getInfo();
    }

    public int[] getNumerosDasContas () {
        Nodo nodo = this.primeiro;
        int[] numeros  = new int[quantidade];
        for (int i = 0; i < quantidade; i++) {
            numeros[i] = nodo.getInfo().getNumero();
            nodo = nodo.getProximo();
        }
        return numeros;
    }

    public int getQuantidadeDeContas()
    {
        return quantidade;
    }        

    public Nodo getUltimo() {
        Nodo nodo = this.primeiro;
        while (nodo.getProximo() != null) {
            nodo = nodo.getProximo();
        }
        return nodo;
    }

    public RetornoPadrao incluirConta(ContaCorrente conta)
    {
        return incluirConta(quantidade, conta);
    }

    public RetornoPadrao incluirConta(int posicao, ContaCorrente conta)
    {
        if (posicao > quantidade) {
            return new RetornoPadrao("Posição fora do alcance", true);
        } else {        
            Nodo nodo = new Nodo();
            nodo.setInfo(conta);
            if (existePosicao(posicao)) {
                Nodo aux = get(posicao);
                nodo.setProximo(aux);
            }
            if (existePosicao(posicao - 1)) {
                get(posicao - 1).setProximo(nodo);
            }
            return new RetornoPadrao("Conta Incluída na " + posicao + "ª posição", false);
        }
    }

    public RetornoPadrao incluirContaInicio(ContaCorrente conta)
    {
        return incluirConta(0, conta);
    }

//     public RetornoPadrao incluirContaFim(ContaCorrente conta)
//     {
//         return incluirConta(getUltimo(), conta);
//     }

    //     public void ordenar() {
    //         ordenar(true);
    //     }
    // 
    //     public void ordenar(boolean ascendente) {
    //         for (int i = 0; i < quantidade; i++)
    //         {
    //             for (int x = i; x > 0; x--)
    //             {
    //                 if (ascendente) {
    //                     if (contas[x].getNumero() < contas[x-1].getNumero()) {
    //                         ContaCorrente troca = new ContaCorrente();
    //                         troca = contas[x];
    //                         contas[x] = contas[x-1];
    //                         contas[x-1] = troca;
    //                     }
    //                 } else {
    //                     if (contas[x].getNumero() > contas[x-1].getNumero()) {
    //                         ContaCorrente troca = new ContaCorrente();
    //                         troca = contas[x];
    //                         contas[x] = contas[x-1];
    //                         contas[x-1] = troca;
    //                     }
    //                 }
    //             }
    //         }
    //     }

    public boolean existeConta(int numeroConta) {
        Nodo nodo = this.primeiro;
        for (int i = 0; i < quantidade; i++) {
            if (nodo.getInfo().getNumero() != numeroConta) {
                nodo = nodo.getProximo();
            } else {
                return true;
            }
        }
        return false;
    }

//     public boolean removerConta(int numeroConta)
//     {
//         int i = 0;
//         while ((i < quantidade && contas[i].getNumero() != numeroConta)) {
//             i++;
//         }
//         if (i == quantidade) {
//             return false;
//         } else {
//             for (i = i; i < (quantidade - 1); i++) {
//                 contas[i] = contas[i+1];
//             }
//             contas[i] = null;
//             quantidade--;
//             return true;
//         }
//     }

    public int size()
    {
        return quantidade;
    } 
}
