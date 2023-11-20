package DataAcessObject;

import Conexao.Conexao;
import Entidades.ArquivosPastasProibidos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArquivosPastasProibidosDAO {

    public static List<String> pegarFolderBlacklist() {

        ArquivosPastasProibidos arquivoPasta = new ArquivosPastasProibidos();
        List<String> folderBlacklist = new ArrayList<>();
        String sql = "SELECT nomePasta FROM pastasProibidas";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nomePasta = rs.getString("nomePasta");
                arquivoPasta.setNomePasta(nomePasta);
                folderBlacklist.add(nomePasta);
            }

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return folderBlacklist;
    }

    public static List<String> pegarFilesBlacklist() {
        ArquivosPastasProibidos arquivoPasta = new ArquivosPastasProibidos();
        List<String> filesBlacklist = new ArrayList<>();
        String sql = "SELECT nomeArquivo FROM arquivosProibidos";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nomeArquivo = rs.getString("nomeArquivo");
                arquivoPasta.setNomeArquivo(nomeArquivo);
                filesBlacklist.add(nomeArquivo);
            }

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filesBlacklist;
    }

//    public static Integer obterIdArquivoPorNome(String nomeArquivo) {
//        ArquivosPastasProibidos arquivoPasta = new ArquivosPastasProibidos();
//        String sql = "SELECT idArquivoProibido FROM arquivosProibidos WHERE nomeArquivo = ?";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try{
//            ps = Conexao.getConexao().prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//                Integer idArquivo = rs.getInt("idArquivoProibido");
//                arquivoPasta.setIdArquivo(idArquivo);
//            }
//
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return arquivoPasta.getIdArquivo();
//    }
//
//    public static Integer obterIdPastaPorNome(String nomePasta) {
//        ArquivosPastasProibidos arquivoPasta = new ArquivosPastasProibidos();
//        String sql = "SELECT idPastaProibida FROM pastasProibidas WHERE nomePasta = ?";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try{
//            ps = Conexao.getConexao().prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//                Integer idPasta = rs.getInt("idPastaProibido");
//                arquivoPasta.setIdPasta(idPasta);
//            }
//
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return arquivoPasta.getIdPasta();
//    }
}
