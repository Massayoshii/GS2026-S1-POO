public class PropulsaoEletrica extends SistemaPropulsao {

    private double cargaBateria;
    private double consumoEnergiaPorSegundo;

    public PropulsaoEletrica(String nome, double cargaBateria) {
        super(nome);
        this.cargaBateria = cargaBateria;
        this.consumoEnergiaPorSegundo = 1.2;
    }

    @Override
    public void acelerar(double potencia) {
        if (cargaBateria <= 0) {
            System.out.println("[" + getNome() + "] Bateria descarregada! Não é possível acelerar.");
            return;
        }
        super.acelerar(potencia);
        double consumo = consumoEnergiaPorSegundo * (potencia / 100);
        cargaBateria -= consumo;
        if (cargaBateria < 0) cargaBateria = 0;
        System.out.println("[" + getNome() + "] Carga da bateria: " + String.format("%.2f", cargaBateria) + " kWh");
    }

    @Override
    public double calcularEmpuxo() {
        if (!isLigado()) return 0;
        return getPotenciaAtual() * 2.8;
    }

    public double getCargaBateria() {
        return cargaBateria;
    }

    public void recarregarBateria(double quantidade) {
        if (quantidade < 0) {
            System.out.println("[ERRO] Quantidade inválida.");
            return;
        }
        if (cargaBateria + quantidade > 100) {
            cargaBateria = 100;
            System.out.println("[" + getNome() + "] Bateria carregada ao máximo: 100 kWh");
            return;
        }
        cargaBateria += quantidade;
        System.out.println("[" + getNome() + "] Bateria recarregada. Total: " + cargaBateria + " kWh");
    }
}