$(document).ready(function() {

    if($("#signup-page").length > 0) {

        jQuery('.dateOfBirthPicker').datetimepicker({
            timepicker: false,
            format:'Y-m-d'
        });
    }
});