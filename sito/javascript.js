var utenti = vettoreUtenti();
/*
function WriteToFile() {

    // se il file esiste aggiunge due righe
    // con il contenuto delle variabili nome/cognome
    if (fso.FileExists(filename)) {
        var a, ForAppending, file;
        ForAppending = 8;
        file = fso.OpenTextFile(filename, ForAppending, false);
        file.WriteLine(nome);
        file.WriteLine(password);
    }
    //Se non esiste crea il file e
    // aggiunge due righe
    // con il contenuto delle variabili nome/cognome
    else {
        
        file.WriteLine(cognome);
        file.WriteLine(cognome);
    }
    file.Close();
}
*/

function register() {
    var filename = "../database.txt";
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    if (controllo) {
        let ut = utente(document.getElementById("username").value, document.getElementById("password").value);

        if (esistente) {
            document.getElementById("err").innerHTML == "l'utente esiste gia, fare log-in";
        } else {

            if (fso.FileExists(filename)) {
                var a, ForAppending, file;
                ForAppending = 8;
                file = fso.OpenTextFile(filename, ForAppending, false);
                file.Write(ut.to_string());
            }
            utenti.push(ut);

            clear();
        }
    } else {
        document.getElementById("err").innerHTML == "inserire tutti i campi";

    }

}
function login() {
    if (controllo) {
        let ut = utente(document.getElementById("username").textContent, document.getElementById("password").textContent);
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
    document.getElementById("username").value == "";
    document.getElementById("password").value == "";
}
function vettoreUtenti() {

    vettore = new Array();
    var filename = "../database.txt";
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    if (fso.FileExists(filename)) {
        var a, ForAppending, file;
        ForAppending = 8;
        file = fso.OpenTextFile(filename, ForAppending, false);
        var testo = file.Read();
        var temp = testo.split(";")

        for (let i = 0; i < temp.lenght(); i++) {
            vettore.push(to_utente(temp[i]));
        }


    }
    return vettore;
}
function to_utente(tmp) {
    let tmp1;
    let tmp2 = tmp.split("-")
    tmp1 = utente(tmp2[0], tmp2[1]);
    return tmp1;
}
function esistente(ut) {
    //controllo se l utente esiste nel vettore di utenti
    for (let i; i < utenti.lenght(); i++) {
        if (utenti[i] == ut)
            return true;
        else
            return false;
    }
}
function controllo() {
    if ((document.getElementById("username").value == "") || (document.getElementById("password").value == "")) {
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
        return this.nome + "-" + this.password + ";";
    }

    utenti = new Array();


}
