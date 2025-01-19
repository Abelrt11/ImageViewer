package es.Miulpgc.software.App;

import es.Miulpgc.software.architecture.model.ImageSet;
import es.Miulpgc.software.architecture.presenter.NextImageCommand;
import es.Miulpgc.software.architecture.presenter.PreviousImageCommand;
import es.Miulpgc.software.App.swing.SwingImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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



        this.addMouseListener(new MouseAdapter() {
            public int startX;
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int deltaX = e.getX() - startX;
                if (Math.abs(deltaX) > getWidth() / 2) {
                    boolean isBackward = deltaX > 0;
                    changeImage(imageLabel, isBackward);
                } else {
                    resetImagePosition(imageLabel);
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            private int startX;
            @Override
            public void mouseMoved(MouseEvent e) {
                startX = e.getX();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (startX != e.getX()) {
                    int deltaX = e.getX() - startX;
                    imageLabel.setLocation(deltaX, imageLabel.getY());
                }
            }
        });
    }

    private void changeImage(JLabel imageLabel, boolean isBackward) {

        if (isBackward) {
            PreviousImageCommand command = new PreviousImageCommand();
            images.setCurrentImageIndex(command.execute(images));
        } else {
            NextImageCommand command = new NextImageCommand();
            images.setCurrentImageIndex(command.execute(images));
        }


        SwingImageLoader loader = new SwingImageLoader();
        BufferedImage newImage;
        try {
            newImage = loader.Load(images.getCurrentImage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        imageLabel.setIcon(updateImage(newImage));
        imageLabel.setLocation(0, imageLabel.getY());
        repaint();
    }

    private void resetImagePosition(JLabel imageLabel) {
        Timer timer = new Timer(10, null);
        final int[] offset = {imageLabel.getX()};
        int direction = offset[0] > 0 ? -1 : 1;

        timer.addActionListener(e -> {
            offset[0] += 10 * direction;
            imageLabel.setLocation(offset[0], imageLabel.getY());

            if (Math.abs(offset[0]) <= 10) {
                timer.stop();
                imageLabel.setLocation(0, imageLabel.getY());
            }
        });

        timer.start();
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