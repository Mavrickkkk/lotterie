import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class LotteryGUI extends JFrame {
    private Lottery lottery;

    public LotteryGUI() {
        super("Lottery Application");
        lottery = new Lottery();

        // Créer des composants graphiques
        JButton sellTicketButton = new JButton("Vendre un billet");
        JButton drawLotteryButton = new JButton("Lancer la loterie");

        // Ajouter des écouteurs d'événements
        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellTicket();
            }
        });

        drawLotteryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLottery();
            }
        });

        // Créer le panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.add(sellTicketButton);
        mainPanel.add(drawLotteryButton);

        // Configurer la fenêtre principale
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    private void sellTicket() {
        // Ici, vous pouvez ajouter la logique pour vendre un billet
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        String serialNumber = "ABC123XYZ456";
        Ticket ticket = new Ticket(numbers, serialNumber);
        lottery.sellTicket(ticket);
        JOptionPane.showMessageDialog(this, "Billet vendu avec succès!");
    }

    private void drawLottery() {
        // Ici, vous pouvez ajouter la logique pour lancer la loterie
        // (par exemple, choisir aléatoirement les numéros gagnants)
        // et annoncer les résultats à l'utilisateur
        JOptionPane.showMessageDialog(this, "Loterie lancée!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LotteryGUI();
            }
        });
    }
}
