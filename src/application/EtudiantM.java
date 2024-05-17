package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.projet.connexion.Connexion;

public class EtudiantM {

	private Connection connection;

	public EtudiantM() {
		this.connection = Connexion.getConnection();
	}

	public boolean create(Etudiant o) {
		String requete = "INSERT INTO etudiant(nom,prenom,filiere,sexe) VALUES(?,?,?,?) ";
		try {
			PreparedStatement stmt = connection.prepareStatement(requete);
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			stmt.setString(3, o.getFilere());
			stmt.setString(4, o.getSexe());

			int resultats = stmt.executeUpdate();
			stmt.close();
			return resultats > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Etudiant o) {
		String requete = "DELETE FROM etudiant WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(requete);
			stmt.setInt(1, o.getId());

			int resultats = stmt.executeUpdate();
			return resultats > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Etudiant o) {

		String requete = "UPDATE etudiant SET nom = ?, prenom = ?, filiere = ?, sexe = ? WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(requete);
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			stmt.setString(3, o.getFilere());
			stmt.setString(4, o.getSexe());
			stmt.setInt(5, o.getId());

			int resultats = stmt.executeUpdate();
			return resultats > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Etudiant findById(int id) {

		String requete = "SELECT * FROM etudiant WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);

			try {
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
							rs.getString("filiere"), rs.getString("sexe"));
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally { 
				try {
					stmt.close(); // Ensure the statement is closed
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Etudiant> findAll() {

		String requete = "SELECT * FROM etudiant";
		try {
			PreparedStatement stmt = connection.prepareStatement(requete);
			ResultSet rs = stmt.executeQuery();

			List<Etudiant> etudiants = new ArrayList<>();
			while (rs.next()) {
				Etudiant etudiant = new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("filiere"), rs.getString("sexe"));
				etudiants.add(etudiant);
			}
			return etudiants;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
