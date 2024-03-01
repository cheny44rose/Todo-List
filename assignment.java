//create a class called asignment
import java.time.LocalDate;
public class assignment implements java.io.Serializable{
    String name;
    LocalDate dueDate;
    String course;
    public assignment(String name, LocalDate dueDate, String course){
        this.name = name;
        this.dueDate = dueDate;
        this.course = course;
    }
    public String getName(){
        return name;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public String getCourse(){
        return course;
    }
    public String toString(){
        return "Name: " + this.name + "\nDue Date: " + this.dueDate + "\nClass: " + this.course;
    }
}