package ui.tools;


import ui.PokerGameGui;

import javax.swing.*;
import java.awt.*;

// Abstract button class for the button functionalities
// The design structure of this class can be sourced to the SimpleDrawingPlayer
public abstract class Button {

    protected JButton button;
    protected PokerGameGui gui;

    // MODIFIES: this
    // EFFECTS: constructs a button with parent in the GUI
    public Button(PokerGameGui gui, JComponent parent) {
        this.gui = gui;
        createButton(parent);
        addToParent(parent);
        addListener(parent);
    }

    // EFFECTS: creates the button to the parent component
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: adds a listener for this button
    protected abstract void addListener(JComponent parent);


    // EFFECTS: enable/disables a button
    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }

    //
    public void removeButton(JComponent parent) {

    }
}

