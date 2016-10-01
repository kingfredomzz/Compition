package king.echomood.compition.classes;

import io.realm.RealmObject;

/**
 * Created by king on 7/1/16.
 */
public class Scores extends RealmObject {

    private int score;
    private String id;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
