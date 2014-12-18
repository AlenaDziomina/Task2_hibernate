/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function confirmSubmit(boxName, confirmMsg, errorMsg){
    var flag = checkIsSelected(boxName);
    if (flag) {
        return confirmation(confirmMsg);
    } else {
        return errorMessage(errorMsg);
    }
}

function errorMessage(errorMsg){
    alert(errorMsg);
    return false;
}

function confirmation(confirmMsg){
    var agree=confirm(confirmMsg);
    if(agree) {
        return true;
    } else {
        return false;
    } 				
}

function checkIsSelected(boxName){
    var elem = document.getElementsByTagName('input');
    var flag = false;
    var i = 0;
    while (!flag && i < elem.length) {
        var e = elem[i];
        if (elem[i].name === boxName) {
            flag |= elem[i].checked;
        }
        i++;
    }
    return flag;
}
