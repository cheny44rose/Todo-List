import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class assignmentComponent extends JComponent{
    private static int HEIGHT = 70;
    private static int BHEIGHT = 90;
	private static int WIDTH = 240;
    static HashMap<String, Color> colorMap = new HashMap<String, Color>();
    private ArrayList<assignment> assignments;
    public assignmentComponent(ArrayList<assignment> assignments) {
        this.assignments = assignments.stream()
                                      .sorted(Comparator.comparing(assignment::getDueDate))
                                      .collect(Collectors.toCollection(ArrayList::new));
    }
    @Override
    protected void paintComponent(Graphics g) {
        LocalDateTime currentTime = LocalDateTime.now();
        makeHashmapColor();
        super.paintComponent(g);
        // Get the 2D graphics object
        Graphics2D g2 = (Graphics2D)g;
        Font courierFontTitle = new Font("Courier New", Font.PLAIN, 20);
        Font courierFontBlock = new Font("Courier New", Font.PLAIN, 15);
        g2.setFont(courierFontTitle);
        g2.drawString("You fucked Up", 100, 30);
        g2.drawString("Assignments to do today", 500, 30);
        g2.drawString("Assignments to due in about a week", 1000, 30);
        g2.setFont(courierFontBlock);
        int addr1 = 0;
        int addr2 = 0;
        int addr3 = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        if(!assignments.isEmpty()){
            for(assignment a: assignments){
                Duration duration = Duration.between(currentTime, a.getDueDate());
                if(duration.toHours()<=0){
                    g2.setColor(colorMap.get(a.getCourse()));
                    g2.fillRect(60, count1*HEIGHT+50+addr1, WIDTH, BHEIGHT);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(60, count1*HEIGHT+50+addr1, WIDTH, BHEIGHT);
                    g2.setColor(Color.BLACK);
                    g2.drawString(a.getName(), 65, count1*HEIGHT+70+addr1);
                    g2.drawString(a.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yy hh:mm a")).toString(), 65, count1*HEIGHT+90+addr1);
                    g2.drawString(a.getCourse(), 65, count1*HEIGHT+110+addr1);
                    g2.drawString("Due in: " + duration.toHours()+" Hours", 65, count1*HEIGHT+130+addr1);
                    addr1 +=30;
                    count1++;
                }
                else if(duration.toDays()<=2){
                    g2.setColor(colorMap.get(a.getCourse()));
                    g2.fillRect(550, count2*HEIGHT+50+addr2, WIDTH, BHEIGHT);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(550, count2*HEIGHT+50+addr2, WIDTH, BHEIGHT);
                    g2.setColor(Color.BLACK);
                    g2.drawString(a.getName(), 555, count2*HEIGHT+70+addr2);
                    g2.drawString(a.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yy hh:mm a")).toString().toString(), 555, count2*HEIGHT+90+addr2);
                    g2.drawString(a.getCourse(), 555, count2*HEIGHT+110+addr2);
                    if(duration.toHours()<0)
                        g2.drawString("Due at: 11:59", 555, count2*HEIGHT+130+addr2);
                    else
                        g2.drawString("Due in: " + duration.toHours()+" Hours", 555, count2*HEIGHT+130+addr2);
                    addr2 +=30;
                    count2++;
                }
                else{
                    g2.setColor(colorMap.get(a.getCourse()));
                    g2.fillRect(1110, count3*HEIGHT+50+addr3, WIDTH, BHEIGHT);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(1110, count3*HEIGHT+50+addr3, WIDTH, BHEIGHT);
                    g2.setColor(Color.BLACK);
                    g2.drawString(a.getName(), 1115, count3*HEIGHT+70+addr3);
                    g2.drawString(a.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yy hh:mm a")).toString(), 1115, count3*HEIGHT+90+addr3);
                    g2.drawString(a.getCourse(), 1115, count3*HEIGHT+110+addr3);
                    g2.drawString("Due in: " + duration.toDays()+" Days", 1115, count3*HEIGHT+130+addr3);
                    addr3 +=30;
                    count3++;
                }
            }
        }
    }
    public void makeHashmapColor(){
        colorMap.put("MA", Color.RED);
        colorMap.put("CSSE", Color.YELLOW);
        colorMap.put("ENGLH", Color.GREEN);
        colorMap.put("BE", Color.BLUE);
        colorMap.put("CHE", Color.ORANGE);
        colorMap.put("CE", Color.PINK);
        colorMap.put("ME", Color.CYAN);
        colorMap.put("PH", Color.MAGENTA);
        colorMap.put("ENGD", Color.LIGHT_GRAY);
        colorMap.put("EMGT", Color.DARK_GRAY);
        colorMap.put("JAPNL", Color.BLUE);
        colorMap.put("EP", Color.lightGray);

    }
    public void updateAssignments(ArrayList<assignment> assignments){
        this.assignments = assignments.stream()
                                      .sorted(Comparator.comparing(assignment::getDueDate))
                                      .collect(Collectors.toCollection(ArrayList::new));
        repaint();
    }
}