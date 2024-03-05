package edu.escuelaing.arem.ASE.app;

import java.rmi.server.RemoteServer;

import static spark.Spark.staticFiles;
import static spark.Spark.get;

public class LogServiceFacade {
    public static void main(String[] args) {

        //RemoteLogServerInvoke remoteLogServerInvoke = new RemoteLogServerInvoke(); // Crear una instancia de RemoteLogServerInvoke
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        staticFiles.location("/public"); // Static files

        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return "{\"login1\":\"20-2-2024-log initial\"}";
        });
    }
}