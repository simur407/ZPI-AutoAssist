package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.service.InsuranceService;
import pl.edu.pwr.zpi.autoasystent.view.fragment.InsuranceAddFragment;

/**
 * Created by argo on 06 maj.
 */
public class InsuranceAddPresenter {

    InsuranceAddFragment fragment;

    public InsuranceAddPresenter(InsuranceAddFragment fragment) { this.fragment = fragment; }

    public void saveInsurance(Insurance insurance) {

        InsuranceService.getInstance().saveInsurance(insurance);
    }
}
