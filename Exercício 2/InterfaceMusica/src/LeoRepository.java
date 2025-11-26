import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeoRepository {

    // C - CREATE
    public void create(String nome, String altura, String aniversario) {
        String sql = "INSERT INTO Leo (nome, altura, aniversario) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, altura);
            pstmt.setString(3, aniversario);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // R - READ
    public List<Leo> readAll() {
        List<Leo> lista = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Leo")) {
            while (rs.next()) {
                lista.add(new Leo(rs.getInt("id"), rs.getString("nome"), rs.getString("altura"), rs.getString("aniversario")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // U - UPDATE (NOVO)
    public void update(int id, String nome, String altura, String aniversario) {
        String sql = "UPDATE Leo SET nome=?, altura=?, aniversario=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, altura);
            pstmt.setString(3, aniversario);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // D - DELETE (NOVO)
    public void delete(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Leo WHERE id=?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}