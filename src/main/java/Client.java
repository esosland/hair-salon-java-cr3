import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client(String name) {
    this.name = name;
  }
}
