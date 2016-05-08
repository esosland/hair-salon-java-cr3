import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client(String name) {
    this.name = name;
  }

  public String getName() {
  return name;
  }

  public static List<Client> all() {
    String sql = "SELECT id, name FROM clients;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
}
