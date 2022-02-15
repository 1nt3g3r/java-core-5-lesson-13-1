package currency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import currency.dto.CurrencyItem;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class CurrencyParser {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter currency (possible values are: USD, EUR, RUR, BTC");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                System.out.println("Bye!");
                System.exit(0);
            }

            String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

            //Get JSON
            String json = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

            //Convert json => Java Object
            Type typeToken = TypeToken
                    .getParameterized(List.class, CurrencyItem.class)
                    .getType();
            List<CurrencyItem> currencyItems = new Gson().fromJson(json, typeToken);

            //Find UAH/USD
            Float uahUsd = currencyItems.stream()
                    .filter(it -> it.getCcy() == CurrencyItem.CCY.valueOf(input))
                    .filter(it -> it.getBase_ccy() == CurrencyItem.CCY.UAH)
                    .map(it -> it.getBuy())
                    .findFirst()
                    .orElseThrow();

            System.out.println("UAH/" + input + " buy course: " + uahUsd);
        }
    }
}
