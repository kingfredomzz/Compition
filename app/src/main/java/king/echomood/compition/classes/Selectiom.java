package king.echomood.compition.classes;

import io.realm.RealmObject;

/**
 * Created by king on 7/1/16.
 */
public class Selectiom extends RealmObject {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
