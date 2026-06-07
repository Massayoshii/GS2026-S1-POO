import java.util.Random;

public class SensorRadiacao implements Sensor {

    private String nome;
    private double valorAtual;
    private double limiteAlerta;
    private boolean funcionando;
    private Random random;

    public SensorRadiacao(String nome) {
        this.nome = nome;
        this.limiteAlerta = 50.0;
        this.funcionando = true;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        valorAtual = 5.0 + (random.nextDouble() * 90.0);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return funcionando;
    }

    @Override
    public String retornarTipo() {
        return "RADIACAO";
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
        System.out.println("[" + nome + "] Radiação: " + String.format("%.2f", leitura) + " mSv");
        if (valorPassouLimite()) {
            System.out.println("*** CRÍTICO: Nível de radiação perigoso (" + limiteAlerta + " mSv)! ***");
        }
    }
}