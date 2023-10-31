package DataAcessObject;
import Conexao.Conexao;
import Entidades.Computador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputadorDAO {
        Integer idComputador = 0;
        Computador computador = new Computador();
        public static boolean cadastrarComputador (Computador computador){
            String sql = "INSERT INTO tbcomputador (SistemaOperacional, processador, discoTotal, memoriaTotal, qtdDiscos) " +
                                                "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = null;
            try{
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, computador.getSO());
                ps.setString(2, computador.getProcessador());
                ps.setLong(3, computador.getDiscoTotal());
                ps.setLong(4, computador.getMemoriaTot());
                ps.setInt(5, computador.getQtdDiscos());
                ps.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return false;
        }

        public static String pegarIdComputador (Computador computador){
            String sql = "SELECT idComputador FROM tbcomputador";
            PreparedStatement ps = null;
            ResultSet rs = null; // ResultSet é uma classe utilizada para poder realizar os selects
            try{
                ps = Conexao.getConexao().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) { // o  next é para ele mover para a prox. linha
                   computador.setId(rs.getInt(1));
                }
                ps.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return sql;
        }
}

