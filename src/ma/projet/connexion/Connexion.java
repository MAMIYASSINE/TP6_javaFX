package ma.projet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/demoJDBC?useSSL=false&serverTimezone=UTC";
    private static Connection cn;

    static {
        try {
            // Étape 2 : Authentification auprès de la base de données et sélectionner le schéma
            cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connected to database");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return cn;
    }
}
