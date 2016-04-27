public class Nodo
{
    private Nodo proximo = null;
    private ContaCorrente info = null;
    
    public Nodo getProximo() {
        return proximo;
    }
    
    public ContaCorrente getInfo() {
        return info;
    }
    
    public void setProximo(Nodo proximo) {
        this.proximo = proximo;
    }
    
    public void setInfo(ContaCorrente conta) {
        this.info = conta;
    }    
}
