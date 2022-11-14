package ui.tools;


import ui.PokerGameGui;

import javax.swing.*;
import java.awt.*;


public abstract class Button {

    protected JButton button;
    protected PokerGameGui gui;

    public Button(PokerGameGui gui, JComponent parent) {
        this.gui = gui;
        createButton(parent);
        addToParent(parent);
        addListener(parent);
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    protected abstract void addListener(JComponent parent);


    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }
}
