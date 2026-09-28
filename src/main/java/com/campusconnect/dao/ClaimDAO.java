package com.campusconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.campusconnect.model.Claim;
import com.campusconnect.util.DBConnection;

public class ClaimDAO {

    public boolean addClaim(Claim claim) {

        String sql = "INSERT INTO claims (item_id, claimant_id, message, status) VALUES (?, ?, ?, 'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, claim.getItemId());
            ps.setInt(2, claim.getClaimantId());
            ps.setString(3, claim.getMessage());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Claim> getClaimsForItem(int itemId) {

        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claims WHERE item_id = ? ORDER BY claim_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, itemId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    claims.add(mapRow(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return claims;
    }

    public List<Claim> getClaimsByUser(int claimantId) {

        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claims WHERE claimant_id = ? ORDER BY claim_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, claimantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    claims.add(mapRow(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return claims;
    }

    public boolean updateClaimStatus(int claimId, String status) {

        String sql = "UPDATE claims SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, claimId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Claim mapRow(ResultSet rs) throws Exception {
        Claim claim = new Claim();
        claim.setId(rs.getInt("id"));
        claim.setItemId(rs.getInt("item_id"));
        claim.setClaimantId(rs.getInt("claimant_id"));
        claim.setMessage(rs.getString("message"));
        claim.setStatus(rs.getString("status"));
        claim.setClaimDate(rs.getTimestamp("claim_date"));
        return claim;
    }
}
