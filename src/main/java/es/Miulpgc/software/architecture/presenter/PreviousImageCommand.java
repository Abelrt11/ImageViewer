package es.Miulpgc.software.architecture.presenter;

import es.Miulpgc.software.architecture.model.ImageSet;

public class PreviousImageCommand implements  command {
    @Override
    public int execute(ImageSet imageSet) {
        if (imageSet.getCurrentImageIndex() - 1 < 0) {
            return imageSet.getImages().size() - 1;

        } else {
            return  (imageSet.getCurrentImageIndex() - 1);

        }

    }
}
