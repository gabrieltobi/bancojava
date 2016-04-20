public class Endereco
{
    private String rua = "";
    private String numero = "";
    private String complemento = "";
    private String bairro = "";
    private String cep = "";

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public String getRua()
    {
        return rua;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getCep()
    {
        return cep;
    }
}
