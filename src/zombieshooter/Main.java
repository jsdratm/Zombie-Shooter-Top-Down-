/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;


import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;


class SimpleThread extends Thread
{
    private Random randomGenerator;

    public SimpleThread()
    {
        randomGenerator = new Random();
    }
    public void run()
    {
	while (true)
        {
            // increment shot counter
            Global.shotCounter++;

            // update projectile positions
            for (int i = 0; i < Global.projectileVector.size(); i++)
            {
                int shiftX = ((Projectile)Global.projectileVector.get(i)).getVelocityX();
                int shiftY = ((Projectile)Global.projectileVector.get(i)).getVelocityY();
                ((Projectile)Global.projectileVector.get(i)).shiftPosition(shiftX, shiftY);

                if (((Projectile)Global.projectileVector.get(i)).getPositionX() > Global.windowWidth ||
                    ((Projectile)Global.projectileVector.get(i)).getPositionX() < 0 ||
                    ((Projectile)Global.projectileVector.get(i)).getPositionY() > Global.windowHeight ||
                    ((Projectile)Global.projectileVector.get(i)).getPositionY() < 0)
                {
                    Global.projectileVector.remove(i);
                }
            }

            // check for projectile collisions with zombies
            if (Global.projectileVector.size() > 0)
            {
                Global.projectileMutexLocked = true;

                Vector zombiesToRemoveVector = new Vector();
                Vector projectilesToRemoveVector = new Vector();

                for (int i = 0; i < Global.projectileVector.size(); i++)
                {
                    for (int j = 0; j < Global.zombieVector.size(); j++)
                    {
                        if(Global.collisionDetector.detectCollision(((Projectile)Global.projectileVector.get(i)).getPositionX(),
                                                                    ((Projectile)Global.projectileVector.get(i)).getPositionY(),
                                                                    ((Projectile)Global.projectileVector.get(i)).getProjectileDiameter(),
                                                                    ((Projectile)Global.projectileVector.get(i)).getProjectileDiameter(),
                                                                    ((Zombie)Global.zombieVector.get(j)).getPositionX(),
                                                                    ((Zombie)Global.zombieVector.get(j)).getPositionY(),
                                                                    Global.zombieHeight,
                                                                    Global.zombieWidth))
                        {
                            projectilesToRemoveVector.add(i);
                            zombiesToRemoveVector.add(j);
                        }
                    }
                }

                for (int i = 0; i < projectilesToRemoveVector.size(); i++)
                {
                    if (Global.projectileVector.size() > i)
                    {
                        Global.projectileVector.remove(i);
                    }
                }

                for (int i = 0; i < zombiesToRemoveVector.size(); i++)
                {
                    if (Global.deadZombieVector.size() >= Global.deadZombieLimit)
                    {
                        Global.deadZombieVector.remove(0);
                    }

                    if (Global.zombieVector.size() > i)
                    {
                        Global.deadZombieVector.add(new Zombie(0,
                                                               ((Zombie)Global.zombieVector.get(i)).getPositionX(),
                                                               ((Zombie)Global.zombieVector.get(i)).getPositionY(),
                                                               0));
                        Global.zombieVector.remove(i);
                    }
                }
                
                Global.projectileMutexLocked = false;
            }

            // update zombie positions
            for (int i = 0; i < Global.zombieVector.size(); i++)
            {
                int shiftX = 0;
                int shiftY = 0;

                if ( ((Zombie)Global.zombieVector.get(i)).getPositionX() > ((Shooter)Global.playerShooter).getPositionX() + 8 )
                {
                    shiftX = -1 * Global.zombieVelocityTotal / 2;
                }
                else if ( ((Zombie)Global.zombieVector.get(i)).getPositionX() < ((Shooter)Global.playerShooter).getPositionX() + 8 )
                {
                    shiftX = Global.zombieVelocityTotal / 2;
                }

                if ( ((Zombie)Global.zombieVector.get(i)).getPositionY() == ((Shooter)Global.playerShooter).getPositionY() )
                {
                    shiftX *= 2;
                }
                else if ( ((Zombie)Global.zombieVector.get(i)).getPositionY() > ((Shooter)Global.playerShooter).getPositionY() )
                {
                    shiftY = -1 * Global.zombieVelocityTotal / 2;
                }
                else if ( ((Zombie)Global.zombieVector.get(i)).getPositionY() < ((Shooter)Global.playerShooter).getPositionY() )
                {
                    shiftY = Global.zombieVelocityTotal / 2;
                }

                if (((Zombie)Global.zombieVector.get(i)).getPositionX() == ((Shooter)Global.playerShooter).getPositionX() + 8)
                {
                    shiftY *= 2;
                }

                ((Zombie)Global.zombieVector.get(i)).shiftPosition(shiftX, shiftY, 0);

            }

            // if there are less than the limit of zombies, spawn one
            if (Global.zombieVector.size() < Global.zombieLimit)
            {
                int newZombiePositionX = randomGenerator.nextInt(Global.windowWidth - Global.zombieWidth);
                int newZombiePositionY = randomGenerator.nextInt(50);

                Global.zombieVector.add(new Zombie(Global.zombieVelocityTotal,
                                                   newZombiePositionX,
                                                   newZombiePositionY,
                                                   0));
            }

            // Wait 25ms
            try
            {
		sleep(25);
	    }
            catch (InterruptedException e)
            {
                // Do nothing
            }
	}
    }
}

