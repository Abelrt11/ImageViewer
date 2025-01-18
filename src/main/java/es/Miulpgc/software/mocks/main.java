package es.Miulpgc.software.mocks;

import es.Miulpgc.software.architecture.model.Image;
import es.Miulpgc.software.architecture.model.ImageSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main {

    public static void main(String[] args) throws IOException {
        ImageSet images = createImageSet();
        MainFrame frame = new MainFrame(images);
        frame.setVisible(true);
    }

    private static ImageSet createImageSet() {
        List<Image> images = new ArrayList<>();
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/Elden_ring.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/ErdTree.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/GraceSite.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/Hailightree.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/horseWalk.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/Ranni.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/StormVeilCastle.jpg"));
        images.add(new Image("src/main/resources/Elden_Ring_wallPapers/StormveilCastleFront.jpg"));
        return new ImageSet(images, 0);
    }
}
