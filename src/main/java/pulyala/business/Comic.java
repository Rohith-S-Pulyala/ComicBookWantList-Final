/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulyala.business;

/**
 *
 * @author rohit
 */
public class Comic implements java.io.Serializable {
    private int id;
    private String title;
    private int issueNumber;
    private String storeName;
    private String storeInfo;
    
    public Comic(String title, int issueNumber, String storeName, String storeInfo) {
        this.title = title;
        this.issueNumber = issueNumber;
        this.storeName = (storeName == null) ? "N/A" : storeName;
        this.storeInfo = (storeInfo == null) ? "None provided" : storeInfo;
    }
    
    public Comic(int id, String title, int issueNumber, String storeName, String storeInfo) {
        this.id = id;
        this.title = title;
        this.issueNumber = issueNumber;
        this.storeName = (storeName == null) ? "N/A" : storeName;
        this.storeInfo = (storeInfo == null) ? "None provided" : storeInfo;
    }
    
    public int getId() { return id; }
    public String getTitle() {return title; }
    public int getIssueNumber() { return issueNumber; }
    public String getStoreName() {return storeName; }
    public String getStoreInfo() {return storeInfo; }
}
