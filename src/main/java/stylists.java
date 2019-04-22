import org.sql2o.Connection;

import java.util.List;

public class stylists {
    private String Name;
    public String mContact;
    public String mEmail;
    public String mGender;
    public int id;

    public stylists(String name){
        Name= name;
//        mContact=contact;
//        mEmail=Email;
//        mGender=Gender;

    }
    //important//
    @Override
    public boolean equals(Object otherstylist) {
        if (!(otherstylist instanceof stylists)) {
            return false;
        } else {
            stylists newStylist = (stylists) otherstylist;
            return this.getName().equals(newStylist.getName()) &&
                    this.getId() == newStylist.getId();
        }
    }

    public static List<stylists> all() {
        String sql = "SELECT id,name FROM stylists";
        try(Connection con = salonDatabase.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(stylists.class);
        }
    }


    public List<clients> getClients() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
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
            String sql = "INSERT INTO stylists (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.Name)
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
        return Name;
    }
    public int getId() {
        return id;
    }
}
