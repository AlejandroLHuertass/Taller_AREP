package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;

public class LogServiceFacade {
    private static final String[] LOG_SERVICE_URL = { "http://log_service_rr1:37000/logservice",
            "http://log_service_rr2:38000/logservice", "http://log_service_rr3:39000/logservice" };

    public static void main(String[] args) {
        port(get_current_port());
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        staticFileLocation("/public");

        RemoteLogServerInvoke remoteLogServerInvoke = new RemoteLogServerInvoke(LOG_SERVICE_URL);

        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return remoteLogServerInvoke.invoke(req.queryParams("msg"));
        });
    }

    private static int get_current_port() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}