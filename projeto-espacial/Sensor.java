public interface Sensor {
    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();
    void definirLimiteAlerta(double limite);
    boolean valorPassouLimite();
}