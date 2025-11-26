public class Mundo {
    private int id;
    private String nome;
    private String nuvens;   // Mudou de int para String
    private String estacoes;

    public Mundo(int id, String nome, String nuvens, String estacoes) {
        this.id = id;
        this.nome = nome;
        this.nuvens = nuvens;
        this.estacoes = estacoes;
    }

    public int getId() { return this.id; }
    public String getNome() { return this.nome; }
    public String getNuvens() { return this.nuvens; }
    public String getEstacoes() { return this.estacoes; }

    public void setNome(String nome) { this.nome = nome; }
    public void setNuvens(String nuvens) { this.nuvens = nuvens; }
    public void setEstacoes(String estacoes) { this.estacoes = estacoes; }

    public void Girar() { System.out.println("O " + this.nome + " está girando."); }
    public void Esquentar() {}
    public void Esfriar() {}
}