public abstract class SistemaPropulsao {

    private String nome;
    private boolean ligado;
    private double potenciaAtual;

    public SistemaPropulsao(String nome) {
        this.nome = nome;
        this.ligado = false;
        this.potenciaAtual = 0;
    }

    public void ligar() {
        this.ligado = true;
        System.out.println("[" + nome + "] Motor ligado.");
    }

    public void desligar() {
        this.ligado = false;
        this.potenciaAtual = 0;
        System.out.println("[" + nome + "] Motor desligado.");
    }

    public void acelerar(double potencia) {
        if (!ligado) {
            System.out.println("[" + nome + "] Motor desligado. Ligue antes de acelerar.");
            return;
        }
        if (potencia < 0 || potencia > 100) {
            System.out.println("[ERRO] Potência deve estar entre 0 e 100.");
            return;
        }
        this.potenciaAtual = potencia;
        System.out.println("[" + nome + "] Potência ajustada para " + potencia + "%");
    }

    public abstract double calcularEmpuxo();

    public void exibirStatus() {
        System.out.println("==============================");
        System.out.println("Propulsão : " + nome);
        System.out.println("Status    : " + (ligado ? "LIGADO" : "DESLIGADO"));
        System.out.println("Potência  : " + potenciaAtual + "%");
        System.out.println("Empuxo    : " + String.format("%.2f", calcularEmpuxo()) + " kN");
        System.out.println("==============================");
    }

    public String getNome() {
        return nome;
    }

    public boolean isLigado() {
        return ligado;
    }

    public double getPotenciaAtual() {
        return potenciaAtual;
    }
}