var errorMessage = "";

function validateNewsForm(form) { 
    var formValidationResult = true; 
    formValidationResult = newsForm_required(form) 
            && newsForm_maxlength(form)
            && newsForm_DateValidations(form); 
    if (!formValidationResult) {
        alert(errorMessage);
        errorMessage = "";
    }
    return (formValidationResult); 
} 

function newsForm_required(form) { 
    var result = validateRequired(form, "newsMessage.title", titleRequired)
        && validateRequired(form, "stringDate", dateRequired)
        && validateRequired(form, "newsMessage.brief", briefRequired)
        && validateRequired(form, "newsMessage.content", contentRequired);
    return result;
} 

function newsForm_maxlength(form) { 
    var result = validateMaxLength(form, "newsMessage.title", titleMaxLength, titleMaxLeng)
        && validateMaxLength(form, "stringDate", dateMaxLength, dateMaxLeng)
        && validateMaxLength(form, "newsMessage.brief", briefMaxLength, briefMaxLeng)
        && validateMaxLength(form, "newsMessage.content", contentMaxLength, contentMaxLeng);
    return result;
} 

function newsForm_DateValidations(form) { 
    var result = validateDate(form, "stringDate", dateFormat, datePattern);
    return result;
} 

function validateDate(form, field_name, msg, datePattern) {
    var field = form[field_name];
    var isValid = true;
    if (!jcv_isFieldPresent(field)) {
        isValid = false;
    }
    var value = field.value;
    var isStrict = true;

    if ((field.type === 'hidden' ||
                field.type === 'text' ||
                field.type === 'textarea') 
                && (value.length > 0) 
                && (datePattern.length > 0)) {
        var MONTH = "MM";
        var DAY = "dd";
        var YEAR = "yyyy";
        var orderMonth = datePattern.indexOf(MONTH);
        var orderDay = datePattern.indexOf(DAY);
        var orderYear = datePattern.indexOf(YEAR);
        if ((orderDay < orderYear && orderDay > orderMonth)) {
            var iDelim1 = orderMonth + MONTH.length;
            var iDelim2 = orderDay + DAY.length;
            var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
            var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
            if (iDelim1 === orderDay && iDelim2 === orderYear) {
               dateRegexp = isStrict 
                    ? new RegExp("^(\\d{2})(\\d{2})(\\d{4})$") 
                    : new RegExp("^(\\d{1,2})(\\d{1,2})(\\d{4})$");
            } else if (iDelim1 === orderDay) {
               dateRegexp = isStrict 
                    ? new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$")
                    : new RegExp("^(\\d{1,2})(\\d{1,2})[" + delim2 + "](\\d{4})$");
            } else if (iDelim2 === orderYear) {
               dateRegexp = isStrict
                    ? new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$")
                    : new RegExp("^(\\d{1,2})[" + delim1 + "](\\d{1,2})(\\d{4})$");
            } else {
               dateRegexp = isStrict
                    ? new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$")
                    : new RegExp("^(\\d{1,2})[" + delim1 + "](\\d{1,2})[" + delim2 + "](\\d{4})$");
            }
            var matched = dateRegexp.exec(value);
            if(matched !== null) {
                if (!jcv_isValidDate(matched[2], matched[1], matched[3])) {
                    isValid =  false;
                }
            } else {
                isValid =  false;
            }
        } else if ((orderMonth < orderYear && orderMonth > orderDay)) {
            var iDelim1 = orderDay + DAY.length;
            var iDelim2 = orderMonth + MONTH.length;
            var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
            var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
            if (iDelim1 === orderMonth && iDelim2 === orderYear) {
                dateRegexp = isStrict 
                   ? new RegExp("^(\\d{2})(\\d{2})(\\d{4})$")
                   : new RegExp("^(\\d{1,2})(\\d{1,2})(\\d{4})$");
            } else if (iDelim1 === orderMonth) {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$")
                   : new RegExp("^(\\d{1,2})(\\d{1,2})[" + delim2 + "](\\d{4})$");
            } else if (iDelim2 === orderYear) {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$")
                   : new RegExp("^(\\d{1,2})[" + delim1 + "](\\d{1,2})(\\d{4})$");
            } else {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$")
                   : new RegExp("^(\\d{1,2})[" + delim1 + "](\\d{1,2})[" + delim2 + "](\\d{4})$");
            }
            var matched = dateRegexp.exec(value);
            if(matched !== null) {
                if (!jcv_isValidDate(matched[1], matched[2], matched[3])) {
                    isValid =  false;
                }
            } else {
                isValid =  false;
            }
        } else if ((orderMonth > orderYear && orderMonth < orderDay)) {
            var iDelim1 = orderYear + YEAR.length;
            var iDelim2 = orderMonth + MONTH.length;
            var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
            var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
            if (iDelim1 === orderMonth && iDelim2 === orderDay) {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{4})(\\d{2})(\\d{2})$")
                   : new RegExp("^(\\d{4})(\\d{1,2})(\\d{1,2})$");
            } else if (iDelim1 === orderMonth) {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$")
                   : new RegExp("^(\\d{4})(\\d{1,2})[" + delim2 + "](\\d{1,2})$");
            } else if (iDelim2 === orderDay) {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$")
                   : new RegExp("^(\\d{4})[" + delim1 + "](\\d{1,2})(\\d{1,2})$");
            } else {
                dateRegexp = isStrict
                   ? new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$")
                   : new RegExp("^(\\d{4})[" + delim1 + "](\\d{1,2})[" + delim2 + "](\\d{1,2})$");
            }
            var matched = dateRegexp.exec(value);
            if(matched !== null) {
                if (!jcv_isValidDate(matched[3], matched[2], matched[1])) {
                    isValid =  false;
                }
            } else {
                isValid =  false;
            }
        } else {
            isValid =  false;
        }
    }
    if (!isValid) {
        jcv_handleErrors(msg);
    }
    return isValid;
 }
    
