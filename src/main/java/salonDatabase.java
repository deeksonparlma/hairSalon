import org.sql2o.Sql2o;

public class salonDatabase {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/salon", "postgres", "password");
}
