package es.Miulpgc.software.mocks.swing;

import es.Miulpgc.software.architecture.model.Image;
import es.Miulpgc.software.architecture.view.ImageLoader;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SwingImageLoader implements ImageLoader {



    @Override
    public BufferedImage Load(Image image) throws IOException {
         SwingImageDisplay imageDisplay = new SwingImageDisplay();
         return imageDisplay.Show(image.path());

    }
}
