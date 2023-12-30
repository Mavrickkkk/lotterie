import java.io.*;
import java.util.*;
class Lotterie {
    private List<Ticket> tickets;

    public Lotterie() {
        tickets = new ArrayList<>();
    }

    // Méthode pour vendre un billet
    public void sellTicket(Ticket ticket) {
        tickets.add(ticket);
        saveTicketsToDisk(); // Enregistrer les billets sur le disque
    }

    // Méthode pour enregistrer les billets sur le disque
    private void saveTicketsToDisk() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tickets.dat"))) {
            oos.writeObject(tickets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour charger les billets depuis le disque
    public void loadTicketsFromDisk() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tickets.dat"))) {
            tickets = (List<Ticket>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Ajoutez d'autres méthodes selon les besoins, par exemple, la sélection aléatoire des numéros gagnants.
}
