package es.Miulpgc.software.presenter;

import es.Miulpgc.software.model.ImageSet;

public class PreviousImageCommand implements  command {
    @Override
    public ImageSet execute(ImageSet imageSet) {
        if (imageSet.getCurrentImageIndex() - 1 < 0) {
            int newIndex = imageSet.getImages().size();
            return new ImageSet(imageSet.getImages(), newIndex);
        } else {
            int newIndex = (imageSet.getCurrentImageIndex() - 1) % imageSet.getImages().size();
            return new ImageSet(imageSet.getImages(), newIndex);
        }

    }
}
