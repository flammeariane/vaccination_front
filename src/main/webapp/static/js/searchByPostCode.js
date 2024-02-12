
document.addEventListener("DOMContentLoaded", function () {
    var input = document.getElementById("searchByPostCode");
    input.addEventListener("keyup", function () {
        myFunction();
    });
});

function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchByPostCode");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableauPatient");
    tr = table.getElementsByTagName("tr");

    for (i = 1; i < tr.length; i++) { // Commencez par i = 1 pour ignorer l'en-tête du tableau
        td = tr[i].getElementsByTagName("td")[2]; // Index  pour la colonne "Code postal"
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
