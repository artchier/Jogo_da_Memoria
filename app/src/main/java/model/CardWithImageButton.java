package model;

import android.widget.ImageButton;

public class CardWithImageButton {
    private int alternativeBackground;

    private ImageButton imageButton;

    public CardWithImageButton(int alternativeBackground, ImageButton imageButton) {
        setAlternativeBackground(alternativeBackground);
        setImageButton(imageButton);
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public int getAlternativeBackground() {
        return alternativeBackground;
    }

    public void setAlternativeBackground(int alternativeBackground) {
        this.alternativeBackground = alternativeBackground;
    }

    public boolean compara(int alternativeBackground) {
        return this.alternativeBackground == alternativeBackground;
    }
}
