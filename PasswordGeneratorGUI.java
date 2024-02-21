import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGeneratorGUI extends JFrame implements ActionListener 
{
    private JTextField lengthField;
    private JCheckBox lowercaseBox, uppercaseBox, digitsBox, specialBox;
    private JTextArea passwordArea;

    public PasswordGeneratorGUI() 
    {
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel lengthLabel = new JLabel("Password Length:");
        lengthField = new JTextField();
        panel.add(lengthLabel);
        panel.add(lengthField);

        lowercaseBox = new JCheckBox("Include Lowercase");
        uppercaseBox = new JCheckBox("Include Uppercase");
        digitsBox = new JCheckBox("Include Digits");
        specialBox = new JCheckBox("Include Special Characters");
        panel.add(lowercaseBox);
        panel.add(uppercaseBox);
        panel.add(digitsBox);
        panel.add(specialBox);

        JButton generateButton = new JButton("Generate Password");
        generateButton.addActionListener(this);
        panel.add(generateButton);

        passwordArea = new JTextArea();
        passwordArea.setEditable(false);
        panel.add(new JScrollPane(passwordArea));

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int length;
        try {
            length = Integer.parseInt(lengthField.getText());
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid length. Please enter a valid number.");
            return;
        }

        boolean includeLowercase = lowercaseBox.isSelected();
        boolean includeUppercase = uppercaseBox.isSelected();
        boolean includeDigits = digitsBox.isSelected();
        boolean includeSpecial = specialBox.isSelected();

        if (!includeLowercase && !includeUppercase && !includeDigits && !includeSpecial)
        {
            JOptionPane.showMessageDialog(this, "Please select at least one character set.");
            return;
        }

        String password = generatePassword(length, includeLowercase, includeUppercase, includeDigits, includeSpecial);
        passwordArea.setText(password);
    }

    private String generatePassword(int length, boolean includeLowercase, boolean includeUppercase, boolean includeDigits, boolean includeSpecial) 
    {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String charactersToUse = "";
        if (includeLowercase) charactersToUse += "abcdefghijklmnopqrstuvwxyz";
        if (includeUppercase) charactersToUse += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (includeDigits) charactersToUse += "0123456789";
        if (includeSpecial) charactersToUse += "!@#$%^&*()-_=+<>?";

        for (int i = 0; i < length; i++) 
        {
            int randomIndex = random.nextInt(charactersToUse.length());
            password.append(charactersToUse.charAt(randomIndex));
        }

        return password.toString();
    }

    public static void main(String[] args)
    {
        new PasswordGeneratorGUI();
    }
}
