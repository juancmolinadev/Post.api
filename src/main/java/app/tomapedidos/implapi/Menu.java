
package app.tomapedidos.implapi;

import static app.tomapedidos.implapi.DtoListGenerator.createPostDtoList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import app.tomapedidos.implapi.dto.PostDto;
import app.tomapedidos.implapi.dto.UserDto;

/**
 *
 * @author MolinAnimation
 */
public class Menu {
    public final static String BASE_URL = "https://jsonplaceholder.typicode.com";

    public final static String POSTS = BASE_URL + "/posts";

    public final static String USERS = BASE_URL + "/users";

    public static Scanner sc = new Scanner(System.in);

    public static int userMenuSelection;

    public static List<JSONObject> jsonObjectList = new LinkedList<>();

    public static void mainMenu() {

        System.out.println("Welcome, please select an option: \n" +
                "1. Users menu \n" +
                "2. Posts menu \n");
        userMenuSelection = sc.nextInt();
        switch (userMenuSelection) {
            case 1:
                jsonObjectList.addAll(ApiConnection.createRequest(USERS));
                usersMenu();
                break;
            case 2:
                jsonObjectList.addAll(ApiConnection.createRequest(POSTS));
                postsMenu();
                break;
            default:
                System.out.println("Please choose a correct option: \n");
                mainMenu();
                break;
        }

    }

    public static void usersMenu() {
        System.out.println("Users Menu \n Select an option: \n" +
                "1. Show users list \n" +
                "2. Show users by id\n");
        userMenuSelection = sc.nextInt();
        switch (userMenuSelection) {
            case 1:
                showAllUsers();
                break;
            case 2:
                showUsersById();
                break;
            default:
                System.out.println("Please choose a correct option: \n");
                usersMenu();
                break;
        }

    }

    public static void showAllUsers() {
        List<UserDto> users = new LinkedList<>();
        users.addAll(DtoListGenerator.createUserDtoList(jsonObjectList));
        for (UserDto user : users) {
            System.out.println("User id: " + user.getUserId() +
                    "\n name: " + user.getName() +
                    "\n nick: " + user.getNick() +
                    "\n email: " + user.getEmail() +
                    "\n address: " + user.getAddress() +
                    "\n");
        }
    }

    public static void showUsersById() {
        System.out.println("Insert user id to search\n");
        //validacion de tipo int
        while (!sc.hasNext("\\d+")) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
        userMenuSelection = sc.nextInt();

        UserDto user = new UserDto();
        boolean found = false;

        for (int i = 0; i < jsonObjectList.size(); i++) {
            if (userMenuSelection == jsonObjectList.get(i).getInt("id")) {
                user = DtoListGenerator.createUserDto(jsonObjectList.get(i));
                found = true;
                break;
            }
        }
        //valida si la informacio ningresada concuerda con la db
        if (found) {
            System.out.println("User id: " + user.getUserId() +
                    "\n name: " + user.getName() +
                    "\n nick: " + user.getNick() +
                    "\n email: " + user.getEmail() +
                    "\n address: " + user.getAddress() +
                    "\n");
        } else {
            System.out.println("User id no encontrada");
        }

    }

    public static void showPostsByUser() {
        System.out.println("Insert user id to search posts asociate to this user\n");
        //validacion de tipo int
        while (!sc.hasNext("\\d+")) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }

        userMenuSelection = sc.nextInt();
        boolean found = false;

        List<JSONObject> validPostJOList = new LinkedList<>();

        for (int i = 0; i < jsonObjectList.size(); i++) {
            if (userMenuSelection == jsonObjectList.get(i).getInt("userId")) {
                validPostJOList.add(jsonObjectList.get(i));
                found = true;
            }
        }
        //valida si la informacion ingresada concuerda con la db
        if (found) {
            List<PostDto> postDtoList = new LinkedList<>();
            postDtoList.addAll(createPostDtoList(validPostJOList));

            System.out.println("The posts asociate to the user " + userMenuSelection + " are: \n");

            for (PostDto post : postDtoList) {
                System.out.println(
                        "\n Post id: " + post.getPostId() +
                                "\n Title: " + post.getPostTitle() +
                                "\n Body: " + post.getPostBody());
            }
        } else {
            System.out.println("There are no posts asociate to the user: " + userMenuSelection);
        }

    }

    public static void postsMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Posts Menu \n Select an option: \n" +
                "1. Show all posts list \n" +
                "2. Show post by user id\n");
        userMenuSelection = sc.nextInt();
        switch (userMenuSelection) {
            case 1:
                showAllPosts();
                break;
            case 2:
                showPostsByUser();
                break;
            default:
                System.out.println("Please choose a correct option: \n");
                postsMenu();
                break;
        }

    }

    public static void showAllPosts() {
        List<PostDto> posts = new LinkedList<>();
        posts.addAll(DtoListGenerator.createPostDtoList(jsonObjectList));
        for (PostDto post : posts) {
            System.out.println("Total posts list are: " + posts.size() +
                    "\n\n User: " + post.getUserId() +
                    "\n Post id: " + post.getPostId() +
                    "\n Title: " + post.getPostTitle() +
                    "\n Body: " + post.getPostBody());
        }
    }
}
