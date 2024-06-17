package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class report {
    private int report_id;
    private int user_id;
    private int verification_id;
    private String input_shoe_model;
    private String purchase_date;
    private String type_seller;
    private byte[] product_photo;
    private byte[] receipt_photo;

    public report(int report_id, int user_id, int verification_id, String input_shoe_model, String purchase_date, String type_seller) {
        this.report_id = report_id;
        this.user_id = user_id;
        this.verification_id = verification_id;
        this.input_shoe_model = input_shoe_model;
        this.purchase_date = purchase_date;
        this.type_seller = type_seller;
    }

    public report(int user_id, int verification_id, String input_shoe_model, String purchase_date, String type_seller) {
        this.user_id = user_id;
        this.verification_id = verification_id;
        this.input_shoe_model = input_shoe_model;
        this.purchase_date = purchase_date;
        this.type_seller = type_seller;
    }

    public report() {
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVerification_id() {
        return verification_id;
    }

    public void setVerification_id(int verification_id) {
        this.verification_id = verification_id;
    }

    public String getInput_shoe_model() {
        return input_shoe_model;
    }

    public void setInput_shoe_model(String input_shoe_model) {
        this.input_shoe_model = input_shoe_model;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getType_seller() {
        return type_seller;
    }

    public void setType_seller(String type_seller) {
        this.type_seller = type_seller;
    }

    public byte[] getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(byte[] product_photo) {
        this.product_photo = product_photo;
    }

    public byte[] getReceipt_photo() {
        return receipt_photo;
    }

    public void setReceipt_photo(byte[] receipt_photo) {
        this.receipt_photo = receipt_photo;
    }

    public int saveReport() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int generatedReportId = -1;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "INSERT INTO report (user_id, verification_id, input_shoe_model, purchase_date, type_seller) VALUES (?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, user_id); // Assuming user_id is set before calling saveReport()
            pstmt.setInt(2, verification_id); // Assuming verification_id is set before calling saveReport()
            pstmt.setString(3, input_shoe_model); // Assuming input_shoe_model is set before calling saveReport()
            pstmt.setString(4, purchase_date); // Assuming purchase_date is set before calling saveReport()
            pstmt.setString(5, type_seller); // Assuming type_seller is set before calling saveReport()

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedReportId = rs.getInt(1); // Get the generated report_id
                }
            }

        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return generatedReportId;
    }

    public void savePhotoToDatabase(String verification_id, byte[] imageData, String photoType) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql;
            if (photoType.equals("product_photo")) {
                sql = "UPDATE report SET product_photo = ? WHERE verification_id = ?";
            } else if (photoType.equals("receipt_photo")) {
                sql = "UPDATE report SET receipt_photo = ? WHERE verification_id = ?";
            } else {
                throw new IllegalArgumentException("Invalid photo type");
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setBytes(1, imageData);
            pstmt.setString(2, verification_id);
            pstmt.executeUpdate();

        } finally {
            // Close resources
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    

    

    // public static void main(String[] args) {
    //     report.addReport(1, 1, "Air Jordan 1", "2021-10-10", "Retail");
    // }
}
