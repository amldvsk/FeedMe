/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


jQuery.extend(jQuery.validator.messages, {
    required: "שדה זה הינו חובה.",
    email: "כתובת אימייל לא חוקית.",
    date: "פורמט תאריך שגוי.",
    number: "מספר לא חוקי.",
    digits: "שדה זה הינו מספרי בלבד.",
    creditcard: "אנא הכנס מספר כרטיס נכון.",
    equalTo: "השדה אינו תואם.",
    maxlength: jQuery.validator.format("נא להכניס לא יותר מ {0} אותיות."),
    minlength: jQuery.validator.format("נא הכנס יותר מ {0} מילים."),
    rangelength: jQuery.validator.format("נא להכניס מספר בין {0} ל {1}."),
    max: jQuery.validator.format("נא להכניס מספר קטן או שווה ל {0}."),
    min: jQuery.validator.format("נא להכניס מספר גדול או שווה ל {0}.")
});