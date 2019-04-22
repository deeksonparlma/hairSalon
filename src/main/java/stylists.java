import org.sql2o.Connection;

import java.util.List;

public class stylists {
    private String Name;
    private String Contact;
    private String Email;
    private String Gender;
    private int id;

    public stylists(String name,String contact,String email,String gender){
        this.setName(name);
        setContact(contact);
        setEmail(email);
        setGender(gender);

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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return super.toString();
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
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(stylists.class);
        }
    }

    public void save() {
        try(Connection con = salonDatabase.sql2o.open()) {
            String sql = "INSERT INTO stylists (name) VALUES (:name)";
            this.setId((int) con.createQuery(sql, true)
                    .addParameter("name", this.getName())
                    .executeUpdate()
                    .getKey());
        }
    }
    //
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

    public void setName(String name) {
        Name = name;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }
}
