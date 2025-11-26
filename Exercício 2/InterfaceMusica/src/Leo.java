public class Leo {
    private int id; // Mantemos ID como int (controle do banco)
    private String nome;
    private String altura;      // Mudou de int para String
    private String aniversario; // Mudou de int para String

    public Leo(int id, String nome, String altura, String aniversario) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.aniversario = aniversario;
    }

    public int getId() { return this.id; }
    public String getNome() { return this.nome; }
    public String getAltura() { return this.altura; }
    public String getAniversario() { return this.aniversario; }

    public void setNome(String nome) { this.nome = nome; }
    public void setAltura(String altura) { this.altura = altura; }
    public void setAniversario(String aniversario) { this.aniversario = aniversario; }

    public void Cantar() { System.out.println(this.nome + " está cantando."); }
    public void Andar() {}
    public void Pensar() {}
}