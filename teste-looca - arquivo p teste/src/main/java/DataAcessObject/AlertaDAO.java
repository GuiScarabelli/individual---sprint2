package DataAcessObject;

import Conexao.Conexao;
import Entidades.Alerta;
import Entidades.Computador;
import Entidades.StatusPc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlertaDAO {

    public static boolean cadastrarAlerta(Alerta alerta, Computador computador, String tipoAlerta) {
        String sql = "INSERT INTO Alerta (descricao, dtHoraAlerta, caminhoArquivo, tipoAlerta, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, alerta.getDescricao());
            ps.setString(2, alerta.getDtHoraAlerta());
            ps.setString(3, alerta.getCaminhoArquivo());
            ps.setString(4, tipoAlerta);  // Tipo de alerta (Pasta ou Arquivo)
            ps.setString(5, computador.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna verdadeiro se pelo menos uma linha for afetada
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Em caso de falha
    }
}
