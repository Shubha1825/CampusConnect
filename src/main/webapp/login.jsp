<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CampusConnect - Login</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f4f7;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }

        .login-box {
            background: white;
            width: 380px;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.15);
        }

        h1 { text-align: center; margin-bottom: 5px; }

        .subtitle { text-align: center; color: #666; margin-bottom: 25px; }

        label { display: block; margin-top: 15px; margin-bottom: 5px; font-weight: bold; }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 25px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            background: #2c3e50;
            color: white;
        }

        .register-link { text-align: center; margin-top: 20px; }

        .msg {
            padding: 10px;
            border-radius: 5px;
            margin-top: 15px;
            font-size: 14px;
            text-align: center;
        }
        .msg.error { background: #fdecea; color: #b3261e; }
        .msg.success { background: #eafaf0; color: #1e7a34; }
    </style>
</head>

<body>

<div class="login-box">

    <h1>CampusConnect</h1>
    <p class="subtitle">Login to your account</p>

    <% if (request.getAttribute("error") != null) { %>
        <div class="msg error"><%= request.getAttribute("error") %></div>
    <% } %>

    <% if ("true".equals(request.getParameter("registered"))) { %>
        <div class="msg success">Registration successful. Please log in.</div>
    <% } %>

    <form action="login" method="post">

        <label>Email</label>
        <input type="email" name="email" placeholder="Enter your email" required>

        <label>Password</label>
        <input type="password" name="password" placeholder="Enter your password" required>

        <button type="submit">Login</button>

    </form>

    <div class="register-link">
        Don't have an account?
        <a href="register.jsp">Register</a>
    </div>

</div>

</body>
</html>
