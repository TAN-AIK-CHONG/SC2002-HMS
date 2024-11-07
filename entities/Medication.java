package entities;

public class Medication {
    private String medicineName;
    private int quantity;
    private int alertLevel;
    private int original;
    private boolean request;

    public Medication(String medicineName, int quantity, int alertLevel , int original , boolean request){
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.alertLevel = alertLevel;
        this.original = original;
        this.request = request;
    }

    public void view(){
        String medicineInfo = String.format("%s, current stock = %d, alert level = %d", this.medicineName, this.quantity, this.alertLevel);
        System.out.println(medicineInfo);
    }

    //getters
    public String getName(){
        return this.medicineName;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getAlertLevel(){
        return this.alertLevel;
    }

    public int getOriginal()
    {
        return this.original;
    }

    public boolean request()
    {
        return this.request;
    }

    //setters
    public void setName(String newName){
        this.medicineName = newName;
    }

    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }

    public void setAlertLevel(int newLevel){
        this.alertLevel = newLevel;
    }

    public void setOriginal(int original)
    {
        this.original = original;
    }

    public void setRequest(boolean request)
    {
        this.request = request;
    }
}
