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
    <title>CampusConnect - Dashboard</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@700;800&family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<nav class="cc-nav">
    <span class="brand">CampusConnect <span class="tag">Lost &amp; Found HQ</span></span>
    <div>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="items">Browse Items</a>
        <a href="add-item.jsp">Post Item</a>
        <a href="logout">Logout</a>
    </div>
</nav>

<div class="cc-content">
    <div class="cc-card">
        <span class="eyebrow">Home base</span>
        <h2 style="margin-top:8px;">Welcome, <%= currentUser.getName() %>!</h2>
        <p style="color: var(--slate);">Report a lost item, post something you found, or browse what others have posted.</p>

        <div class="action-row">
            <a class="btn btn-primary" href="add-item.jsp">+ Post Lost/Found Item</a>
            <a class="btn btn-secondary" href="items">Browse Items</a>
        </div>
    </div>
</div>

</body>
</html>
