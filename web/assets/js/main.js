$(document).ready(function() {

    $("#allTickets").shuffle({
        itemSelector : '.shuffleItem'
    });

    setInterval(function() {
        $("#allTickets").shuffle('update');
    }, 500);

    if($("#signup-page").length > 0) {

        $('.dateOfBirthPicker').datetimepicker({
            timepicker: false,
            format:'Y-m-d'
        });

    }
});