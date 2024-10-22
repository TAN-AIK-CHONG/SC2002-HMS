package records;

public class MedicationRecord implements IRecord{
    private String medicineName;
    private int quantity;
    private int alertLevel;

    public MedicationRecord(String medicineName, int quantity, int alertLevel){
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.alertLevel = alertLevel;
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
}
