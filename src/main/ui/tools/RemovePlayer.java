package ui.tools;

import ui.PokerGameGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePlayer extends Button {

    public RemovePlayer(PokerGameGui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Remove Player");
    }

    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new RemovePlayer.ButtonClickHandler());
    }

    private class ButtonClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the PlayDrawing Tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.doRemovePlayer();

        }
    }
}
