public class Student {
    private String name;
    private int age;
    private String course;

    private int id;
    public Student(){}

    public Student(int id, String name, int age, String course){
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public String getCourse(){return this.course;}


    @Override
    public String toString() {
        return "Name: " + this.name + "  Age: " + this.age + "  Course: " + this.course;
    }
}
