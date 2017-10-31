var selectPostion;
$(document).ready(function() {
    var answer = $('#answer').val();
    
    if ($('#editResult').val() == 'success') {
         $('#disabled-screen').css('display', 'block');
         $('#result_text').text('edit successfully');
         $('#result_window').css('display', 'block');
    }
    
    $('#fork').bind('keydown', function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            closeResultWindow(context);
        }
    });
    
    if(answer == 'A') {
        $('#1').css('backgroundImage', 'url(http://examstatic.com/css/images/BTN_Radio_Selected_16x16.png)');
        $('#radio1').css('background-color', '#D2DAE3');

    } else if (answer == 'B') {
        $('#2').css('backgroundImage', 'url(http://examstatic.com/css/images/BTN_Radio_Selected_16x16.png)');
        $('#radio2').css('background-color', '#D2DAE3');

    } else if (answer == 'C') {
        $('#3').css('backgroundImage', 'url(http://examstatic.com/css/images/BTN_Radio_Selected_16x16.png)');
        $('#radio3').css('background-color', '#D2DAE3');

    } else if (answer == 'D') {
        $('#4').css('backgroundImage', 'url(http://examstatic.com/css/images/BTN_Radio_Selected_16x16.png)');
        $('#radio4').css('background-color', '#D2DAE3');
   } 
});

function edit() {
    var answer = document.getElementById("answer");
    $('#answer').val($('#radio' + selectPostion).val());
    $('#editQuestion').submit();
}

function closeResultWindow(context){
    $('#result_window').css('display', 'none');
    $('#disabled-screen').css('display', 'none');
    window.location.href = context + "/page/question/query";
}

function forwardEdit(i, context) {
    var id = i;
    window.location.href = context + '/page/question/edit/' + id;
}

function select(i) {
    selectPostion = i;
    $('#radio' + i).css('background-color', '#D2DAE3');
    var str = getImageSuffix($("#" + i).css('backgroundImage'));

    if (str == "BTN_Radio_Unselected_16x16.png") {
        $('#' + i).css('backgroundImage', 'url(http://examstatic.com/css/images/BTN_Radio_Selected_16x16.png)');
        for (var k = 1; k <= 4; k++) {
            if (i != k) {
                $('#radio' + k).css('background-color', '#FFFFFF');
                $('#' + k).css('backgroundImage', 'url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)');
            }
        }
    } 
} 