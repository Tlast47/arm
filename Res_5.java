import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//////////
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
//////////
/**
 *
 * @author sitap
 */
public class Res_5 extends javax.swing.JFrame {

    ///////////////////////
    private JButton  favoriteButton;
    private String imageName = "res_5.jpg"; 
    ///////////////////////

    public Res_5() {
        initComponents();
    }

    ///////////////////////////
    private void toggleFavorite() {
        try {
            List<String> favorites = new ArrayList<>(Files.readAllLines(Paths.get("favorites.txt")));
        
            if (favorites.contains(imageName)) {
                favorites.remove(imageName);
                System.out.println("Removed from favorites");
            } else {
                favorites.add(imageName);
                System.out.println("Added to favorites");
            }

            Files.write(Paths.get("favorites.txt"), favorites, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ///////////////////////////

    @SuppressWarnings("unchecked")

    private void initComponents() {

    	img_5 = new javax.swing.JLabel();
        res_5 = new javax.swing.JLabel();
        booking = new javax.swing.JButton();
        home = new javax.swing.JLabel();

        ////////////////////
        favoriteButton = new JButton("FA");
        ////////////////////

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(413, 735));
        setSize(new java.awt.Dimension(413, 735));

        img_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res_5re.jpg"))); // NOI18N

        res_5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        res_5.setText("Res_5");

        //////////
        favoriteButton.setPreferredSize(new java.awt.Dimension(50, 40));
        //////////

        booking.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        booking.setText("Booking");
        booking.setPreferredSize(new java.awt.Dimension(222, 40));
        booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingActionPerformed(evt);
            }
        });

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home.jpg"))); // NOI18N
        
        home.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		MainFrame mf = new MainFrame();
                mf.setVisible(true);
                dispose();
        	}
        });

        ///////////
        favoriteButton.addActionListener(e -> toggleFavorite());
        ///////////

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                /////////////
                .addComponent(favoriteButton,javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16,16,16)
                ////////////
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(booking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img_5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(res_5)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(img_5)
                .addGap(18, 18, 18)
                .addComponent(res_5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 476, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(booking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    /////////////
                    .addComponent(favoriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    /////////////
                    .addComponent(home))
                .addGap(49, 49, 49))
        );

        pack();
    }                     

    private void bookingActionPerformed(java.awt.event.ActionEvent evt) {                                        

    	Booking b = new Booking();
        b.setVisible(true);
        dispose();
    }                                       

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    	try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "Failed to apply look-and-feel.", "Error", JOptionPane.ERROR_MESSAGE);
        }


        java.awt.EventQueue.invokeLater(() -> {
            new Res_5().setVisible(true);
        });
    }
              
    private javax.swing.JButton booking;
    private javax.swing.JLabel home;
    private javax.swing.JLabel img_5;
    private javax.swing.JLabel res_5;
               
}
