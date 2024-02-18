package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Functions {
    private Connection connection;

    public Functions(Connection connection) {
        this.connection = connection;
    }


    public ArrayList<Movie> getMovies() throws SQLException {
        String query = "SELECT * FROM movies;";
        ArrayList<Movie> movie_list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setMovie_id(resultSet.getInt("movie_id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setRelease_date(resultSet.getString("release_date"));
                movie.setGenre(resultSet.getString("genre"));
                movie_list.add(movie);
            }
        }
        return movie_list;
    }

    public ArrayList<String> getTime(int chosenId) throws SQLException {
        String query = "SELECT time FROM Showtimes WHERE movie_id = ?";
        ArrayList<String> showTime = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, chosenId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    showTime.add(resultSet.getString("time"));
                }
            }
        }
        return showTime;
    }

    public ArrayList<Integer> getSeat(int chosen_showtime_id, int chosen_movie_id) throws SQLException {
        String query = "SELECT s.seat_id, s.row, s.col, s.showtime_id\n" +
                "FROM Seats s\n" +
                "INNER JOIN Showtimes st ON s.showtime_id = st.showtime_id AND s.movie_id = ?\n" +
                "WHERE st.showtime_id = ?\n" +
                "AND EXISTS (\n" +
                "  SELECT 1 FROM Bookings b\n" +
                "  WHERE b.showtime_id = ?\n" +
                "  AND b.seat_id = s.seat_id\n" +
                ");\n";
        ArrayList<Integer> seats = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, chosen_showtime_id);
            statement.setInt(2, chosen_movie_id);
            statement.setInt(3, chosen_showtime_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    seats.add(resultSet.getInt("row"));
                    seats.add(resultSet.getInt("col"));
                }
            }
        }
        return seats;
    }

    public void bookingTicket(User user, int showtimeid, int seatid) throws SQLException{
        String useer_query = "INSERT INTO users (id, name, surname, gender) VALUES (?, ?, ?, ?)";
        String book_query = "INSERT INTO bookings (booking_id, client_id, showtime_id, seat_id, numbers_of_seats, booking_date) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(useer_query)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setBoolean(4, user.isGender());
            statement.executeUpdate();
        }
    }





}
