package defaultDatabase;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySQL {
    public static void main(String[] args) throws Exception {
        get();
    }

    public static void createTable() throws Exception{
        try{
            Scanner myObj = new Scanner(System.in);
            Connection con = getConnection();
            System.out.println("請輸入一個數字作為新創資料表的參考");
            String number = myObj.nextLine();
            String newData = "CREATE TABLE IF NOT EXISTS `input"+number+"data`(`data_id` INT primary KEY,";
            for(int i = 1 ;i < Integer.parseInt(number) ; i++){
                newData +="`DATA"+ i +"` INT default(0),";
            }
            newData += "`DATA"+number+"` INT default(0) );";
            System.out.println(newData);
            PreparedStatement create = con.prepareStatement(newData);
            create.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Function complete");
        }
    }

    public static void post() throws Exception{
        try{
            Scanner myObj = new Scanner(System.in);
            Connection con = getConnection();
            System.out.println("請輸入資料數量:");
            int number = Integer.valueOf(myObj.nextLine());
            System.out.println("請輸入要儲存的資料:");
            String Data = myObj.nextLine();
            String Datalist[]= Data.split(",");
            String addData = "INSERT INTO input"+ number +"data (";
            for(int i = 1 ; i <number ; i++){
                addData +="DATA"+ i +", ";
            }
            addData += "DATA"+ number +") VALUES (";
            for(int i = 1 ; i <number ; i++){
                addData += Datalist[i-1] + ", " ;
            }
            addData += Datalist[number-1]+")";
            System.out.println(addData);
            PreparedStatement posted = con.prepareStatement(addData);
            posted.executeUpdate();
        }catch (Exception e){System.out.println(e);}
        finally {
            System.out.println("Insert Completed");
        }
    }

    public static void get() throws Exception{
        try{
            Scanner myObj = new Scanner(System.in);
            Connection con = getConnection();
            System.out.println("請輸入要拿取的資料:");
            String number = null;
            number = myObj.nextLine();
            String getData = "SELECT * FROM input"+number+"Data;";
            PreparedStatement statement = null;
            System.out.println(con);
            statement = con.prepareStatement(getData);
            ResultSet result = statement.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while(result.next()){
                String Datalist = "";
                for(int i = 1;i < Integer.parseInt(number) ;i++){
                    Datalist += result.getString("DATA"+ i) +",";
                }
                Datalist += result.getString("DATA"+number)+"\n";
                System.out.println(Datalist);
            }
            System.out.println("ALL record have been selected");
        }catch (Exception e){System.out.println(e);}
    }

    public static Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/mydata";
            String username = "root";
            String password = "29992278";
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("try login");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inputdata", "root", "29992278");
            System.out.println("success");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
