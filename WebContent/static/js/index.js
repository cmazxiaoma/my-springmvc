var context;
var delIds;
var pagesize;
var url;
var isQuery;
var sort;
var sortUrl;
var currentPage;
var pageCount;
var totalCount;
var baseUrl;
var searchKey;
$(document).ready(function() {
    context = $('#context').val();
    baseUrl = $('#baseUrl').val();
    getQuestionData(context + '/page/json/query?page=1');
    
    $('#searchkey').focus(function() {
        pressEnterKey();
    });
    
    $('#fork').bind('keydown', function(event) {
        if (keycode == '13') {
            closedel();
        }
    });
    
    $('#question_list').mouseover(function() {
        $('#question_list').css('background-color', '#2E4358');
        $('#question_list').css('color', '#FFFFFF');
        $('#create_question').css('background-color', '#FFFFFF');
        $('#create_question').css('color', '#2E4358');
    });
    
    $('#create_question').mouseover(function() {
        $('#create_question').css('background-color', '#2E4358');
        $('#create_question').css('color', '#FFFFFF');
        $('#question_list').css('background-color', '#FFFFFF');
        $('#question_list').css('color', '#2E4358');
    });
    
    $('#create_question').mouseleave(function() {
         $('#create_question').css('background-color', '#FFFFFF');
         $('#create_question').css('color', '#2E4358');
         $('#question_list').css('background-color', '#FFFFFF');
         $('#question_list').css('color', '#2E4358');
         
         $('#question_list').css('background-color', '#2E4358');
         $('#question_list').css('color', '#FFFFFF');
    });
});

function getQuestionData(url) {
    if (typeof(pagesize) == "undefined") {
       pagesize = 10;
    }
    $.ajax ({
        type: 'GET',
        url: url + '&pagesize=' + pagesize,
        ContentType: 'application/json; charset=UTF-8',
        success: function(data) {
            var result = data.result;
            if (result == 'success') {
                isQuery = data.data.isQuery;
                sort = data.data.sort;
                changeSortImg();
                showQuestionData(data.data.pageBean);
                $('.delete_button').show();
                $('.pageBox').show();
                $('#header_checkbox').css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Unselected_15x15.png)');
            } else {
                if (data.errorCode == '1005' || data.errorCode == '1000' || data.errorCode == '1001') {
                     $('.delete_button').hide();
                     $('.pageBox').hide();
                     $('#disabled-screen').css('display', 'block');
                     $('#del_window').css('display', 'block');
                     $('#del_window_text').text('Sorry, no find data');
                     $('#del_window').css('display', 'block');
                     $('#del_window_yes_button').hide();
                     $('#del_window_no_button').hide();
                     $('#tableContentBox').empty();
                }
            }
        }
    });
}

function changeSortImg() {
    if (sort == 'DESC') {
        $('#increase_ico').attr('src', 'http://examstatic.com/css/images/ICN_Increase_10x15.png');
    } else {
        $('#increase_ico').attr('src', 'http://examstatic.com/css/images/ICN_Decrese_10x15.png');
    }
    
}
function getUrl() {
    if (!isQuery) {
        sortUrl = baseUrl + 'json/search?searchkey=' + searchKey + '&';
        if (sort == 'DESC') {
            url = baseUrl + 'json/search?searchkey=' + searchKey + '&sort=DESC&';
        } else {
            url = baseUrl + 'json/search?searchkey=' + searchKey + '&sort=ASC&';
        }
    } else {
        sortUrl = baseUrl + 'json/query?';
        if (sort == 'DESC') {
            url = baseUrl + 'json/query?sort=DESC&';
        } else {
            url = baseUrl + 'json/query?sort=ASC&';
        }
    }
}

