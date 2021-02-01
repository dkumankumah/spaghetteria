package app.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Entity
@NamedQueries({
  @NamedQuery(name = "find_all",
    query = "select l, a.location from Log l " + "inner join Account a " +
      "on a.username = l.username where a.location = ?1"),
  @NamedQuery(name = "find_all_view",
    query = "select l, a.location from Log l " + "inner join Account a " +
      "on a.username = l.username " +
      "where l.checked = ?1"),
  @NamedQuery(name = "findLogDuplDate",
    query = "select l from Log l " +
      "inner join Account a on a.username = l.username " +
      "where l.date = ?1 and a.location = ?2"),
})
public class Log {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;

  private String name;
  private String new_order;
  private String defects;
  private String losts;

  private String pasta_order_res;
  private String pasta_order_del;
  private String revenue_res;
  private String revenue_del;
  private String revenue_tot;

  private String expendpp;
  private String houres_worked;
  private String return_incl_del;
  private String on_house;
  private String discount;
  private String broken;
  private String correction;
  private String cash_difference;
  private String general_details;

  private Boolean checked = false;

  // TODO
  private String username;

  public Log(long id, Date date, String name, String newOrder, String defects, String losts,
             String pastaOrderRes, String pastaOrderDel, String revenueRes, String revenueDel, String revenueTot,
             String expendPP, String houresWorked, String returnInclDel, String fromHouse, String discount,
             String broken, String correctie, String cashDifference, String generalDetails, Boolean checked,
             String username) {
    this.id = id;
    this.date = date.toLocalDate();
    this.name = name;
    this.new_order = newOrder;
    this.defects = defects;
    this.losts = losts;
    this.pasta_order_res = pastaOrderRes;
    this.pasta_order_del = pastaOrderDel;
    this.revenue_res = revenueRes;
    this.revenue_del = revenueDel;
    this.revenue_tot = revenueTot;
    this.expendpp = expendPP;
    this.houres_worked = houresWorked;
    this.return_incl_del = returnInclDel;
    this.on_house = fromHouse;
    this.discount = discount;
    this.broken = broken;
    this.correction = correctie;
    this.cash_difference = cashDifference;
    this.general_details = generalDetails;
    this.checked = checked;
    this.username = username;
  }

  public Log() {
  }

  public static Log createRandomLog() {

    String name = "John Doe";
    String newOrder = "borden";
    String defects = "vriezer";
    String losts = "mobiele telefoon, jas, tas, sleutels, portomonee, pinpas";

    int pastaOrderRes = (int) Log.randomNumber(300, 1200);
    String pastaOrderRes2 = String.valueOf(pastaOrderRes);
    int pastaOrderDel = (int) Log.randomNumber(100, 300);
    String pastaOrderDel2 = String.valueOf(pastaOrderDel);

    double rrNum = (Log.randomNumber(9000, 12000));
    //    double revenueRes = ((rrNum * 0.002) * 100.0) / 100.0;
    String revenueRes = String.valueOf(rrNum);

    double rdNum = (Log.randomNumber(3000, 4000));
    //    double revenueDel = ((rdNum * 0.002) * 100.0) / 100.0;
    String revenueDel = String.valueOf(rdNum);

    double rtNum = (rrNum + rdNum);
    //    double revenueTot = ((rtNum * 0.002) * 100.0) / 100.0;
    String revenueTot = String.valueOf(rtNum);

    double expendPP = ((rrNum / pastaOrderRes) * 100.0) / 100.0;
    String expendPP2 = String.valueOf(expendPP);

    double hwNum = Log.randomNumber(4, 12);
    //    double houresWorked = ((hwNum * 0.002) * 100.0) / 100.0;
    String houresWorked = String.valueOf(hwNum);

    double ridNum = (rrNum * 0.20);
    //    double returnInclDel = ((ridNum * 0.002) * 100.0) / 100.0;
    String returnInclDel = String.valueOf(ridNum);

    double fromHouse = ((rtNum * 0.002) * 100.0) / 100.0;
    String fromHouse2 = String.valueOf(fromHouse);

    double discount = Math.round((rtNum * 0.008) * 100.0) / 100.0;
    String discount2 = String.valueOf(discount);

    double brkNum = Log.randomNumber(0, (rtNum * 0.005));
    //    double broken = Math.round(brkNum * 100.0) / 100.0;
    String broken = String.valueOf(brkNum);

    double corNum = Log.randomNumber(50, 200);
    //    double correctie = Math.round(corNum * 100.0) / 100.0;
    String correctie = String.valueOf(corNum);

    double csNum = Log.randomNumber(50, 200);
    //    String cashDifference = Math.round(csNum * 100.0) / 100.0;
    String cashDifference = String.valueOf(csNum);

    String generalDetails = "Algemene details worden hier beschreven.";

//    return new Log(0, timestamp(), name, newOrder, defects, losts, pastaOrderRes, pastaOrderDel, revenueRes,
//      revenueDel, revenueTot,
//      expendPP2, houresWorked, returnInclDel, fromHouse2, discount2,
//      broken, correctie, cashDifference, generalDetails, null);
    return null;
  }


  private static double randomNumber(double min, double max) {
    int random = (int) (Math.random() * (max - min) + min);
    return (double) Math.round(random * 100) / 100;
  }

  public static LocalDate timestamp() {
    LocalDate start = LocalDate.of(2019, Month.JANUARY, 1);
    long days = ChronoUnit.DAYS.between(start, LocalDate.now());
    LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));

    return randomDate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id_logs) {
    this.id = id_logs;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getName() {
    return name;
  }

  public String getNew_order() {
    return new_order;
  }

  public String getDefects() {
    return defects;
  }

  public String getLosts() {
    return losts;
  }

  public String getPasta_order_res() {
    return pasta_order_res;
  }

  public String getPasta_order_del() {
    return pasta_order_del;
  }

  public String getRevenue_res() {
    return revenue_res;
  }

  public String getRevenue_del() {
    return revenue_del;
  }

  public String getRevenue_tot() {
    return revenue_tot;
  }

  public String getExpendpp() {
    return expendpp;
  }

  public String getHoures_worked() {
    return houres_worked;
  }

  public String getReturn_incl_del() {
    return return_incl_del;
  }

  public String getOn_house() {
    return on_house;
  }

  public String getDiscount() {
    return discount;
  }

  public String getBroken() {
    return broken;
  }

  public String getCorrection() {
    return correction;
  }

  public String getCash_difference() {
    return cash_difference;
  }

  public String getGeneral_details() {
    return general_details;
  }

  public Boolean getChecked() {
    return checked;
  }

  public String getUsername() {
    return username;
  }
}
