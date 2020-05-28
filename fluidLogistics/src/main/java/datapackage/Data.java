package datapackage;
//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public abstract class Data{
    private String table;
    private String[] fields;
    private String where;
    private DbConnector dbConnector;


    public  Data(){}

    public List<String[]> getData() throws Exception{

        String query = "";
        if(fields.length == 0 || fields == null){
            query = "*";
        }else{
            for (int i = 0; i< fields.length; i++){
                query = query + fields[i].trim() + ", ";
            }
        }
        query = query.substring(query.length()-2);
        if ( table.trim() == "") {
            throw new Exception("Table is not set!");
        }

        query += " FROM " + table.trim();

        if (!(where.trim() == "" )){
           query = query + " WHERE " + where.trim();
        }

        ArrayList<String[]> list = new ArrayList<String[]>();

        Statement stmt = dbConnector.createStatement();

        try(ResultSet resultset = stmt.executeQuery(query)){

            List<String[]> returnList = new ArrayList<String[]>();

            while(resultset.next()){
                String[] tmpArray = new String[fields.length];
                for (int i = 0; i<fields.length; i++){
                    tmpArray[i] = resultset.getString(fields[i]);
                }
                returnList.add(tmpArray);
            }

            return returnList;
        }
        catch (Exception e){
            throw new Exception("Error in SQL Statement!");
        }
    }
}