$(document).ready(function() {

    $("#allTickets").shuffle({
        itemSelector : '.shuffleItem'
    });

    $("#newTickets").shuffle({
        itemSelector : '.shuffleItem'
    });

    if($("#signup-page").length > 0) {

        $('.dateOfBirthPicker').datetimepicker({
            timepicker: false,
            format:'Y-m-d'
        });

    }
});