package logic;


import com.google.gson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLparser {

    public static NBU_course get_NBU_course_today(){


        String date = "";
        String usd = "";
        String eur = "";
        String rub = "";


        try {
            Document doc = Jsoup.connect("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange/").get();
            date = doc.select("cc:contains(USD)").parents().first().select("exchangedate").text();
            usd = doc.select("cc:contains(USD)").parents().first().select("rate").text();
            eur = doc.select("cc:contains(EUR)").parents().first().select("rate").text();
            rub = doc.select("cc:contains(RUB)").parents().first().select("rate").text();


        } catch (IOException e) {
            e.printStackTrace();
        }
        NBU_course curs = new NBU_course(usd,eur,rub,date);
        return curs;
    }

    public static Course get_Obmenka_course_today() {

        String usd_buy="",usd_sell="",usd_date="";
        String eur_buy="",eur_sell="",eur_date="";
        String rub_buy = "",rub_sell="",rub_date="";
        try {
            Document doc = Jsoup.connect("https://obmenka.kharkov.ua/USD-UAH").get();
            Elements currency_elements = doc.select("head").select("script");
            Element cur_el = currency_elements.get(8);
           String jspart = cur_el.data();
            jspart = jspart.trim();
           int f_index = jspart.indexOf("=");
            int l_index = jspart.length();
            jspart = jspart.substring(++f_index,--l_index);
            jspart = jspart.trim();
     JsonParser parser = new JsonParser();
            JsonElement  json_el = parser.parse(jspart);
           JsonObject jsonObject = json_el.getAsJsonObject();
            json_el =  jsonObject.get("rates");
            JsonArray jsarray = json_el.getAsJsonArray();
            jsonObject = jsarray.get(0).getAsJsonObject();
           usd_buy = jsonObject.get("amountWholesaleFrom").getAsString();
            usd_sell = jsonObject.get("amountWholesaleTo").getAsString();
           usd_date = jsonObject.get("createdAt").getAsString();
            jsonObject = jsarray.get(1).getAsJsonObject();
            eur_buy = jsonObject.get("amountWholesaleFrom").getAsString();
            eur_sell = jsonObject.get("amountWholesaleTo").getAsString();
            eur_date = jsonObject.get("createdAt").getAsString();
            jsonObject = jsarray.get(2).getAsJsonObject();
            rub_buy = jsonObject.get("amountWholesaleFrom").getAsString();
            rub_sell = jsonObject.get("amountWholesaleTo").getAsString();
            rub_date = jsonObject.get("createdAt").getAsString();


                }

         catch (IOException e) {
            e.printStackTrace();
        }
        Usd usd = new Usd(usd_buy,usd_sell,usd_date);
        Eur eur = new Eur(eur_buy,eur_sell,eur_date);
        Rub rub = new Rub(rub_buy,rub_sell,rub_date);

        Course obmnka_course = new Course(usd,eur,rub);

        return obmnka_course;
    }

    protected static Course get_Privat_bank_course(){

        String usd_buy="",usd_sell="",usd_date="";
        String eur_buy="",eur_sell="",eur_date="";
        String rub_buy = "",rub_sell="",rub_date="";

        try {
            Document doc = Jsoup.connect("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5").get();
            Elements elements = doc.select("row");
          // String eur_buy = elements.get(0).select("buy").val();

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        Usd usd = new Usd(usd_buy,usd_sell,usd_date);
        Eur eur = new Eur(eur_buy,eur_sell,eur_date);
        Rub rub = new Rub(rub_buy,rub_sell,rub_date);

        Course privat_course = new Course(usd,eur,rub);

     return privat_course;
    }





}
