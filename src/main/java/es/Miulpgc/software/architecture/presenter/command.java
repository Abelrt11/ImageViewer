package es.Miulpgc.software.architecture.presenter;

import es.Miulpgc.software.architecture.model.ImageSet;

public interface command {

    int execute(ImageSet imageSet);
}
