package com.example.battleship_game

class Room(){
    var id: String = ""
    var player1: String = ""
    var player2: String = ""
    var player1Ready: Boolean = false
    var player2Ready: Boolean = false

    constructor(
        id: String,
        player1: String,
        player2: String,
        player1Ready: Boolean,
        player2Ready: Boolean
    ) : this() {
        this.id = id
        this.player1 = player1
        this.player2 = player2
        this.player1Ready = player1Ready
        this.player2Ready = player2Ready
    }
}