import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mazsi.json.Post;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTest {

    @Test
    public void jsonTry() {
        ObjectMapper objectMapper = new ObjectMapper();

        String postJson =
                "{ \"userId\" : 1, \"id\" : 5, \"title\" : \"Szia\", \"body\" : \"Na sziaaa :) \" }";

        try {
            Post post = objectMapper.readValue(postJson, Post.class);

            System.out.println("Post id = " + post.getId());
            System.out.println("Post body = " + post.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsonFromFileToList() {
        ObjectMapper objectMapper = new ObjectMapper();

        try  {
            File file = new File("C:\\Java\\Converter\\src\\main\\data\\data.json");
            List<Post> posts = objectMapper.readValue(file, new TypeReference<List<Post>>(){});
            posts.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
            for (Post post : posts) {
                System.out.println("Id: " + post.getId() + " | " + "Címe: " + post.getTitle());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
