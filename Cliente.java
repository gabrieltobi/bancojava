public class Cliente
{
    private String nome = "";
    private String cpf = "";
    private Endereco endereco = new Endereco();
    private ListaDeContaCorrente contas =  new ListaDeContaCorrente(10);

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    public void setEndereco(String rua, String numero, String complemento, String bairro, String cep) {
        this.endereco.setRua(rua);
        this.endereco.setNumero(numero);
        this.endereco.setComplemento(complemento);
        this.endereco.setBairro(bairro);
        this.endereco.setCep(cep);
    }

    public String getEndereco() {
        String strEndereco = "";
        if (!this.endereco.getRua().equals("")) {
            strEndereco += "Rua: " + this.endereco.getRua() + "\n";
        }
        if (!this.endereco.getNumero().equals("")) {
            strEndereco += "nº " + this.endereco.getNumero() + "\n";
        }
        if (!this.endereco.getComplemento().equals("")) {
            strEndereco += "Complemento: " + this.endereco.getComplemento() + "\n";
        }
        if (!this.endereco.getBairro().equals("")) {
            strEndereco += "Bairro: " + this.endereco.getBairro() + "\n";
        }
        if (!this.endereco.getCep().equals("")) {
            strEndereco += "CEP: " + this.endereco.getCep() + "\n";
        }

        if (strEndereco.endsWith("\n")) {
            strEndereco = strEndereco.substring(0, strEndereco.length() - 1);
        }
        return strEndereco;
    }

    public boolean setContaAssociada(int numeroConta) {
        ContaCorrente cc = new ContaCorrente();
        cc.setNumero(numeroConta);
        cc.setCPFDono(this.cpf);
        if (contas.incluirConta(cc)) {            
            return true;
        } else {
            return false;
        }
    }

    public int[] getNumerosDasContasAssociadas() {
        return contas.getNumerosDasContas();
    }

    public int getQuantidadeDeContasAssociadas() { //Quantas contas o cliente tem
        return contas.getQuantidadeDeContas();
    }

    public int getMaximoDeContasAssociadas() { //Máximo de contas por cliente
        return contas.getMaxContas();
    }

    public boolean possuiConta(int numeroConta) {
        return contas.existeConta(numeroConta);
    }
}