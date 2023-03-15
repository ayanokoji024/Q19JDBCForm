import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Q19JDBCFormServlet extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>020_Anomitro_Das</title>");
        out.println("</head>");
        out.println("<body>");

        String name = request.getParameter("name");
        try{
          Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e){
          System.out.println("Driver object not instantiated!!");
          e.printStackTrace();
        }

        Connection con = null;

        try{
          con = DriverManager.getConnection("jdbc:mysql://172.16.4.50/test", "be20020", "swGFgUqJ");
        } catch(Exception e){
          System.out.println("Connction Failed!!");
          e.printStackTrace();
        }
        out.println("Connected");

        Statement stmt = null;
        ResultSet rs = null;

        try{
              stmt = con.createStatement();
            } catch(SQLException e){
              out.println("Connection not created");
              e.printStackTrace();
            }

        

        if (name!=null) {
            out.println("Name:");
            out.println(" = " + name + "<br>");
            try{
              rs = stmt.executeQuery("select * from students where name like '%" + name +"%'");
              while(rs.next()) {
                out.println(rs.getString("accNum"));
                out.println(rs.getString("holderName"));
                out.println(rs.getString("balance"));
              }
            } catch(SQLException e){
              out.println("Command not executed");
              e.printStackTrace();
            }
        } else {
            out.println("No Parameters, Please enter some");
        }

        out.println("statement executed");


        out.println("<P>");
        out.print("<form action=\"");
        out.print("Q19JDBCForm\" ");
        out.println("method=POST>");
        out.println("name:");
        out.println("<input type=text size=20 name=name>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}