import org.sql2o.Connection;

import java.util.List;

public class clients {
    private String Name;
    private String Contact;
    private String Email;
    private String Gender;
    private int id;

    public clients(String name,String contact,String email,String gender){
        Name= name;
        Contact=contact;
        Email=email;
        Gender=gender;

    }

    public String getContact() {
        return Contact;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }

    //over ride//
    @Override
    public boolean equals(Object otherclient){
        if (!(otherclient instanceof clients)) {
            return false;
        } else {
            clients newClient = (clients) otherclient;
            return this.getName().equals(newClient.getName()) &&
                    this.getId() == newClient.getId();
        }
    }

    //method for updating data//
    public void update(String description) {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "UPDATE tasks SET description = :description WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    //method for save //

    public void save() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "INSERT INTO tasks(name, email,stylist) VALUES (:description, :categoryId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("description", this.Name)
                    .addParameter("categoryId", this.Email)
                    .addParameter("categoryId", this.Contact)

                    .executeUpdate()
                    .getKey();
        }
    }
    public static clients find(int id) {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "SELECT * FROM tasks where id=:id";
            clients client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(clients.class);
            return client;
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
    public static List<clients> all() {
        String sql = "SELECT * FROM clients";
        try(Connection con = salonDatabase.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(clients.class);
        }
    }
}
