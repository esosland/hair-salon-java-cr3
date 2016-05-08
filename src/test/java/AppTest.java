import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

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
    public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Hair Salon");
    }

    @Test
    public void stylistIsCreatedTest() {
      goTo("http://localhost:4567/");
      fill("#name").with("Fozzie Bear");
      submit(".btn");
      assertThat(pageSource()).contains("Fozzie Bear");
    }

    @Test
    public void stylistPageIsDisplayedTest() {
      Stylist myStylist = new Stylist("Fozzie Bear");
      myStylist.save();
      String stylistPath = String.format("http://localhost:4567/stylist/%d", myStylist.getId());
      goTo(stylistPath);
      assertThat(pageSource()).contains("Fozzie Bear");
    }


  }
