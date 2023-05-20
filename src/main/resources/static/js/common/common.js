function reqJsonOption(method, sendObject){
    return {
        method : method,
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(sendObject)
    }
}
function sweetAlert(icon, title, text, timer) {
    Swal.fire({
        icon: icon,
        title: title,
        text: text,
        timer: timer
    })
}