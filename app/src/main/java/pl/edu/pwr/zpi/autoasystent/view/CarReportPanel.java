package pl.edu.pwr.zpi.autoasystent.view;

import java.util.List;
import java.util.Map;

/**
 * Created by Szymon on 2016-04-04.
 */
public interface CarReportPanel {
    int PICKER_FROM = 0x00;
    int PICKER_TO = 0x01;

    void openReportActivity(Map<String, Boolean> checkedOptions);

    /**
     * @param picker {@link CarReportPanel#PICKER_FROM} lub {@link CarReportPanel#PICKER_TO}
     */
    void showPicker(int picker);
    /**
     * @param picker {@link CarReportPanel#PICKER_FROM} lub {@link CarReportPanel#PICKER_TO}
     */
    void setPickerDate(int picker);
    void setReportListItems(List<String> items);
}
