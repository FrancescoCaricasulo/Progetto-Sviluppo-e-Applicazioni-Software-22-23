package catering.businesslogic.kitchenTasks;

import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.MenuItem;
import catering.businesslogic.menu.Section;
import catering.businesslogic.recipe.Recipe;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SummarySheet {
    private String name;
    private ServiceInfo service;
    // private EventInfo event;
    private ArrayList<KitchenTask> tasks;

    public SummarySheet(ServiceInfo service){
        this.service = service;
        this.name = service.getMenu().getTitle() + " " + service.getEvent().getName();
        tasks = new ArrayList<>();

        ArrayList<Section> listSections = new ArrayList<>(Section.loadSectionsFor(service.getMenu().getId()));
        // come se fossero presi da UI

        for(Section s : listSections){
            ArrayList<MenuItem> listMI = new ArrayList<>(s.getItems());
            for(MenuItem mi : listMI) {
                KitchenTask kt = new KitchenTask(mi.getItemRecipe());
                tasks.add(kt);
            }
        }
    }
    public ServiceInfo getService(){
        return service;
    }

    public EventInfo getEvent(){
        return service.getEvent();
    }

    public static void saveNewSummarySheet(SummarySheet s) {
        String summarySheetInsert = "INSERT INTO catering.summarysheet VALUES (?, ?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(summarySheetInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setString(1, PersistenceManager.escapeString(s.name));
                ps.setInt(2, s.getEvent().getId());
                ps.setInt(3, s.getService().getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                /*
                if (count == 0) {
                    s.name = rs.getInt(1);
                }*/
            }
        });
    }

    public ArrayList<KitchenTask> getKitchenTasks() {
        return tasks;
    }

    /* Ordinamento kitchen task */
    public void order(KitchenTask kt, int destIndex){
        tasks.remove(kt);
        tasks.add(destIndex, kt);
    }

    public void assignTask(KitchenTask kt, Turn t, Cook ck, int q){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(t, ck, q);
    }

    public void assignTask(KitchenTask kt, Turn t, int q) {
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(t, q);
    }

    public void modifyKitchenTask(KitchenTask kt, Turn t, Cook ck, int q){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        kt.editKitchenTask(t, ck, q);
    }

    public void modifyKitchenTask(KitchenTask kt, Turn t, int q){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(t, q);
    }

    public void modifyKitchenTask(KitchenTask kt, int q) {
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(q);
    }

    public void modifyKitchenTask(KitchenTask kt, Cook ck){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(ck);
    }

    public void modifyKitchenTask(KitchenTask kt, Turn t){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(t);
    }

    public void modifyKitchenTask(KitchenTask kt, Turn t, Cook ck){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(ck, t);
    }

    public void modifyKitchenTask(KitchenTask kt, Cook ck, int q){
        int indexOfTask = tasks.indexOf(kt);
        KitchenTask task = tasks.get(indexOfTask);
        task.editKitchenTask(ck, q);
    }

    public ArrayList<KitchenTask> addKitchenTask(Recipe r){
        KitchenTask kt = new KitchenTask(r);
        tasks.add(kt);
        return tasks;
    }
}
