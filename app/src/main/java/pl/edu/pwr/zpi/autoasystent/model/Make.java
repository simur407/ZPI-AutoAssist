package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by Szymon on 2016-03-21.
 */
public class Make extends SugarRecord {

    @Unique
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
