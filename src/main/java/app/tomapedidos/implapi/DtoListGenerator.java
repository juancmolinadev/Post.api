package app.tomapedidos.implapi;

import app.tomapedidos.implapi.dto.PostDto;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import app.tomapedidos.implapi.dto.UserDto;
import app.tomapedidos.sync.logger.TomaPedidosSyncLogger;
import java.util.stream.Collectors;

public class DtoListGenerator {

    public static UserDto createUserDto(JSONObject jsonObject) {
        UserDto userDto = new UserDto();
        try {

            JSONObject addressObject = jsonObject.getJSONObject("address");
            String street = addressObject.getString("street");
            String suite = addressObject.getString("suite");
            String city = addressObject.getString("city");
            String zipcode = addressObject.getString("zipcode");

            String addressString = street + ", " + suite + ", " + city + ", " + zipcode;

            userDto.setUserId(jsonObject.getInt("id"));
            userDto.setName(jsonObject.getString("name"));
            userDto.setNick(jsonObject.getString("username"));
            userDto.setEmail(jsonObject.getString("email"));
            userDto.setAddress(addressString);
        } catch (JSONException ex) {
            TomaPedidosSyncLogger.log("Error al cargar el usuario: " + ex.getMessage());
        }
        return userDto;
    }

    public static PostDto createPostDto(JSONObject jsonObject) {
        PostDto postDto = new PostDto();

        try {
            postDto.setUserId(jsonObject.getInt("userId"));
            postDto.setPostId(jsonObject.getInt("id"));
            postDto.setPostTitle(jsonObject.getString("title"));
            postDto.setPostBody(jsonObject.getString("body"));

        } catch (JSONException ex) {
            TomaPedidosSyncLogger.log("Error al cargar el post " + ex.getMessage());
        }
        return postDto;
    }

    public static List<UserDto> createUserDtoList(List<JSONObject> userJOList) {
        return userJOList.stream()
                .map(DtoListGenerator::createUserDto)
                .collect(Collectors.toList());

    }

    public static List<PostDto> createPostDtoList(List<JSONObject> postJOList) {
        return postJOList.stream()
                .map(DtoListGenerator::createPostDto)
                .collect(Collectors.toList());
    }
}
