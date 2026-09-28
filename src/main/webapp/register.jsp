<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CampusConnect - Register</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@700;800&family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<div class="cc-hero">
    <div class="cc-card" style="max-width: 420px;">

        <span class="eyebrow">Join the crew</span>
        <h1 style="font-size: 30px;">Create your account</h1>
        <p class="subtitle">Student sign-up for CampusConnect.</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>

        <form id="registerForm" action="register" method="post" novalidate>

            <div class="field">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>

            <div class="field">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="you@college.edu" required>
                <div class="field-error" id="emailError">Enter a valid email address.</div>
            </div>

            <div class="field">
                <label for="password">Password</label>
                <div class="password-wrap">
                    <input type="password" id="password" name="password" placeholder="At least 6 characters" required minlength="6">
                    <button type="button" class="toggle-pw" onclick="ccTogglePassword(this, 'password')">Show</button>
                </div>
                <div class="pw-strength"><div class="pw-strength-bar" id="pwBar"></div></div>
                <div class="pw-strength-label" id="pwLabel"></div>
                <div class="field-error" id="passwordError">Password must be at least 6 characters.</div>
            </div>

            <div class="field">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone" placeholder="Enter your phone number" required>
            </div>

            <button type="submit" class="btn btn-primary btn-block" style="margin-top: 24px;">Register</button>

        </form>

        <p style="text-align:center; margin-top: 20px; font-size: 14px;">
            Already have an account? <a href="login.jsp" style="color: var(--gold-dark); font-weight: 700;">Login</a>
        </p>

    </div>
</div>

<script src="assets/js/validate.js"></script>
<script>
    const emailInput = document.getElementById('email');
    const emailError = document.getElementById('emailError');
    const passwordInput = document.getElementById('password');
    const passwordError = document.getElementById('passwordError');
    const pwBar = document.getElementById('pwBar');
    const pwLabel = document.getElementById('pwLabel');

    emailInput.addEventListener('input', () => ccValidateEmail(emailInput, emailError));
    passwordInput.addEventListener('input', () => {
        ccValidatePasswordFormat(passwordInput, passwordError, 6);
        ccUpdatePasswordStrengthUI(passwordInput.value, pwBar, pwLabel);
    });

    document.getElementById('registerForm').addEventListener('submit', (e) => {
        const emailOk = ccValidateEmail(emailInput, emailError);
        const pwOk = ccValidatePasswordFormat(passwordInput, passwordError, 6);
        if (!emailOk || !pwOk) {
            e.preventDefault();
        }
    });
</script>

</body>
</html>
