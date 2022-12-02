package ui.tools;

import ui.PokerGameGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class represents the "Add Player" button to add a player
// The design structure of this class can be sourced to the SimpleDrawingPlayer

public class AddPlayer extends Button {

    //EFFECTS: constructs the button
    public AddPlayer(PokerGameGui gui, JComponent parent) {
        super(gui, parent);
    }

    //MODIFIES: this
    //EFFECTS: creates new button and adds it to the parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Player");
    }

    //EFFECTS: listener for the event
    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new ButtonClickHandler());
    }

    private class ButtonClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the button tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.doAddPlayer();
        }
    }
}
