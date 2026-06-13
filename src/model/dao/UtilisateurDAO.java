package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Utilisateur utilisateur) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("INSERT INTO Utilisateur VALUES (?, ?, ?, ?)")) {
            st.setString(1, utilisateur.getEmail());
            st.setString(2, utilisateur.getMdp());
            st.setString(3, utilisateur.getNom());
            st.setString(4, utilisateur.getPrenom());
            try {
                return st.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new LinkedList<>();
        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Utilisateur")) {
            while (rs.next()) {
                String email = rs.getString("email");
                String motDePasse = rs.getString("motDePasse");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                utilisateurs.add(new Utilisateur(nom, prenom, email, motDePasse));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur findByID(String email) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM Utilisateur WHERE email= ?")) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String motDePasse = rs.getString("motDePasse");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    return new Utilisateur(nom, prenom, email, motDePasse);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Utilisateur utilisateur) {
        String query = "UPDATE Utilisateur SET motDePasse= ?, nom= ?, prenom= ? WHERE email= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, utilisateur.getMdp());
            st.setString(2, utilisateur.getNom());
            st.setString(3, utilisateur.getPrenom());
            st.setString(4, utilisateur.getEmail());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Utilisateur utilisateur) {
        String query = "DELETE FROM Utilisateur WHERE email= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, utilisateur.getEmail());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public String findNomByID(String email) {
        String res="";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT nom,prenom FROM Utilisateur WHERE email= ?")) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    res += prenom+" "+nom;
                    return res;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String findMdpByID(String mail){
        String res="";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT motDePasse FROM Utilisateur WHERE email= ?")) {
            st.setString(1, mail);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String mdp = rs.getString("motDePasse");
                    res += mdp;
                    return res;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
