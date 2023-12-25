import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Country {
    String name;
    String capital;
    String language;
    int population;
    double landArea;
    String currency;
    String establishment;
    String leader;

    public Country(String name, String capital, String language, int population, double landArea, String currency, String establishment, String leader) {
        this.name = name;
        this.capital = capital;
        this.language = language;
        this.population = population;
        this.landArea = landArea;
        this.currency = currency;
        this.establishment = establishment;
        this.leader = leader;
    }
}

public class Countries {

    public static void main(String[] args) {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Mongolia", "Ulaanbaatar", "Mongolian", 3300000, 1564110, "Tugrik", "1921", "Ukhnaa Hurelsukh"));
        countries.add(new Country("United States Of America", "Washington DC", "English", 331000000, 9833517, "Dollar", "1775", "Joe Biden"));

        saveCountriesToFile(countries, "countries.txt");

        System.out.println("1. Хүн амын тоо нь 20 саяас дээш байдаг улсын бүх мэдээлэл: ");
        for (Country country : countries) {
            if (country.population > 20000000) {
                System.out.println(country.name + ": " + country.population + " хүн");
            }
        }

        Country maxPopulationCountry = getMaxPopulationCountry(countries);
        if (maxPopulationCountry != null) {
            System.out.println("\n2. Газар нутгийн нэгж талбайд хамгийн олон хүн ноогддог улс: " + maxPopulationCountry.name);
        }
    }

    private static void saveCountriesToFile(List<Country> countries, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Country country : countries) {
                writer.write("Улсын нэр: " + country.name + "\n");
                writer.write("Нийслэл: " + country.capital + "\n");
                writer.write("Улсын хэл: " + country.language + "\n");
                writer.write("Хүн амын тоо: " + country.population + "\n");
                writer.write("Газар нутгийн хэмжээ: " + country.landArea + " км²\n");
                writer.write("Мөнгөн нэгжийн нэр: " + country.currency + "\n");
                writer.write("Төрийн зохион байгуулалт: " + country.establishment + "\n");
                writer.write("Төрийн тэргүүн: " + country.leader + "\n\n");
            }
            System.out.println("Мэдээлэл файлд хадгалагдлаа: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Country getMaxPopulationCountry(List<Country> countries) {
        return countries.stream()
                .max(Comparator.comparingInt(c -> c.population))
                .orElse(null);
    }
}
