/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;

/**
 *
 * @author jsdratm
 */
public class Zombie
{
    private int velocityTotal;
    private int positionX;
    private int positionY;
    private int degreesRotation;

    public Zombie(int velocityTotalIn, int positionXIn, int positionYIn, int degreesRotationIn)
    {
        velocityTotal = velocityTotalIn;
        positionX = positionXIn;
        positionY = positionYIn;
        degreesRotation = degreesRotationIn;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public int getPositionY()
    {
        return positionY;
    }

    public int getDegreesRotation()
    {
        return degreesRotation;
    }

    public int getVelocityTotal()
    {
        return velocityTotal;
    }

        public void shiftPosition(int positionXDelta, int positionYDelta, int degreesRotationDelta)
    {
        positionX += positionXDelta;
        positionY += positionYDelta;
        degreesRotation += degreesRotationDelta;

        if (degreesRotation < 0)
        {
            degreesRotation += 360;
        }
        else if (degreesRotation > 359)
        {
            degreesRotation -= 360;
        }
    }
}
