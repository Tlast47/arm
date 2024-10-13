import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Favorites extends JFrame {
    private JPanel panel;

    public Favorites() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel("Favorites", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); 
        panel.add(titleLabel); 

        List<String> favoriteImages;
        try {
            favoriteImages = Files.readAllLines(Paths.get("favorites.txt"));
           
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading favorites.txt: " + e.getMessage());
            return;
        }

        // เพิ่มรูปภาพและชื่อจาก favorites.txt ใน JPanel
        for (String fileName : favoriteImages) {
            fileName = fileName.trim(); // ตัดช่องว่างออก
            if (fileName.endsWith(".jpg")) {
                // ตรวจสอบว่าไฟล์รูปภาพมีอยู่
                if (!new java.io.File(fileName).exists()) {
                    JOptionPane.showMessageDialog(null, "Image file not found: " + fileName);
                    continue;
                }

                // สร้าง JLabel สำหรับรูปภาพ
                JLabel imageLabel = new JLabel(new ImageIcon(fileName));
                String displayName;
                int lastDotIndex = fileName.lastIndexOf("."); // หาตำแหน่งจุดสุดท้าย
                if (lastDotIndex != -1) {
                    displayName = fileName.substring(0, lastDotIndex); // ใช้ชื่อไฟล์ที่ไม่มีนามสกุล
                } else {
                    displayName = fileName; // ถ้าไม่มีจุด ให้ใช้ชื่อไฟล์ทั้งหมด
                }

                imageLabel.setText(displayName);
                imageLabel.setHorizontalTextPosition(JLabel.CENTER);
                imageLabel.setVerticalTextPosition(JLabel.BOTTOM);

                imageLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        openResFile(displayName);
                        dispose();
                    }
                });

                panel.add(imageLabel);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid image file: " + fileName);
            }
        }

        add(panel);
        setSize(413, 735);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void openResFile(String fileName) {
        try {
            String className = fileName.substring(fileName.lastIndexOf('/') + 1);
            className = className.substring(0, className.lastIndexOf(".")); 
            className = className.substring(0, 1).toUpperCase() + className.substring(1); // เปลี่ยนตัวอักษรตัวแรกเป็นตัวใหญ่ (Res_x)
            Class<?> resClass = Class.forName(className); 
            JFrame resInstance = (JFrame) resClass.getDeclaredConstructor().newInstance();
            resInstance.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error opening Res file: " + fileName);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Favorites().setVisible(true));
    }
}
