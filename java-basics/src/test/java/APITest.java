import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class APITest {

    JSONPlaceholderAPI api = JSONPlaceholderAPI.getInstance();

    @Test
    public void testGetPhotos(){

        List<Photo> photos;
        try {
            photos = api.getPhoto().execute().body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(5000, photos.size(), "There should be 5000 photos");

        Photo photo=photos.get(0);

        assertEquals(1, photo.getAlbumId());
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", photo.getTitle());
        assertEquals(1, photo.getId());

    }

    @Test
    public void testGetPosts(){

        List<Post> posts;
        try {
            posts = api.getPosts().execute().body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(100, posts.size(), "There should be 100 posts");
    }
}
