import java.util.Random;

public class SensorPressao implements Sensor {

    private String nome;
    private double valorAtual;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    private boolean funcionando;
    private Random random;

    public SensorPressao(String nome) {
        this.nome = nome;
        this.limiteAtencao = 1.2;
        this.limiteAlerta = 1.5;
        this.limiteCritico = 1.8;
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
        System.out.println("[" + nome + "] Pressão: " + String.format("%.2f", leitura) + " atm");
        if (!nivel.equals("NORMAL")) {
            System.out.println("*** " + nivel + ": Pressão em " + String.format("%.2f", leitura) + " atm (limites: ATENÇÃO " + limiteAtencao + " | ALERTA " + limiteAlerta + " | CRÍTICO " + limiteCritico + ") ***");
        }
    }

    public String getNome() {
        return nome;
    }
}