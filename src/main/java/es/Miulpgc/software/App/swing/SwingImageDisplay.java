package es.Miulpgc.software.App.swing;

import es.Miulpgc.software.architecture.view.ImageDisplay;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay implements ImageDisplay {



    @Override
    public BufferedImage Show(String path) throws IOException {
        return ImageIO.read(new File(path));

    }
}
