import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mazsi.json.Post;
import com.mazsi.json.User;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonTest {



    @Test
    public void jsonTry() {
        ObjectMapper objectMapper = new ObjectMapper();

        String postJson =
                "{ \"userId\" : 1, \"id\" : 5, \"title\" : \"Szia\", \"body\" : \"Na sziaaa :) \" }";

        try {
            Post post = objectMapper.readValue(postJson, Post.class);

            assertEquals(post.getId(), 5);
            assertEquals(post.getUserId(), 1);
            assertEquals(post.getTitle(), "Szia");
            assertEquals(post.getBody(), "Na sziaaa :) ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsonFromFileToList() {

        URL resource = getClass().getResource("posts.json");
        List<Post> posts = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(resource.getFile());
            List<Post> list = objectMapper.readValue(file, new TypeReference<List<Post>>(){});
            posts = list;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(posts.get(0).getUserId(), 1);
        assertEquals(posts.get(0).getId(), 1);
        assertEquals(posts.get(0).getTitle(), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        assertEquals(posts.get(0).getBody(), "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
        assertEquals(posts.size(), 100);
    }

    @Test
    public void jsonFromComplexClassFromFileToList() {

        URL resource = getClass().getResource("users.json");
        List<User> users = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(resource.getFile());
            List<User> list = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            users = list;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(users.get(0).getAddress().getCity(), "Gwenborough");
        assertEquals(users.size(), 10);
    }
}
