import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Engine extends JPanel implements KeyListener, ActionListener {

    private static final int DELAY = 6;

    private boolean play = false;
    private int score = 0;
    private int totalBrick = 30;

    private Timer timer;

    private int playerX = 310;

    private BrickGenerator bricks;
    private Ball ball;

    public Engine() {
        this.bricks = new BrickGenerator(3, 10);
        this.ball = new Ball();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.timer = new Timer(DELAY, this);
        timer.start();
    }


    public void paint(Graphics g) {
        //background
        g.setColor(new Color(0,63,92));
        g.fillRect(10, 10, 700, 600);
        //bricks
        bricks.draw((Graphics2D) g);
        //paddle
        g.setColor(new Color(88,80,141));
        g.fillRect(playerX, 550, 100, 8);
        //the ball
        ball.draw(g);

        ifWin(this.totalBrick, g);
        ifLose(g);

        //score
        g.setColor(Color.CYAN);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + this.score, 570, 30);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ball.getBallPosX(), ball.getBallPosY(), 20, 20)
                    .intersects(new Rectangle(playerX, 550, 100, 8))) {
                ball.reverseTheDir('y');
            }

            A:
            for (int i = 0; i < bricks.map.length; i++) {
                for (int j = 0; j < bricks.map[i].length; j++) {
                    if (bricks.map[i][j] > 0) {
                        int brickX = j * bricks.brickWidth + 80;
                        int brickY = i * bricks.brickHeight + 50;
                        int brickWidth = bricks.brickWidth;
                        int brickHeight = bricks.brickHeight;

                        Rectangle ballRect = new Rectangle(ball.getBallPosX(), ball.getBallPosY(), 20, 20);
                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

                        if (ballRect.intersects(brickRect)) {
                            bricks.setBrickValue(0, i, j);
                            totalBrick--;
                            this.score += 5;

                            if (ball.getBallPosX() + 19 <= brickRect.x
                                    || ball.getBallPosX() + 1 >= brickRect.x + brickRect.width) {
                               ball.reverseTheDir('x');
                            } else {
                                ball.reverseTheDir('y');
                            }
                            break A;
                        }
                    }
                }
            }
            ball.move();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) playerX = 10;
            else moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                playerX = 310;
                this.ball=new Ball();
                score = 0;
                totalBrick = 30;
                bricks = new BrickGenerator(3, 10);
                repaint();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void moveRight() {
        this.play = true;
        playerX += 20;
    }

    private void moveLeft() {
        this.play = true;
        playerX -= 20;
    }

    private void ifLose(Graphics g) {
        if (this.ball.isOutside()) {
            play = false;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + this.score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
    }

    private void ifWin(int totalBrick, Graphics g) {
        if (totalBrick == 0) {
            play = false;
            this.ball.stopTheBall();
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won! Congratulations", 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
    }

}
