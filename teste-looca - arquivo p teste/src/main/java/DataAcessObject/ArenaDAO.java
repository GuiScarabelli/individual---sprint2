package DataAcessObject;

import Conexao.Conexao;
import Entidades.Arena;
import Entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArenaDAO {
    public static List<String> pegarArenasDaEmpresa(Usuario usuario) {
        List<String> arenasEmpresa = new ArrayList<>();
        String sql = "SELECT nomeArena FROM tbArena WHERE fkEmpresa = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, usuario.getFkempresa()); // Configurar parâmetro após a criação do PreparedStatement
            rs = ps.executeQuery();

            while (rs.next()) {
                String nomeArena = rs.getString("nomeArena");
                arenasEmpresa.add(nomeArena);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arenasEmpresa;
    }


}
