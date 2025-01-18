package es.Miulpgc.software.architecture.presenter;

import es.Miulpgc.software.architecture.model.ImageSet;

public class NextImageCommand implements  command {
    @Override
    public int execute(ImageSet imageSet) {
        if (imageSet.getCurrentImageIndex() + 1 >= imageSet.getImages().size()) {
            return 0;

        } else {
            return (imageSet.getCurrentImageIndex() + 1);

        }

    }

}
