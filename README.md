# Link Shortener Application Design Choices:

## 1. Basic Hash Function:

I chose a basic hash function (using the hashCode() method) to generate short URLs for simplicity. However, this method may result in collisions, so the implementation includes collision handling to generate a new short URL in case of a collision.
In a production environment, a more robust and collision-resistant hash function, such as SHA-256, could be used for better security and uniqueness.

## 2. In-Memory Data Storage:

For simplicity, the implementation uses in-memory data structures (HashMaps) to store the link mappings. This allows for quick retrieval of long URLs given a short URL and vice versa.
In a real-world scenario, where data persistence is crucial, a database (e.g., MySQL, MongoDB) should be considered to store link mappings persistently between sessions.

## 3. Error Handling:

The implementation includes error handling for scenarios such as duplicate long URLs and invalid short URLs. If a user tries to shorten an already shortened URL, the existing short URL is returned. If an invalid short URL is provided during expansion, an IllegalArgumentException is thrown.
This ensures a better user experience and prevents unexpected behavior.

## 4. Base36 Encoding:

To create shorter representations of the hash code, the implementation converts the hash code to base36. This provides a more concise representation of the short URL compared to the decimal system.
The choice of base36 helps reduce the length of the generated short URLs.

# Challenges Encountered:

Collision Handling: The basic hash function used may result in collisions, where two different long URLs produce the same hash code. To address this, the implementation checks for collisions and generates a new short URL in case of a collision.

Data Persistence: Storing link mappings in-memory is suitable for a basic implementation, but in a production environment, data persistence is crucial. Introducing a database layer would add complexity but is necessary for maintaining link mappings between sessions and improving scalability.
