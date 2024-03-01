import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class assignmentComponent extends JComponent{
    private static final int HEIGHT = 80;
	private static final int WIDTH = 100;
    static HashMap<String, Color> colorMap = new HashMap<String, Color>();
    private ArrayList<assignment> assignments;
    public assignmentComponent(ArrayList<assignment> assignments) {
        this.assignments = assignments.stream()
                                      .sorted(Comparator.comparing(assignment::getDueDate))
                                      .collect(Collectors.toCollection(ArrayList::new));
    }
    @Override
    protected void paintComponent(Graphics g) {
        makeHashmapColor();
        super.paintComponent(g);
        // Get the 2D graphics object
        Graphics2D g2 = (Graphics2D)g;
        for(int i=0; i<assignments.size(); i++){
            assignment a = assignments.get(i);
            g2.setColor(colorMap.get(a.getCourse()));
            g2.fillRect(0, i*HEIGHT, WIDTH, HEIGHT);
            g2.setColor(Color.BLACK);
            g2.drawString(a.getName(), 0, i*HEIGHT+20);
            g2.drawString(a.getDueDate().toString(), 0, i*HEIGHT+40);
            g2.drawString(a.getCourse(), 0, i*HEIGHT+60);
        }
    }
    public void makeHashmapColor(){
        colorMap.put("MA", Color.RED);
        colorMap.put("CSSE", Color.YELLOW);
    }
    public void updateAssignments(ArrayList<assignment> assignments){
        this.assignments = assignments.stream()
                                      .sorted(Comparator.comparing(assignment::getDueDate))
                                      .collect(Collectors.toCollection(ArrayList::new));
        repaint();
    }
}