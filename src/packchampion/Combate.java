package packchampion;
import java.util.Scanner;



class Campeao {
    private String nome;
    private int ataque;
    private int armadura;
    private int vida;

    public Campeao(String nome, int ataque, int armadura, int vida) {
        this.nome = nome;
        this.ataque = ataque;
        this.armadura = armadura;
        this.vida = vida;
    }

    public void takeDamage(int ataqueInimigo) {
        int dano = Math.max(ataqueInimigo - this.armadura, 1); // dano mínimo de 1
        this.vida = Math.max(this.vida - dano, 0); // a vida não pode ser menor que zero
    }

    public String status() {
        if (this.vida > 0) {
            return this.nome + ": " + this.vida + " de vida";
        } else {
            return this.nome + ": 0 de vida (morreu)";
        }
    }

    public boolean isAlive() {
        return this.vida > 0;
    }

    public int getAtaque() {
        return this.ataque;
    }
}

public class Combate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Dados do primeiro campeão
            System.out.println("Digite os dados do primeiro campeão:");
            String nome1 = solicitarNome(scanner, "Nome: ");
            int vida1 = solicitarValorPositivo(scanner, "Vida inicial: ");
            int ataque1 = solicitarValorPositivo(scanner, "Ataque: ");
            int armadura1 = solicitarValorPositivo(scanner, "Armadura: ");
            
            // Dados do segundo campeão
            System.out.println("Digite os dados do segundo campeão:");
            String nome2 = solicitarNome(scanner, "Nome: ");
            int vida2 = solicitarValorPositivo(scanner, "Vida inicial: ");
            int ataque2 = solicitarValorPositivo(scanner, "Ataque: ");
            int armadura2 = solicitarValorPositivo(scanner, "Armadura: ");
            
            // Instanciação dos campeões
            Campeao campeao1 = new Campeao(nome1, ataque1, armadura1, vida1);
            Campeao campeao2 = new Campeao(nome2, ataque2, armadura2, vida2);

            // Turnos de combate
            int numeroDeTurnos = solicitarValorPositivo(scanner, "Quantos turnos você deseja executar? ");
            System.out.println();

            for (int turno = 1; turno <= numeroDeTurnos; turno++) {
                System.out.println("Resultado do turno " + turno + ":");

                // Cada campeão ataca o outro
                campeao1.takeDamage(campeao2.getAtaque());
                campeao2.takeDamage(campeao1.getAtaque());

                // Mostra o status de cada campeão após o turno
                System.out.println(campeao1.status());
                System.out.println(campeao2.status());
                System.out.println();

                // Verifica se algum campeão morreu
                if (!campeao1.isAlive() || !campeao2.isAlive()) {
                    System.out.println("### FIM DO COMBATE ###");
                    break; // Encerra o loop se um campeão morreu
                }
            }
            
            // Mensagem de fim do combate
            if (campeao1.isAlive() && campeao2.isAlive()) {
                System.out.println("### FIM DO COMBATE ###");
            }

            // Pergunta se o usuário deseja executar outro combate
            System.out.print("Deseja executar outro combate? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (!resposta.equals("s")) {
                break; // Sai do loop se a resposta não for 's'
            }
            System.out.println(); // Linha em branco para separar os combates
        }

        scanner.close();
        System.out.println("Obrigado por jogar!");
    }

    // Função para validar entrada de nome não vazio
    private static String solicitarNome(Scanner scanner, String mensagem) {
        String nome;
        do {
            System.out.print(mensagem);
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome não pode ser vazio. Tente novamente.");
            }
        } while (nome.isEmpty());
        return nome;
    }

    // Função para validar entrada de inteiros positivos
    private static int solicitarValorPositivo(Scanner scanner, String mensagem) {
        int valor;
        do {
            System.out.print(mensagem);
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro positivo.");
                System.out.print(mensagem);
                scanner.next(); // Descarta a entrada inválida
            }
            valor = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            if (valor <= 0) {
                System.out.println("O valor deve ser maior que zero. Tente novamente.");
            }
        } while (valor <= 0);
        return valor;
    }
}
