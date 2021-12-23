/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAL.DBConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author CodeBlue
 */
public class SplashScreen extends JWindow {

    private int percent = 0;
    private Timer t;
    private JProgressBar progessBar;
    private JLabel splashImage;
    private JLabel lb;

    public void showSplash() {
        JPanel content = (JPanel) getContentPane();
        int width = 660;
        int height = 220;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        splashImage = new JLabel();
        splashImage.setIcon(new ImageIcon(getClass().getResource("/Images/backgroud660x220.jpg")));
        lb = new JLabel();
        lb.setForeground(new Color(120, 113, 113));
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText("Kiểm tra kết nối CSDL      ");
        progessBar = new javax.swing.JProgressBar();
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                percent += (Math.random() * 10);
                if (percent > 10) {
                    lb.setText("Đang kiểm tra.     ");
                }
                if (percent > 20) {
                    lb.setText("Ráng đợi tí..    ");
                }
                if (percent > 30) {
                    lb.setText("Chuẩn bị kết nối nè...   ");
                }
                if (percent > 50) {
                    try {
                        if (DBConnect.getConnect()!= null) {
                            lb.setText("Kết nối.... OK rồi đó!");
                        }
                    } catch (Exception ex) {
                        Untils.MessBox.alert(null, "Không thể kết nối CSDL! \n Vui lòng kiểm tra lại!");
                    }
                }
                if (percent >= 100) {
                    t.stop();
                    progessBar.setValue(100);
                    dispose();
                    new form_DangNhap().setVisible(true);
                }
                progessBar.setValue(percent);
            }
        };
        t = new Timer(200, action);
        progessBar.setValue(0);
        t.start();
        content.add(splashImage, BorderLayout.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(lb, BorderLayout.PAGE_START);
        panel.add(progessBar, BorderLayout.PAGE_END);
        content.add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
