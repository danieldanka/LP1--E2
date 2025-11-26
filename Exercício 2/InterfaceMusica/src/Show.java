public class Show {
    private int id;
    private String cantor;
    private String local;
    private String publico; // Mudou de int para String

    public Show(int id, String cantor, String local, String publico) {
        this.id = id;
        this.cantor = cantor;
        this.local = local;
        this.publico = publico;
    }

    public int getId() { return this.id; }
    public String getCantor() { return this.cantor; }
    public String getLocal() { return this.local; }
    public String getPublico() { return this.publico; }

    public void setCantor(String cantor) { this.cantor = cantor; }
    public void setLocal(String local) { this.local = local; }
    public void setPublico(String publico) { this.publico = publico; }

    public void Lotacao() { System.out.println("Lotação: " + this.publico); }
    public void Cancelado() {}
    public void Adiado() {}
}