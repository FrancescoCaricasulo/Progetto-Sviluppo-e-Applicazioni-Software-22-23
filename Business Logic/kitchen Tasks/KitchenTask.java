package catering.businesslogic.kitchenTasks;

import catering.businesslogic.recipe.Recipe;

public class KitchenTask {
    private Recipe taskRecipe;
    private Boolean done;
    private int quantity;
    private Cook taskCook;
    private Turn taskTurn;

    public KitchenTask(Recipe r){
        taskRecipe = r;
        done = false;
        quantity = 0;
        taskCook = null;
        taskTurn = null;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(taskRecipe);

        if(taskCook != null)
            s.append(" ").append(taskCook);

        s.append(" ").append(quantity);

        if(taskTurn != null)
            s.append(" ").append(taskTurn);

        return s.toString();
    }

    public void editKitchenTask(Turn t, Cook ck, int q){
        this.taskTurn = t;
        this.taskCook = ck;
        this.quantity = q;
    }

    public void editKitchenTask(Turn t, int q){
        this.taskTurn = t;
        this.quantity = q;
    }

    public void editKitchenTask(int q){
        this.quantity = q;
    }

    public void editKitchenTask(Turn t){
        this.taskTurn = t;
    }

    public void editKitchenTask(Cook ck, int q){
        this.taskCook = ck;
        this.quantity = q;
    }

    public void editKitchenTask(Cook ck, Turn t){
        this.taskTurn = t;
        this.taskCook = ck;
    }

    public void editKitchenTask(Cook ck){
        this.taskCook = ck;
    }
}
