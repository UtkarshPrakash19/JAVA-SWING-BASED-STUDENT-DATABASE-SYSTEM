import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystemGUI extends JFrame {
    private List<Student> students;
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea studentListArea;

    public StudentManagementSystemGUI() {
        students = new ArrayList<>();

        setTitle("Student Management System");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1)); 

        JPanel namePanel = new JPanel();
        JPanel rollNumberPanel = new JPanel();
        JPanel gradePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        nameField = new JTextField(20);
        rollNumberField = new JTextField(20);
        gradeField = new JTextField(20);

        JLabel nameLabel = new JLabel("Name:");
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberPanel.add(rollNumberLabel);
        rollNumberPanel.add(rollNumberField);

        JLabel gradeLabel = new JLabel("Grade:");
        gradePanel.add(gradeLabel);
        gradePanel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton editButton = new JButton("Edit Student");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        inputPanel.add(namePanel);
        inputPanel.add(rollNumberPanel);
        inputPanel.add(gradePanel);
        inputPanel.add(buttonPanel);

        studentListArea = new JTextArea();
        studentListArea.setEditable(false);

        add(inputPanel, BorderLayout.WEST);
        add(new JScrollPane(studentListArea), BorderLayout.CENTER);
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumberStr = rollNumberField.getText();
        String grade = gradeField.getText();

        if (name.isEmpty() || rollNumberStr.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = new Student(name, rollNumber, grade);
            students.add(student);
            clearFields();
            JOptionPane.showMessageDialog(this, "Student added successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number. Please enter a number.");
        }
    }

    private void editStudent() {
        String rollNumberStr = rollNumberField.getText();
        if (rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Roll Number to edit a student.");
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = findStudentByRollNumber(rollNumber);

            if (student != null) {
                String name = nameField.getText();
                String grade = gradeField.getText();

                student.setName(name);
                student.setGrade(grade);

                JOptionPane.showMessageDialog(this, "Student information updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found with the provided Roll Number.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number. Please enter a number.");
        }
    }

    private void searchStudent() {
        String rollNumberStr = rollNumberField.getText();
        if (rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Roll Number to search for a student.");
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = findStudentByRollNumber(rollNumber);

            if (student != null) {
                nameField.setText(student.getName());
                gradeField.setText(student.getGrade());
                JOptionPane.showMessageDialog(this, "Student found.");
            } else {
                clearFields();
                JOptionPane.showMessageDialog(this, "Student not found with the provided Roll Number.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number. Please enter a number.");
        }
    }

    private void displayStudents() {
        studentListArea.setText("Student List:\n\n");
        for (Student student : students) {
            studentListArea.append("Name: " + student.getName() + "\n");
            studentListArea.append("Roll Number: " + student.getRollNumber() + "\n");
            studentListArea.append("Grade: " + student.getGrade() + "\n\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    private Student findStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystemGUI sms = new StudentManagementSystemGUI();
            sms.setVisible(true);
        });
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystemGUI sms = new StudentManagementSystemGUI();
            sms.setVisible(true);
        });
    }
}
