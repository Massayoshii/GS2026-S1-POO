import java.util.Random;

public class SensorRadiacao implements Sensor {

    private String nome;
    private double valorAtual;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    private boolean funcionando;
    private Random random;

    public SensorRadiacao(String nome) {
        this.nome = nome;
        this.limiteAtencao = 20.0;
        this.limiteAlerta = 50.0;
        this.limiteCritico = 75.0;
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
        System.out.println("[" + nome + "] Radiação: " + String.format("%.2f", leitura) + " mSv");
        if (!nivel.equals("NORMAL")) {
            System.out.println("*** " + nivel + ": Radiação em " + String.format("%.2f", leitura) + " mSv (limites: ATENÇÃO " + limiteAtencao + " | ALERTA " + limiteAlerta + " | CRÍTICO " + limiteCritico + ") ***");
        }
    }

    public String getNome() {
        return nome;
    }
}