import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton startButton;
    private JButton stopButton;
    private Renderer renderer;

    public ButtonPanel(Renderer renderer) {
        this.renderer = renderer;

        setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the panel
        setLayout(new FlowLayout(FlowLayout.CENTER));

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(30, 30)); // Set the size for the start button
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(30, 30)); // Set the size for the stop button

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.setGameState(true);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.setGameState(false);
            }
        });

        add(startButton);
        add(stopButton);
    }
}
