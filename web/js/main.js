/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function confirmSubmit(boxName){
    var flag = checkIsSelected(boxName);
    if (flag) {
        return confirmation();
    } else {
        errorMessage();
        return false;
    }
}

function errorMessage(){
    alert(errMessage);
    return false;
}

function confirmation(){
    var agree=confirm(confirmMessage);
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

function setListVisited(){
    var elem = document.getElementById("menuLinkList");
    if (elem !== null) {
        elem.className = "active";
    }
}

function setAddingVisited(){
    var elem = document.getElementById("menuLinkAdd");
    if (elem !== null) {
        elem.className = "active";
    }
}