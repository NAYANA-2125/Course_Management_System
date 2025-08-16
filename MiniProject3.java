package com.gqt.core_java.mini_project;

/**
 * @author nayana
 * @category Mini Project
 * Course Management System
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MiniProject3 {
    private static String[][] adminCredentials = {{"admin", "admin123"}};

    private static Professor111[] professors;
    private static int professorCount;
    private static final int MAX_PROFESSORS = 200; 
    
    private static Student111[] students;
    private static int studentCount;
    private static final int MAX_STUDENTS = 200; 

    private static Course111[] courses;
    private static int courseCount;
    private static final int MAX_COURSES = 200;

    private static JFrame currentFrame;

    public static void main(String[] args) {
        
        professors = new Professor111[MAX_PROFESSORS];
        professorCount = 0;
        students = new Student111[MAX_STUDENTS];
        studentCount = 0;
        courses = new Course111[MAX_COURSES];
        courseCount = 0;

        SwingUtilities.invokeLater(MiniProject3::initializeAndShowGUI);
    }

    private static void initializeAndShowGUI() {
        initializeSampleData();
        showInitialRoleSelection();
    }

    private static void initializeSampleData() {
       
        if (professorCount < MAX_PROFESSORS) {
            professors[professorCount++] = new Professor111("p001", "Smith", "smith@uni.edu", "smith123");
        }
        if (professorCount < MAX_PROFESSORS) {
            professors[professorCount++] = new Professor111("p002", "Girish", "girish@uni.edu", "girish123");
        }

        if (studentCount < MAX_STUDENTS) {
            students[studentCount++] = new Student111("s001", "John", "John@student.edu", "john123");
        }
        if (studentCount < MAX_STUDENTS) {
            students[studentCount++] = new Student111("s002", "Bob", "bob@student.edu", "bob123");
        }

        if (courseCount < MAX_COURSES) {
            courses[courseCount++] = new Course111("CS101", "Core Java", "p001");
        }
        if (courseCount < MAX_COURSES) {
            courses[courseCount++] = new Course111("CS201", "Data Structures", "p002");
        }
    }

    private static void showInitialRoleSelection() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        JFrame frame = new JFrame("Course Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); 
        frame.setLayout(new GridLayout(0, 1, 10, 10)); 
        frame.setLocationRelativeTo(null); 

        JLabel label = new JLabel("Welcome To Course Management System", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        JButton adminButton = new JButton("Admin");
        JButton professorButton = new JButton("Professor");
        JButton studentButton = new JButton("Student");
        JButton exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        adminButton.setFont(buttonFont);
        professorButton.setFont(buttonFont);
        studentButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        adminButton.setBackground(new Color(173, 216, 230));
        professorButton.setBackground(new Color(152, 251, 152)); 
        studentButton.setBackground(new Color(255, 223, 186)); 
        exitButton.setBackground(Color.LIGHT_GRAY);

        adminButton.setForeground(Color.BLACK);
        professorButton.setForeground(Color.BLACK);
        studentButton.setForeground(Color.BLACK);
        exitButton.setForeground(Color.BLACK);

        adminButton.addActionListener(e -> adminLogin());
        professorButton.addActionListener(e -> professorLogin());
        studentButton.addActionListener(e -> studentMenu());
        exitButton.addActionListener(e -> {
            System.out.println("Exiting system. Goodbye!");
            System.exit(0);
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 50, 5, 50); 

        panel.add(label, gbc);
        panel.add(adminButton, gbc);
        panel.add(professorButton, gbc);
        panel.add(studentButton, gbc);
        panel.add(exitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        currentFrame = frame;
    }

    private static void adminLogin() {
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Admin Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            for (String[] credentials : adminCredentials) {
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    JOptionPane.showMessageDialog(currentFrame, "Login successful!");
                    adminMenu();
                    return;
                }
            }
            JOptionPane.showMessageDialog(currentFrame, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void adminMenu() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        JFrame frame = new JFrame("Admin Menu");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(0, 1, 10, 10)); 
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                    showInitialRoleSelection();
                }
            }
        });

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        JButton addProfessorButton = new JButton("Add Professor");
        addProfessorButton.setFont(buttonFont);
        addProfessorButton.addActionListener(e -> addProfessor());

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.setFont(buttonFont);
        addStudentButton.addActionListener(e -> addStudent());

        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.setFont(buttonFont);
        addCourseButton.addActionListener(e -> addCourse());

        JButton viewProfessorsButton = new JButton("View All Professors");
        viewProfessorsButton.setFont(buttonFont);
        viewProfessorsButton.addActionListener(e -> viewAllProfessors());

        JButton viewStudentsButton = new JButton("View All Students");
        viewStudentsButton.setFont(buttonFont);
        viewStudentsButton.addActionListener(e -> viewAllStudents());

        JButton viewCoursesButton = new JButton("View All Courses");
        viewCoursesButton.setFont(buttonFont);
        viewCoursesButton.addActionListener(e -> viewAllCourses());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                showInitialRoleSelection();
            }
        });

        addProfessorButton.setBackground(Color.WHITE);
        addStudentButton.setBackground(Color.WHITE);
        addCourseButton.setBackground(Color.WHITE);
        viewProfessorsButton.setBackground(Color.WHITE);
        viewStudentsButton.setBackground(Color.WHITE);
        viewCoursesButton.setBackground(Color.WHITE);
        logoutButton.setBackground(Color.LIGHT_GRAY);

        addProfessorButton.setForeground(Color.BLACK);
        addStudentButton.setForeground(Color.BLACK);
        addCourseButton.setForeground(Color.BLACK);
        viewProfessorsButton.setForeground(Color.BLACK);
        viewStudentsButton.setForeground(Color.BLACK);
        viewCoursesButton.setForeground(Color.BLACK);
        logoutButton.setForeground(Color.BLACK);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 60, 8, 60);

        panel.add(new JLabel("Admin Options:", JLabel.CENTER), gbc);
        panel.add(addProfessorButton, gbc);
        panel.add(addStudentButton, gbc);
        panel.add(addCourseButton, gbc);
        panel.add(viewProfessorsButton, gbc);
        panel.add(viewStudentsButton, gbc);
        panel.add(viewCoursesButton, gbc);
        panel.add(logoutButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        currentFrame = frame;
    }

    private static void addProfessor() {
        JTextField idField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Professor ID:"));
        panel.add(idField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Add New Professor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(currentFrame, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < professorCount; i++) {
                if (professors[i].getId().equals(id)) {
                    JOptionPane.showMessageDialog(currentFrame, "Professor with this ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Add professor to array if space is available
            if (professorCount < MAX_PROFESSORS) {
                professors[professorCount++] = new Professor111(id, name, email, password);
                JOptionPane.showMessageDialog(currentFrame, "Professor added successfully!");
            } else {
                JOptionPane.showMessageDialog(currentFrame, "Cannot add more professors. System limit reached.", "Capacity Full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void addStudent() {
        JTextField idField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Add New Student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(currentFrame, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId().equals(id)) {
                    JOptionPane.showMessageDialog(currentFrame, "Student with this ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (studentCount < MAX_STUDENTS) {
                students[studentCount++] = new Student111(id, name, email, password);
                JOptionPane.showMessageDialog(currentFrame, "Student added successfully!");
            } else {
                JOptionPane.showMessageDialog(currentFrame, "Cannot add more students. System limit reached.", "Capacity Full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void addCourse() {
        JTextField codeField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField professorIdField = new JTextField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Course Code:"));
        panel.add(codeField);
        panel.add(new JLabel("Course Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Professor ID (optional):"));
        panel.add(professorIdField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Add New Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String professorId = professorIdField.getText().trim();

            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(currentFrame, "Course Code and Name are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < courseCount; i++) {
                if (courses[i].getCode().equals(code)) {
                    JOptionPane.showMessageDialog(currentFrame, "Course with this code already exists!", "Duplicate Code", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!professorId.isEmpty()) {
                boolean professorExists = false;
                for (int i = 0; i < professorCount; i++) { // Iterate through professors array
                    if (professors[i].getId().equals(professorId)) {
                        professorExists = true;
                        break;
                    }
                }
                if (!professorExists) {
                    JOptionPane.showMessageDialog(currentFrame, "Professor with ID " + professorId + " does not exist! Course not added.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (courseCount < MAX_COURSES) {
                courses[courseCount++] = new Course111(code, name, professorId.isEmpty() ? null : professorId);
                JOptionPane.showMessageDialog(currentFrame, "Course added successfully!");
            } else {
                JOptionPane.showMessageDialog(currentFrame, "Cannot add more courses. System limit reached.", "Capacity Full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void viewAllProfessors() {
        StringBuilder sb = new StringBuilder("=== All Professors ===\n");
        if (professorCount == 0) { // Check count for emptiness
            sb.append("No professors found!");
        } else {
            for (int i = 0; i < professorCount; i++) { // Iterate up to count
                sb.append(professors[i]).append("\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "All Professors", JOptionPane.PLAIN_MESSAGE);
    }

    private static void viewAllStudents() {
        StringBuilder sb = new StringBuilder("=== All Students ===\n");
        if (studentCount == 0) { 
            sb.append("No students found!");
        } else {
            for (int i = 0; i < studentCount; i++) { 
                sb.append(students[i]).append("\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "All Students", JOptionPane.PLAIN_MESSAGE);
    }

    private static void viewAllCourses() {
        StringBuilder sb = new StringBuilder("=== All Courses ===\n");
        if (courseCount == 0) { 
            sb.append("No courses found!");
        } else {
            for (int i = 0; i < courseCount; i++) { 
                Course111 c = courses[i];
                String professorName = "Not Assigned";
                if (c.getProfessorId() != null && !c.getProfessorId().isEmpty()) {
                    for (int j = 0; j < professorCount; j++) { 
                        if (professors[j].getId().equals(c.getProfessorId())) {
                            professorName = professors[j].getName();
                            break;
                        }
                    }
                }
                sb.append("Course [Code: ").append(c.getCode()).append(", Name: ").append(c.getName())
                  .append(", Professor: ").append(professorName).append("]\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "All Courses", JOptionPane.PLAIN_MESSAGE);
    }

    private static void professorLogin() {
        JTextField idField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Professor ID:"));
        panel.add(idField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Professor Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String password = new String(passwordField.getPassword());

            for (int i = 0; i < professorCount; i++) {
                Professor111 p = professors[i];
                if (p.getId().equals(id) && p.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(currentFrame, "Login successful!");
                    professorMenu(p);
                    return;
                }
            }
            JOptionPane.showMessageDialog(currentFrame, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void professorMenu(Professor111 professor) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        JFrame frame = new JFrame("Professor Menu - " + professor.getName());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(0, 1, 10, 10));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(152, 251, 152));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                    showInitialRoleSelection();
                }
            }
        });

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        JButton viewMyCoursesButton = new JButton("View My Courses");
        viewMyCoursesButton.setFont(buttonFont);
        viewMyCoursesButton.addActionListener(e -> viewProfessorCourses(professor));

        JButton viewStudentsInMyCoursesButton = new JButton("View Students in My Courses");
        viewStudentsInMyCoursesButton.setFont(buttonFont);
        viewStudentsInMyCoursesButton.addActionListener(e -> viewStudentsInCourses(professor));

        JButton assignCourseButton = new JButton("Assign Myself to a Course");
        assignCourseButton.setFont(buttonFont);
        assignCourseButton.addActionListener(e -> enrollProfessorInCourse(professor));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                showInitialRoleSelection();
            }
        });

        viewMyCoursesButton.setBackground(Color.WHITE);
        viewStudentsInMyCoursesButton.setBackground(Color.WHITE);
        assignCourseButton.setBackground(Color.WHITE);
        logoutButton.setBackground(Color.LIGHT_GRAY);

        viewMyCoursesButton.setForeground(Color.BLACK);
        viewStudentsInMyCoursesButton.setForeground(Color.BLACK);
        assignCourseButton.setForeground(Color.BLACK);
        logoutButton.setForeground(Color.BLACK);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(152, 251, 152)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 60, 8, 60);

        panel.add(new JLabel("Professor Options:", JLabel.CENTER), gbc);
        panel.add(viewMyCoursesButton, gbc);
        panel.add(viewStudentsInMyCoursesButton, gbc);
        panel.add(assignCourseButton, gbc);
        panel.add(logoutButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        currentFrame = frame;
    }

    private static void viewProfessorCourses(Professor111 professor) {
        StringBuilder sb = new StringBuilder("=== Courses Taught by " + professor.getName() + " ===\n");
        boolean hasCourses = false;
        for (int i = 0; i < courseCount; i++) {
            Course111 c = courses[i];
            if (c.getProfessorId() != null && c.getProfessorId().equals(professor.getId())) {
                sb.append(c).append("\n");
                hasCourses = true;
            }
        }
        if (!hasCourses) {
            sb.append("You are not teaching any courses yet.");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "My Courses", JOptionPane.PLAIN_MESSAGE);
    }

    private static void viewStudentsInCourses(Professor111 professor) {
        StringBuilder sb = new StringBuilder("=== Students in My Courses ===\n");
        boolean hasCourses = false;

        for (int i = 0; i < courseCount; i++) { 
            Course111 course = courses[i];
            if (course.getProfessorId() != null && course.getProfessorId().equals(professor.getId())) {
                sb.append("\nCourse: ").append(course.getName()).append(" (").append(course.getCode()).append(")\n");
                hasCourses = true;

                boolean hasStudents = false;
                for (int j = 0; j < studentCount; j++) { // Iterate through students array
                    Student111 student = students[j];
                    if (student.isEnrolledInCourse(course.getCode())) {
                        sb.append(student).append("\n");
                        hasStudents = true;
                    }
                }
                if (!hasStudents) {
                    sb.append("   No students enrolled in this course.\n");
                }
            }
        }
        if (!hasCourses) {
            sb.append("You are not teaching any courses!");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 350));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "Students in My Courses", JOptionPane.PLAIN_MESSAGE);
    }

    private static void enrollProfessorInCourse(Professor111 professor) {
        StringBuilder sb = new StringBuilder("=== Available Courses to Assign Yourself To ===\n");
        if (courseCount == 0) {
            sb.append("No courses available for assignment!");
        } else {
            for (int i = 0; i < courseCount; i++) { 
                Course111 c = courses[i];
                if (c.getProfessorId() == null || !c.getProfessorId().equals(professor.getId())) {
                    String currentProfessor = "Not Assigned";
                    if (c.getProfessorId() != null && !c.getProfessorId().isEmpty()) {
                        for (int j = 0; j < professorCount; j++) { 
                            if (professors[j].getId().equals(c.getProfessorId())) {
                                currentProfessor = professors[j].getName();
                                break;
                            }
                        }
                    }
                    sb.append(c.getCode()).append(" - ").append(c.getName()).append(" (Currently assigned to: ").append(currentProfessor).append(")\n");
                }
            }
        }

        String courseCode = JOptionPane.showInputDialog(currentFrame, sb.toString() + "\nEnter Course Code to assign yourself to:");
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return; 
        }
        courseCode = courseCode.trim();

        for (int i = 0; i < courseCount; i++) { 
            Course111 course = courses[i];
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                if (course.getProfessorId() != null && course.getProfessorId().equals(professor.getId())) {
                    JOptionPane.showMessageDialog(currentFrame, "You are already assigned to this course!", "Already Assigned", JOptionPane.INFORMATION_MESSAGE);
                } else if (course.getProfessorId() != null && !course.getProfessorId().isEmpty()) {
                    // Course is assigned to another professor
                    int confirm = JOptionPane.showConfirmDialog(currentFrame,
                                    "This course is currently assigned to another professor. Do you want to reassign it to yourself?",
                                    "Reassign Course?", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        course.setProfessorId(professor.getId());
                        JOptionPane.showMessageDialog(currentFrame, "You have been assigned to " + course.getName() + " successfully!");
                    }
                } else {
                    course.setProfessorId(professor.getId());
                    JOptionPane.showMessageDialog(currentFrame, "You have been assigned to " + course.getName() + " successfully!");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(currentFrame, "Course not found with code: " + courseCode, "Course Not Found", JOptionPane.ERROR_MESSAGE);
    }

    private static void studentMenu() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        JFrame frame = new JFrame("Student Options");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close manually
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1, 10, 10));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(255, 223, 186)); // Peach background for student initial menu

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showInitialRoleSelection(); // Go back to initial selection
            }
        });

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(buttonFont);
        registerButton.addActionListener(e -> studentRegister());

        JButton loginButton = new JButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.addActionListener(e -> studentLogin());

        JButton backButton = new JButton("Back to Role Selection");
        backButton.setFont(buttonFont);
        backButton.addActionListener(e -> showInitialRoleSelection());

        registerButton.setBackground(Color.WHITE);
        loginButton.setBackground(Color.WHITE);
        backButton.setBackground(Color.LIGHT_GRAY);

        registerButton.setForeground(Color.BLACK);
        loginButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.BLACK);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 223, 186)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 60, 8, 60);

        panel.add(new JLabel("Student:", JLabel.CENTER), gbc);
        panel.add(registerButton, gbc);
        panel.add(loginButton, gbc);
        panel.add(backButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        currentFrame = frame;
    }

    private static void studentRegister() {
        JTextField idField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Student Registration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(currentFrame, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId().equals(id)) {
                    JOptionPane.showMessageDialog(currentFrame, "Student with this ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (studentCount < MAX_STUDENTS) {
                students[studentCount++] = new Student111(id, name, email, password);
                JOptionPane.showMessageDialog(currentFrame, "Registration successful! You can now login.");
                // Optionally auto-login or go to login screen
                studentLogin();
            } else {
                JOptionPane.showMessageDialog(currentFrame, "Cannot register more students. System limit reached.", "Capacity Full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void studentLogin() {
        JTextField idField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(currentFrame, panel, "Student Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String password = new String(passwordField.getPassword());

            for (int i = 0; i < studentCount; i++) { // Iterate through students array
                Student111 s = students[i];
                if (s.getId().equals(id) && s.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(currentFrame, "Login successful!");
                    studentLoggedInMenu(s);
                    return;
                }
            }
            JOptionPane.showMessageDialog(currentFrame, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void studentLoggedInMenu(Student111 student) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        JFrame frame = new JFrame("Student Menu - " + student.getName());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(0, 1, 10, 10));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(255, 223, 186)); // Peach background for student

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                    showInitialRoleSelection();
                }
            }
        });

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        JButton viewAvailableCoursesButton = new JButton("View Available Courses");
        viewAvailableCoursesButton.setFont(buttonFont);
        viewAvailableCoursesButton.addActionListener(e -> viewAvailableCoursesForStudent(student));

        JButton enrollCourseButton = new JButton("Enroll in Course");
        enrollCourseButton.setFont(buttonFont);
        enrollCourseButton.addActionListener(e -> enrollStudentInCourse(student));

        JButton viewMyEnrolledCoursesButton = new JButton("View My Enrolled Courses");
        viewMyEnrolledCoursesButton.setFont(buttonFont);
        viewMyEnrolledCoursesButton.addActionListener(e -> viewStudentEnrolledCourses(student));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                showInitialRoleSelection();
            }
        });

        viewAvailableCoursesButton.setBackground(Color.WHITE);
        enrollCourseButton.setBackground(Color.WHITE);
        viewMyEnrolledCoursesButton.setBackground(Color.WHITE);
        logoutButton.setBackground(Color.LIGHT_GRAY);

        viewAvailableCoursesButton.setForeground(Color.BLACK);
        enrollCourseButton.setForeground(Color.BLACK);
        viewMyEnrolledCoursesButton.setForeground(Color.BLACK);
        logoutButton.setForeground(Color.BLACK);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 223, 186));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 60, 8, 60);

        panel.add(new JLabel("Student Options:", JLabel.CENTER), gbc);
        panel.add(viewAvailableCoursesButton, gbc);
        panel.add(enrollCourseButton, gbc);
        panel.add(viewMyEnrolledCoursesButton, gbc);
        panel.add(logoutButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        currentFrame = frame;
    }

    private static void viewAvailableCoursesForStudent(Student111 student) {
        StringBuilder sb = new StringBuilder("=== Available Courses ===\n");
        if (courseCount == 0) {
            sb.append("No courses available!");
        } else {
            for (int i = 0; i < courseCount; i++) { 
                Course111 c = courses[i];
                String professorName = "Not Assigned";
                if (c.getProfessorId() != null && !c.getProfessorId().isEmpty()) {
                    for (int j = 0; j < professorCount; j++) {
                        if (professors[j].getId().equals(c.getProfessorId())) {
                            professorName = professors[j].getName();
                            break;
                        }
                    }
                }
                sb.append("Course [Code: ").append(c.getCode()).append(", Name: ").append(c.getName())
                  .append(", Professor: ").append(professorName).append("]");
                if (student.isEnrolledInCourse(c.getCode())) {
                    sb.append(" (Enrolled)\n");
                } else {
                    sb.append("\n");
                }
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 350));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "Available Courses", JOptionPane.PLAIN_MESSAGE);
    }

    private static void enrollStudentInCourse(Student111 student) {
        String courseCode = JOptionPane.showInputDialog(currentFrame, "Enter Course Code to enroll in:");
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return; // User cancelled or entered nothing
        }
        courseCode = courseCode.trim();

        Course111 targetCourse = null;
        for (int i = 0; i < courseCount; i++) { 
            if (courses[i].getCode().equalsIgnoreCase(courseCode)) {
                targetCourse = courses[i];
                break;
            }
        }

        if (targetCourse == null) {
            JOptionPane.showMessageDialog(currentFrame, "Course not found with code: " + courseCode, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (student.isEnrolledInCourse(targetCourse.getCode())) {
            JOptionPane.showMessageDialog(currentFrame, "You are already enrolled in this course!", "Enrollment Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (student.enrollInCourse(targetCourse.getCode())) {
                JOptionPane.showMessageDialog(currentFrame, "Successfully enrolled in " + targetCourse.getName() + "!");
            } else {
                JOptionPane.showMessageDialog(currentFrame, "Could not enroll. Maximum course limit reached for student or other error.", "Enrollment Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void viewStudentEnrolledCourses(Student111 student) {
        StringBuilder sb = new StringBuilder("=== Your Enrolled Courses ===\n");
        String[] enrolledCourses = student.getEnrolledCourses();
        int enrolledCount = student.getEnrolledCourseCount(); 

        if (enrolledCount == 0) {
            sb.append("You are not enrolled in any courses yet.");
        } else {
            for (int i = 0; i < enrolledCount; i++) { 
                String courseCode = enrolledCourses[i];
                Course111 enrolledCourse = null;
                for (int j = 0; j < courseCount; j++) { // Find course details
                    if (courses[j].getCode().equals(courseCode)) {
                        enrolledCourse = courses[j];
                        break;
                    }
                }
                if (enrolledCourse != null) {
                    sb.append(enrolledCourse.getCode()).append(" - ").append(enrolledCourse.getName()).append("\n");
                } else {
                    sb.append(courseCode).append(" - (Details not found)\n");
                }
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(currentFrame, scrollPane, "My Enrolled Courses", JOptionPane.PLAIN_MESSAGE);
    }
}

class Professor111 {
    private String id;
    private String name;
    private String email;
    private String password;

    public Professor111(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Professor [ID: " + id + ", Name: " + name + ", Email: " + email + "]";
    }
}

class Student111 {
    private String id;
    private String name;
    private String email;
    private String password;
    private String[] enrolledCourses;
    private int enrolledCourseCount; 
    private static final int MAX_ENROLLED_COURSES = 50;

    public Student111(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enrolledCourses = new String[MAX_ENROLLED_COURSES]; 
        this.enrolledCourseCount = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean enrollInCourse(String courseCode) {
        if (enrolledCourseCount < MAX_ENROLLED_COURSES) {
            for (int i = 0; i < enrolledCourseCount; i++) {
                if (enrolledCourses[i].equals(courseCode)) {
                    return false; 
                }
            }
            enrolledCourses[enrolledCourseCount++] = courseCode;
            return true;
        }
        return false; 
    }

    public boolean isEnrolledInCourse(String courseCode) {
        for (int i = 0; i < enrolledCourseCount; i++) {
            if (enrolledCourses[i].equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public String[] getEnrolledCourses() {
        String[] currentCourses = new String[enrolledCourseCount];
        for (int i = 0; i < enrolledCourseCount; i++) {
            currentCourses[i] = enrolledCourses[i];
        }
        return currentCourses;
    }

    public int getEnrolledCourseCount() {
        return enrolledCourseCount;
    }

    @Override
    public String toString() {
        return "Student [ID: " + id + ", Name: " + name + ", Email: " + email + "]";
    }
}

class Course111 {
    private String code;
    private String name;
    private String professorId; // Can be null if not assigned

    public Course111(String code, String name, String professorId) {
        this.code = code;
        this.name = name;
        this.professorId = professorId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @Override
    public String toString() {
        String profStatus = (professorId == null || professorId.isEmpty()) ? "Not Assigned" : "Assigned to Professor ID: " + professorId;
        return "Course [Code: " + code + ", Name: " + name + ", " + profStatus + "]";
    }
}