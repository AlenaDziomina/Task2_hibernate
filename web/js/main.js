/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function chechIsSelected(boxName){
    var elem = document.getElementsByTagName('input');
    var flag = false;
    var button;
    var i = 0;
    while (i < elem.length) {
        var e = elem[i];
        if (elem[i].name === boxName) {
            flag |= elem[i].checked;
        } else {
            if (elem[i].type === "submit") {
                button = elem[i];
            }
        }
        i++;
    }
    if (flag) {
        button.disabled = false;
    } else {
        button.disabled = true;
    }

}
