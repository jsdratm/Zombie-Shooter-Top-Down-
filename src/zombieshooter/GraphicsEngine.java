/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;

/**
 *
 * @author jsdratm
 */
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GraphicsEngine extends JComponent
{

    private static Color m_tRed = new Color(255, 0, 0, 150);

    private static Color m_tGreen = new Color(0, 255, 0, 50);

    private static Color m_tBlue = new Color(0, 0, 255, 150);

    private static Font monoFont = new Font("Monospaced", Font.BOLD
    | Font.ITALIC, 36);

    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

    private static Font serifFont = new Font("Serif", Font.BOLD, 24);

    private static ImageIcon java2sLogo = new ImageIcon("java2s.gif");

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // draw entire component black
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        // draw shooter
        g.setColor(Color.red);
        g.fillOval(Global.playerShooter.getPositionX(),
                   Global.playerShooter.getPositionY(),
                   20,
                   10);
        g.fillRect(Global.playerShooter.getPositionX() + Global.projectileStartOffsetX,
                   Global.playerShooter.getPositionY() + Global.projectileStartOffsetY,
                   2,
                   8);

        g.setColor(Color.white);
        g.fillOval(Global.playerShooter.getPositionX() + 6,
                   Global.playerShooter.getPositionY() + 1,
                   8,
                   8);

        // draw dead zombies
        for (int i = 0; i < Global.deadZombieVector.size(); i++)
        {
            g.setColor(Color.red);
            g.fillOval(((Zombie)Global.deadZombieVector.get(i)).getPositionX() - 5,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() - 5,
                       Global.zombieWidth + 10,
                       Global.zombieHeight + 5);
            
            g.setColor(Color.green);
            // head
            g.fillOval(((Zombie)Global.deadZombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY(),
                       10,
                       10);
            g.setColor(Color.red);
            // mouth
            g.drawLine(((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 2,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 7,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 7,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 7);
            // left eye
            g.drawLine(((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 3,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 3,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 3,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 3);

            // right eye
            g.drawLine(((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 6,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 3,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 6,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 3);

            g.setColor(Color.gray);
            // torso
            g.fillRect(((Zombie)Global.deadZombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 10,
                       12,
                       12);
            // left leg
            g.fillRect(((Zombie)Global.deadZombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 22,
                       2,
                       8);

            // right leg
            g.fillRect(((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 10,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 22,
                       2,
                       8);

            // left arm
            g.drawLine(((Zombie)Global.deadZombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 10,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionX() - 5,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY());

            // right arm
            g.drawLine(((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 12,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY() + 10,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionX() + 15,
                       ((Zombie)Global.deadZombieVector.get(i)).getPositionY());
        }

        // draw moving zombies
        for (int i = 0; i < Global.zombieVector.size(); i++)
        {
            g.setColor(Color.green);
            // head
            g.fillOval(((Zombie)Global.zombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.zombieVector.get(i)).getPositionY(),
                       10,
                       10);
            g.setColor(Color.red);
            // mouth
            g.drawLine(((Zombie)Global.zombieVector.get(i)).getPositionX() + 2,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 7,
                       ((Zombie)Global.zombieVector.get(i)).getPositionX() + 7,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 7);
            // left eye
            g.drawLine(((Zombie)Global.zombieVector.get(i)).getPositionX() + 3,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 3,
                       ((Zombie)Global.zombieVector.get(i)).getPositionX() + 3,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 3);

            // right eye
            g.drawLine(((Zombie)Global.zombieVector.get(i)).getPositionX() + 6,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 3,
                       ((Zombie)Global.zombieVector.get(i)).getPositionX() + 6,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 3);

            g.setColor(Color.gray);
            // torso
            g.fillRect(((Zombie)Global.zombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 10,
                       12,
                       12);
            // left leg
            g.fillRect(((Zombie)Global.zombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 22,
                       2,
                       8);

            // right leg
            g.fillRect(((Zombie)Global.zombieVector.get(i)).getPositionX() + 10,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 22,
                       2,
                       8);

            // left arm
            g.drawLine(((Zombie)Global.zombieVector.get(i)).getPositionX(),
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 10,
                       ((Zombie)Global.zombieVector.get(i)).getPositionX() - 5,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY());

            // right arm
            g.drawLine(((Zombie)Global.zombieVector.get(i)).getPositionX() + 12,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY() + 10,
                       ((Zombie)Global.zombieVector.get(i)).getPositionX() + 15,
                       ((Zombie)Global.zombieVector.get(i)).getPositionY());
        }

        // draw projectiles
        g.setColor(Color.white);
        for (int i = 0; i < Global.projectileVector.size(); i++)
        {
            g.fillOval(((Projectile)Global.projectileVector.get(i)).getPositionX(),
                       ((Projectile)Global.projectileVector.get(i)).getPositionY(),
                       ((Projectile)Global.projectileVector.get(i)).getProjectileDiameter(),
                       ((Projectile)Global.projectileVector.get(i)).getProjectileDiameter());
        }
    }
}