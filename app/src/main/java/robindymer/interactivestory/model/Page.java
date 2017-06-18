package robindymer.interactivestory.model;

public class Page {
    // private - Only accessed through methods, OOP principle that helps to hide the details
    // of how the data is actually stored in the object. Also knows as encapsulation
    // int because of string resource
    private int imageId;
    private int textId;
    // Choice - Our own model
    private Choice choice1;
    private Choice choice2;
    private boolean isFinalPage = false;

    public Page(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
        this.isFinalPage = true;
    }

    // Constructor that will populate a new page object with all 4 settings
    public Page(int imageId, int textId, Choice choice1, Choice choice2) {
        this.imageId = imageId;
        this.textId = textId;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public boolean isFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(boolean finalPage) {
        isFinalPage = finalPage;
    }

    // Getters and setters. Allows us to customize at anytime if we want to change how the information
    // is retrieved or stored
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

    public Choice getChoice2() {
        return choice2;
    }

    public void setChoice2(Choice choice2) {
        this.choice2 = choice2;
    }
}
