package es.Miulpgc.software.presenter;

import es.Miulpgc.software.model.ImageSet;

public interface command {

    ImageSet execute(ImageSet imageSet);
}
