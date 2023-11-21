package DataAcessObject;

import Entidades.*;
import Conexao.Conexao;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StatusPcDAO {
    Integer idCaptura = 0;
    StatusPc statusPc = new StatusPc();

    public static void exibirInformacoesMaquina(String nomeProcessador, String sistemaOperacional,
                Long memoriaTotal,Long discoTotal, Integer qtdDiscos){

            System.out.println(String.format("""
             +==============================================================================+
             ||                         Informações da máquina                             ||
             +==============================================================================+
                                                                                         
                Processador: %s                                                          
                Sistema Operacional: %s                                                  
                Memória total: %s                                                        
                Disco total: %s 
                Quantidade de Discos: %s                                                         
                                                                                         
             +==============================================================================+
                """, nomeProcessador, sistemaOperacional,
                    Conversor.formatarBytes(memoriaTotal), Conversor.formatarBytes(discoTotal), qtdDiscos));

    }
    public static String pegarIdCaptura (StatusPc statusPc){
        String sql = "SELECT idCaptura FROM status_pc";
        PreparedStatement ps = null;
        ResultSet rs = null; // ResultSet é uma classe utilizada para poder realizar os selects
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) { // o  next é para ele mover para a prox. linha
                statusPc.setIdCaptura(rs.getInt(1));
            }
            ps.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return sql;
    }
    public static void cadastrarCapturas( StatusPc statusMemoria, StatusPc statusProcessador, StatusPc Disco,
                                         StatusPc dtHora, Computador computador) {
        String sql = "INSERT INTO status_pc " +
                "(memoriaUso, processadorUso, discoDisponivel, tempProcessador, dtHoraCaptura, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setLong(1, statusMemoria.getMemoriaUso());
            ps.setDouble(2, statusProcessador.getProcessadorEmUso());
            ps.setDouble(3, Disco.getDiscoDisponivel());
            ps.setDouble(4, statusProcessador.getTempProcessador());
            ps.setString(5, dtHora.getDtHoraCaptura());
            ps.setString(6, computador.getId());
            ps.execute();

            String dataFormatadaa = dtHora.getDtHoraCaptura();
            Date dataAtual = new Date();
            // Definir o formato desejado
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            // Formatar a data
            String dataFormatada = formato.format(dataAtual);

            System.out.printf(String.format("""
                +================================================+
                ||               Dados da captura               ||
                +================================================+
                                                                                                                  
                              cpu em uso: %.2f                
                            Memórria em uso: %s               
                           Disco Disponivel: %s
                         Temperatura do Processador: %.2f               
                       data/hora da captura: %s            
                                                              
                +================================================+      
                
                """,
                    statusProcessador.getProcessadorEmUso(),
                    Conversor.formatarBytes(statusMemoria.getMemoriaUso()),
                    Conversor.formatarBytes(Disco.getDiscoDisponivel()), statusProcessador.getTempProcessador(),
                    dataFormatada));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
