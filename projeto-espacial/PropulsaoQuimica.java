public class PropulsaoQuimica extends SistemaPropulsao {

    private double quantidadeCombustivel;
    private double taxaConsumoPorSegundo;

    public PropulsaoQuimica(String nome, double quantidadeCombustivel) {
        super(nome);
        this.quantidadeCombustivel = quantidadeCombustivel;
        this.taxaConsumoPorSegundo = 2.5;
    }

    @Override
    public void acelerar(double potencia) {
        if (quantidadeCombustivel <= 0) {
            System.out.println("[" + getNome() + "] Sem combustível! Não é possível acelerar.");
            return;
        }
        super.acelerar(potencia);
        double consumo = taxaConsumoPorSegundo * (potencia / 100);
        quantidadeCombustivel -= consumo;
        if (quantidadeCombustivel < 0) quantidadeCombustivel = 0;
        System.out.println("[" + getNome() + "] Combustível restante: " + String.format("%.2f", quantidadeCombustivel) + " L");
    }

    @Override
    public double calcularEmpuxo() {
        if (!isLigado()) return 0;
        return getPotenciaAtual() * 5.0;
    }

    public double getQuantidadeCombustivel() {
        return quantidadeCombustivel;
    }

    public void abastecerCombustivel(double quantidade) {
        if (quantidade < 0) {
            System.out.println("[ERRO] Quantidade inválida.");
            return;
        }
        this.quantidadeCombustivel += quantidade;
        System.out.println("[" + getNome() + "] Abastecido. Total: " + quantidadeCombustivel + " L");
    }
}