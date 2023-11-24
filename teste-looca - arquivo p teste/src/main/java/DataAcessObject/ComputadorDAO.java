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
            String sql = "INSERT INTO tbComputador VALUES (?, ?, ?, ?, ?, ?, ?, (select idArena from tbArena where nomeArena = ?))";
            PreparedStatement ps = null;

            PreparedStatement psSQLServer = null;
            try{
//                ps = Conexao.getConexao().prepareStatement(sql);
//                ps.setString(1,idUnico);
//                ps.setString(2, nomePC);
//                ps.setString(3, computador.getSO());
//                ps.setString(4, computador.getProcessador());
//                ps.setLong(5, computador.getDiscoTotal());
//                ps.setLong(6, computador.getMemoriaTot());
//                ps.setInt(7, computador.getQtdDiscos());
//                ps.setString(8,arena);
//                ps.execute();

                psSQLServer = Conexao.getConexaoSQLServer().prepareStatement(sql);
                psSQLServer.setString(1,idUnico);
                psSQLServer.setString(2, nomePC);
                psSQLServer.setString(3, computador.getSO());
                psSQLServer.setString(4, computador.getProcessador());
                psSQLServer.setLong(5, computador.getDiscoTotal());
                psSQLServer.setLong(6, computador.getMemoriaTot());
                psSQLServer.setInt(7, computador.getQtdDiscos());
                psSQLServer.setString(8,arena);
                psSQLServer.execute();
                System.out.println("Computador cadastrado com sucesso");
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return false;
        }

        public static String pegarIdComputador (Computador computador){
            String sql = "SELECT idComputador FROM tbComputador";
            PreparedStatement ps = null;
            ResultSet rs = null;

            PreparedStatement psSQLServer = null;
            ResultSet rsSQLServer = null; // ResultSet é uma classe utilizada para poder realizar os selects
            try{
//                ps = Conexao.getConexao().prepareStatement(sql);
//                rs = ps.executeQuery();
//                while(rs.next()) { // o  next é para ele mover para a prox. linha
//                   computador.setId(rs.getString(1));
//                }
//                ps.execute();

                psSQLServer = Conexao.getConexaoSQLServer().prepareStatement(sql);
                rsSQLServer = psSQLServer.executeQuery();
                while(rsSQLServer.next()) { // o  next é para ele mover para a prox. linha
                    computador.setId(rsSQLServer.getString(1));
                }
                psSQLServer.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return sql;
        }

    public static Boolean JaExiste(String idUnico) {
        String sql = "SELECT COUNT(*) FROM tbComputador WHERE idComputador = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        PreparedStatement psSQLServer = null;
        ResultSet rsSQLServer = null;

        try {
//            ps = Conexao.getConexao().prepareStatement(sql);
//            ps.setString(1, idUnico);
//
//            rs = ps.executeQuery();
//
//            // Verificar se há algum resultado
//            if (rs.next()) {
//                int count = rs.getInt(1);
//                if (count > 0){
//                    return true;
//                }
//                else{
//                    return false;
//                }
//            }

            psSQLServer = Conexao.getConexaoSQLServer().prepareStatement(sql);
            psSQLServer.setString(1, idUnico);

            rsSQLServer = psSQLServer.executeQuery();

            // Verificar se há algum resultado
            if (rsSQLServer.next()) {
                int count = rsSQLServer.getInt(1);
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
                if (rsSQLServer != null) {
                    rsSQLServer.close();
                }
                if (psSQLServer != null) {
                    psSQLServer.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}

