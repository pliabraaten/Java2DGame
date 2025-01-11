package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KEYBOARD INPUTS
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;  // Pressed conditions

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // When keys are pressed, the Pressed condition is True
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();  // Returns associated KeyCode from numbers pressed

        // WASD Key Commands
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    // If key is released, then Pressed condition is false
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();  // Returns associated KeyCode from numbers pressed

        // WASD Key Commands
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
