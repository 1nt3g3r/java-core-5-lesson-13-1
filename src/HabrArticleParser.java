import org.jsoup.nodes.Element;

public class HabrArticleParser {
    private Element article;

    public HabrArticleParser(Element article) {
        this.article = article;
    }

    public String getTitle() {
        return article.getElementsByTag("h2").first().text();
    }

    public int getRating() {
        String textRating = article
                .selectFirst("div.tm-votes-meter")
                .selectFirst("span.tm-votes-meter__value")
                .text();
        return Integer.parseInt(textRating.replace("+", ""));
    }
}
