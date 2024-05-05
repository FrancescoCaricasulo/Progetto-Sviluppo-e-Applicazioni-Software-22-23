package catering.businesslogic.event;

import catering.businesslogic.CatERing;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.MenuManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ServiceInfo implements EventItemInfo {
    private int id;
    private String name;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int participants;
    private Menu menu;
    private EventInfo event;

    private int eventId;

    public ServiceInfo(String name) {
        this.name = name;
    }

    public int getId(){return id;}

    public Menu getMenu(){
        return menu;
    }

    public EventInfo getEvent(){
        return event;
    }

    public void setEventById(){
        for(EventInfo e : CatERing.getInstance().getEventManager().getEventInfo()){
            if(e.getId() == eventId){
                this.event = e;
            }
        }
    }

    public String toString() {
        return name + ": " + date + " (" + timeStart + "-" + timeEnd + "), " + participants + " pp.";
    }

    public void addMenu(Menu menu){
        this.menu = menu;
    }

    public void addEvent(EventInfo e){
        event = e;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
        ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
        String query = "SELECT id, name, service_date, time_start, time_end, expected_participants, approved_menu_id, event_id " +
                "FROM Services WHERE event_id = " + event_id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String s = rs.getString("name");
                ServiceInfo serv = new ServiceInfo(s);
                serv.id = rs.getInt("id");
                serv.date = rs.getDate("service_date");
                serv.timeStart = rs.getTime("time_start");
                serv.timeEnd = rs.getTime("time_end");
                serv.participants = rs.getInt("expected_participants");
                int menuId = rs.getInt("approved_menu_id");
                if(menuId == 0)
                    serv.menu = null;
                else{
                    for(Menu m : CatERing.getInstance().getMenuManager().getAllMenus()){
                        if(m.getId() == menuId){
                            serv.menu = m;
                            break;
                        }
                    }
                }
                serv.eventId = rs.getInt("event_id");
                result.add(serv);
            }
        });

        return result;
    }
}
