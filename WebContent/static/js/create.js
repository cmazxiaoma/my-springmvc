var selectPostion;
var input1;
var input2;
var input3;
var input4;
var desc;
var context;
var questionCodeId;
$(document).ready(function() {
    context = $('#context').val();
    $('#fork').bind('keydown', function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            closeResultWindow(context)
        }
    });
    
    questionCodeId = $("input[name='questionCodeId']").val();
    if ($('#createResult').val() == 'success') {
        $('#disabled-screen').css('display', 'block');
        $('#result_text').text('create successfully');
        $('#result_window').css('display', 'block');
    }
    $("input[name='questionCodeId']").val('Q' + getRandom(99999999));
    checkQuestionCodeId();
    $('#createButton').attr('disabled', "true");
    $('#createButton').css('background-color', "#2E4358");
    desc = $('.desc').val();
    $(document).on('input propertychange', function(){
        input1 = $('#radio1').val();
        input2 = $('#radio2').val();
        input3 = $('#radio3').val();
        input4 = $('#radio4').val();
        changeCreateButtonStyle();
    });
    
    $('.desc').on('input propertychange', function(){
        desc = $('.desc').val();
        changeCreateButtonStyle();
    });
});

function checkQuestionCodeId() {
    $.ajax ({
        type: 'GET',
        url: context + '/page/json/checkCodeId?questionCodeId=' + $("input[name='questionCodeId']").val(),
        ContentType: 'application/json; charset=UTF-8',
        success: function(data) {
            var result = data.result;
            if (result == 'error') {
                $("input[name='questionCodeId']").val('Q' + getRandom(99999999));
                checkQuestionCodeId();
            } else {
                return false;
            }
        } 
    });
}

function getRandom(n) {
    return Math.floor(Math.random() * n + 1);
}

function changeCreateButtonStyle() {
    if (input1 != "" && input2 != "" && input3 != "" && input4 != "" && typeof(input1) != "undefined" && typeof(input2) != "undefined" && typeof(input3) != "undefined" && typeof(input4) != "undefined" && desc != "" && isSelected()) {
        $('#createButton').removeAttr('disabled');
        $('#createButton').css('background-color', "#FE9901");
    } else {
        $('#createButton').attr('disabled', "true");
        $('#createButton').css('background-color', "#2E4358");
    }
}

function isSelected() {
    var isSelected = false;
    $('.a-checkbox').each(function() {
        var str = getImageSuffix($(this).css('backgroundImage'));
        if (str == 'BTN_Radio_Selected_16x16.png') {
            isSelected = true;
            return false;
        }
    });
    return isSelected;
}
function create() {
    $('#answer').val($('#radio' + selectPostion).val());
    $('#createQuestion').submit();
}

function closeResultWindow(context){
    $('#result_window').css('display', 'none');
    $('#disabled-screen').css('display', 'none');
    window.location.href = context + "/page/question/query";
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
    changeCreateButtonStyle();
} 