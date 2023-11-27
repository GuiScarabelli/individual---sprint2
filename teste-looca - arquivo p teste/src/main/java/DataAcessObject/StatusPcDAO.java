package DataAcessObject;

import Entidades.*;
import Conexao.Conexao;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class StatusPcDAO {
    Integer idCaptura = 0;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    static PreparedStatement psSQLServer = null;
    static ResultSet rsSQLServer = null;

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
        try{
            psSQLServer = Conexao.getConexao().prepareStatement(sql);
            rsSQLServer = psSQLServer.executeQuery();
            while(rsSQLServer.next()) { // o  next é para ele mover para a prox. linha
                statusPc.setIdCaptura(rsSQLServer.getInt(1));
            }
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) { // o  next é para ele mover para a prox. linha
                statusPc.setIdCaptura(rs.getInt(1));
            }
            ps.execute();
            psSQLServer.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return sql;
    }
    public static void cadastrarCapturas(StatusPc statusMemoria, StatusPc statusProcessador, StatusPc Disco,
                                         Double cpuTemperature, StatusPc dtHora, Computador computador) {
        String sql = "INSERT INTO status_pc " +
                "(memoriaUso, processadorUso, discoDisponivel, tempProcessador, dtHoraCaptura, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            String dataParaInserir = "2023-11-26";

            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setLong(1, statusMemoria.getMemoriaUso());
            ps.setDouble(2, statusProcessador.getProcessadorEmUso());
            ps.setDouble(3, Disco.getDiscoDisponivel());
            ps.setDouble(4, statusProcessador.getTempProcessador());
            ps.setString(5, dataParaInserir);
            ps.setString(6, computador.getId());
            ps.execute();

            psSQLServer = Conexao.getConexaoSQLServer().prepareStatement(sql);
            psSQLServer.setLong(1, statusMemoria.getMemoriaUso());
            psSQLServer.setDouble(2, statusProcessador.getProcessadorEmUso());
            psSQLServer.setDouble(3, Disco.getDiscoDisponivel());
            psSQLServer.setDouble(4, statusProcessador.getTempProcessador());
            psSQLServer.setString(5, dataParaInserir);
            psSQLServer.setString(6, computador.getId());
            psSQLServer.execute();

            String dataFormatadaa = String.valueOf(dtHora.getDtHoraCaptura());
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
