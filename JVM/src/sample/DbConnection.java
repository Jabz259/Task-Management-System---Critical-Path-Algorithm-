package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class DbConnection {

    public static Connection connect() {
        //connection assigned to null
        Connection con = null;
        try {
            //setting up database
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:ProjectInformation.db");
            System.out.println("Connected!");
        } catch (ClassNotFoundException |  SQLException e) {
            System.out.println(e + " connection issues");
        }
        return con;
    }

    //function to update row dependency costs, so that critical path can update dynamically
    public static void updateDependencyCost (String dependency, String location) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE ProjectInfo set dependencyCost = ? WHERE dependency = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,dependency);
            ps.setString(2,location);
            ps.execute();

            System.out.println("Changes have been made");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    //function to update dependency name per row to ensure critical path can update dynamically
    public static void updateDependencyName (String dependency, String location) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE ProjectInfo set dependency = ? WHERE dependency = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,dependency);
            ps.setString(2,location);
            ps.execute();

            System.out.println("Changes have been made");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    //updating any projects that require edits
    public static void updateDatabase (String projectName, String teamName, String teamLocation, String supportTeamLocation, String mainTaskInfo, String additionalTaskInfo, String dependency, String cost, String location) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE ProjectInfo set projectName= ?,teamName = ?,teamLocation = ?,supportTeamLocation = ?,mainTaskInfo = ?,additionalTaskInfo = ?,dependency = ?,cost = ? WHERE projectName = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,projectName);
            ps.setString(2,teamName);
            ps.setString(3,teamLocation);
            ps.setString(4,supportTeamLocation);
            ps.setString(5,mainTaskInfo);
            ps.setString(6,additionalTaskInfo);
            ps.setString(7,dependency);
            ps.setString(8,cost);

            ps.setString(9,location);
            ps.execute();

            System.out.println("Changes have been made");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }


    //critical path function, retrieve and return data to kotlin constructor Task
    public static HashSet<Task> testingData () {
        //Connecting to database
        Connection con = DbConnection.connect();
        //Preparing statement
        PreparedStatement ps = null;
        //result set declaration
        ResultSet rs = null;
        HashSet <Task> allTasks = new HashSet<>();
        DashboardInterface di = new DashboardInterface();

        try {
            //Database initialization
            String sql = "SELECT * FROM ProjectInfo";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                //for each iteration store the data into variable a from column projectName
                String a = rs.getString("projectName");
                int b = rs.getInt("cost");
                String c = rs.getString("dependency");
                int d = rs.getInt("dependencyCost");

                //retrieveing data for dependency and dependency cost and storing
                //data into kotlin task constructor
                if (c.equals("none")) {
                    Task end = new Task ("End",d);
                    Task A = new Task(a,b,end);
                    allTasks.add(end);
                    allTasks.add(A);
                } else {
                    Task dep = new Task (c,d);
                    Task A = new Task (a,b,dep);
                    allTasks.add(dep);
                    allTasks.add(A);
                }
            }
            //other catch exceptions
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }

        return allTasks;
    }

    //function which fetches the details required for kotlin critcal path and is used to display general info
    public static ArrayList<String> fetchDetails () {
        //Connecting to database
        Connection con = DbConnection.connect();
        //Preparing statement
        PreparedStatement ps = null;
        //result set declaration
        ResultSet rs = null;
        DashboardInterface di = new DashboardInterface();
        ArrayList<String> tableData = new ArrayList<String>();

        try {
            //Database initialization
            String sql = "SELECT * FROM ProjectInfo";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int earlyFinishMessage = 0;

            while (rs.next()) {
                //for each iteration store the data into variable a from column projectName
                String a = rs.getString("projectName");
                int b = rs.getInt("cost");
                String c = rs.getString("dependency");
                int d = rs.getInt("dependencyCost");
                 earlyFinishMessage += b;
                if (c.equals("none")) {
                    tableData.add ("PROJECT NAME = |" + a + "| DAYS= |" + b + "| FIRST NODE IN GRAPH |" + "| EARLY START 0 |"+ "\n \n");
                    tableData.add("Early finish = " + earlyFinishMessage + "\n");
                } else {
                    tableData.add ("PROJECT NAME = |" + a + "| DAYS = |" + b + "| DEPENDENCY = |" +
                            c + "| DEPENDENCY COST = |" + d + "\n \n");
                    tableData.add("Early finish = " + earlyFinishMessage + "\n");
                }
            }
            //other catch exceptions
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }
        return tableData;
    }

    //inserting text into our database.
    public static void insert (String projectName,String teamName, String teamLocation, String supportTeamLocation, String mainTaskInfo, String additionalTaskInfo, String dependency, String cost, String dependencyCost) {

        Connection con = DbConnection.connect();
        PreparedStatement ps = null;

        try {
            //teamName,teamLocation,supportTeamLocation
            String sql = "INSERT INTO ProjectInfo(projectName,teamName,teamLocation,supportTeamLocation, mainTaskInfo, additionalTaskInfo, dependency, cost, dependencyCost) VALUES(?,?,?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString (1, projectName);
            ps.setString (2, teamName);
            ps.setString (3, teamLocation);
            ps.setString (4, supportTeamLocation);
            ps.setString (5, mainTaskInfo);
            ps.setString (6, additionalTaskInfo);
            ps.setString (7, dependency);
            int costData = Integer.parseInt(String.valueOf(cost));
            ps.setString(8, String.valueOf(costData));
            ps.setString(9, String.valueOf(dependencyCost));

            ps.execute();
            System.out.println("Data has been saved!");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    //get the cost of the current dependency
    public static String fetchDependencyCost(String projectName ) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "Select cost from ProjectInfo where projectName = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, projectName);
            rs = ps.executeQuery();
            String dependencyData = rs.getString(1);
            //System.out.println(dependencyData);

            return dependencyData;

        } catch (SQLException e) {

        }finally {
            try {
                rs.close();
                ps.close();
                con.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }

        }

        return null;
    }

    //read all data, this will be used to store data into listview
    public static ArrayList<String> readAllData () {
        //Connecting to database
        Connection con = DbConnection.connect();
        //Preparing statement
        PreparedStatement ps = null;
        //result set declaration
        ResultSet rs = null;
        //tableData String array
        ArrayList<String> tableData = new ArrayList<String>();
            try {
                //Database initialization
                String sql = "SELECT * FROM ProjectInfo";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    //for each iteration store the data into variable a from column projectName
                    String a = rs.getString("projectName");
                    //print out each element
                    //System.out.println("a = " + a);
                    tableData.add(a);
                }
                //other catch exceptions
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                try {
                    rs.close();
                    ps.close();
                    con.close();
                } catch (SQLException e){
                    System.out.println(e.toString());
                }
            }
            //System.out.println(tableData);
            //return all the data that has been stored into this array
            return tableData;
        }



