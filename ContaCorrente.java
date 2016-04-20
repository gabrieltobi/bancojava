public class ContaCorrente
{
    private int numero = 0;
    private double saldo = 0.0;
    private String cpfDono = "";

    public int getNumero() 
    {
        return numero;
    }  

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public double getSaldo() 
    {
        return saldo;
    }  

    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }

    public String getCPFDono() 
    {
        return cpfDono;
    }  

    public void setCPFDono(String cpfDono)
    {
        this.cpfDono = cpfDono;
    }

    public boolean depositar(double quantia)
    {
        if (quantia > 0) {
            saldo += quantia;
            return true;
        } else {
            return false;
        }
    }

    public boolean sacar(double quantia)
    {
        if (quantia <= saldo && quantia > 0) 
        {
            saldo -= quantia;
            return true;
        }
        else 
        {
            return false;
        }
    }   

    public boolean cpfPertence (String cpf) {
        if (cpfDono.equals(cpf)) {
            return true;
        } else {
            return false;
        }
    }
}
