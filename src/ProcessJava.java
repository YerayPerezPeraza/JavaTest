import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* This class defines objects with Java to analyze a Json file to obtain relevant information
* @author: Yeray Pérez Peraza
* @version: 1.0.2
 */

public class ProcessJava {

/**
 * this is the main method where we process the different options that the user has and we call
*  the different methods that allow the user to interact and see the information contained in the Json
 * @param args Unused.
 * @return Nothing.
 */

    public static void main(String[] args) {
        /**
         * We create an object of the class to call the methods.
         * A variable for the selection of the file and so the change is very simple.
         * We create the menu and the control variables of it
         * Within each of the options we call the corresponding method
         */
        ProcessJava Subject = new ProcessJava();
        String file = "./db.txt";

        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Save user option

        while (!exit) {
            System.out.println("\n");
            System.out.println("1. List active users");
            System.out.println("2. Search for cities by the initial letter ");
            System.out.println("3. List users by creation date");
            System.out.println("4. Add user");
            System.out.println("5. Exit");
            try {
                System.out.println("Choose an option\n");
                option = sn.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("1. List active users");
                        Subject.SearchActivite(file);
                        break;
                    case 2:
                        System.out.println("2. Search for cities by the initial letter ");
                        char character;
                        Scanner keyboard = new Scanner(System.in);
                        System.out.println("Enter a letter, if you enter more, the first one will be taken: ");
                        character = keyboard.next().charAt(0);
                        while ((!Character.isLetter(character))) {
                            System.out.println("Enter a letter, if you enter more, the first one will be taken:");
                            character = keyboard.next().charAt(0);
                        }
                        char fUpper = Character.toUpperCase(character);
                        Subject.searchCity(fUpper, file);
                        break;
                    case 3:
                        System.out.println("3. List users by creation date");
                        Subject.organizeUserForDate(file);
                        break;
                    case 4:
                        System.out.println("4. Add user");
                        Subject.newUser(file);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Only numbers between 1 and 5");
                }
            }catch (InputMismatchException e) {
                System.out.println("Only numbers between 1 and 5");
                sn.next();
            }
        }


    }

    /**
     * Method to add a new user to the Json file
     * @param file, file save Json
     */
    public void newUser(String file) {
        /**
         * user fields
         */
        String name;
        String surname;
        Boolean active;
        String email;
        String city;
        String creationDate;

        /**
         * We ask for personal information fields by keyboard
         * active is by default to true since we just added the user
         * Creationdate is the current system date, since we just created the user
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Name: ");
        name = keyboard.nextLine();

        System.out.println("Surname: ");
        surname = keyboard.nextLine();

        active = true;

        System.out.println("Email: ");
        email = keyboard.nextLine();


        System.out.println("City: ");
        city = keyboard.nextLine();

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSSZ");

        creationDate = ft.format(dNow);

        Users user = new Users(name, surname, active, email, city, creationDate);


        ArrayList<Users> arrayJson = LoadUser(file);
        arrayJson.add(user);
        /**
         * we add a new user and write the information back to the file
         */
        try (Writer writer = new FileWriter("src/db.txt")) {
            Gson gson1 = new GsonBuilder().create();
            gson1.toJson(arrayJson, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * in this method instance Gson read the file and store it in an ArrayList of the user class
     * @param file, file save Json
     * @return arrayJson, ArrayList containing the user information contained in the json file
     */
    public ArrayList<Users> LoadUser(String file){
        Gson gson = new Gson();


        InputStream is = Users.class.getClassLoader().getResourceAsStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        Type listType = new TypeToken<ArrayList<Users>>() {
        }.getType();
        ArrayList<Users> arrayJson = gson.fromJson(bufferedReader, listType);

        return arrayJson;
    }

    /**
     *In this method we organize the users by first creation date ascendingly and secondly descendingly
     * @param file, file save Json
     */
    public void organizeUserForDate(String file) {


        ArrayList<Users> arrayJson = LoadUser(file);


        List<String> DateCreation = new ArrayList<String>();
        List<String> UserOrganizes = new ArrayList<String>();

        for (Users user : arrayJson) {
            DateCreation.add(user.getCreationDate());
        }


        Collections.sort(DateCreation);

        for (int i = 0; i < DateCreation.size(); i++) {
            for (Users user : arrayJson) {
                if (DateCreation.get(i) == user.getCreationDate()) {
                    UserOrganizes.add(user.getName());
                }
            }
        }
        System.out.println("List of Users Ordered by creation date Ascendingly ");
        System.out.println(UserOrganizes);
        Collections.sort(DateCreation, Collections.reverseOrder());

        UserOrganizes.clear();

        for (int i = 0; i < DateCreation.size(); i++) {
            for (Users user : arrayJson) {
                if (DateCreation.get(i) == user.getCreationDate()) {
                    UserOrganizes.add(user.getName());
                }
            }
        }
        System.out.println("List of Users Ordered by creation date Desendentemente ");
        System.out.println(UserOrganizes);

    }

    /**
     * this method searches for all cities that start with a certain letter
     * @param cityToSearch, letter by which the city starts looking
     * @param file, file save Json
     */
    public void searchCity(char cityToSearch, String file) {


         ArrayList<Users> arrayJson = LoadUser(file);

        List<String> cities = new ArrayList<String>();

        for (Users user : arrayJson) {
            char a = user.getCity().charAt(0);
            if (a == cityToSearch) {
                cities.add(user.getCity());
            }
        }

        cities = cities.stream().distinct().collect(Collectors.toList());

        for (int i = 0; i < cities.size(); i++) {
            System.out.println(cities.get(i));
        }
        System.out.println("There are: " + cities.size() + " cities that start with: " + cityToSearch);
    }

    /**
     * this method lists the active users and the amount of them
     * @param file, file save Json
     */
    private void SearchActivite(String file) {

        ArrayList<Users> arrayJson = LoadUser(file);


        int counter = 0;
        for (Users user : arrayJson) {
            if (user.getActive() == true) {
                System.out.println("The user: " + user.getName() + " is active ");
                counter = counter + 1;
            }
        }
        System.out.println("There are: " + counter + " active user.");
    }
}

