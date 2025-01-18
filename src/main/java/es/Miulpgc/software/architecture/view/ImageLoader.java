package es.Miulpgc.software.architecture.view;


import es.Miulpgc.software.architecture.model.Image;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageLoader {

    BufferedImage Load(Image image) throws IOException;
}