function showQuestionData(data) {
    totalCount = data.totalCount;
    pagesize = data.pageSize;
    pageCount = data.pageCount;
    delIds = new Array(pagesize);
    currentPage = data.currentPage;
    if (typeof(data.searchKey) != "undefined") {
        searchKey = data.searchKey;
    }
    getUrl();
    var tableContentBox = $('#tableContentBox');
    tableContentBox.empty();
    $.each(data.data, function(index, item) {
        var tableContent = $('<div id="tableContent"></div>');
        var num = $('<div class="num"></div>')
        var questionCodeId = $('<div class="table-header-id"></div>');
        var desc = $('<a href = "" class="table-header-desc"></a>');
        var edit = $('<a href = "" class="table-header-edit-ico" ></a>')
        var choose = $('<div class="table-header-allchoose-ico"></div>')
        var hiddenQuestionId = $('<input type="hidden">')
        
        num.text((index + 1));
        questionCodeId.text(item.questionCodeId);
        desc.text(item.questionDesc);
        desc.attr('href', context + '/page/question/detail/' + item.questionId);
        edit.attr('href', context + '/page/question/edit/' + item.questionId);
        choose.attr('id', 'child' + (index + 1));
        choose.on('click', function() {
            SingleSelected((index + 1), item.questionId);
        });
        hiddenQuestionId.attr('id', 'questionId' + (index + 1));
        hiddenQuestionId.text(item.questionId);
        tableContent.append(num);
        tableContent.append(questionCodeId);
        tableContent.append(desc);
        tableContent.append(edit);
        tableContent.append(choose);
        tableContent.append(hiddenQuestionId);
        tableContentBox.append(tableContent);
    });
    page();
    var select = $('#page_select');
    select.empty();
    if (pagesize == 10) {
        select.append("<option selected='selected'>" + "10" + "</option>");
        select.append("<option>" + "5" + "</option>");
    } else if (pagesize == 5) {
        select.append("<option>" + "10" + "</option>");
        select.append("<option selected='selected'>" + "5" + "</option>");
    }
}
function page() {
    if (currentPage >= pageCount) {
    $('#next').attr('disabled', "true");
    $('#next').css('background-color', '#D2DAE3');
    } else {
        $('#next').removeAttr('disabled');
        $('#next').css('background-color', '#FFFFFF');
    }

    if (currentPage == 1 || currentPage == null) {
        $('#pre').attr('disabled', "true");
        $('#pre').css('background-color', '#D2DAE3');
    } else {
        $('#pre').removeAttr('disabled');
        $('#pre').css('background-color', '#FFFFFF');
    }

    var showpage = $('#page');
    showpage.empty();
    var begin = 1;
    var end = pageCount;
    if (currentPage > 0) {
        begin = currentPage;
    }
    if (currentPage + 4 < pageCount) {
        end = currentPage + 4;
    }
    var x = 0;
    var textcolor = '';
    for (var i = begin; i <= end ; i++) {
        x = x + 1;
        if (i == currentPage) {
            textcolor = 'currentpage';
        }
        
        if (i != currentPage) {
            textcolor = 'otherpage';
        }
        
        if (x <= 3) {
            var page = $('<a href="" class="' + textcolor +'">' + i +' </a>');
            page.attr('href',"javascript:go(" + i + ")");
            showpage.append(page);
        } else if (x == 4) {
            if (i == pageCount) {
                var page = $('<a href="" class="' + textcolor +'">' + i +' </a>');
                page.attr('href',"javascript:go(" + i + ")");
                showpage.append(page);
            } 

            if (i != pageCount) {
                showpage.append('<a href="" class="' + textcolor + '">...</a>');
            }
        } else if (x == 5) {
            var page = $('<a href="" class="' + textcolor +'">' + pageCount +' </a>');
            page.attr('href',"javascript:go(" + pageCount + ")");
            showpage.append(page);
            showpage.append(page);
        }
    }
}

function go(page) {
    getQuestionData(url + 'page=' + page);
}
function sortQuestion() {
    var sort = "";
    var img = document.getElementById("increase_ico");
    var str = img.src.substring(img.src.lastIndexOf("/") + 1, img.src.length);
    if (str == "ICN_Increase_10x15.png") {
        sort = "ASC";
    } else {
        sort = "DESC";
    }
    
    getQuestionData(sortUrl + 'sort=' + sort);
}

function forwarDetailQuestion(context, id) {
    window.location.href = context + "/page/question/detail/" + id;
}

function pressEnterKey() {
    $(document).keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
            searchQuestion();
        }
    });
}

function searchQuestion(){
    searchKey = filterInvalidCharacter($('#searchkey').val());
    getQuestionData(context + '/page/json/search?searchkey=' + searchKey);
}

