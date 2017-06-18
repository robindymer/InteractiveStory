package robindymer.interactivestory.model;

public class Choice {
    // Each choice will consist of text to describe choice and a page number destination
    // int because of string resource
    private int textId;
    private int nextPage;

    // Constructor - Initialize objects from the class blueprint
    public Choice(int textId, int nextPage) {
        this.textId = textId;
        this.nextPage = nextPage;
    }

    // Getters and setters. Allows us to customize at anytime if we want to change how the information
    // is retrieved or stored
    // get - Get the value of something
    public int getTextId() {
        return textId;
    }
    // set - Set the value of something
    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
