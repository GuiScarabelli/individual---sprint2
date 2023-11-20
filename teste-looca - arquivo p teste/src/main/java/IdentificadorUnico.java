import DataAcessObject.ComputadorDAO;
import Entidades.Computador;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class IdentificadorUnico {
    public static String GerarId(){
        try {
            // Obtém o endereço MAC
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(localHost);

            byte[] macAddressBytes = network.getHardwareAddress();
            StringBuilder macAddress = new StringBuilder();
            for (int i = 0; i < macAddressBytes.length; i++) {
                macAddress.append(String.format("%02X%s", macAddressBytes[i], (i < macAddressBytes.length - 1) ? "-" : ""));
            }

            // Obtém o nome do usuário
            String userName = System.getProperty("user.name");

            // Combina as informações para criar um identificador único
            String uniqueIdentifier = macAddress.toString() + userName;
            return uniqueIdentifier;
            // Imprime o identificador único
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
