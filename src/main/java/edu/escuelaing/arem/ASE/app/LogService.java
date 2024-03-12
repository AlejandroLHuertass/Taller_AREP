package edu.escuelaing.arem.ASE.app;
import static spark.Spark.get;
import static spark.Spark.port;
import java.util.Date;
import com.mongodb.client.MongoDatabase;

import edu.escuelaing.arem.ASE.app.Repo_DB.MongoUtil;
import edu.escuelaing.arem.ASE.app.Repo_DB.MyServiceDAO;

public class LogService {
    public static void main( String[] args )
    {
        port(getPort());
        MongoDatabase database = MongoUtil.getDB();
        MyServiceDAO myDAO = new MyServiceDAO(database);
        get("/logservice", (req, res) -> {
            Date savingTime = new Date();
            res.type("application/json");
            myDAO.addLog(savingTime, req.queryParams("msg"));
            return myDAO.listLogs();
        });
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}