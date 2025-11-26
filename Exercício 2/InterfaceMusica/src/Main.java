import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main extends JFrame {
    private JComboBox<String> seletorClasses;
    private JPanel painelCampos;
    private CardLayout cardLayout;
    private JLabel statusLabel;
    private Map<String, JTextField[]> camposPorClasse;

    private LeoRepository repoLeo = new LeoRepository();
    private ShowRepository repoShow = new ShowRepository();
    private MundoRepository repoMundo = new MundoRepository();

    public Main() {
        super("CRUD Completo - Projeto Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));

        // Painel Superior
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.seletorClasses = new JComboBox<>(new String[]{"Leo", "Show", "Mundo"});
        this.seletorClasses.addActionListener((e) -> this.trocarCampos());
        painelTopo.add(new JLabel("Classe:"));
        painelTopo.add(this.seletorClasses);
        this.add(painelTopo, "North");

        // Painel Central (Campos)
        this.cardLayout = new CardLayout();
        this.painelCampos = new JPanel(this.cardLayout);
        this.camposPorClasse = new HashMap<>();
        this.configurarPainelEntrada();
        this.add(this.painelCampos, "Center");

        // Painel Inferior (Botões CRUD)
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnCreate = new JButton("Registrar");
        JButton btnRead = new JButton("Listar");
        JButton btnUpdate = new JButton("Atualizar (ID)");
        JButton btnDelete = new JButton("Deletar (ID)");

        btnCreate.addActionListener(e -> registrarDados());
        btnRead.addActionListener(e -> listarDados());
        btnUpdate.addActionListener(e -> atualizarDados());
        btnDelete.addActionListener(e -> deletarDados());

        painelBotoes.add(btnCreate);
        painelBotoes.add(btnRead);
        painelBotoes.add(btnUpdate);
        painelBotoes.add(btnDelete);

        JPanel painelFundo = new JPanel(new BorderLayout());
        painelFundo.add(painelBotoes, "Center");
        this.statusLabel = new JLabel("Sistema pronto.", JLabel.CENTER);
        painelFundo.add(this.statusLabel, "South");
        this.add(painelFundo, "South");

        this.pack();
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
        this.trocarCampos();
    }

    private void configurarPainelEntrada() {
        this.camposPorClasse.put("Leo", criarCampos("Leo", new String[]{"Nome", "Altura", "Aniversario"}));
        this.camposPorClasse.put("Show", criarCampos("Show", new String[]{"Cantor", "Local", "Publico"}));
        this.camposPorClasse.put("Mundo", criarCampos("Mundo", new String[]{"Nome", "Nuvens", "Estações"}));
    }

    private JTextField[] criarCampos(String nome, String[] attrs) {
        JPanel p = new JPanel(new GridLayout(attrs.length, 2, 5, 5));
        JTextField[] tfs = new JTextField[attrs.length];
        for(int i=0; i<attrs.length; i++) {
            p.add(new JLabel(attrs[i] + ":"));
            tfs[i] = new JTextField(20);
            p.add(tfs[i]);
        }
        this.painelCampos.add(p, nome);
        return tfs;
    }

    private void trocarCampos() {
        this.cardLayout.show(this.painelCampos, (String)this.seletorClasses.getSelectedItem());
        this.pack();
    }

    // --- C: CREATE ---
    private void registrarDados() {
        String classe = (String)this.seletorClasses.getSelectedItem();
        JTextField[] c = this.camposPorClasse.get(classe);
        try {
            if (classe.equals("Leo")) repoLeo.create(c[0].getText(), c[1].getText(), c[2].getText());
            else if (classe.equals("Show")) repoShow.create(c[0].getText(), c[1].getText(), c[2].getText());
            else if (classe.equals("Mundo")) repoMundo.create(c[0].getText(), c[1].getText(), c[2].getText());

            statusLabel.setText(classe + " registrado com sucesso!");
            for(JTextField f : c) f.setText(""); // Limpa
        } catch (Exception e) { e.printStackTrace(); }
    }

    // --- R: READ ---
    private void listarDados() {
        String classe = (String)this.seletorClasses.getSelectedItem();
        StringBuilder sb = new StringBuilder();
        if (classe.equals("Leo")) {
            for(Leo l : repoLeo.readAll()) sb.append(l.getId()).append(" | ").append(l.getNome()).append(" | ").append(l.getAltura()).append("\n");
        } else if (classe.equals("Show")) {
            for(Show s : repoShow.readAll()) sb.append(s.getId()).append(" | ").append(s.getCantor()).append(" | ").append(s.getLocal()).append("\n");
        } else if (classe.equals("Mundo")) {
            for(Mundo m : repoMundo.readAll()) sb.append(m.getId()).append(" | ").append(m.getNome()).append(" | ").append(m.getNuvens()).append("\n");
        }
        JTextArea ta = new JTextArea(sb.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Lista de " + classe, JOptionPane.PLAIN_MESSAGE);
    }

    // --- U: UPDATE ---
    private void atualizarDados() {
        String classe = (String)this.seletorClasses.getSelectedItem();
        JTextField[] c = this.camposPorClasse.get(classe);
        String idStr = JOptionPane.showInputDialog("Digite o ID para atualizar com os dados da tela:");
        if(idStr == null) return;

        try {
            int id = Integer.parseInt(idStr);
            if (classe.equals("Leo")) repoLeo.update(id, c[0].getText(), c[1].getText(), c[2].getText());
            else if (classe.equals("Show")) repoShow.update(id, c[0].getText(), c[1].getText(), c[2].getText());
            else if (classe.equals("Mundo")) repoMundo.update(id, c[0].getText(), c[1].getText(), c[2].getText());

            statusLabel.setText(classe + " ID " + id + " atualizado!");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    // --- D: DELETE ---
    private void deletarDados() {
        String classe = (String)this.seletorClasses.getSelectedItem();
        String idStr = JOptionPane.showInputDialog("Digite o ID para deletar:");
        if(idStr == null) return;

        try {
            int id = Integer.parseInt(idStr);
            if (classe.equals("Leo")) repoLeo.delete(id);
            else if (classe.equals("Show")) repoShow.delete(id);
            else if (classe.equals("Mundo")) repoMundo.delete(id);

            statusLabel.setText(classe + " ID " + id + " deletado!");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    public static void main(String[] args) {
        Database.inicializar();
        SwingUtilities.invokeLater(() -> new Main());
    }
}