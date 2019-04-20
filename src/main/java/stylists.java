import org.sql2o.Connection;

import java.util.List;

public class stylists {
    private String mName;
    private String mContact;
    private String mEmail;
    private String mGender;
    private int id;

    public stylists(String name,String contact,String Email,String Gender){
        mName= name;
        mContact=contact;
        mEmail=Email;
        mGender=Gender;

    }
    //important//
    @Override
    public boolean equals(Object otherstylists) {
        if (!(otherstylists instanceof stylists)) {
            return false;
        } else {
            stylists newStylist = (stylists) otherstylists;
            return this.getName().equals(newStylist.getName()) &&
                    this.getId() == newStylist.getId();
        }
    }

    public static List<stylists> all() {
        String sql = "SELECT id, name FROM stylists";
        try(Connection con = salonDatabase.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(stylists.class);
        }
    }


    public List<clients> getClients() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(clients.class);
        }
    }
    public static stylists find(int id) {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            stylists stylists = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(stylists.class);
            return stylists;
        }
    }

    public void save() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "INSERT INTO stylists(name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.mName)
                    .executeUpdate()
                    .getKey();
        }
    }
    //
    public String getContact() {
        return mContact;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getGender() {
        return mGender;
    }

    public String getName() {
        return mName;
    }
    public int getId() {
        return id;
    }
}
