import java.util.Random;

public class SensorTemperatura implements Sensor {

    private String nome;
    private double valorAtual;
    private double limiteAlerta;
    private boolean funcionando;
    private Random random;

    public SensorTemperatura(String nome) {
        this.nome = nome;
        this.limiteAlerta = 80.0;
        this.funcionando = true;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        valorAtual = 20.0 + (random.nextDouble() * 100.0);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return funcionando;
    }

    @Override
    public String retornarTipo() {
        return "TEMPERATURA";
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
        System.out.println("[" + nome + "] Temperatura: " + String.format("%.2f", leitura) + " °C");
        if (valorPassouLimite()) {
            System.out.println("*** ALERTA: Temperatura acima do limite (" + limiteAlerta + " °C)! ***");
        }
    }
}