package edu.nju.git.VO.chartvo;

import edu.nju.git.type.OwnerType;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserTypeChartVO extends MyChartVO {
    public UserTypeChartVO() {
        super();
        super.fields = new String[2];
        fields[0] = "User";
        fields[1] = "Organization";

        super.values = new int[2];
        values[0] = values[1] = 0;
    }

    public void increase (OwnerType type) {
        if (type == OwnerType.USER) {
            values[0]++;
        }
        else {
            values[1]++;
        }
    }
}