// CampusConnect - shared client-side validation
// NOTE: this is UX help only. The server (servlets/DAO) still re-validates
// and is the real source of truth - never trust client-side checks alone.

function ccValidateEmail(input, errorEl) {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const valid = pattern.test(input.value.trim());

    input.classList.toggle('valid', valid && input.value.length > 0);
    input.classList.toggle('invalid', !valid && input.value.length > 0);

    if (errorEl) {
        errorEl.classList.toggle('show', !valid && input.value.length > 0);
    }
    return valid;
}

function ccValidatePasswordFormat(input, errorEl, minLength) {
    minLength = minLength || 6;
    const valid = input.value.length >= minLength;

    input.classList.toggle('valid', valid);
    input.classList.toggle('invalid', !valid && input.value.length > 0);

    if (errorEl) {
        errorEl.classList.toggle('show', !valid && input.value.length > 0);
    }
    return valid;
}

function ccTogglePassword(button, inputId) {
    const input = document.getElementById(inputId);
    const isHidden = input.type === 'password';
    input.type = isHidden ? 'text' : 'password';
    button.textContent = isHidden ? 'Hide' : 'Show';
}

// Simple strength scoring: length + variety of character classes
function ccPasswordStrength(password) {
    let score = 0;
    if (password.length >= 6) score++;
    if (password.length >= 10) score++;
    if (/[A-Z]/.test(password)) score++;
    if (/[0-9]/.test(password)) score++;
    if (/[^A-Za-z0-9]/.test(password)) score++;

    if (score <= 1) return { level: 'weak', label: 'Weak', color: '#E63946', percent: 25 };
    if (score <= 3) return { level: 'okay', label: 'Okay', color: '#FCA311', percent: 60 };
    return { level: 'strong', label: 'Strong', color: '#2A9D8F', percent: 100 };
}

function ccUpdatePasswordStrengthUI(password, barEl, labelEl) {
    if (password.length === 0) {
        barEl.style.width = '0%';
        labelEl.textContent = '';
        return;
    }
    const result = ccPasswordStrength(password);
    barEl.style.width = result.percent + '%';
    barEl.style.background = result.color;
    labelEl.textContent = result.label + ' password';
    labelEl.style.color = result.color;
}
