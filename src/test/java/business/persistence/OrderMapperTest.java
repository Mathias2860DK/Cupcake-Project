package business.persistence;
import business.entities.Orders;
import business.entities.User;
import business.exceptions.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderMapperTest {

    private final static String DATABASE = "olskerCupcake";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "Vinter2020";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static OrderMapper orderMapper;;


    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            orderMapper = new OrderMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

        // reset test database
        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists orderline");
            stmt.execute("drop table if exists orders");
            stmt.execute("drop table if exists users" );
            stmt.execute("create table " + TESTDATABASE + ".orderline LIKE " + DATABASE + ".orderline;" );
            stmt.execute("create table " + TESTDATABASE + ".orders LIKE " + DATABASE + ".orders;" );
            stmt.execute("create table " + TESTDATABASE + ".users LIKE " + DATABASE + ".users;" );
            stmt.execute(
                    "insert into users values " +
                            "(1,'jens@somewhere.com','jensen','customer','1000'), " +
                            "(2,'ken@somewhere.com','kensen','customer','500'), " +
                            "(3,'robin@somewhere.com','batman','employee','10')");
           stmt.execute("INSERT INTO `olskerCupcake_test`.`orders` (`user_id`, `status`) VALUES ('1', 'paid');");
           stmt.execute("INSERT INTO `olskerCupcake_test`.`orders` (`user_id`, `status`) VALUES ('1', 'paid');");
           stmt.execute("INSERT INTO `olskerCupcake_test`.`orders` (`user_id`, `status`) VALUES ('2', 'paid');");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }


    @AfterEach
    void tearDown() {//Vores 'tearDown' sker i princippet i vores @BeforeAll
        //I vores 'drop table if exists xxx'
    }

    @Test
    void insertOrder() throws UserException {
        //timestamp sættes i mysql med live tider (svært at teste) kunne dog hardcodes.
        int orderId = orderMapper.insertOrder(1,null,"paid");
        assertEquals(4,orderId);
    }

    @Test
    void getAllOrders() throws UserException {
List<Orders> ordersList = orderMapper.getAllOrders();
//Tests the size
int acutal = ordersList.size();
int expected = 3;
assertEquals(expected,acutal);
//tests userId
assertEquals(1,ordersList.get(0).getUserId());
assertEquals(2,ordersList.get(2).getUserId());
//cheks the status
assertEquals("paid",ordersList.get(1).getStatus());


    }

    @Test
    void getOrdersByUserId() throws UserException {
        //henter alle ordre fra userId 1
        List<Orders> ordersList = orderMapper.getOrdersByUserId(1);
        int acutal = ordersList.size();
        int expected = 2;
        assertEquals(expected,acutal);
    }

    @Test
    void getOrderIdByUserIdAndStatus() {
        //Skal måske ændres så ingen test her.
    }

    @Test
    void deleteOrder() throws UserException {
        //Note: deleteOrder returnere de rækker der bliver returneret i mysql.
        int rowsaffected = orderMapper.deleteOrder(2);
        assertEquals(1,rowsaffected);
        //Der findes ikke et 5 orderId, derefter returnere mySql 0 rækker.
        rowsaffected = orderMapper.deleteOrder(5);
        assertEquals(0,rowsaffected);

    }
}