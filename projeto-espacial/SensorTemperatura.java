import java.util.Random;

public class SensorTemperatura implements Sensor {

    private String nome;
    private double valorAtual;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    private boolean funcionando;
    private Random random;

    public SensorTemperatura(String nome) {
        this.nome = nome;
        this.limiteAtencao = 60.0;
        this.limiteAlerta = 80.0;
        this.limiteCritico = 100.0;
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
        return valorAtual > limiteAtencao;
    }

    public String verificarNivelAlerta() {
        if (valorAtual >= limiteCritico) {
            return "CRÍTICO";
        } else if (valorAtual >= limiteAlerta) {
            return "ALERTA";
        } else if (valorAtual >= limiteAtencao) {
            return "ATENÇÃO";
        }
        return "NORMAL";
    }

    public void exibirLeitura() {
        double leitura = lerValor();
        String nivel = verificarNivelAlerta();
        System.out.println("[" + nome + "] Temperatura: " + String.format("%.2f", leitura) + " °C");
        if (!nivel.equals("NORMAL")) {
            System.out.println("*** " + nivel + ": Temperatura em " + String.format("%.2f", leitura) + " °C (limites: ATENÇÃO " + limiteAtencao + " | ALERTA " + limiteAlerta + " | CRÍTICO " + limiteCritico + ") ***");
        }
    }

    public String getNome() {
        return nome;
    }
}