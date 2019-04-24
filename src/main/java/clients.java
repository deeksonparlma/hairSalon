import org.sql2o.Connection;

import java.util.List;

public class clients {
    private String Name;
    private String Email;
    private int id;
    private int stylistid;

    public clients(String name, String email,int stylistId) {
        Name = name;
        Email = email;
        stylistid=stylistId;

    }


    public String getEmail() {
        return Email;
    }



    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }
    public void save() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "INSERT INTO clients (name, email,stylistid) VALUES (:name, :email,:stylistid)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("name", this.Name)
                    .addParameter("email", this.Email)
                    .addParameter("stylistid", this.stylistid)
                    .executeUpdate()
                    .getKey();
        }


    }
    public void delete() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "DELETE FROM clients WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public static clients find(int id) {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "SELECT * FROM clients where id = :id;";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(clients.class);
        }
    }
}