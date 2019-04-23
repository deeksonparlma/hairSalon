import org.sql2o.Connection;

import java.util.List;

public class stylists {
    private String name;
    public String contact;
    private String email;
    private String gender;
    private int id;

    public stylists(String Sname,String Semail,String Sgender){
        this.name= Sname;
         this.email= Semail;
        this.gender= Sgender;

    }


    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
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
        String sql = "SELECT * FROM stylists";
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
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(stylists.class);
        }
    }

    public void save() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "INSERT INTO stylists (name, email, gender) VALUES (:name, :email, :gender)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("gender", this.gender)
                    .executeUpdate()
                    .getKey();
        }

    }
    //
//

    public void setId(int id) {
        this.id = id;
    }
}
