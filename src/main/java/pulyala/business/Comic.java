/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulyala.business;

/**
 * Business Logic for Comic Book Want List.
 * 
 * @author rohit
 */
public class Comic implements java.io.Serializable {
    private int id;
    private String title;
    private int issueNumber;
    private String publisher;
    private String author;
    private String illustrator;
    private boolean isVariant;
    private String storeName;
    private String ownerName;
    private String storeInfo;
    
    // First Constructor
    public Comic(String title, int issueNumber, String storeName, String storeInfo) {
        this.title = title;
        this.issueNumber = issueNumber;
        this.storeName = (storeName == null) ? "N/A" : storeName;
        this.storeInfo = (storeInfo == null) ? "None provided" : storeInfo;
    }
    
    // Current Constructor
    public Comic(int id, String title, int issueNumber, String storeName, String storeInfo) {
        this.id = id;
        this.title = title;
        this.issueNumber = issueNumber;
        this.storeName = (storeName == null) ? "N/A" : storeName;
        this.storeInfo = (storeInfo == null) ? "None provided" : storeInfo;
    }
    
    // Future Constructor
    public Comic(int id, String title, int issueNumber, String publisher, String author, String illustrator, boolean isVariant, String storeName, String ownerName, String storeInfo) {
        this.id = id;
        this.title = title;
        this.issueNumber = issueNumber;
        this.publisher = publisher;
        this.author = author;
        this.illustrator = illustrator;
        this.isVariant = isVariant;
        this.storeName = (storeName == null) ? "N/A" : storeName;
        this.ownerName = (ownerName == null) ? "Author Unknown" : ownerName;
        this.storeInfo = (storeInfo == null) ? "None provided" : storeInfo;
    }
    
    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getIssueNumber() { return issueNumber; }
    public String getPublisher() { return title; }
    public String getAuthor() { return title; }
    public String getIllustrator() { return title; }
    public boolean getIsVariant() { return isVariant; }
    public String getStoreName() {return storeName; }
    public String getOwnerName() {return ownerName; }
    public String getStoreInfo() {return storeInfo; }
}
