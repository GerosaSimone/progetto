class utente {

    constructor(nome, password) {
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

}

var utenti;
var tmp = new ActiveXObject("Scripting.FileSystemObject");
var fileName = "D:\\github\\progetto\\sito\\database.txt";
tmp.CreateTextFile(fileName);


function registrati() {
    alert("asdidasi");
    if (controllo) {
        let ut = utente(document.getElementById("username").value, document.getElementById("password").value);

        if (esistente) {
            document.getElementById("err").innerHTML == "l'utente esiste gia, fare log-in";
        } else {

            var file = tmp.GetFile(fileName);
            var streamAppend = file.OpenAsTextStream(8);
            streamAppend.WriteLine(ut.to_string());
            streamAppend.Close();
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
    alert("asdidasi");
    var file = tmp.GetFile(fileName);
    var streamReader = file.OpenAsTextStream(1);

    vettore = new Array();
    while (!streamReader.AtEndOfStream) {
        let line = streamReader.ReadLine();
        vettore.push(to_utente(line));
    }
    utenti = vettore;


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


