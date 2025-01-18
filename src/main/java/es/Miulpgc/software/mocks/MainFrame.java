package es.Miulpgc.software.mocks;

import es.Miulpgc.software.architecture.model.ImageSet;
import es.Miulpgc.software.architecture.presenter.NextImageCommand;
import es.Miulpgc.software.architecture.presenter.PreviousImageCommand;
import es.Miulpgc.software.mocks.swing.SwingImageDisplay;
import es.Miulpgc.software.mocks.swing.SwingImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MainFrame extends javax.swing.JFrame {

    private ImageSet images;

    public MainFrame(ImageSet images) throws IOException {
        this.images = images;
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        SwingImageLoader imageIconLoader = new SwingImageLoader();
        BufferedImage image = imageIconLoader.Load(this.getImages().getCurrentImage());
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        this.add(imageLabel, BorderLayout.CENTER);

        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingImageLoader imageIconLoader = new SwingImageLoader();
                BufferedImage image = null;
                try {
                    image = imageIconLoader.Load(images.getCurrentImage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                imageLabel.setIcon(updateImage(image));
                repaint();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NextImageCommand command = new NextImageCommand();
                images.setCurrentImageIndex(command.execute(getImages()));
                SwingImageLoader imageIconLoader = new SwingImageLoader();
                BufferedImage image = null;
                try {
                    image = imageIconLoader.Load(images.getCurrentImage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                imageLabel.setIcon(updateImage(image));
                repaint();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreviousImageCommand command = new PreviousImageCommand();
                images.setCurrentImageIndex(command.execute(getImages()));
                SwingImageLoader imageIconLoader = new SwingImageLoader();
                BufferedImage image = null;
                try {
                    image = imageIconLoader.Load(images.getCurrentImage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                imageLabel.setIcon(updateImage(image));
                repaint();
            }
        });
    }

    private ImageIcon updateImage(BufferedImage originalImage) {
        int width = this.getContentPane().getWidth();
        int height = this.getContentPane().getHeight();

        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(scaledImage);

    }

    private ImageSet getImages() {
        return images;
    }
}