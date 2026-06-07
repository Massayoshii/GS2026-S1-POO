import java.util.Random;

public class SensorPressao implements Sensor {

    private String nome;
    private double valorAtual;
    private double limiteAlerta;
    private boolean funcionando;
    private Random random;

    public SensorPressao(String nome) {
        this.nome = nome;
        this.limiteAlerta = 1.5;
        this.funcionando = true;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        valorAtual = 0.5 + (random.nextDouble() * 2.0);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return funcionando;
    }

    @Override
    public String retornarTipo() {
        return "PRESSAO";
    }

    @Override
    public void definirLimiteAlerta(double limite) {
        if (limite > 0) {
            limiteAlerta = limite;
        }
    }

    @Override
    public boolean valorPassouLimite() {
        return valorAtual > limiteAlerta;
    }

    public void exibirLeitura() {
        double leitura = lerValor();
        System.out.println("[" + nome + "] Pressão: " + String.format("%.2f", leitura) + " atm");
        if (valorPassouLimite()) {
            System.out.println("*** ALERTA: Pressão acima do limite (" + limiteAlerta + " atm)! ***");
        }
    }
}