function jcv_isValidDate(day, month, year) {
    var d = parseInt(day);
    var m = parseInt(month);
    var y = parseInt(year);
    
    if (m < 1 || m > 12) {
        return false;
    }
    if (d < 1 || d > 31) {
        return false;
    }
    if ((m === 4 || m === 6 || m === 9 || m === 11) &&
        (d === 31)) {
        return false;
    }
    if (m === 2) {
        var leap = (y % 4 === 0 && (y % 100 !== 0 || y % 400 === 0));
        if (d>29 || (d === 29 && !leap)) {
            return false;
        }
    }
    return true;
}

function validateMaxLength(form, field_name, msg, max_size) {
    var field = form[field_name];
    var isValid = true;
    if (!jcv_isFieldPresent(field)) {
        isValid=false;
    } else if ((field.type === 'hidden' ||
                field.type === 'text' ||
                field.type === 'password' ||
                field.type === 'textarea')) {
        if (field.value.length > max_size) {
            isValid = false;
        }
    }
    if (!isValid) {
       jcv_handleErrors(msg);
    }
    return isValid;
}

function validateRequired(form, field_name, msg) {
    var field = form[field_name];
    var isValid = true;
    if (!jcv_isFieldPresent(field)) {
        isValid=false;
    } else if ((field.type === 'hidden' ||
                field.type === 'text' ||
                field.type === 'textarea' ||
                field.type === 'file' ||
                field.type === 'radio' ||
                field.type === 'checkbox' ||
                field.type === 'select-one' ||
                field.type === 'password')) {
            
            var value = '';
            // get field's value
            if (field.type === "select-one") {
                var si = field.selectedIndex;
                if (si >= 0) {
                    value = field.options[si].value;
                }
            } else if (field.type === 'radio' || field.type === 'checkbox') {
                if (field.checked) {
                    value = field.value;
                }
            } else {
                value = field.value;
            }
            
            if (trim(value).length === 0) {
                isValid = false;
            }
        } else if (field.type === "select-multiple") { 
            var numOptions = field.options.length;
            lastSelected=-1;
            for(loop=numOptions-1;loop>=0;loop--) {
                if(field.options[loop].selected) {
                    lastSelected = loop;
                    value = field.options[loop].value;
                    break;
                }
            }
            if(lastSelected < 0 || trim(value).length === 0) {
                isValid=false;
            }
        } else if ((field.length > 0) && (field[0].type === 'radio' || field[0].type === 'checkbox')) {
            isChecked=-1;
            for (loop=0;loop < field.length;loop++) {
                if (field[loop].checked) {
                    isChecked=loop;
                    break; // only one needs to be checked
                }
            }
            if (isChecked < 0) {
                isValid=false;
            }
        }
    if (!isValid) {
       jcv_handleErrors(msg);
    }
    return isValid;
}

function trim(s) {
    return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

function jcv_handleErrors(message) {
    errorMessage = errorMessage.concat(message, "\n");
}

function jcv_isFieldPresent(field) {
    var fieldPresent = true;
    if (field === null || (typeof field === 'undefined')) {
        fieldPresent = false;
    } else {
        if (field.disabled) {
            fieldPresent = false;
        }
    }
    return fieldPresent;
}
