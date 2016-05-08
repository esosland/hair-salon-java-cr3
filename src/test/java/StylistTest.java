import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

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



}
