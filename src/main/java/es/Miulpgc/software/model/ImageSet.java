package es.Miulpgc.software.model;


import java.util.Set;

public class ImageSet{

    private Set<Image> images;
    private int currentImageIndex;

    public ImageSet(Set<Image> images, int currentImageIndex) {
        this.images = images;
        this.currentImageIndex = currentImageIndex;
    }

    public Set<Image> getImages() {
        return images;
    }

    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public Image getCurrentImage() {
        return images.stream().toList().get(currentImageIndex);
    }

    public Image nextImage() {
        return images.stream()
                .toList()
                .get((currentImageIndex + 1) % images.size());

    }

    public Image previousImage() {
        return images.stream()
                .toList()
                .get((currentImageIndex - 1) % images.size());

    }
}
