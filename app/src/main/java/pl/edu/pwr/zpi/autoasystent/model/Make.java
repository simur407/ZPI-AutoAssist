package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

/**
 * Created by Szymon on 2016-03-21.
 */
public class Make extends SugarRecord {

    private String makeName;

    public Make() {

    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }
}
