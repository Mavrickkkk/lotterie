import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LotterieGUI extends JFrame {
    private Lotterie lotterie;
    private List<Integer> selectedNumbers;
    private JTextArea ticketsTextArea;
    private JLabel moneyLabel;
    private int money=15;
    private int ticketnbr=20;

    public LotterieGUI() {
        super("loterie");
        lotterie = new Lotterie();
        selectedNumbers = new ArrayList<>();

        moneyLabel = new JLabel(String.valueOf(money));
        Font futura = loadCustomFont("futura.ttf");


        JButton sellTicketButton = new JButton("Vendre un billet");
        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellTicket();
            }
        });

        JButton lotterieButton = new JButton("Lancer la loterie");
        lotterieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLotterie();
            }
        });

        JComboBox<Integer> numberComboBox = new JComboBox<>();
        for (int i = 1; i <= ticketnbr; i++) {
            numberComboBox.addItem(i);
        }

        JButton addToTicketButton = new JButton("Acheter le ticket");
        addToTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyType2Ticket((Integer) numberComboBox.getSelectedItem());
            }
        });

        JButton buyType1TicketButton = new JButton("Acheter un ticket de type 1");
        buyType1TicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyType1Ticket();
            }
        });

        ticketsTextArea = new JTextArea(10, 30);
        ticketsTextArea.setEditable(false);

        if (futura != null) {
            moneyLabel.setFont(futura);
            sellTicketButton.setFont(futura);
            numberComboBox.setFont(futura);
            addToTicketButton.setFont(futura);
            ticketsTextArea.setFont(futura);
            lotterieButton.setFont(futura);
        }

        JPanel mainPanel = new JPanel();
        mainPanel.add(moneyLabel);
        mainPanel.add(numberComboBox);
        mainPanel.add(addToTicketButton);
        mainPanel.add(buyType1TicketButton);
        mainPanel.add(new JScrollPane(ticketsTextArea));
        mainPanel.add(sellTicketButton);
        mainPanel.add(lotterieButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1080, 720);
        this.setContentPane(mainPanel);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);
    }

    private void buyType1Ticket() {
        if (money >= 1) {
            if (selectedNumbers.size() < ticketnbr) {
                int randomNum;
                do {
                    randomNum = (int) (Math.random() * ticketnbr) + 1;
                } while (selectedNumbers.contains(randomNum));

                selectedNumbers.add(randomNum);

                money -= 1;

                updateTicketsTextArea();
                updateMoneyLabel();
            } else {
                JOptionPane.showMessageDialog(this, "Vous avez achete tous les tickets restants !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas assez d'argent pour acheter un ticket de type 1.");
        }
    }
    private void buyType2Ticket(int number) {
        if (money >= 2) {
            if (!selectedNumbers.contains(number)) {
                selectedNumbers.add(number);

                money-=2;

                updateTicketsTextArea();
                updateMoneyLabel();
            } else {
                JOptionPane.showMessageDialog(this, "Vous avez deja ce ticket");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas assez d'argent pour acheter un ticket de type 2.");
        }
    }

    private void updateTicketsTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("Billets choisis : \n");
        for (int i = 0; i < selectedNumbers.size(); i++) {
            sb.append("Billet ").append(i + 1).append(": ").append(selectedNumbers.get(i)).append("\n");
        }
        ticketsTextArea.setText(sb.toString());
    }

    private void updateMoneyLabel() {
        moneyLabel.setText(String.valueOf(money));
    }

    private void sellTicket() { // ici faut ajouter un système qui va compter le nombre de ticket qui sont bons dans le tickets tirés au sort et qui va ajouter l'argent en conséquence
        String serialNumber = "ABC123XYZ456";
        Ticket ticket = new Ticket(selectedNumbers, serialNumber);
        lotterie.sellTicket(ticket);
        JOptionPane.showMessageDialog(this, "Billet vendu avec succes!");

        selectedNumbers.clear();

        updateTicketsTextArea();
    }

    private void drawLotterie() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tickets.txt"))) {
            for (int i = 0; i < selectedNumbers.size(); i++) {
                List<Integer> numbers = Arrays.asList(selectedNumbers.get(i));
                String serialNumber = "L1" + i; // le L1 c'est le numéro de série
                Ticket ticket = new Ticket(numbers, serialNumber);
                outputStream.writeObject(ticket);
            }
            JOptionPane.showMessageDialog(this, "Loterie lancee! Les tickets ont ete enregistres dans le fichier tickets.txt.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement des tickets dans le fichier.");
        }
    }
    private static Font loadCustomFont(String filePath) { // t'en fais pas pour ça c'est juste pour que ça soit plus beau
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new java.io.File(filePath)).deriveFont(Font.PLAIN, 14);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
