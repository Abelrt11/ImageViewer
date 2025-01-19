package es.Miulpgc.software.architecture.model;


import java.util.List;


public class ImageSet{

    private List<Image> images;
    private int currentImageIndex;

    public ImageSet(List<Image> images, int currentImageIndex) {
        this.images = images;
        this.currentImageIndex = currentImageIndex;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setCurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }

    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public Image getCurrentImage() {
        return images.get(currentImageIndex);
    }

    public Image getNextImage() {
        if(currentImageIndex < images.size() - 1) {
            return images.get(currentImageIndex + 1);
        } else {
            return images.get(0);
        }


    }

    public Image getPreviousImage() {
        if(currentImageIndex > 0) {
            return images.get(currentImageIndex - 1);
        } else {
            return images.get(images.size() - 1);
        }
    }
}

