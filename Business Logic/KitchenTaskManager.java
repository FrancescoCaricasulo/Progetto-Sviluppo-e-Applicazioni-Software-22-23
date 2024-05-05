package catering.businesslogic.kitchenTasks;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.recipe.RecipeManager;

import java.util.ArrayList;

public class KitchenTaskManager {
    ArrayList<SummarySheetEventReceiver> er = new ArrayList<>();

    public SummarySheet defineSummarySheet(ServiceInfo service) throws Exception {
        if(service.getMenu() != null){
            SummarySheet sum = new SummarySheet(service);
            notifySummarySheetAdded(service, sum);
            return sum;
        }else{
            throw new UseCaseLogicException();
        }
    }

    public void orderSummarySheet(SummarySheet sum, KitchenTask kt, int destIndex){
        if(sum == null){
            // no-op
        }else if (sum.getKitchenTasks().size() < 2){
            // no-op
        }else{
            sum.order(kt, destIndex);
        }

        notifySummarySheetRearranged(sum);
    }

    public void assignTask(SummarySheet sum, KitchenTask kt, Turn t, Cook ck, int q){
        sum.assignTask(kt, t, ck, q);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void assignTask(SummarySheet sum, KitchenTask kt, Turn t, int q) {
        sum.assignTask(kt, t, q);

        notifyKitchenTaskChanged(sum, kt);
    }

    public ArrayList<Recipe> getRecipeBook(){
        return new ArrayList<>(CatERing.getInstance().getRecipeManager().getRecipes());
    }

    public void addKitchenTask(SummarySheet sum, Recipe r){
        sum.addKitchenTask(r);

        notifyKitchenTaskListChanged(sum);
    }


    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, Turn t, Cook ck, int q){
        sum.modifyKitchenTask(kt, t, ck, q);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, Cook ck, int q){
        sum.modifyKitchenTask(kt, ck, q);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, Turn t, int q){
        sum.modifyKitchenTask(kt, t, q);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, Turn t, Cook ck){
        sum.modifyKitchenTask(kt, t, ck);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, Turn t){
        sum.modifyKitchenTask(kt, t);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, Cook ck){
        sum.modifyKitchenTask(kt, ck);

        notifyKitchenTaskChanged(sum, kt);
    }

    public void modifyKitchenTask(SummarySheet sum, KitchenTask kt, int q){
        sum.modifyKitchenTask(kt, q);

        notifyKitchenTaskChanged(sum, kt);
    }

    private void notifySummarySheetAdded(ServiceInfo service, SummarySheet sum) {
        for (SummarySheetEventReceiver er : this.er) {
            er.updateSummarySheetAdded(service, sum);
        }
    }

    private void notifySummarySheetRearranged(SummarySheet sum) {
        for (SummarySheetEventReceiver er : this.er) {
            er.updateSummarySheetRearranged(sum);
        }
    }

    private void notifyKitchenTaskChanged(SummarySheet sum, KitchenTask kt) {
        for (SummarySheetEventReceiver er : this.er) {
            er.updateKitchenTaskChanged(sum, kt);
        }
    }

    private void notifyKitchenTaskListChanged(SummarySheet sum) {
        for (SummarySheetEventReceiver er : this.er) {
            er.updateKitchenTaskListChanged(sum);
        }
    }

    public void addReceiver(SummarySheetEventReceiver er){
        this.er.add(er);
    }

    public void removeReceiver(SummarySheetEventReceiver er){
        this.er.remove(er);
    }
}
