import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MundoRepository {

    public void create(String nome, String nuvens, String estacoes) {
        String sql = "INSERT INTO Mundo (nome, nuvens, estacoes) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, nuvens);
            pstmt.setString(3, estacoes);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Mundo> readAll() {
        List<Mundo> lista = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Mundo")) {
            while (rs.next()) {
                lista.add(new Mundo(rs.getInt("id"), rs.getString("nome"), rs.getString("nuvens"), rs.getString("estacoes")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public void update(int id, String nome, String nuvens, String estacoes) {
        String sql = "UPDATE Mundo SET nome=?, nuvens=?, estacoes=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, nuvens);
            pstmt.setString(3, estacoes);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Mundo WHERE id=?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}