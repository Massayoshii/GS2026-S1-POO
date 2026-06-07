public abstract class ComponenteEspacial {

    private int id;
    private String nome;
    private String status;
    private double temperatura;

    public ComponenteEspacial(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.status = "DESLIGADO";
        this.temperatura = 0.0;
    }

    public void ligar() {
        this.status = "LIGADO";
        System.out.println("[" + nome + "] Componente ligado com sucesso.");
    }


    public void desligar() {
        this.status = "DESLIGADO";
        System.out.println("[" + nome + "] Componente desligado.");
    }

    public void exibirInfo() {
        System.out.println("==============================");
        System.out.println("ID        : " + id);
        System.out.println("Nome      : " + nome);
        System.out.println("Status    : " + status);
        System.out.println("Temp.     : " + temperatura + " °C");
        System.out.println("==============================");
    }


    public abstract String verificarEstado();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        if (temperatura < -273.15) {
            System.out.println("[ERRO] Temperatura abaixo do zero absoluto! Valor ignorado.");
        } else {
            this.temperatura = temperatura;
        }
    }
}