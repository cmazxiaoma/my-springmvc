$(document).ready(function() {
    $('#question').mouseover(function() {
        $(this).css('background-color', '#D2DAE3');
        $('#exam').css('background-color', '#FFFFFF');
    });
    
    $('#exam').mouseover(function() {
        $(this).css('background-color', '#D2DAE3');
        $('#question').css('background-color', '#FFFFFF');
    });
    
    $('#exam').mouseleave(function() {
        $('#exam').css('background-color', '#FFFFFF');
        $('#question').css('background-color', '#FFFFFF');
        $('#question').css('background-color', '#D2DAE3');
    });
    
})

function getImageSuffix(str) {
    return str.substring(str.lastIndexOf("/") + 1, str.length - 2);
}