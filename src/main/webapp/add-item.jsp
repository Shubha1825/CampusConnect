<%@ page import="com.campusconnect.model.User" %>
<%
    User currentUser = (session != null) ? (User) session.getAttribute("user") : null;
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CampusConnect - Post Item</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@700;800&family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<nav class="cc-nav">
    <span class="brand">CampusConnect <span class="tag">Lost &amp; Found HQ</span></span>
    <div>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="items.jsp">Browse Items</a>
        <a href="add-item.jsp">Post Item</a>
        <a href="logout">Logout</a>
    </div>
</nav>

<div class="cc-content" style="max-width: 560px;">
    <div class="cc-card">
        <span class="eyebrow">New posting</span>
        <h2 style="margin-top:8px;">Post a Lost or Found Item</h2>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="add-item" method="post">

            <div class="field">
                <label for="type">Type</label>
                <select id="type" name="type" required>
                    <option value="LOST">Lost Item</option>
                    <option value="FOUND">Found Item</option>
                </select>
            </div>

            <div class="field">
                <label for="itemName">Item Name</label>
                <input type="text" id="itemName" name="itemName" placeholder="e.g. Blue backpack" required>
            </div>

            <div class="field">
                <label for="description">Description</label>
                <textarea id="description" name="description" rows="4" placeholder="Any identifying details" required></textarea>
            </div>

            <div class="field">
                <label for="category">Category</label>
                <input type="text" id="category" name="category" placeholder="e.g. Electronics, Bag, ID Card" required>
            </div>

            <div class="field">
                <label for="location">Location</label>
                <input type="text" id="location" name="location" placeholder="Where it was lost/found" required>
            </div>

            <div class="field">
                <label for="itemDate">Date</label>
                <input type="date" id="itemDate" name="itemDate" required>
            </div>

            <button type="submit" class="btn btn-primary btn-block" style="margin-top: 24px;">Post Item</button>

        </form>
    </div>
</div>

</body>
</html>
