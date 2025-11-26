import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/projeto_java";
    private static final String USER = "root";
    private static final String PASS = "1234"; // Sua senha aqui

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MySQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void inicializar() {
        // Agora tudo é VARCHAR (Texto) exceto o ID
        String sqlLeo = "CREATE TABLE IF NOT EXISTS Leo ("
                + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + " nome VARCHAR(100) NOT NULL,"
                + " altura VARCHAR(100),"      // Mudou para VARCHAR
                + " aniversario VARCHAR(100)"  // Mudou para VARCHAR
                + ");";

        // Usando crase no `Show` para evitar erro de sintaxe
        String sqlShow = "CREATE TABLE IF NOT EXISTS `Show` ("
                + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + " cantor VARCHAR(100) NOT NULL,"
                + " local VARCHAR(100),"
                + " publico VARCHAR(100)"     // Mudou para VARCHAR
                + ");";

        String sqlMundo = "CREATE TABLE IF NOT EXISTS Mundo ("
                + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + " nome VARCHAR(100) NOT NULL,"
                + " nuvens VARCHAR(100),"    // Mudou para VARCHAR
                + " estacoes VARCHAR(100)"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlLeo);
            stmt.execute(sqlShow);
            stmt.execute(sqlMundo);

            System.out.println("Tabelas atualizadas (tudo String) verificadas!");

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco:");
            e.printStackTrace();
        }
    }
}