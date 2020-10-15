import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame  frame=new JFrame();
        Engine engine=new Engine();

        frame.setBounds(10,10,700,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        frame.setVisible(true);
    }
}
