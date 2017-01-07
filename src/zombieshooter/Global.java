/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;

import java.util.Vector;


/**
 *
 * @author jsdratm
 */
public class Global
{
    public static int windowHeight = 600;
    public static int windowWidth = 600;
    public static Vector projectileVector = new Vector();
    public static Vector zombieVector = new Vector();
    public static Vector deadZombieVector = new Vector();
    public static Shooter playerShooter = new Shooter((Global.windowWidth / 2), (Global.windowHeight - (Global.windowHeight / 4)), 0);
    public static CollisionDetection collisionDetector = new CollisionDetection();

    public static int zombieWidth = 12;
    public static int zombieHeight = 30;
    public static int zombieVelocityTotal = 2;

    public static int zombieLimit = 10;
    public static int deadZombieLimit = 10;
    public static int projectileStartOffsetX = 18;
    public static int projectileStartOffsetY = -4;
    public static int playerMoveIncrement = 5;

    public static int shotCounter = 0;
    public static int shotCounterLimit = 15;

    public static boolean projectileMutexLocked = false;
}
