// Code Done all by Harrell the Horrid

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorldScreen extends JPanel
{
    OverPlayer player;
    Camera camera;
    JFrame screen;
    Map OverWorld;

    
    WorldScreen(JFrame screen)
    {
        this.screen = screen;
        this.setLayout(new GridBagLayout());
        
        OverWorld = new Map(40, 70);
        OverWorld.createRoom();
        OverWorld.tunnel();
        OverWorld.createStuff();

        screen.add(this, BorderLayout.CENTER);

        player = OverWorld.getPlayer(screen);

        camera = new Camera(OverWorld, player, new Output(this));
        keyMap(this, player, camera);
        camera.print();
        Math2D.print(OverWorld.getMap());
    }

    private static void keyMap(JPanel pane, OverPlayer player, Camera camera)
    {
        InputMap input = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap action = pane.getActionMap();

        KeyStroke wKey =  KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke aKey =  KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        KeyStroke sKey =  KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        KeyStroke dKey =  KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);

        input.put(wKey, "MoveN");
        input.put(aKey, "MoveW");
        input.put(sKey, "MoveS");
        input.put(dKey, "MoveE");

        action.put("MoveN", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.moveN();
                camera.print();
            }
        });
        action.put("MoveE", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.moveE();
                camera.print();
            }
        });
        action.put("MoveS", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.moveS();
                camera.print();
            }
        });
        action.put("MoveW", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.moveW();
                camera.print();
            }
        });
    }
}
