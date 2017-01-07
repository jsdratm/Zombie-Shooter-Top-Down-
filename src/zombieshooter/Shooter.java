/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;

/**
 *
 * @author jsdratm
 */
public class Shooter {
    private int degreesRotation;
    private int positionX;
    private int positionY;

    public Shooter(int positionXIn, int positionYIn, int degreesRotationIn)
    {
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
