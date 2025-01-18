package es.Miulpgc.software.architecture.view;




import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageDisplay {

    BufferedImage Show(String path) throws IOException;

}
