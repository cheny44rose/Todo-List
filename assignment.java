import java.time.LocalDate;
import java.time.LocalDateTime;
public class assignment implements java.io.Serializable{
    String name;
    LocalDateTime dueDate;
    String course;
    String time;
    public assignment(String name, LocalDateTime dueDate, String course){
        this.name = name;
        this.dueDate = dueDate;
        this.course = course;
    }
    public String getName(){
        return name;
    }
    public LocalDateTime getDueDate(){
        return dueDate;
    }
    public String getCourse(){
        return course;
    }
    public String toString(){
        return "Name: " + this.name + "\nDue Date: " + this.dueDate + "\nClass: " + this.course;
    }
    public String getTime(){
        return time;
    }
}