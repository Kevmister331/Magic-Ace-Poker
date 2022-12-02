package ui;


import model.Event;
import model.EventLog;
import model.Game;
import model.Player;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;
import ui.tools.Button;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Note: Parts of this code were taken from the course material
//       this term and other official java documentation.

// Poker chip counter application using a GUI
public class PokerGameGui extends JPanel {

    private Button addPlayer;
    private Button removePlayer;
    private Button makeBet;
    private Button claimPot;
    private Button load;
    private Button exit;
    private Button newGame;
    private Button loadGame;

    private JFrame frame;
    private JPanel panel;
    private JList list;
    private DefaultListModel listModel;
    private JLabel potBalance;


    private JTextField addPlayerName;
    private JTextField addPlayerBalance;
    private JTextField makeBetAmount;

    private Game game;

    // JSON
    private static final String JSON_STORE = "./data/pokergame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Jackpot Image
    private ImageIcon img;
    private JLabel jackpot;


    //EFFECTS: main method to run the application
    public static void main(String[] args) {
        new PokerGameGui();
    }

    //EFFECTS: constructor for PokerGameGui class
    public PokerGameGui() {
        initializeGame();
        initializeGraphics();
        generateButtons();
        initializePlayers();
    }


    //EFFECTS: initializes the game and corresponding JSON files
    private void initializeGame() {
        game = new Game();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: create and set up the GUI window
    private void initializeGraphics() {
        frame = new JFrame("Poker Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        frame.add(panel);

        Image image = null;

        try {
            image = ImageIO.read(new File("./data/jackpot.png"));
            image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        img = new ImageIcon(image);
        jackpot = new JLabel(img);


        //displays the window
        frame.pack();
        frame.setSize(475, 400);
    }

    //EFFECTS: constructs buttons, text fields, and labels
    private void generateButtons() {

        addPlayer = new AddPlayer(this, panel);
        addPlayerName = new JTextField(10);
        addPlayerBalance = new JTextField(10);
        makeBetAmount = new JTextField(10);
        potBalance = new JLabel("Pot Balance: " + game.getPotBalance());

        panel.add(addPlayerName);
        panel.add(addPlayerBalance);

        removePlayer = new RemovePlayer(this, panel);
        makeBet = new MakeBet(this, panel);
        panel.add(makeBetAmount);
        claimPot = new ClaimPot(this, panel);
        panel.add(potBalance);

        load = new Load(this, panel);
        exit = new Exit(this, panel);
    }

    //EFFECTS: constructs the list of players in a JList
    private void initializePlayers() {
        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);

        JScrollPane listScrollPane = new JScrollPane(list);

        panel.add(listScrollPane);
        listScrollPane.setPreferredSize(new Dimension(200, 100));
        frame.setVisible(true);
    }

    //REQUIRES: name to be a String, and text to be an int
    //MODIFIES: this
    //EFFECTS: adds a player into the game, displaying it in the JList
    public void doAddPlayer() {
        String name = addPlayerName.getText();
        String text = addPlayerBalance.getText();
        int balance = Integer.parseInt(text);

        Player p = new Player(name, balance);
        game.addPlayer(p.getPlayerName(), p.getBalance());

        // Might need to implement to make sure the balance is int

        if (name.equals("") || alreadyInList(name)) {
            Toolkit.getDefaultToolkit().beep();
            addPlayerName.requestFocusInWindow();
            addPlayerName.selectAll();
            return;
        }

        listModel.addElement(addPlayerName.getText() + " --- " + addPlayerBalance.getText());

        //Reset the text field.
        addPlayerName.requestFocusInWindow();
        addPlayerName.setText("");
        addPlayerBalance.requestFocusInWindow();
        addPlayerBalance.setText("");
    }

    //REQUIRES: name to be a String, and text to be an int
    //MODIFIES: this
    //EFFECTS: removes a player from the game, displaying it in the JList
    public void doRemovePlayer() {
        int index = list.getSelectedIndex();
        listModel.remove(index);
        game.removePlayerByIndex(index);

        int size = listModel.getSize();

        if (size == 0) {
            removePlayer.setEnabled(false);

        } else {
            if (index == listModel.getSize()) {

                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }


    //REQUIRES: a player to be selected
    //MODIFIES: this
    //EFFECTS: subtracts player balance and adds it to the pot balance
    public void doMakeBet() {
        panel.remove(jackpot);
        panel.repaint();

        int index = list.getSelectedIndex();
        Player player = game.getPlayerByIndex(index);

        String text = makeBetAmount.getText();
        int balance = Integer.parseInt(text);

        game.makeBet(player, balance);

        int pot = game.getPotBalance();
        String potBal = Integer.toString(pot);
        potBalance.setText("Pot Balance: " + potBal);

        listModel.set(index, player.getPlayerName() + " --- " + player.getBalance());
    }

    //REQUIRES: a player to be selected
    //MODIFIES: this
    //EFFECTS: pot balance is deposited into player balance, pot balance resets to 0
    public void doClaimPot() {
        int index = list.getSelectedIndex();
        Player player = game.getPlayerByIndex(index);

        game.claimPot(player);

        potBalance.setText("Pot Balance: 0");
        listModel.set(index, player.getPlayerName() + " --- " + player.getBalance());

        panel.add(jackpot);
        panel.repaint();
    }

    //EFFECTS: loads the data from JSON file
    public void doLoadGame() {
        try {
            game = jsonReader.read();
            for (Player p : game.getPlayers()) {
                listModel.addElement(p.getPlayerName() + " --- " + p.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        potBalance.setText("Pot Balance: " + game.getPotBalance());

    }

    //EFFECTS: exits and saves the data to JSON file
    public void doExitGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE);
            printLog();
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        System.exit(1);
    }

    //EFFECTS: prints log
    private void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
    }

    //EFFECTS: returns if a string is already in the listModel
    protected boolean alreadyInList(String name) {
        return listModel.contains(name);
    }

}


