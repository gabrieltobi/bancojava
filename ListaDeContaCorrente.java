public class ListaDeContaCorrente
{
    private int maxContas = 100; //Máximo de contas da lista
    private ContaCorrente[] contas = new ContaCorrente[maxContas];
    private int quantidade = 0;

    public ListaDeContaCorrente () {
    }

    public ListaDeContaCorrente (int qtdMaxContas) {
        this.maxContas = qtdMaxContas;
        this.contas = new ContaCorrente[maxContas];
    }

    public boolean estaVazia () {
        if (quantidade == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ContaCorrente get (int i) {
        return contas[i];
    }

    public ContaCorrente getConta (int numeroConta) {
        for (int i = 0; i < quantidade; i++) {
            if (contas[i].getNumero() == numeroConta) {
                return contas[i];
            } 
        }
        return null;
    }

    public int getMaxContas() {
        return maxContas;
    }

    public int[] getNumerosDasContas () {
        int[] contas  = new int[quantidade];
        for (int i = 0; i < quantidade; i++) {
            contas[i] = this.contas[i].getNumero();
        }
        return contas;
    }

    public int getQuantidadeDeContas()
    {
        return quantidade;
    }        

    public boolean incluirConta(ContaCorrente conta)
    {
        return incluirConta(quantidade, conta);
    }

    public boolean incluirConta(int posicao, ContaCorrente conta)
    {
        if (quantidade < maxContas) {
            for (int i = quantidade; i > posicao; i--) {
                contas[i] = contas[i-1];
            }
            contas[posicao] = conta;
            quantidade++;
            return true;
        } else {
            return false;
        }
    }

    public boolean incluirContaInicio(ContaCorrente conta)
    {
        return incluirConta(0, conta);
    }

    public void ordenar() {
        ordenar(true);
    }

    public void ordenar(boolean ascendente) {
        for (int i = 0; i < quantidade; i++)
        {
            for (int x = i; x > 0; x--)
            {
                if (ascendente) {
                    if (contas[x].getNumero() < contas[x-1].getNumero()) {
                        ContaCorrente troca = new ContaCorrente();
                        troca = contas[x];
                        contas[x] = contas[x-1];
                        contas[x-1] = troca;
                    }
                } else {
                    if (contas[x].getNumero() > contas[x-1].getNumero()) {
                        ContaCorrente troca = new ContaCorrente();
                        troca = contas[x];
                        contas[x] = contas[x-1];
                        contas[x-1] = troca;
                    }
                }
            }
        }
    }

    public boolean existeConta(int numeroConta) {
        for (int i = 0; i < quantidade; i++) {
            if (contas[i].getNumero() == numeroConta) {
                return true;
            }
        }
        return false;
    }

    public boolean removerConta(int numeroConta)
    {
        int i = 0;
        while ((i < quantidade && contas[i].getNumero() != numeroConta)) {
            i++;
        }
        if (i == quantidade) {
            return false;
        } else {
            for (i = i; i < (quantidade - 1); i++) {
                contas[i] = contas[i+1];
            }
            contas[i] = null;
            quantidade--;
            return true;
        }
    }

    public int size()
    {
        return quantidade;
    }
}
