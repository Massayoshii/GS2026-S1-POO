public class PainelSolar extends ComponenteEspacial {

    private double eficiencia;
    private double energiaGerada;

    public PainelSolar(int id, String nome, double eficiencia) {
        super(id, nome);
        this.eficiencia = eficiencia;
        this.energiaGerada = 0.0;
    }

    @Override
    public String verificarEstado() {
        if (getStatus().equals("DESLIGADO")) {
            return "INATIVO - Painel desligado.";
        }
        if (eficiencia < 20) {
            return "CRÍTICO - Eficiência muito baixa: " + eficiencia + "%";
        } else if (eficiencia < 50) {
            return "ALERTA - Eficiência abaixo do ideal: " + eficiencia + "%";
        } else {
            return "OK - Eficiência normal: " + eficiencia + "%";
        }
    }

    public void gerarEnergia() {
        if (getStatus().equals("LIGADO")) {
            energiaGerada = eficiencia * 10;
            System.out.println("[" + getNome() + "] Energia gerada: " + energiaGerada + "W");
        } else {
            System.out.println("[" + getNome() + "] Painel desligado, nenhuma energia gerada.");
        }
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(double eficiencia) {
        if (eficiencia < 0 || eficiencia > 100) {
            System.out.println("[ERRO] Eficiência deve estar entre 0 e 100.");
        } else {
            this.eficiencia = eficiencia;
        }
    }

    public double getEnergiaGerada() {
        return energiaGerada;
    }
}