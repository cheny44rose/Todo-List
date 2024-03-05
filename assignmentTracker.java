import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class assignmentTracker{
    public static final Dimension SCENE_VIEWER_SIZE = new Dimension(1500, 600);
    public static ArrayList<assignment>  assignments = new ArrayList<assignment>();
    
    public static void main(String[] args){
        readFromFile();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
		frame.setVisible(true);
        frame.setSize(SCENE_VIEWER_SIZE);
        JButton addButton = new JButton("Add Assignment");
        JButton removeButton = new JButton("Remove Assignment");
        panel.add(addButton);
        panel.add(removeButton);
        frame.add(panel, BorderLayout.SOUTH);
        assignmentComponent a = new assignmentComponent(assignments);
        frame.add(a, BorderLayout.CENTER);
        a.updateAssignments(assignments);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame removeAssignmentFrame = new JFrame();
                removeAssignmentFrame.setSize(600, 600);
                removeAssignmentFrame.setVisible(true);
                JPanel textBoxPanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                removeAssignmentFrame.add(textBoxPanel, BorderLayout.NORTH);
                removeAssignmentFrame.add(buttonPanel, BorderLayout.SOUTH);
                JLabel name = new JLabel("Assignment Name");
                JTextField assignmentName = new JTextField();
                JButton submit = new JButton("Submit");
                textBoxPanel.setLayout(new BoxLayout(textBoxPanel, BoxLayout.Y_AXIS));
                textBoxPanel.add(name);
                textBoxPanel.add(assignmentName);
                buttonPanel.add(submit);
                assignmentName.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            String name = assignmentName.getText();
                            for(int i = 0; i < assignments.size(); i++){
                                if(assignments.get(i).getName().equals(name)){
                                    assignments.remove(i);
                                    writeToFile();
                                    a.updateAssignments(assignments);
                                    break;
                                }
                            }
                            removeAssignmentFrame.dispose();
                        }
                    }
                });
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = assignmentName.getText();
                        for(int i = 0; i < assignments.size(); i++){
                            if(assignments.get(i).getName().equals(name)){
                                assignments.remove(i);
                                writeToFile();
                                a.updateAssignments(assignments);
                                break;
                            }
                        }
                        
                        removeAssignmentFrame.dispose();
                    }
                });
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JLabel name = new JLabel("Assignment Name");
                JLabel date = new JLabel("Due Date (MM-dd-yy HH:mm Military Time)");
                JLabel dept = new JLabel("Department");
                JFrame addAssignmentFrame = new JFrame();
                addAssignmentFrame.setSize(600, 600);
                JPanel textBoxPanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                addAssignmentFrame.setVisible(true);
                JTextField assignmentName = new JTextField();
                assignmentName.setBounds(50, 50, 200, 30);
                JTextField dueDate = new JTextField();
                dueDate.setBounds(50, 100, 200, 30);
                JTextField course = new JTextField();
                course.setBounds(50, 150, 200, 30);
                JButton submit = new JButton("Submit");
                
                textBoxPanel.setLayout(new BoxLayout(textBoxPanel, BoxLayout.Y_AXIS));
                textBoxPanel.add(name);
                textBoxPanel.add(assignmentName);
                textBoxPanel.add(date);
                textBoxPanel.add(dueDate);
                textBoxPanel.add(dept);
                textBoxPanel.add(course);
                addAssignmentFrame.add(textBoxPanel, BorderLayout.NORTH);

                buttonPanel.add(submit);
                addAssignmentFrame.add(buttonPanel, BorderLayout.SOUTH);
                course.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            String name = assignmentName.getText();
                            LocalDateTime date = LocalDateTime.parse(dueDate.getText(),DateTimeFormatter.ofPattern("MM-dd-yy HH:mm"));
                            String classCourse = course.getText();
                            assignment newAssignment = new assignment(name, date, classCourse);
                            assignments.add(newAssignment);
                            addAssignmentFrame.dispose();
                            writeToFile();
                            a.updateAssignments(assignments);
                        }
                    }
                });
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = assignmentName.getText();
                        LocalDateTime date = LocalDateTime.parse(dueDate.getText(),DateTimeFormatter.ofPattern("MM-dd-yy HH:mm"));
                        String classCourse = course.getText();
                        assignment newAssignment = new assignment(name, date, classCourse);
                        assignments.add(newAssignment);
                        addAssignmentFrame.dispose();
                        writeToFile();
                        a.updateAssignments(assignments);
                    }
                });
            }
        });
        frame.setTitle("AssignmentTracker");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void writeToFile(){
        System.out.println("writing to file");
        try{
            FileOutputStream file = new FileOutputStream("assignmentData.ser");
            ObjectOutputStream writeFile = new ObjectOutputStream(file);
            writeFile.writeObject(assignments);
            writeFile.flush();
            writeFile.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static void readFromFile(){
        System.err.println("reading from file");
        try{
            FileInputStream readData = new FileInputStream("assignmentData.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            assignmentTracker.assignments  = (ArrayList<assignment>) readStream.readObject();
            readStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}