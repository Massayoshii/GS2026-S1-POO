import java.util.Scanner;

public class SistemaMonitoramento {

    private static SensorTemperatura sensorTemp = new SensorTemperatura("Sensor Temp-01");
    private static SensorPressao sensorPressao = new SensorPressao("Sensor Press-01");
    private static SensorRadiacao sensorRadiacao = new SensorRadiacao("Sensor Rad-01");

    private static PropulsaoQuimica propQuimica = new PropulsaoQuimica("Motor Químico Alpha", 500.0);
    private static PropulsaoEletrica propEletrica = new PropulsaoEletrica("Motor Elétrico Beta", 100.0);

    private static DadosMissao missao = new DadosMissao("Missão Apolo-X", "1234", 3);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   BEM-VINDO AO SISTEMA DE MONITORAMENTO");
        System.out.println("        ESTAÇÃO ESPACIAL FIAP-01");
        System.out.println("==========================================");

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("\nEncerrando sistema. Boa viagem!\n");
                default -> System.out.println("[ERRO] Opção inválida.\n");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("  MENU PRINCIPAL");
        System.out.println("------------------------------------------");
        System.out.println("  1. Verificar Sensores");
        System.out.println("  2. Controlar Propulsão");
        System.out.println("  3. Gerenciar Dados da Missão");
        System.out.println("  4. Simular Alertas");
        System.out.println("  5. Exibir Status Completo");
        System.out.println("  0. Sair");
        System.out.println("------------------------------------------");
    }

    private static void menuSensores() {
        System.out.println("\n--- SISTEMA DE SENSORES ---");
        System.out.println("1. Ler todos os sensores");
        System.out.println("2. Verificar funcionamento");
        System.out.println("3. Alterar limite de alerta");
        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> {
                sensorTemp.exibirLeitura();
                sensorPressao.exibirLeitura();
                sensorRadiacao.exibirLeitura();
            }
            case 2 -> {
                System.out.println("Sensor Temperatura : " + (sensorTemp.verificarFuncionamento() ? "OK" : "COM FALHA"));
                System.out.println("Sensor Pressão     : " + (sensorPressao.verificarFuncionamento() ? "OK" : "COM FALHA"));
                System.out.println("Sensor Radiação    : " + (sensorRadiacao.verificarFuncionamento() ? "OK" : "COM FALHA"));
            }
            case 3 -> {
                System.out.println("Qual sensor? 1-Temperatura  2-Pressão  3-Radiação");
                int sensor = lerInteiro("Sensor: ");
                double limite = lerDouble("Novo limite: ");
                switch (sensor) {
                    case 1 -> sensorTemp.definirLimiteAlerta(limite);
                    case 2 -> sensorPressao.definirLimiteAlerta(limite);
                    case 3 -> sensorRadiacao.definirLimiteAlerta(limite);
                    default -> System.out.println("[ERRO] Sensor inválido.");
                }
                System.out.println("Limite atualizado.");
            }
            default -> System.out.println("[ERRO] Opção inválida.");
        }
    }
    private static void menuPropulsao() {
        System.out.println("\n--- SISTEMA DE PROPULSÃO ---");
        System.out.println("1. Ligar motor");
        System.out.println("2. Desligar motor");
        System.out.println("3. Acelerar");
        System.out.println("4. Ver status dos motores");
        int opcao = lerInteiro("Escolha: ");

        System.out.println("Qual motor? 1-Químico  2-Elétrico");
        int motor = lerInteiro("Motor: ");

        SistemaPropulsao propSelecionada = (motor == 1) ? propQuimica : propEletrica;

        switch (opcao) {
            case 1 -> propSelecionada.ligar();
            case 2 -> propSelecionada.desligar();
            case 3 -> {
                double potencia = lerDouble("Potência (0-100): ");
                propSelecionada.acelerar(potencia);
            }
            case 4 -> {
                propQuimica.exibirStatus();
                propEletrica.exibirStatus();
            }
            default -> System.out.println("[ERRO] Opção inválida.");
        }
    }

    private static void menuMissao() {
        System.out.println("\n--- DADOS DA MISSÃO ---");
        System.out.println("1. Exibir dados gerais");
        System.out.println("2. Ver coordenadas (requer senha)");
        System.out.println("3. Atualizar coordenadas (requer senha)");
        System.out.println("4. Atualizar combustível");
        System.out.println("5. Atualizar trajetória");
        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> missao.exibirDadosGerais();
            case 2 -> {
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                missao.getCoordenadas(senha);
            }
            case 3 -> {
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                double x = lerDouble("X: ");
                double y = lerDouble("Y: ");
                double z = lerDouble("Z: ");
                missao.setCoordenadas(senha, x, y, z);
            }
            case 4 -> {
                double nivel = lerDouble("Novo nível de combustível (0-100): ");
                missao.setNivelCombustivel(nivel);
            }
            case 5 -> {
                System.out.print("Nova trajetória: ");
                String traj = scanner.nextLine();
                missao.setTrajetoria(traj);
                System.out.println("Trajetória atualizada.");
            }
            default -> System.out.println("[ERRO] Opção inválida.");
        }
    }

    private static void simularAlertas() {
        System.out.println("\n--- SIMULAÇÃO DE ALERTAS ---");
        System.out.println("Verificando todos os sensores automaticamente...\n");

        verificarSensorComAlerta("Temperatura", sensorTemp.lerValor(), sensorTemp.verificarNivelAlerta());
        verificarSensorComAlerta("Pressão", sensorPressao.lerValor(), sensorPressao.verificarNivelAlerta());
        verificarSensorComAlerta("Radiação", sensorRadiacao.lerValor(), sensorRadiacao.verificarNivelAlerta());

        System.out.println("\nVerificação concluída.");
    }

    private static void verificarSensorComAlerta(String tipo, double valor, String nivel) {
        System.out.print("[" + tipo + "] Valor: " + String.format("%.2f", valor) + " => ");
        switch (nivel) {
            case "CRÍTICO" -> System.out.println("*** CRÍTICO: Situação de emergência! Ação imediata necessária! ***");
            case "ALERTA"  -> System.out.println("** ALERTA: Valor fora do padrão seguro. Monitorar com atenção.");
            case "ATENÇÃO" -> System.out.println("* ATENÇÃO: Valor se aproximando do limite.");
            default        -> System.out.println("NORMAL.");
        }
    }

    private static void exibirStatusCompleto() {
        System.out.println("\n========== STATUS COMPLETO DA ESTAÇÃO ==========");
        missao.exibirDadosGerais();
        System.out.println("\n--- Sensores ---");
        sensorTemp.exibirLeitura();
        sensorPressao.exibirLeitura();
        sensorRadiacao.exibirLeitura();
        System.out.println("\n--- Propulsão ---");
        propQuimica.exibirStatus();
        propEletrica.exibirStatus();
        System.out.println("=================================================");
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("[ERRO] Digite um número inteiro: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.print("[ERRO] Digite um número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}