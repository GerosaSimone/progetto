function register() {
    if (controllo) {
        var utente = utente(document.getElementById("username").textContent, document.getElementById("password").textContent);

        if (esistente) {
            document.getElementById("err").innerHTML == "l'utente esiste gia, fare log-in";
        } else {
            //aggiungi al file l'utente
            clear();
        }
    } else {
        document.getElementById("err").innerHTML == "inserire tutti i campi";
    }
}
function login() {
    if (controllo) {
        var ut = utente(document.getElementById("username").textContent, document.getElementById("password").textContent);
//controllo che l'utente esista nel file
        if (esistente(ut)) {
            //passare alla pagina del download
        } else {
            document.getElementById("err").innerHTML == "utente inesistente";
        }
    } else {
        document.getElementById("err").innerHTML == "inserire tutti i campi";
    }

}
function clear() {
    document.getElementById("username").textContent == "";
    document.getElementById("password").textContent == "";
}
function vettoreUtenti() {

    return Object;//ritorna un vettore con tutti gli utenti esistenti
}
function esistente(utente) {
//controllo se l utente esiste nel vettore di utenti
}
function controllo() {
    if ((document.getElementById("username").textContent == "") || (document.getElementById("password").textContent == "")) {
        return false;
    } else {
        return true;
    }
}

class utente {
    utente(nome, password) {
        this.nome = nome;
        this.password = password;
    }

    getNome() {
        return this.nome;
    }
    getPassword() {
        return this.password;
    }

    to_string() {
        return this.nome + "-" + this.password;
    }



}
