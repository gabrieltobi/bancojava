public class RetornoPadrao
{
    private String mensagem = "";
    private boolean erro = false;

    public RetornoPadrao() {
    }

    public RetornoPadrao(String mensagem, boolean erro) {
        this.mensagem = mensagem;
        this.erro = erro;
    }
}
