package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// That student class act as a intermediate between the database and table Controller
public class Student {

    // The purpose of using Properties and ObservableLists is that these are listenable elements. When properties are used, if the
    // value of a property attribute in the dataModel changes, the view of the item in the TableView is automatically updated to match
    // the updated dataModel value.

    // https://stackoverflow.com/questions/13381067/simplestringproperty-and-simpleintegerproperty-tableview-javafx

    // Under the observable List There are Property data types which can automatically update their values in tableView

    // https://www.youtube.com/watch?v=JaqExzdJhEI&t=0s

    // Source - https://www.youtube.com/watch?v=7n7ZgUmflR8&list=PLA7e3zmT6XQUR-PrG8OQ9nJu2jT5xcOy0&index=3

    // Just create five properties for the columns in database table

    private final IntegerProperty idProperty;

    private final StringProperty nameProperty;

    private final StringProperty payMethodProperty;

    private final StringProperty emailProperty;

    private final IntegerProperty TelNoProperty;

    public Student(){

        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.payMethodProperty = new SimpleStringProperty();
        this.emailProperty = new SimpleStringProperty();
        this.TelNoProperty = new SimpleIntegerProperty();;
    }


    public int getIdProperty() { return idProperty.get(); }

    // that will return IntegerProperty into the javaFx table's id column
    public IntegerProperty idPropertyProperty() { return idProperty; }

    public void setIdProperty(int idProperty) { this.idProperty.set(idProperty); }

    //----------------------------------------------------------------------------

    public String getNameProperty() {
        return nameProperty.get();
    }

    // that will return StringProperty into the javaFx database table's name column
    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    //----------------------------------------------------------------------------

    public String getPMethodProperty() {
        return payMethodProperty.get();
    }

    // that will return StringProperty into the javaFx database table's payment Method column
    public StringProperty PMethodPropertyProperty() {
        return payMethodProperty;
    }

    public void setPMethodProperty(String pMethodProperty) {
        this.payMethodProperty.set(pMethodProperty);
    }

    //----------------------------------------------------------------------------

    public String getEmailProperty() {
        return emailProperty.get();
    }

    // that will return StringProperty into the javaFx database table's Email column
    public StringProperty emailPropertyProperty() { return emailProperty; }

    public void setEmailProperty(String emailProperty) {
        this.emailProperty.set(emailProperty);
    }

    //---------------------------------------------------------------------------

    public int getTelNoProperty() { return TelNoProperty.get(); }

    // that will return IntegerProperty into the javaFx database table's telNo column
    public IntegerProperty telNoPropertyProperty() { return TelNoProperty; }

    public void setTelNoProperty(int telNoProperty) { this.TelNoProperty.set(telNoProperty); }

}