//        public static void listSelectionData (String selectedItem) {
//            Connection con = DbConnection.connect();
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//
//            try {
//                String sql = "Select projectName,teamName,teamLocation,supportTeamLocation from ProjectInfo where projectName = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1,selectedItem);
//                rs = ps.executeQuery();
//
//                String a = rs.getString(1);
//                String b = rs.getString(2);
//                String c = rs.getString(3);
//                String d = rs.getString(4);
//
//                System.out.println("Project Name: " +  a + " Team Name: " + b + " teamLocation: " + c + " Support Location: " + d);
//
//            } catch (SQLException e) {
//                System.out.println(e.toString());
//
//            } finally {
//
//                try {
//                    rs.close();
//                    ps.close();
//                    con.close();
//                } catch (SQLException e) {
//                    System.out.println(e.toString());
//                }
//            }
//
//        }



//    public static void readSpecificRow() {
//        Connection con = DbConnection.connect();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String test = "UXDesign";
//
//        try {
//            String sql = "Select projectName,teamName,teamLocation,supportTeamLocation from ProjectInfo where projectName = ?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1,test);
//            rs = ps.executeQuery();
//
//            String a = rs.getString(1);
//            String b = rs.getString(2);
//            String c = rs.getString(3);
//            String d = rs.getString(4);
//
//            System.out.println(a + "                          "+ b + c + d);
//
//
//        } catch (SQLException e) {
//            System.out.println(e.toString());
//        } finally {
//
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (SQLException e) {
//                System.out.println(e.toString());
//            }
//        }
//
//    }

//
//    public static String [] readAllData () {
//        //Connecting to database
//        Connection con = DbConnection.connect();
//        //Preparing statement
//        PreparedStatement ps = null;
//        //result set declaration
//        ResultSet rs = null;
//
//        //tableData String array
//        String tableData [] = new String[300];
//
//        try {
//            //Database initialization
//            String sql = "SELECT * FROM ProjectInfo";
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            //counter initialization
//            int counter = 0;
//
//            while (rs.next()) {
//                //for each iteration store the data into variable a from column projectName
//                String a = rs.getString("projectName");
//                //print out each element
//                //increment counter
//                //for each increment, store a element from projectName
//                tableData[counter]= a;
//                counter ++;
//                //testing extra stuff
//                //String b = rs.getString("teamName");
//                //String c = rs.getString("teamLocation");
//                //String d = rs.getString("supportTeamLocation");
//            }
//            //other catch exceptions
//        } catch (SQLException e) {
//            System.out.println(e.toString());
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (SQLException e){
//                System.out.println(e.toString());
//            }
//        }
//
//        System.out.println(Arrays.toString(tableData));
//        //return all the data that has been stored into this array
//        return tableData;
//    }
}


