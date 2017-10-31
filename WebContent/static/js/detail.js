$(document).ready(function() {
    var answer = $('#answer').val();
    
    if(answer == 'A') {
        $('#chooseA').css('background-color', '#D2DAE3');
    } else if (answer == 'B') {
        $('#chooseB').css('background-color', '#D2DAE3');
    } else if (answer == 'C') {
        $('#chooseC').css('background-color', '#D2DAE3');
    } else if (answer == 'D') {
        $('#chooseD').css('background-color', '#D2DAE3');
    } 
});

function forwardEdit(i, context) {
    var id = i;
    window.location.href = context + '/page/question/edit/' + id;
}