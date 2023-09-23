import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity String schedule) {
 this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (hasCourseCapacity(course) && !isAlreadyRegistered(course)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getCode());
        } else {
            System.out.println("Unable to register for course: " + course.getCode());
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            System.out.println("Successfully dropped course: " + course.getCode());
        } else {
            System.out.println("Course not found in registered courses: " + course.getCode());
        }
    }

    private boolean hasCourseCapacity(Course course) {
        return course.getCapacity() > course.getRegisteredStudentsCount();
    }

    private boolean isAlreadyRegistered(Course course) {
        return registeredCourses.contains(course);
    }
}

class Database {
    private List<Course> courses;
    private List<Student> students;

    public Database() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getStudents() {
        return students;
    }
}

public class StudentCourseRegistrationSystem {
    private static Database database;

    public static void main(String[] args) {
        initializeDatabase();
        showMenu();
    }

    private static void initializeDatabase() {
        database = new Database();

        Course course1 = new Course("CSE101", "Introduction to Computer Science",
                "Introduction to programming concepts", 50, "Mon, Wed, Fri 10:00 AM - 11:30 AM");
        database.addCourse(course1);

        Course course2 = new Course("CSE201", "Data Structures and Algorithms",
                "Advanced data structures and algorithms", 40, "Tue, Thu 1:00 PM - 2:30 PM");
        database.addCourse(course2);

        Course course3 = new Course("CSE301", "Database Management Systems", "Introduction to databases and SQL", 30,
                "Wed, Fri 3:00 PM - 4:30 PM");
        database.addCourse(course3);

        Student student1 = new Student("S101", "John Doe");
        database.addStudent(student1);

        Student student2 = new Student("S102", "Jane Smith");
        database.addStudent(student2);
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSTUDENT COURSE REGISTRATION SYSTEM\n");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void viewAvailableCourses() {
        System.out.println("\nAVAILABLE COURSES:\n");
        List<Course> courses = database.getCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        for (Course course : courses) {
            System.out.println("Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("--------------------------");
        }
    }

    private static void registerForCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student == null || course == null) {
            System.out.println("Invalid student ID or course code.");
            return;
        }

        student.registerCourse(course);
    }

    private static void dropCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student == null || course == null) {
            System.out.println("Invalid student ID or course code.");
            return;
        }

        student.dropCourse(course);
    }

    private static Student findStudentById(String studentId) {
        for (Student student : database.getStudents()) {
            if (student.getId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : database.getCourses()) {
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
