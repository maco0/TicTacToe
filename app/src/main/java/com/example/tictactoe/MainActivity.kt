package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var Player1Text: TextView
    lateinit var Player2Text: TextView
    lateinit var buttonArray: Array<Array<Button>>
    lateinit var TurnText: TextView
    private var Player1Score: Int = 0
    private var Player2Score: Int = 0
    private var P1Turn:Boolean = true

    private var roundCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Player1Text = findViewById(R.id.PlayerOne)
        Player2Text = findViewById(R.id.PlayerTwo)
        TurnText = findViewById(R.id.turn)
        buttonArray = Array(3){
                 row-> Array(3){ col->  Buttoninit(row,col)}}
        val ResetButton:Button=findViewById(R.id.resetButton)
        resetButton.setOnClickListener{
            clearBoard()
        }
    }

private fun Buttoninit(Row:Int,Col:Int): Button{
    val button: Button=
        findViewById(resources.getIdentifier("Button$Row$Col","id", packageName))
    button.setOnClickListener{
        onButton( button)

    }
    return button
}
private fun onButton(btn:Button) {
    if (btn.text != "") return
    if (P1Turn) {
        turn.text = "O turn"
        btn.text = "X"
    } else {
        turn.text = "X turn"
        btn.text = "O"
    }
   roundCount++

    if(winconditions()) {
        if (P1Turn) {
            turn.text = "P1 Won"
            win(1)
        }
        else if(!P1Turn) {
            turn.text = "P2 Won"
            win(2)
        }
    }
    else if (roundCount == 9) {
        turn.text = "Draw"
        draw()
    }
    else {
        P1Turn = !P1Turn
    }
}

 private fun winconditions(): Boolean{
   val fields = Array(3){
       row->Array(3){col->
       buttonArray[row][col].text
   }
   }
     //check rows
     for(i in 0..2){
         if(fields[i][0]==fields[i][1]&&fields[i][0]==fields[i][2]&&fields[i][0]!="")
             return true
     }
     //check columns
     for(i in 0..2){
         if(fields[0][i]==fields[1][i]&&fields[0][i]==fields[2][i]&&fields[0][i]!="")
             return true
     }
     //check diagonals
   if(fields[0][2]==fields[1][1]&& fields[0][2]==fields[2][0]&&fields[0][2]!="")
       return true
     if(fields[0][0]==fields[1][1]&&fields[0][0]==fields[2][2]&&fields[0][0]!="")
         return true
 return false
 }
    private fun win(player:Int){
        if(player==1) Player1Score++
        else Player2Score++
        updatescore()
        for(i in 0..2){
            for(j in 0..2){
                buttonArray[i][j].isClickable=false
            }
        }
    }

   private fun draw(){
       Toast.makeText(applicationContext, "Draw", Toast.LENGTH_SHORT).show()
   }
    private fun clearBoard(){
        for(i in 0..2){
            for(j in 0..2){
                buttonArray[i][j].text = ""
                buttonArray[i][j].isClickable=true
            }
        }
        roundCount = 0
        P1Turn=true
        turn.text = "X turn"
    }

    private fun updatescore(){
        Player1Text.text = "Player 1: $Player1Score"
        Player2Text.text = "Player 2: $Player2Score"
    }
}