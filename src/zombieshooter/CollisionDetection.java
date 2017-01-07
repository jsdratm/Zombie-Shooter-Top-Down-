/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;

/**
 *
 * @author jsdratm
 */
public class CollisionDetection {

    public boolean detectCollision(int object1PositionX,
                                   int object1PositionY,
                                   int object1Height,
                                   int object1Width,
                                   int object2PositionX,
                                   int object2PositionY,
                                   int object2Height,
                                   int object2Width)
    {
        boolean collisionDetected = false;

        for (int x = object1PositionX; x <= (object1PositionX + object1Width); x++)
        {
            if (x >= object2PositionX && x <= (object2PositionX + object2Width))
            {
                for (int y = object1PositionY; y <= (object1PositionY + object1Height); y++)
                {
                    if (y >= object2PositionY && y <= (object2PositionY + object2Height))
                    {
                        collisionDetected = true;
                        break;
                    }
                }
            }
        }

        return collisionDetected;
    }
}
