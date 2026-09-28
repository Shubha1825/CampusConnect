package com.campusconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.campusconnect.model.Item;
import com.campusconnect.util.DBConnection;

public class ItemDAO {

    public boolean addItem(Item item) {

        String sql = "INSERT INTO items (user_id, item_name, description, category, location, item_date, type, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 'OPEN')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getUserId());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getCategory());
            ps.setString(5, item.getLocation());
            ps.setDate(6, item.getItemDate());
            ps.setString(7, item.getType());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> getAllItems() {
        return searchItems(null, null);
    }

    /**
     * Search items by free-text keyword (matched against item_name,
     * description, category, location) and/or type (LOST / FOUND).
     * Pass null to skip a filter.
     */
    public List<Item> searchItems(String keyword, String type) {

        List<Item> items = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT * FROM items WHERE 1=1"
        );

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (item_name LIKE ? OR description LIKE ? OR category LIKE ? OR location LIKE ?)");
        }
        if (type != null && !type.trim().isEmpty()) {
            sql.append(" AND type = ?");
        }
        sql.append(" ORDER BY item_date DESC, id DESC");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int idx = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                String like = "%" + keyword.trim() + "%";
                ps.setString(idx++, like);
                ps.setString(idx++, like);
                ps.setString(idx++, like);
                ps.setString(idx++, like);
            }
            if (type != null && !type.trim().isEmpty()) {
                ps.setString(idx++, type);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapRow(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public Item getItemById(int id) {

        String sql = "SELECT * FROM items WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStatus(int itemId, String status) {

        String sql = "UPDATE items SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, itemId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> getItemsByUser(int userId) {

        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE user_id = ? ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapRow(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    private Item mapRow(ResultSet rs) throws Exception {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setUserId(rs.getInt("user_id"));
        item.setItemName(rs.getString("item_name"));
        item.setDescription(rs.getString("description"));
        item.setCategory(rs.getString("category"));
        item.setLocation(rs.getString("location"));
        item.setItemDate(rs.getDate("item_date"));
        item.setType(rs.getString("type"));
        item.setStatus(rs.getString("status"));
        return item;
    }
}
