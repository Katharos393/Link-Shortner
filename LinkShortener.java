import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;

    public LinkShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }

    // Generate a short URL using a basic hash function
    private String generateShortUrl(String longUrl) {
        // For simplicity, using a basic hash of the long URL
        int hashCode = longUrl.hashCode();
        return Integer.toString(hashCode, 36);  // Convert to base36 for a shorter representation
    }

    // Shorten a long URL
    public String shortenUrl(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            // URL is already shortened, return existing short URL
            return longToShortMap.get(longUrl);
        }

        // Generate a short URL
        String shortUrl = generateShortUrl(longUrl);

        // Check for collisions, generate a new short URL if necessary
        while (shortToLongMap.containsKey(shortUrl)) {
            shortUrl = generateShortUrl(longUrl);
        }

        // Save the mapping in both maps
        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);

        return shortUrl;
    }

    // Expand a short URL
    public String expandUrl(String shortUrl) {
        if (shortToLongMap.containsKey(shortUrl)) {
            return shortToLongMap.get(shortUrl);
        } else {
            throw new IllegalArgumentException("Invalid short URL");
        }
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        // Test shortening and expanding URLs
        String longUrl1 = "https://www.youtube.com/watch?v=q-_ezD9Swz4";
        String shortUrl1 = linkShortener.shortenUrl(longUrl1);
        System.out.println("Shortened URL: " + shortUrl1);
        System.out.println("Expanded URL: " + linkShortener.expandUrl(shortUrl1));

        // Test duplicate long URL
        String longUrl2 = "https://www.youtube.com/watch?v=q-_ezD9Swz4";
        String shortUrl2 = linkShortener.shortenUrl(longUrl2);
        System.out.println("Shortened URL (duplicate): " + shortUrl2);

        // Test invalid short URL
        String invalidShortUrl = "https://www.youtube.com/watch";
        try {
            System.out.println("Expanded URL: " + linkShortener.expandUrl(invalidShortUrl));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
