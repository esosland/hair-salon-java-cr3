import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Kermit");
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_String() {
    Stylist myStylist = new Stylist("Gonzo");
    assertEquals("Gonzo", myStylist.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Stylist firstStylist = new Stylist("Fozzy");
    Stylist secondStylist = new Stylist("Fozzy");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Stylist myStylist = new Stylist("Miss Piggy");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Animal");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
  }

  @Test
    public void save_assignsIdToObject() {
      Stylist myStylist = new Stylist("Scooter");
      myStylist.save();
      Stylist savedStylist = Stylist.all().get(0);
      assertEquals(myStylist.getId(), savedStylist.getId());
    }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Beaker");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Swedish Chef");
    myStylist.save();
    Client firstClient = new Client("Jim Morrison", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Sebastian Bach", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }




}
