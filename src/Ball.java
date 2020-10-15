import java.awt.*;

public class Ball {
    private int ballPosX = 350;
    private int ballPosY = 530;
    private int ballXDir = -1;
    private int ballYDir = -2;

    public void draw(Graphics g) {
        g.setColor(new Color(255,99,97));
        g.fillOval(ballPosX, ballPosY, 20, 20);
    }

    public boolean isOutside() {
        if (ballPosY > 570) {
            this.ballXDir = 0;
            this.ballYDir = 0;
            return true;
        }
        return false;
    }
    public void move(){
        ballPosX += ballXDir;
        ballPosY += ballYDir;
        if (ballPosX < 0) {
            ballXDir = -ballXDir;
        }
        if (ballPosY < 0) {
            ballYDir = -ballYDir;
        }
        if (ballPosX > 670) {
            ballXDir = -ballXDir;
        }
    }

    public void reverseTheDir(char c) {
        if (c == 'x' || c == 'X') this.ballXDir=-ballXDir;
        else this.ballYDir=-ballYDir;
    }

    public void stopTheBall() {
        setBallXDir(0);
        setBallYDir(0);
    }

    public int getBallPosX() {
        return ballPosX;
    }

    public int getBallPosY() {
        return ballPosY;
    }

    private void setBallXDir(int ballXDir) {
        this.ballXDir = ballXDir;
    }

    private void setBallYDir(int ballYDir) {
        this.ballYDir = ballYDir;
    }

}

