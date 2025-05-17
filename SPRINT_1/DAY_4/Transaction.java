package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {

    public static boolean transferCredits(int sender, int receiver, int credits) {
        String deductQuery = "UPDATE students SET credits = credits - ? WHERE id = ? AND credits >= ?";
        String addQuery = "UPDATE students SET credits = credits + ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement deductStmt = null;
        PreparedStatement addStmt = null;
        try {
            conn = Util.getConnection();
            if (conn == null) {
                System.out.println("Failed to connect to database.");
                return false;
            }

            conn.setAutoCommit(false);
            deductStmt = conn.prepareStatement(deductQuery);
            deductStmt.setInt(1, credits);
            deductStmt.setInt(2, sender);
            deductStmt.setInt(3, credits);

            int rowsDeducted = deductStmt.executeUpdate();

            if (rowsDeducted != 1) {
                System.out.println("Sender does not have enough credits or student not found.");
                conn.rollback();
                return false;
            }

            addStmt = conn.prepareStatement(addQuery);
            addStmt.setInt(1, credits);
            addStmt.setInt(2, receiver);

            int rowsAdded = addStmt.executeUpdate();

            if (rowsAdded != 1) {
                System.out.println("Recipient student not found.");
                conn.rollback();
                return false;
            }

            conn.commit();
            System.out.println("Credits transferred successfully.");
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Rollback failed: " + rollbackEx.getMessage());
            }
            System.out.println("Transaction failed: " + e.getMessage());
            return false;

        } finally {
            try {
                if (deductStmt != null) deductStmt.close();
                if (addStmt != null) addStmt.close();
                if (conn != null) conn.setAutoCommit(true); // Reset to default
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Cleanup failed: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        int fromId = 1;
        int toId = 2;
        int creditAmount = 5;

        boolean result = transferCredits(fromId, toId, creditAmount);
        System.out.println("Transaction status: " + (result ? "Success" : "Failure"));
    }
}
