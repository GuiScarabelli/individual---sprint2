import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ProgramScanner {

    private String absoluteFilePath = null;

    public void scanForBlacklistedApps() throws ProgramProibidoEncontradoException {
        File rootDirectory = new File("C:\\");

        // Lista de programas proibidos (pastas)
        List<String> folderBlacklist = Arrays.asList("HxD", "The Cheat", "CoSMOS", "WeMod", "Squalr", "TestSign", "KDMapper", "Windows API", "ArtMoney", "Cheat Engine");

        // Lista de programas proibidos (arquivos)
        List<String> fileBlacklist = Arrays.asList("Squalr.exe", "ArtMoney.exe", "Cheat Engine.exe", "HxD.exe", "CoSMOS.exe", "WeMod.exe");

        findBlacklistedAppsInDirectory(rootDirectory, folderBlacklist, fileBlacklist);
    }

    public void findBlacklistedAppsInDirectory(File directory, List<String> folderBlacklist, List<String> fileBlacklist) throws ProgramProibidoEncontradoException {
        if (directory.exists() && directory.isDirectory()) {
            // Lista de arquivos e pastas no diretório
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && folderBlacklist.contains(file.getName())) {
                        // Se a pasta estiver na lista de programas proibidos, armazene o caminho absoluto
                        absoluteFilePath = file.getAbsolutePath();
                        throw new ProgramProibidoEncontradoException("Pasta proibida encontrada: " + absoluteFilePath);
                    }
                    if (file.isFile() && fileBlacklist.contains(file.getName())) {
                        // Se o arquivo estiver na lista de programas proibidos, armazene o caminho absoluto
                        absoluteFilePath = file.getAbsolutePath();
                        throw new ProgramProibidoEncontradoException("Arquivo proibido encontrado: " + absoluteFilePath);
                    }
                }
                for (File subDirectory : files) {
                    if (subDirectory.isDirectory()) {
                        // Verifica subdiretórios de forma recursiva
                        findBlacklistedAppsInDirectory(subDirectory, folderBlacklist, fileBlacklist);
                    }
                }
            }
        }
    }

    // Método para obter o caminho absoluto do arquivo
    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public static class ProgramProibidoEncontradoException extends Exception {
        // Exceção personalizada para indicar que um programa proibido foi encontrado
        public ProgramProibidoEncontradoException(String message) {
            super(message);
        }
    }
}
