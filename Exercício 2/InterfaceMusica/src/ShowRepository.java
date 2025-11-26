import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowRepository {

    public void create(String cantor, String local, String publico) {
        String sql = "INSERT INTO `Show` (cantor, local, publico) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cantor);
            pstmt.setString(2, local);
            pstmt.setString(3, publico);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Show> readAll() {
        List<Show> lista = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `Show`")) {
            while (rs.next()) {
                lista.add(new Show(rs.getInt("id"), rs.getString("cantor"), rs.getString("local"), rs.getString("publico")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public void update(int id, String cantor, String local, String publico) {
        String sql = "UPDATE `Show` SET cantor=?, local=?, publico=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cantor);
            pstmt.setString(2, local);
            pstmt.setString(3, publico);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM `Show` WHERE id=?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}