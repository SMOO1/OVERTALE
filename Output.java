// Code Done all by Harrell the Horrid

import javax.swing.*;
import java.awt.*;


public class Output 
{
    private JPanel pane;
    private GridBagConstraints printer;
    Dimension fixedSize = new Dimension(20, 20);

    Output(JPanel pane)
    {
        this.pane = pane;
        printer = new GridBagConstraints();
        printer.fill = GridBagConstraints.BOTH;
        printer.weightx = 1;
        printer.weighty = 1;
        //printer.insets = new Insets(1,1,1,1);
    }

    public void printScreen(char[][] gameMap)
    {
        for(Component comp: pane.getComponents())
        {
            pane.remove(comp);
        }
        for(int y = 0; y < gameMap.length; y++){
            for(int x = 0; x < gameMap[y].length;x++){
                JPanel text;
                switch(gameMap[y][x]){
                    case '*':
                        text = new WallImage();
                        break;
                    case 'f':
                        text = new FloorImage();
                        break;
                    case 'P':
                        text = new PlayerImage();
                        break;
                    case 'H':
                        text = new HatchImage();
                        break;
                    case 'B':
                        text = new BossImage();
                        break;
                    default:
                        text = new Error();
                }
                text.setPreferredSize(fixedSize);
                text.setMinimumSize(fixedSize);
                text.setMaximumSize(fixedSize);
                printer.gridx = x;
                printer.gridy = y;

                pane.add(text,printer);
            }
        }
        pane.revalidate();
        pane.repaint();
    }

}
