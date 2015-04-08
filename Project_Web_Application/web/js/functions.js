/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.src);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    sessionStorage.setItem("panier" + sessionStorage.getItem("count"), data);
    sessionStorage.setItem("count", sessionStorage.getItem("count") + 0);
//                var p = document.createElement("p");
//                p.innerHTML = sessionStorage.getItem("count").getName();
//                document.getElementById("div1").appendChild(p);
}