function filterInvalidCharacter(str) {
    return str.replace(/[~'!<>@#$%^&*()-+_=:]/g, "");
}
function forwardEdit(i) {
    var id = i;
    window.location.href = context + '/page/question/edit/' + id;
}

function open(){
    $('#disabled-screen').css('display', 'block');
    $('#del_window').css('display', 'block');
    $('#del_window_text').text('Are you sure delete the selected options?');
    $('#del_window_yes_button').show();
    $('#del_window_no_button').show();
}

function closedel(){
    $('#disabled-screen').css('display', 'none');
    $('#del_window').css('display', 'none');
    getQuestionData(context + '/page/json/query?page=1');
}

function prepage() {
    var page =parseInt(currentPage) - 1;
    getQuestionData(url + 'page=' + page);
}

function nextpage() {
    var page = parseInt(currentPage) + 1;
    getQuestionData(url + 'page=' + page);
}

function selectChange(text) {
    pagesize = parseInt(text);
    getQuestionData(url);
}

function forwardPage() {
    var page = parseInt($('#perpage').val());
    if (isNaN(page)) {
        getQuestionData(url + 'page=1');
    } else if (page <= 0) {
        getQuestionData(url + 'page=1');
    } else if (page < pageCount) {
        getQuestionData(url + 'page=' + page);
    } else if (page >= pageCount) {
        getQuestionData(url + 'page=' + pageCount);
    }
}

function SingleSelected(checkBoxId, questionId) {
    var childId = "child" + checkBoxId;
    var str = getImageSuffix($('#' + childId).css('backgroundImage'));
    if (str == "ICN_Unselected_15x15.png"){
        delIds[checkBoxId] = questionId;
        $('#' + childId).css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Selected_15x15.png)')
    } else if (str == "ICN_Selected_15x15.png"){
        delIds[checkBoxId] = "";
        $('#' + childId).css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Unselected_15x15.png)')
        $('#header_checkbox').css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Unselected_15x15.png)');
    }
    
    var flag = true;
    $('.table-header-allchoose-ico').each(function() {
        if (getImageSuffix($(this).css('backgroundImage')) != 'ICN_Selected_15x15.png') {
            flag = false;
            return false;
        }
    });
    
    if (flag) {
        $('#header_checkbox').css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Selected_15x15.png)');
    }
}

function dodel() {
    var count = delIds.length;
    var msg = "";
    var flag = false;
    for (var k = 0; k< count; k++) {
        if (typeof(delIds[k]) != "undefined" && delIds[k] != "") {
             if (msg == "") {
                 msg =  delIds[k];
             } else {
                msg = msg + "-" + delIds[k];
             }
             flag = true;
        }
    } 
    
    if (flag == true) {
        ajaxDel(context + '/page/json/del/' + msg)
    } else {
        $('#del_window_text').text('Sorry, you are not checked');
        $('#del_window_yes_button').hide();
        $('#del_window_no_button').hide();
    }
}

function ajaxDel(url) {
    $.ajax ({
        type: 'GET',
        url: url,
        ContentType: 'application/json; charset=UTF-8',
        success: function(data) {
            var result = data.result;
            if (result == 'success') {
                $('#disabled-screen').css('display', 'block');
                $('#del_window').css('display', 'block');
                $('#del_window_text').text('delete successfully');
                $('#del_window').css('display', 'block');
                $('#del_window_yes_button').hide();
                $('#del_window_no_button').hide();
            }
        }
    });
}

function selectedAll(){
    var str = getImageSuffix($('#header_checkbox').css('backgroundImage'));
    if (str == "ICN_Unselected_15x15.png") {
        $('#header_checkbox').css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Selected_15x15.png)')
        for (var k = 1; k<= pagesize; k++) {
            delIds[k - 1] = $('#questionId' + k).text();
            $('#child' + k).css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Selected_15x15.png)');
        }
    } else {
        $('#header_checkbox').css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Unselected_15x15.png)')
        for (var k = 1; k <= pagesize; k++) {
            delIds[k - 1] = "";
            $('#child' + k).css('backgroundImage', 'url(http://examstatic.com/css/images/ICN_Unselected_15x15.png)');
        }
    }  
}