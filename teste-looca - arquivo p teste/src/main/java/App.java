import DataAcessObject.*;
import Entidades.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.CentralProcessor;
import oshi.hardware.Sensors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDateTime;
import java.util.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Fazer o login do usuário tambem. //// juhrs vai fazer
        System.out.println("""
                +===========================================================================================+ 
                ||                   ___   ____ __  ___        _   __ ____ _  __ ___   ____   __           ||
                ||                  / _ ) / __//  |/  / ____  | | / //  _// |/ // _ \\ / __ \\ / /           ||
                ||                 / _  |/ _/ / /|_/ / /___/  | |/ /_/ / /    // // // /_/ //_/\s           ||
                ||                /____//___//_/  /_/         |___//___//_/|_//____/ \\____/(_) \s           ||  
                ||                                                                             \s           ||            
                ||                                   ___   ____\s                                           ||    
                ||                                  / _ | / __ \\                                           ||                                                    
                ||                                 / __ |/ /_/ /                                           ||      
                ||                                /_/ |_|\\____/\s                                           ||    
                ||                                             \s                                           ||  
                ||                                                                                         ||            
                ||                   ____ ____ _  __ ______ ____ _  __ ____ __\s                            ||
                ||                  / __// __// |/ //_  __//  _// |/ // __// /\s                            ||
                ||                 _\\ \\ / _/ /    /  / /  _/ / /    // _/ / /__                            ||     
                ||                /___//___//_/|_/  /_/  /___//_/|_//___//____/                            ||
                ||                                                            \s                            ||  
                ||                                                                                         ||
                +==========================================================================================+
                """);

        // objetos que foram criados na mão
        Looca looca = new Looca();
        Computador computador = new Computador();
        StatusPc idCaptura = new StatusPc();
        StatusPc memoriaUso = new StatusPc();
        StatusPc processadorUso = new StatusPc();
        StatusPc discoDisponivel = new StatusPc();
        StatusPc dtHoraCaptura = new StatusPc();
        Usuario usuario = new Usuario();
        Scanner entrada = new Scanner(System.in);
        Alerta alerta = new Alerta();
        boolean autenticado = false;

        // Objetos do looca
        Memoria memoria = looca.getMemoria();
        Processador processador = looca.getProcessador();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Sistema sistema = looca.getSistema();


        // Variáveis que guardam as informações para o cadastro
        String nomeProcessador = processador.getNome();
        computador.setProcessador(nomeProcessador);

        String sistemaOperacional = sistema.getSistemaOperacional();
        computador.setSO(sistemaOperacional);


        Long memoriaTotal = (memoria.getTotal());
        computador.setMemoriaTot(memoriaTotal);

        Long discoTotal = (disco.getTamanhoTotal());
        computador.setDiscoTotal((discoTotal));

        Integer qtdDicos = disco.getVolumes().size();
        computador.setQtdDiscos(qtdDicos);

        do {
            System.out.println("Digite o usuario: ");
            String emailLogin = entrada.nextLine();

            System.out.println("Digite a senha: ");
            String senhaLogin = entrada.nextLine();

            UsuarioDAO.pegarUsuario(usuario);
            if (!Objects.equals(emailLogin, usuario.getEmail()) || !Objects.equals(senhaLogin, usuario.getSenha())) {
                System.out.println("usuario ou senha inválidos!");
            } else {
                System.out.println("Usuário encontrado!");
                System.out.println("""
                    +=============================================+
                    ||                                           ||   
                    ||    1- Especificações do computador        ||
                    ||                                           ||
                    ||          2- Iniciar capturas              ||
                    ||                                           ||
                    +=============================================+ 
                    """);
                Integer escolha = entrada.nextInt();

                switch (escolha){
                    case 1:
                        do {
                            StatusPcDAO.exibirInformacoesMaquina(nomeProcessador, sistemaOperacional, memoriaTotal, discoTotal,qtdDicos);
                            System.out.println("""
                            +=============================================+
                            ||                                           ||   
                            ||    1- Especificações do computador        ||
                            ||                                           ||
                            ||          2- Iniciar capturas              ||
                            ||                                           ||
                            +=============================================+ 
                            """);
                            escolha = entrada.nextInt();
                            break;

                        } while (escolha == 1);

                        break;
                    case 2:
                        UsuarioDAO.pegarEmpresaUsuario(usuario);
                        ArenaDAO.pegarArenasDaEmpresa(usuario);
                        if (ArenaDAO.pegarArenasDaEmpresa(usuario).isEmpty()){
                            System.out.println("Você ainda não tem uma arena cadastrada, acesse nosso site para fazer isso");
                            return;
                        }
                        else {
                            String idPC = IdentificadorUnico.GerarId();
                            if (!ComputadorDAO.JaExiste(idPC)){
                                System.out.println();
                                System.out.println("Parece que essa é a primeira vez que você utiliza o Sentinel nesse PC");
                                System.out.println("Em qual arena você deseja cadastrar esse computador?");
                                System.out.println();
                                for (int i = 0; i < ArenaDAO.pegarArenasDaEmpresa(usuario).size(); i++) {
                                    if (i == ArenaDAO.pegarArenasDaEmpresa(usuario).size()-1){
                                        System.out.println("""
                                +----------------------------------------------------
                                | %d - %s
                                +----------------------------------------------------"""
                                                .formatted(i + 1, ArenaDAO.pegarArenasDaEmpresa(usuario).get(i)));
                                    }
                                    else {
                                        System.out.println("""
                                +----------------------------------------------------
                                | %d - %s""".formatted(i + 1, ArenaDAO.pegarArenasDaEmpresa(usuario).get(i)));
                                    }
                                }
                                Integer numArena = entrada.nextInt();
                                String nomeArena = ArenaDAO.pegarArenasDaEmpresa(usuario).get(numArena - 1);

                                System.out.println("Certo, agora dê um apelido para esse computador");
                                entrada.nextLine();
                                String nomePC = entrada.nextLine();
                                ComputadorDAO.cadastrarComputador(computador, idPC, nomeArena, nomePC);
                            }
                        }
                        break;

                }


                UsuarioDAO.pegarEmpresaUsuario(usuario);
                ArenaDAO.pegarArenasDaEmpresa(usuario);
                if (ArenaDAO.pegarArenasDaEmpresa(usuario).isEmpty()){
                    System.out.println("Você ainda não tem uma arena cadastrada, acesse nosso site para fazer isso");
                    return;
                }
                else {
                    String idPC = IdentificadorUnico.GerarId();
                    if (!ComputadorDAO.JaExiste(idPC)){
                        System.out.println();
                        System.out.println("Parece que essa é a primeira vez que você utiliza o Sentinel nesse PC");
                        System.out.println("Em qual arena você deseja cadastrar esse computador?");
                        System.out.println();
                        for (int i = 0; i < ArenaDAO.pegarArenasDaEmpresa(usuario).size(); i++) {
                            if (i == ArenaDAO.pegarArenasDaEmpresa(usuario).size()-1){
                                System.out.println("""
                                +----------------------------------------------------
                                | %d - %s
                                +----------------------------------------------------"""
                                        .formatted(i + 1, ArenaDAO.pegarArenasDaEmpresa(usuario).get(i)));
                            }
                            else {
                                System.out.println("""
                                +----------------------------------------------------
                                | %d - %s""".formatted(i + 1, ArenaDAO.pegarArenasDaEmpresa(usuario).get(i)));
                            }
                        }
                        Integer numArena = entrada.nextInt();
                        String nomeArena = ArenaDAO.pegarArenasDaEmpresa(usuario).get(numArena - 1);

                        System.out.println("Certo, agora dê um apelido para esse computador");
                        entrada.nextLine();
                        String nomePC = entrada.nextLine();
                        ComputadorDAO.cadastrarComputador(computador, idPC, nomeArena, nomePC);
                    }
                }

                computador.gerarTextoInicio();
                ComputadorDAO.pegarIdComputador(computador);
                StatusPcDAO.pegarIdCaptura(idCaptura);
                StatusPcDAO.exibirInformacoesMaquina(nomeProcessador, sistemaOperacional, memoriaTotal, discoTotal, qtdDicos);

                Integer pontosMontagem = disco.getVolumes().size();
                long TEMPO = (2000);
                Timer timer = new Timer();
                TimerTask tarefa = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            LocalDateTime data = LocalDateTime.now();
                            dtHoraCaptura.setDtHoraCaptura(String.valueOf(data));

                            Long memoriaEmUso = memoria.getEmUso();
                            memoriaUso.setMemoriaUso(memoriaEmUso);

                            Double processadorEmUso = processador.getUso();
                            processadorUso.setProcessadorEmUso(processadorEmUso);

                            Long discoEmUso = disco.getVolumes().get(0).getDisponivel();
                            discoDisponivel.setDiscoDisponivel(discoEmUso);

                            // Crie uma instância do SystemInfo
                            SystemInfo systemInfo = new SystemInfo();

                            // Obtenha a camada de abstração de hardware
                            HardwareAbstractionLayer hardware = systemInfo.getHardware();

                            // Obtenha as informações do sensor
                            Sensors sensors = hardware.getSensors();

                            // Obtenha a temperatura do processador
                            Double cpuTemperature = sensors.getCpuTemperature();
                            processadorUso.setTempProcessador(cpuTemperature);

                            StatusPcDAO.cadastrarCapturas(memoriaUso, processadorUso, discoDisponivel, dtHoraCaptura, computador);

                            if (disco.getVolumes().size() > pontosMontagem){
                                System.out.println("ATENÇÃO!\nDISCO DESCONHECIDO CONECTADO ");
                            } else {
                                System.out.println("QUANTIDADE DE DISCOS ESTÁ DE ACORDO :)");

                            }


                        } catch (Exception e ){
                            e.printStackTrace();
                        }
                    }
                };
                timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
                autenticado = true;

                ProgramScanner programScanner = new ProgramScanner();

                System.out.println("COMEÇOU O PROCESSO DE VERIFICAÇÃO DE ARQUIVOS E PASTAS PROIBIDOS");

                try {
                    programScanner.scanForBlacklistedApps();
                    // Se chegou aqui, nenhum programa proibido foi encontrado
                    System.out.println("A máquina está segura para uso.");
                } catch (ProgramScanner.ProgramProibidoEncontradoException e) {
                    System.out.println(e.getMessage());

                    LocalDateTime data = LocalDateTime.now();
                    alerta.setDtHoraAlerta(String.valueOf(data));
                    alerta.setCaminhoArquivo(programScanner.getAbsoluteFilePath());

                    String tipoAlerta;
                    if (e.getMessage().startsWith("Pasta proibida")) {
                        tipoAlerta = "Pasta Proibida";
                        alerta.setDescricao("Pasta proibida encontrada");
                    } else if (e.getMessage().startsWith("Arquivo proibido")) {
                        tipoAlerta = "Arquivo Proibido";
                        alerta.setDescricao("Arquivo proibido encontrado");
                    } else {
                        tipoAlerta = "Desconhecido";
                        alerta.setDescricao("Alerta desconhecido encontrado");
                    }

                    // Aqui deve ser o id do computador
                    AlertaDAO.cadastrarAlerta(alerta, computador, tipoAlerta);

                    System.out.println("CADASTROU O ALERTA DE ARQUIVO OU PASTA PROIBIDA");
                }
            }

        } while(!autenticado);

        // Caminho da raiz do disco (pode variar dependendo do sistema operacional)

    }
}
