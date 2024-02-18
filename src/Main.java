import models.Movie;
import models.User;
import models.ConnectionManager;
import models.Functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            ArrayList<Movie> lst = new ArrayList<>();
            Functions func = new Functions(connection);
            lst = func.getMovies();
            System.out.println("Movies: ");
            for (int i = 0; i < lst.size(); i++){
                System.out.println(lst.get(i));
            }
            System.out.println("Choose time: ");
            Scanner scan = new Scanner(System.in);

            int num1 = scan.nextInt();

            ArrayList<String> swt = new ArrayList<>();
            swt = func.getTime(num1);
            for (int i = 0; i < swt.size(); i++){
                System.out.println(swt.get(i));
            }
            ArrayList<Integer> st = new ArrayList<>();
            int num2 = scan.nextInt();


            st = func.getSeat(num2, num1);
            for (int i = 0; i < st.size(); i++){
                System.out.println(st.get(i));
            }
            System.out.println("Do you gonna book");
            int cond = scan.nextInt();
            String t_name = scan.nextLine();
            String t_surname = scan.nextLine();



            if (cond == 1){
                User temp = new User(t_name, t_surname, true);
                func.bookingTicket(temp,2,2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }

    }
}

