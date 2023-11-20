package DataAcessObject;
import Conexao.Conexao;
import Entidades.Computador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputadorDAO {
        Integer idComputador = 0;
        Computador computador = new Computador();
        public static boolean cadastrarComputador (Computador computador, String idUnico, String arena, String nomePC){
            String sql = "INSERT INTO tbcomputador VALUES (?, ?, ?, ?, ?, ?, ?, (select idArena from tbArena where nomeArena = ?))";
            PreparedStatement ps = null;
            try{
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1,idUnico);
                ps.setString(2, nomePC);
                ps.setString(3, computador.getSO());
                ps.setString(4, computador.getProcessador());
                ps.setLong(5, computador.getDiscoTotal());
                ps.setLong(6, computador.getMemoriaTot());
                ps.setInt(7, computador.getQtdDiscos());
                ps.setString(8,arena);
                ps.execute();
                System.out.println("Computador cadastrado com sucesso");
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
                   computador.setId(rs.getString(1));
                }
                ps.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return sql;
        }

    public static Boolean JaExiste(String idUnico) {
        String sql = "SELECT COUNT(*) FROM tbComputador WHERE idComputador = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, idUnico);

            rs = ps.executeQuery();

            // Verificar se há algum resultado
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Certifique-se de fechar os recursos (PreparedStatement e ResultSet)
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}

