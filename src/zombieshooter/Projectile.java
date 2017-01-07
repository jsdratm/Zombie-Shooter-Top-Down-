/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombieshooter;

/**
 *
 * @author jsdratm
 */
public class Projectile {
    private int velocityX;
    private int velocityY;
    private int positionX;
    private int positionY;
    private int projectileDiameter;

    public Projectile(int velocityXIn, int velocityYIn, int positionXIn, int positionYIn, int projectileDiameterIn)
    {
        velocityX = velocityXIn;
        velocityY = velocityYIn;
        positionX = positionXIn;
        positionY = positionYIn;
        projectileDiameter = projectileDiameterIn;
    }

    public void shiftPosition(int deltaXIn, int deltaYIn)
    {
        positionX += deltaXIn;
        positionY += deltaYIn;
    }

    public int getVelocityX()
    {
        return velocityX;
    }

    public int getVelocityY()
    {
        return velocityY;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public int getPositionY()
    {
        return positionY;
    }

    public int getProjectileDiameter()
    {
        return projectileDiameter;
    }
}
