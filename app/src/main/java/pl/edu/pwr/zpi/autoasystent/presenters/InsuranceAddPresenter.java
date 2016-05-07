package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.service.InsuranceService;
import pl.edu.pwr.zpi.autoasystent.view.dialog.InsuranceAddDialog;

/**
 * Created by argo on 06 maj.
 */
public class InsuranceAddPresenter {

    InsuranceAddDialog dialog;

    public InsuranceAddPresenter(InsuranceAddDialog dialog) {
        this.dialog = dialog;
    }

    public void saveInsurance(Insurance insurance) {

        InsuranceService.getInstance().saveInsurance(insurance);
    }
}
