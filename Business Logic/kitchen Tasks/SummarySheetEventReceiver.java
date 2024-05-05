package catering.businesslogic.kitchenTasks;

import catering.businesslogic.event.ServiceInfo;

public interface SummarySheetEventReceiver {
    public void updateSummarySheetAdded(ServiceInfo service, SummarySheet sumAdded);
    public void updateSummarySheetRearranged(SummarySheet sumRearranged);
    public void updateKitchenTaskChanged(SummarySheet sum, KitchenTask ktChanged);

    public void updateKitchenTaskListChanged(SummarySheet sum);
}
