import java.util.Scanner;

public class Interface
{   
    int qtdC = 10; //Máximo de clientes
    //int qtdCC = 100; //Máximo de contas
    Cliente[] clientes = new Cliente[qtdC];
    ListaDeContaCorrente contas = new ListaDeContaCorrente();
    int indexC = 0;
    //int indexCC = 0;
    int opcaoMenu = -1;

    public int Menu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n------------------Menu------------------");
        System.out.println("1 - Incluir novo cliente");
        System.out.println("2 - Incluir nova conta corrente");
        System.out.println("3 - Exibir informações dos clientes");
        System.out.println("4 - Depositar em uma conta");
        System.out.println("5 - Sacar de uma conta");
        System.out.println("6 - Exibir informações das contas");
        System.out.println("0 - Sair");
        System.out.println("----------------------------------------");

        return sc.nextInt();
    }

    public boolean criarCliente() {
        Scanner sc = new Scanner(System.in);

        if (indexC <= qtdC) {
            boolean cpfExiste = false;                        
            String nome = "";
            String cpf = "";

            System.out.print("\nInforme o nome do cliente: ");
            nome = sc.nextLine();

            do {
                cpfExiste = false;                
                System.out.print("Informe o cpf: ");
                cpf = sc.nextLine();               

                if (!cpf.equals("0")) {
                    for (int i = 0; ((i < indexC) && !cpfExiste); i++) {
                        if (cpf.equals(clientes[i].getCPF())) {
                            cpfExiste = true;                        
                        }
                    }

                    if (cpfExiste) {
                        System.out.println("\nERRO: Este CPF já está cadastrado.");
                        System.out.println("(Caso deseje voltar ao menu digite 0)");
                    }
                } else {
                    return false;
                }
            } while (cpfExiste);

            Endereco endereco = new Endereco();
            System.out.print("Informe a rua: ");
            endereco.setRua(sc.nextLine());
            System.out.print("Informe o número: ");
            endereco.setNumero(sc.nextLine());
            System.out.print("Informe o complemento: ");
            endereco.setComplemento(sc.nextLine());
            System.out.print("Informe o bairro: ");
            endereco.setBairro(sc.nextLine());
            System.out.print("Informe o CEP: ");
            endereco.setCep(sc.nextLine());

            if (gravarCliente(nome, cpf, endereco)) {
                System.out.println("\nCliente " + clientes[indexC-1].getNome() + " cadastrado com sucesso.");    
                return true;
            } else {
                System.out.println("Erro inesperado.");
                return false;
            }
        } else {
            System.out.println("\nO limite de clientes já foi atingido.");
            return false;
        }
    }

    public boolean gravarCliente(String nome, String cpf, Endereco endereco) {
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCPF(cpf);
        c.setEndereco(endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep());

        clientes[indexC] = c;
        indexC++;
        return true;
    }

    public boolean criarContaCorrente() {
        Scanner sc = new Scanner(System.in);
        int qtdContas = contas.getQuantidadeDeContas();
        boolean cpfExiste = false;

        do {
            if (qtdContas < contas.getMaxContas()) {
                String cpf = "";

                System.out.print("\nInforme o cpf que será vinculado a essa conta: ");
                cpf = sc.nextLine();

                if (!cpf.equals("0")) {
                    for (int i = 0; (i < indexC); i++) {
                        if (clientes[i].getCPF().equals(cpf)) {
                            cpfExiste = true;

                            if (gravarContaCorrente(qtdContas, 0.0, clientes[i])) {
                                System.out.println("\nConta de número '" + (qtdContas)  + "' criada com sucesso.");          
                                return true;
                            } else {
                                System.out.println("\nO cliente atingiu o máximo de contas.");
                                return false;
                            }
                        }
                    }

                    if (!cpfExiste) {
                        System.out.println("\nERRO: Nenhum cliente encontrado para este CPF.");
                        return false;
                    }
                } else {
                    return false;
                }                
            } else {
                System.out.println("\nO limite de contas já foi atingido.");
                return false;
            }
        }while(!cpfExiste);               
        return false;        
    }

    public boolean gravarContaCorrente (int numero, double saldo, Cliente dono){
        if (dono.setContaAssociada(numero)) {
            ContaCorrente cc = new ContaCorrente();
            cc.setNumero(numero);
            cc.setSaldo(saldo);
            cc.setCPFDono(dono.getCPF());
            contas.incluirConta(cc);
            return true;
        } else {
            return false;
        }
    }

    public void consultarClientes() {
        if (indexC > 0) {
            for (int i = 0; i < indexC; i++) {
                System.out.println("\nCliente: " + clientes[i].getNome());
                System.out.println(clientes[i].getEndereco());
                if (clientes[i].getQuantidadeDeContasAssociadas() > 0) {
                    System.out.print("Contas: ");
                    int[] contas = clientes[i].getNumerosDasContasAssociadas();
                    for (int j = 0; j < contas.length; j++) {
                        System.out.print(contas[j]);
                        if (j != (contas.length - 1)) {
                            System.out.print(", ");
                        } else {
                            System.out.println(".");
                        }
                    }
                } else {
                    System.out.println("Nenhuma conta associada");
                }
            }
        } else {
            System.out.println("\nNão há nenhuma conta cadastrada");
        }
    }

    public void depositar () {
        Scanner sc = new Scanner(System.in);

        int numero = 0;
        String cpfVinculado = "";
        boolean cpfCorreto = false;
        boolean existeNumero = false;
        ContaCorrente cc = new ContaCorrente();

        System.out.print("\nInforme o número da conta em que deseja depositar: ");
        numero = sc.nextInt();

        do {
            cc = contas.getConta(numero);
            if (cc != null) { 
                existeNumero = true;
                System.out.print("Informe o CPF vinculado à conta em que deseja depositar: ");
                //sc.nextLine(); //Aqui parece que buga na primeira
                cpfVinculado = sc.nextLine();                                   

                if (cc.cpfPertence(cpfVinculado)) {
                    cpfCorreto = true;
                    boolean saldoValido = true;
                    double quantia = 0.0;

                    do {
                        if (!saldoValido) {
                            System.out.println("(Digite '0' caso deseje voltar ao menu.)");
                        }
                        System.out.print("Informe a quantia a ser depositada: R$");
                        quantia = sc.nextDouble();
                        if (quantia != 0.0) {
                            if (cc.depositar(quantia)) {
                                System.out.println("\nDepósito efetuado com sucesso. Novo saldo: R$" + cc.getSaldo());
                                saldoValido = true;
                            } else {
                                System.out.println("\nERRO: Quantia inválida para depósito.");
                                saldoValido = false;
                            }
                        } else {
                            saldoValido = true;
                        }
                    } while(!saldoValido);                    
                } else {
                    System.out.println("\nERRO: O CPF informado não pertence à esta conta."); 
                }
            } else {
                cpfCorreto = true;
            }
        } while(!cpfCorreto);

        if (!existeNumero) {
            System.out.println("\nERRO: Não existe conta com este número.");
        }
    }

    public void sacar() {
        Scanner sc = new Scanner(System.in);

        int numero = 0;
        String cpfVinculado = "";
        boolean existeNumero = false;
        ContaCorrente cc = new ContaCorrente();

        System.out.print("\nInforme o número da conta da qual deseja sacar: ");
        numero = sc.nextInt();

        cc = contas.getConta(numero);
        if (cc != null) {    
            existeNumero = true;
            System.out.print("Informe o CPF vinculado à conta da qual deseja sacar: ");
            sc.nextLine(); //Pra desbugar
            cpfVinculado = sc.nextLine();                                   

            if (cc.cpfPertence(cpfVinculado)) {
                double quantia = -1;
                boolean saldoSuficiente = true;

                do {
                    if (!saldoSuficiente) {
                        System.out.println("(Digite '0' caso deseje voltar ao menu.)");
                    }
                    System.out.print("Informe a quantia a ser retirada: R$");
                    quantia = sc.nextDouble();
                    if (quantia != 0.0) {
                        if (cc.sacar(quantia)) {
                            System.out.println("\nSaque efetuado com sucesso. Novo saldo: R$" + cc.getSaldo());
                            saldoSuficiente = true;
                        } else if (quantia <= 0) {                               
                            System.out.println("\nERRO: Quantia inválida para saque.");
                            saldoSuficiente = false;
                        } else  {
                            System.out.println("\nERRO: Saldo insuficiente.");
                            saldoSuficiente = false;
                        }
                    } else {
                        saldoSuficiente = true; //Pra cair fora do loop
                    }
                } while (!saldoSuficiente);
                sc.nextLine(); //Pra desbugar
            } else {
                System.out.println("\nERRO: O CPF informado não pertence à esta conta.");                    
            }   
        }   

        if (!existeNumero) {
            System.out.println("\nERRO: Não existe conta com este número.");
        }
    }

    public void exibirContas() {
        for (int i = 0; i < indexC; i++) {

        }
    }

    public void Iniciar() {            
        Scanner sc = new Scanner(System.in);

        while (opcaoMenu != 0) {
            opcaoMenu = Menu();

            switch (opcaoMenu) {
                case 1:
                criarCliente();
                break;

                case 2:
                criarContaCorrente();
                break;

                case 3:
                consultarClientes();
                break;

                case 4:
                depositar();
                break;

                case 5:
                sacar();
                break;

                case 6:
                exibirContas();
                break;

                case 0:
                System.out.println("\nAté logo.");
                break;

                default:
                System.out.println("\nERRO: Opção Inválida");
                break;
            }
        }
    }
}
