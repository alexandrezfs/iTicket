$(document).ready(function() {

    $("#allTickets").shuffle({
        itemSelector : '.shuffleItem'
    });

    if($("#signup-page").length > 0) {

        jQuery('.dateOfBirthPicker').datetimepicker({
            timepicker: false,
            format:'Y-m-d'
        });

    }
});