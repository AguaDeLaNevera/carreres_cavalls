package classes;
import javax.swing.*;
import java.awt.*;

public class TrackWindow extends JFrame {
    public TrackWindow() {
        setTitle("Track Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager to null to have full control over the component positions
        setLayout(null);

        // Create a JPanel for the track
        TrackPanel trackPanel = new TrackPanel();

        // Set the window size to the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth, screenHeight);

        // Set the bounds of trackPanel after the window becomes visible
        trackPanel.setBounds(50, 50, getWidth() - 100, getHeight() - 100);

        // Add the track panel to the frame
        add(trackPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrackWindow());
    }
}

class TrackPanel extends JPanel {
    private static final int NUM_LANES = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(139, 69, 19));
        // Draw the elliptical track
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int horizontalRadius = getWidth() / 2 - 30; // Adjust this value based on your preference
        int verticalRadius = getHeight() / 2 - 10; // Adjust this value based on your preference

        // Fill the elliptical track
        g.fillOval(centerX - horizontalRadius, centerY - verticalRadius, 2 * horizontalRadius, 2 * verticalRadius);
        g.drawOval(centerX - horizontalRadius, centerY - verticalRadius, 2 * horizontalRadius, 2 * verticalRadius);

        // Draw multiple lanes
        for (int i = 0; i < NUM_LANES; i++) {
            // Alternate lane colors for better visibility
            Color laneColor = (i % 2 == 0) ? Color.GREEN : Color.getHSBColor(0.3f, 0.85f, 0.8f);
            g.setColor(laneColor);

            // Calculate the inner and outer radii for the lane
            int innerHorizontalRadius = horizontalRadius - (i + 1) * 20; // Adjust this value based on your preference
            int innerVerticalRadius = verticalRadius - (i + 1) * 20; // Adjust this value based on your preference

            int outerHorizontalRadius = horizontalRadius - i * 20;
            int outerVerticalRadius = verticalRadius - i * 20;

            // Draw the lane as a ring segment
            g.fillArc(centerX - outerHorizontalRadius, centerY - outerVerticalRadius,
                    2 * outerHorizontalRadius, 2 * outerVerticalRadius, 0, 360);
            //draw movement here
            g.setColor(Color.black);
            g.setColor(getBackground());
            // draw ellipse in the middle
            g.fillArc(centerX - innerHorizontalRadius, centerY - innerVerticalRadius,
                  2 * innerHorizontalRadius, 2 * innerVerticalRadius, 0, 360);
        }
    }
}

class MovingObject{
    int x;
    int y;
    int width;
    int height;
    int startAngle;
    int finishAngle;
    int speed;
    MovingObject(int x, int y, int width, int height, int startAngle, int finishAngle, int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.finishAngle = finishAngle;
        this.speed = speed;
    }

}