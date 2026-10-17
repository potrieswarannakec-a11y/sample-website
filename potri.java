
import javax.swing.*;
import java.awt.*;

public class ContactForm extends JFrame {

    JTextField nameField, emailField;
    JTextArea messageArea;
    JCheckBox english, math, science, history;
    JRadioButton male, female;
    JComboBox<String> countryBox;

    ContactForm() {
        setTitle("Contact Form");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Contact Form");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(title);

        panel.add(new JLabel("Share your details and choose your options"));
        panel.add(Box.createVerticalStrut(20));

        panel.add(new JLabel("Name"));
        nameField = new JTextField(25);
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Email"));
        emailField = new JTextField(25);
        panel.add(emailField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Message"));
        messageArea = new JTextArea(4, 25);
        messageArea.setLineWrap(true);
        panel.add(new JScrollPane(messageArea));
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Courses of Interest"));

        english = new JCheckBox("English");
        math = new JCheckBox("Math");
        science = new JCheckBox("Science");
        history = new JCheckBox("History");

        panel.add(english);
        panel.add(math);
        panel.add(science);
        panel.add(history);

        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Gender"));

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        panel.add(male);
        panel.add(female);

        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Country"));

        String[] countries = {
            "Select a country", "India", "United States",
            "United Kingdom", "Canada", "Australia"
        };

        countryBox = new JComboBox<>(countries);
        panel.add(countryBox);

        panel.add(Box.createVerticalStrut(20));

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);

        submitButton.addActionListener(e -> submitForm());

        add(new JScrollPane(panel));
        setVisible(true);
    }

    void submitForm() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String message = messageArea.getText().trim();

        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please fill in all required fields."
            );
            return;
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            JOptionPane.showMessageDialog(this, "Enter a valid email.");
            return;
        }

        if (!male.isSelected() && !female.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select your gender.");
            return;
        }

        if (countryBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a country.");
            return;
        }

        String gender = male.isSelected() ? "Male" : "Female";

        StringBuilder courses = new StringBuilder();

        if (english.isSelected()) courses.append("English ");
        if (math.isSelected()) courses.append("Math ");
        if (science.isSelected()) courses.append("Science ");
        if (history.isSelected()) courses.append("History ");

        String result = "Name: " + name
            + "\nEmail: " + email
            + "\nMessage: " + message
            + "\nCourses: " + courses
            + "\nGender: " + gender
            + "\nCountry: " + countryBox.getSelectedItem();

        JOptionPane.showMessageDialog(
            this, result, "Form Submitted", JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactForm());
    }
}
