package com.hlogg.hloggbe;

public class Activity {

    public int ActivityId;
    public String ActivityDescription;

    public Activity(int activityId, String activityDescription) {
        ActivityId = activityId;
        ActivityDescription = activityDescription;
    }

    public int getActivityId() {
        return ActivityId;
    }

    public void setActivityId(int activityId) {
        ActivityId = activityId;
    }

    public String getActivityDescription() {
        return ActivityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        ActivityDescription = activityDescription;
    }
}
