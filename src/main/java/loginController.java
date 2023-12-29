import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/login")
public class loginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //  String username1 = req.getParameter("username");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url="jdbc:mysql::/localhost:3306/employee";
            String username="root";
            String password="123456";
            Connection connection = DriverManager.getConnection(url, username, password);


            String sqlQuery="select username,password from loginfo where username=? AND password=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            preparedStatement.executeQuery(sqlQuery);
            PrintWriter writer = resp.getWriter();


        } catch (ClassNotFoundException e)
        {
            PrintWriter writer = resp.getWriter();
            writer.println("class not found");

        } catch (SQLException e) {
            PrintWriter writer = resp.getWriter();
            writer.println("SQL exception");
        }
    }
}
