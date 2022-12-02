package ui.tools;

import ui.PokerGameGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class represents the "Remove Player" button to remove a player
// The design structure of this class can be sourced to the SimpleDrawingPlayer

public class RemovePlayer extends Button {

    //EFFECTS: constructor for the button
    public RemovePlayer(PokerGameGui gui, JComponent parent) {
        super(gui, parent);
    }

    //MODIFIES: this
    //EFFECTS: creates a new button and adds it to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Remove Player");
    }

    //EFFECTS: listener for the event
    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new RemovePlayer.ButtonClickHandler());
    }

    private class ButtonClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the button tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.doRemovePlayer();

        }
    }
}
