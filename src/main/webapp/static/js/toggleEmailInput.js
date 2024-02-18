
function toggleEmailInput() {
    var checkbox = document.getElementById('recevoirEmail');
    var emailInput = document.getElementById('emailInput');
    if (checkbox.checked) {
        emailInput.style.display = 'block';
    } else {
        emailInput.style.display = 'none';
    }
}
   