/**
 *
 * @author jsdratm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFrame mainFrame = new JFrame("Zombie Shooter by Dunderware");
        mainFrame.setResizable(false);
        GraphicsEngine graphicsEngine = new GraphicsEngine();

        new SimpleThread().start();

        mainFrame.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();

                // left arrow key
                if (keyCode == KeyEvent.VK_LEFT)
                {
                    int shiftAmountX = 0;

                    if (Global.playerShooter.getPositionX() > 0)
                    {
                        shiftAmountX = Global.playerShooter.getPositionX();

                        if (shiftAmountX > Global.playerMoveIncrement)
                        {
                            shiftAmountX = Global.playerMoveIncrement;
                        }

                        Global.playerShooter.shiftPosition(-1 * shiftAmountX, 0, 0);
                    }
                }
                // right arrow key
                else if (keyCode == KeyEvent.VK_RIGHT)
                {
                    int shiftAmountX = 0;

                    if (Global.playerShooter.getPositionX() < Global.windowWidth)
                    {
                        shiftAmountX = Global.windowWidth - Global.playerShooter.getPositionX();

                        if (shiftAmountX > Global.playerMoveIncrement)
                        {
                            shiftAmountX = Global.playerMoveIncrement;
                        }

                        Global.playerShooter.shiftPosition(shiftAmountX, 0, 0);
                    }
                }
                // up arrow key
                else if (keyCode == KeyEvent.VK_UP)
                {
                    int shiftAmountY = 0;

                    if (Global.playerShooter.getPositionY() > 0)
                    {
                        shiftAmountY = Global.playerShooter.getPositionY();

                        if (shiftAmountY > Global.playerMoveIncrement)
                        {
                            shiftAmountY = Global.playerMoveIncrement;
                        }

                        Global.playerShooter.shiftPosition(0, -1 * shiftAmountY, 0);
                    }
                }
                // down arrow key
                else if (keyCode == KeyEvent.VK_DOWN)
                {
                    int shiftAmountY = 0;

                    if (Global.playerShooter.getPositionY() < Global.windowHeight)
                    {
                        shiftAmountY = Global.windowHeight - Global.playerShooter.getPositionY();

                        if (shiftAmountY > Global.playerMoveIncrement)
                        {
                            shiftAmountY = Global.playerMoveIncrement;
                        }

                        Global.playerShooter.shiftPosition(0, shiftAmountY, 0);
                    }
                }
                // space key
                else if (keyCode == KeyEvent.VK_SPACE)
                {
                    if (!Global.projectileMutexLocked && Global.shotCounter > Global.shotCounterLimit)
                    {
                        
                        Global.projectileVector.add(new Projectile(0,
                                                                   -5,
                                                                   Global.playerShooter.getPositionX() + Global.projectileStartOffsetX,
                                                                   Global.playerShooter.getPositionY() + Global.projectileStartOffsetY,
                                                                   3));
                        Global.projectileVector.add(new Projectile(1,
                                                                   -4,
                                                                   Global.playerShooter.getPositionX() + Global.projectileStartOffsetX,
                                                                   Global.playerShooter.getPositionY() + Global.projectileStartOffsetY,
                                                                   3));
                        Global.projectileVector.add(new Projectile(-1,
                                                                   -4,
                                                                   Global.playerShooter.getPositionX() + Global.projectileStartOffsetX,
                                                                   Global.playerShooter.getPositionY() + Global.projectileStartOffsetY,
                                                                   3));
                        
                        Global.shotCounter = 0;
                    }

                }
            }
        });

        mainFrame.getContentPane().add(graphicsEngine);

        mainFrame.setSize(Global.windowWidth,
                          Global.windowHeight);

        mainFrame.setVisible(true);

        while (true)
        {
            mainFrame.repaint();
            try {
		Thread.currentThread().sleep(50);
	    }
            catch (InterruptedException e)
            {

            }
        }

    }

}
