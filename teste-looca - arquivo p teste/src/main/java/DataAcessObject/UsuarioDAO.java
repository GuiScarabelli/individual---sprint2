package DataAcessObject;
import Conexao.Conexao;
import Entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public static String pegarUsuario (Usuario usuario){
        String sql = "SELECT idUsuario, nome, sobrenome, email, senha FROM tbusuario";
        PreparedStatement ps = null;
        ResultSet rs = null; // ResultSet é uma classe utilizada para poder realizar os selects
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) { // o  next é para ele mover para a prox. linha
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setSobrenome(rs.getString(3));
                usuario.setEmail(rs.getString(4));
                usuario.setSenha(rs.getString(5));
            }
//            System.out.println(String.format("""
//                        Dados do usuário
//                        id: %d
//                        nome: %s
//                        email: %s
//                        senha: %s
//                        """, usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail(), usuario.getSenha()));
            ps.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return sql;
    }


    public static Integer pegarEmpresaUsuario(Usuario usuario) {
        String sql = "SELECT idEmpresa FROM tbEmpresa JOIN tbUsuario as a ON fkEmpresa = idEmpresa WHERE a.email = ? and a.senha = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer idEmpresa = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            rs = ps.executeQuery();

            if (rs.next()) {
                idEmpresa = rs.getInt("idEmpresa");
                usuario.setFkempresa(idEmpresa);
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
        return idEmpresa;
    }

}
