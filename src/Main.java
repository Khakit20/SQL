import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Scanner;
 public class Main {
    public static void main(String[] args) {
        class MySQL {
            public MySQL() {
            }

            public  void main(String[] args) throws Exception {
                get();
            }

            public  void createTable() throws Exception {
                try {
                    Scanner myObj = new Scanner(System.in);
                    Connection con = getConnection();
                    System.out.println("請輸入");
                    String number = myObj.nextLine();
                    String newData = "CREATE TABLE IF NOT EXISTS `input" + number + "data`(`data_id` INT primary KEY,";

                    for(int i = 1; i < Integer.parseInt(number); ++i) {
                        newData = newData + "`DATA" + i + "` INT default(0),";
                    }

                    newData = newData + "`DATA" + number + "` INT default(0) );";
                    System.out.println(newData);
                    PreparedStatement create = con.prepareStatement(newData);
                    create.executeUpdate();
                } catch (Exception var8) {
                    System.out.println(var8);
                } finally {
                    System.out.println("Function complete");
                }

            }

            public  void post() throws Exception {
                try {
                    Scanner myObj = new Scanner(System.in);
                    Connection con = getConnection();
                    System.out.println("請輸入資料數量:");
                    int number = Integer.valueOf(myObj.nextLine());
                    System.out.println("請輸入要儲存的資料:");
                    String Data = myObj.nextLine();
                    String[] Datalist = Data.split(",");
                    String addData = "INSERT INTO input" + number + "data (";

                    int i;
                    for(i = 1; i < number; ++i) {
                        addData = addData + "DATA" + i + ", ";
                    }

                    addData = addData + "DATA" + number + ") VALUES (";

                    for(i = 1; i < number; ++i) {
                        addData = addData + Datalist[i - 1] + ", ";
                    }

                    addData = addData + Datalist[number - 1] + ")";
                    System.out.println(addData);
                    PreparedStatement posted = con.prepareStatement(addData);
                    posted.executeUpdate();
                } catch (Exception var10) {
                    System.out.println(var10);
                } finally {
                    System.out.println("Insert Completed");
                }

            }

            public  void get() throws Exception {
                try {
                    Scanner myObj = new Scanner(System.in);
                    Connection con = getConnection();
                    System.out.println("請輸入要拿取的資料:");
                    String number = myObj.nextLine();
                    String getData = "SELECT * FROM input" + number + "Data;";
                    PreparedStatement statement = con.prepareStatement(getData);
                    ResultSet result = statement.executeQuery();
                    System.out.println(getData);
                    new ArrayList();

                    while(result.next()) {
                        String Datalist = "";

                        for(int i = 1; i < Integer.parseInt(number); ++i) {
                            Datalist = Datalist + result.getString("DATA" + i) + ",";
                        }

                        Datalist = Datalist + result.getString("DATA" + number) + "\n";
                        System.out.println(Datalist);
                    }

                    System.out.println("ALL record have been selected");
                } catch (Exception var9) {
                    System.out.println(var9);
                }

            }

            public  Connection getConnection() throws Exception {
                try {
                    String url = "jdbc:mysql://localhost:3306/mydata";
                    String username = "root";
                    String password = "29992278";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    System.out.println("Driver loaded!");
                    System.out.println("try login");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inputdata?useSSL=false&allowPublicKeyRetrieval=true", "root", "29992278");
                    System.out.println("success");
                    return conn;
                } catch (Exception var5) {
                    System.out.println("ERROR");
                    System.out.println(var5);
                    return null;
                }
            }
        }
        System.out.println("Hello world!");
    }
}