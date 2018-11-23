import java.util.*;
 
public class Student {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Valid(minValue = 0.0f, maxValue = 4.0f)
    private float gpa;

    @NotEmpty
    private List<Course> courses;

    public Student(String firstName, String lastName, float gpa){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        courses = new ArrayList<>();
    }

    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public float getGpa(){
        return this.gpa;
    }

    public List<Course> getCourses(){
        List<Course> temp = new ArrayList<>(courses);
        return temp;
    }


    void enroll(Course course) {
        courses.add(course);
    }

}