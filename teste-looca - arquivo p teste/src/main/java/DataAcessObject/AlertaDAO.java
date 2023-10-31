package DataAcessObject;

import Conexao.Conexao;
import Entidades.Alerta;
import Entidades.Computador;
import Entidades.StatusPc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlertaDAO {

    public static boolean cadastrarAlerta (Alerta alerta, Computador computador){
        String sql = "INSERT INTO Alerta (descricao, dtHoraAlerta, caminhoArquivo, fkComputador) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, alerta.getDescricao());
            ps.setString(2, alerta.getDtHoraAlerta());
            ps.setString(3, alerta.getCaminhoAqrquivo());
            ps.setLong(4, computador.getId());
            ps.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    }
}
