import java.util.Scanner;

// Class to store student details
class Student {
    int roll;
    String name, division;
    double marks;

    // Constructor to initialize student details
    Student(int r, String n, String d, double m) {
        roll = r;
        name = n;
        division = d;
        marks = m;
    }

    // Returns student details in string format
    public String toString() {
        return roll + " " + name + " " + division + " " + marks;
    }
}

// Node class represents each element of the linked list
class Node {
    Student data;
    Node next;

    // Constructor to initialize node with student data
    Node(Student s) {
        data = s;
        next = null;
    }
}

// Linked list class to manage student records
class StudentLinkedList {
    private Node head, tail;
    private int size = 0;

    // Adds a new student at the end of the linked list
    void add(Student s) {
        Node n = new Node(s);

        // If list is empty, new node becomes both head and tail
        if (head == null)
            head = tail = n;
        else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    // Searches for a student using roll number
    Student searchRoll(int r) {
        Node c = head;

        // Traverse the linked list until the student is found
        while (c != null) {
            if (c.data.roll == r)
                return c.data;
            c = c.next;
        }
        return null; // Return null if student is not found
    }

    // Searches for students using their names
    void searchName(String name) {
        Node c = head;
        boolean found = false;

        // Traverse the list and compare names
        while (c != null) {
            if (c.data.name.equalsIgnoreCase(name)) {
                System.out.println(c.data);
                found = true;
            }
            c = c.next;
        }

        // Display message if no matching student is found
        if (!found)
            System.out.println("Student not found!");
    }

    // Deletes a student using roll number
    void delete(int r) {
        Node c = head, p = null;

        // Find the node containing the given roll number
        while (c != null && c.data.roll != r) {
            p = c;
            c = c.next;
        }

        // If student does not exist
        if (c == null) {
            System.out.println("Student not found!");
            return;
        }

        // If the first node is to be deleted
        if (p == null)
            head = c.next;
        else
            p.next = c.next;

        // Update tail if the last node is deleted
        if (c == tail)
            tail = p;

        size--;
        System.out.println("Student deleted!");
    }

    // Updates marks of a student using roll number
    void update(int r, double m) {
        Student s = searchRoll(r);

        if (s == null)
            System.out.println("Student not found!");
        else {
            s.marks = m;
            System.out.println("Marks updated!");
        }
    }

    // Displays all student records
    void display() {
        Node c = head;

        // Check whether the linked list is empty
        if (c == null) {
            System.out.println("No records available!");
            return;
        }

        // Traverse and print each student record
        while (c != null) {
            System.out.println(c.data);
            c = c.next;
        }
    }

    // Returns the total number of students
    int getSize() {
        return size;
    }
}

// Main class of the Student Record System
public class StudentRecordSystem {

    // Scanner object to accept user input
    static Scanner sc = new Scanner(System.in);

    // Object of StudentLinkedList to manage student records
    static StudentLinkedList list = new StudentLinkedList();

    public static void main(String[] args) {
        int choice;

        // Menu-driven program runs until user selects Exit
        do {
            System.out.println("\n--- STUDENT RECORD SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search by Roll Number");
            System.out.println("3. Search by Name");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Display All Students");
            System.out.println("7. Total Records");
            System.out.println("0. Exit");

            // Accept menu choice from the user
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            // Perform operation based on user's choice
            switch (choice) {

                case 1:
                    // Accept details and add a new student
                    System.out.print("Roll Number: ");
                    int r = sc.nextInt();
                    sc.nextLine();

                    // Prevent duplicate roll numbers
                    if (list.searchRoll(r) != null) {
                        System.out.println("Duplicate roll number!");
                        break;
                    }

                    System.out.print("Name: ");
                    String n = sc.nextLine();

                    System.out.print("Division: ");
                    String d = sc.nextLine();

                    System.out.print("Marks: ");
                    double m = sc.nextDouble();
                    sc.nextLine();

                    // Create student object and add it to linked list
                    list.add(new Student(r, n, d, m));
                    System.out.println("Student added!");
                    break;

                case 2:
                    // Search student using roll number
                    System.out.print("Enter roll number: ");
                    Student s = list.searchRoll(sc.nextInt());
                    sc.nextLine();

                    // Display student details or not-found message
                    System.out.println(s == null ? "Not found!" : s);
                    break;

                case 3:
                    // Search student using name
                    System.out.print("Enter name: ");
                    list.searchName(sc.nextLine());
                    break;

                case 4:
                    // Update marks of an existing student
                    System.out.print("Enter roll number: ");
                    r = sc.nextInt();

                    System.out.print("New marks: ");
                    m = sc.nextDouble();
                    sc.nextLine();

                    list.update(r, m);
                    break;

                case 5:
                    // Delete student using roll number
                    System.out.print("Enter roll number: ");
                    list.delete(sc.nextInt());
                    sc.nextLine();
                    break;

                case 6:
                    // Display all stored student records
                    list.display();
                    break;

                case 7:
                    // Display total number of student records
                    System.out.println("Total students: " + list.getSize());
                    break;

                case 0:
                    // Exit the program
                    System.out.println("Exiting program!");
                    break;

                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}