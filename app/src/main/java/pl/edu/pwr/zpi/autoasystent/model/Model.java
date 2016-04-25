package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

/**
 * Created by Szymon on 2016-03-21.
 */
public class Model extends SugarRecord {

    private Make make;
    private String modelName;

    public Model() {

    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Make getMake() {

        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return modelName;
    }
}
