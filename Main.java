import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Exemple d'utilisation
        Lotterie lottery = new Lotterie();

        // Vendre un billet
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        String serialNumber = "ABC123XYZ456";
        Ticket ticket = new Ticket(numbers, serialNumber);
        lottery.sellTicket(ticket);

        // Charger les billets depuis le disque
        lottery.loadTicketsFromDisk();
    }
}
