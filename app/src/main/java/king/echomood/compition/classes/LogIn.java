package king.echomood.compition.classes;

import io.realm.RealmObject;

/**
 * Created by king on 7/13/16.
 */
public class LogIn extends RealmObject {
    private String ID ;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
