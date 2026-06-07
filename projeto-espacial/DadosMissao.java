public class DadosMissao {

    private String nomeMissao;
    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private String codigoAcesso;
    private double nivelCombustivel;
    private String trajetoria;
    private int numeroDeTripulantes;

    public DadosMissao(String nomeMissao, String codigoAcesso, int numeroDeTripulantes) {
        this.nomeMissao = nomeMissao;
        this.codigoAcesso = codigoAcesso;
        this.nivelCombustivel = 100.0;
        this.numeroDeTripulantes = numeroDeTripulantes;
        this.trajetoria = "Indefinida";
        this.coordenadaX = 0;
        this.coordenadaY = 0;
        this.coordenadaZ = 0;
    }

    public String getNomeMissao() {
        return nomeMissao;
    }

    public boolean getCoordenadas(String senha) {
        if (senha.equals(codigoAcesso)) {
            System.out.println("Coordenadas => X: " + coordenadaX + " | Y: " + coordenadaY + " | Z: " + coordenadaZ);
            return true;
        }
        System.out.println("[ACESSO NEGADO] Código incorreto.");
        return false;
    }

    public boolean setCoordenadas(String senha, double x, double y, double z) {
        if (!senha.equals(codigoAcesso)) {
            System.out.println("[ACESSO NEGADO] Código incorreto.");
            return false;
        }
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadaZ = z;
        System.out.println("Coordenadas atualizadas com sucesso.");
        return true;
    }

    public double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("[ERRO] Nível de combustível deve estar entre 0 e 100.");
            return;
        }
        this.nivelCombustivel = nivel;
        if (nivelCombustivel < 20) {
            System.out.println("*** ALERTA: Combustível crítico! Nível: " + nivelCombustivel + "% ***");
        }
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria == null || trajetoria.isEmpty()) {
            System.out.println("[ERRO] Trajetória inválida.");
            return;
        }
        this.trajetoria = trajetoria;
    }

    public int getNumeroDeTripulantes() {
        return numeroDeTripulantes;
    }

    public void setNumeroDeTripulantes(int numero) {
        if (numero < 0) {
            System.out.println("[ERRO] Número de tripulantes não pode ser negativo.");
            return;
        }
        this.numeroDeTripulantes = numero;
    }

    public void exibirDadosGerais() {
        System.out.println("==============================");
        System.out.println("Missão       : " + nomeMissao);
        System.out.println("Trajetória   : " + trajetoria);
        System.out.println("Combustível  : " + nivelCombustivel + "%");
        System.out.println("Tripulantes  : " + numeroDeTripulantes);
        System.out.println("Coordenadas  : [PROTEGIDAS]");
        System.out.println("==============================");
    }
}