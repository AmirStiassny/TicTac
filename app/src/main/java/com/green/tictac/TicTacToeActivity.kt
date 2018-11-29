package com.green.tictac

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*
import java.lang.Exception

class TicTacToeActivity : AppCompatActivity() {

    enum class PLAYINGPLAYER {

        FIRST_PALYER ,
        SECOND_PLAYER
    }

    enum class WINNER_OF_GAME {

        PLAYER_ONE,
        PLAYER_TWO,
        NO_ONE
    }

    //Instance varible
    var playingplayer : PLAYINGPLAYER ?= null
    var winner_Of_Game : WINNER_OF_GAME ?= null

    var player1options : ArrayList<Int> = ArrayList()
    var player2options : ArrayList<Int> = ArrayList()
    var allDisabledImages : ArrayList<ImageButton?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        try {

            startService(Intent(this@TicTacToeActivity,SoundService::class.java))

        }catch (e : Exception){
            e.printStackTrace()
        }

        playingplayer = PLAYINGPLAYER.FIRST_PALYER
    }

    fun imageButtonTapped(view: View){

        val selectedImageButton : ImageButton = view as ImageButton
        var randomNumber = (Math.random() * 9 + 1).toInt()
        when(randomNumber){

            1 -> TableLayout.setBackgroundColor(Color.DKGRAY)
            2 -> TableLayout.setBackgroundColor(Color.YELLOW)
            3 -> TableLayout.setBackgroundColor(Color.GREEN)
            4 -> TableLayout.setBackgroundColor(Color.BLUE)
            5 -> TableLayout.setBackgroundColor(Color.WHITE)
            6 -> TableLayout.setBackgroundColor(Color.MAGENTA)
            7 -> TableLayout.setBackgroundColor(Color.RED)
            8 -> TableLayout.setBackgroundColor(Color.CYAN)
            9 -> TableLayout.setBackgroundColor(Color.LTGRAY)

        }

        var optionNumber = 0

        when(selectedImageButton.id) {

            R.id.imgButton -> optionNumber = 1
            R.id.imgButton2 -> optionNumber = 2
            R.id.imgButton3 -> optionNumber = 3
            R.id.imgButton4 -> optionNumber = 4
            R.id.imgButton5 -> optionNumber = 5
            R.id.imgButton6 -> optionNumber = 6
            R.id.imgButton7 -> optionNumber = 7
            R.id.imgButton8 -> optionNumber = 8
            R.id.imgButton9 -> optionNumber = 9

        }

        action(optionNumber,selectedImageButton)

    }

    private fun action(optionNumber : Int , _selcetdImageButton : ImageButton) {

        var selcetdImageButton = _selcetdImageButton

        if (playingplayer == PLAYINGPLAYER.FIRST_PALYER) {

            selcetdImageButton.setImageResource(R.drawable.x_letter)
            player1options.add(optionNumber)
            selcetdImageButton.isEnabled = false
            allDisabledImages.add(selcetdImageButton)
            playingplayer = PLAYINGPLAYER.SECOND_PLAYER

        }
        if (playingplayer == PLAYINGPLAYER.SECOND_PLAYER) {

            ///algoritem to play ourself

            /*selcetdImageButton.setImageResource(R.drawable.o_letter)
            player2options.add(optionNumber)
            selcetdImageButton.isEnabled = false
            allDisabledImages.add(selcetdImageButton)
            playingplayer = PLAYINGPLAYER.FIRST_PALYER*/

            
            ///algoritem for play against the computer
            var notSelectedImageNumbers = ArrayList<Int>()

            for(imageNumber in 1..9) {

                if(!(player1options.contains(imageNumber))) {

                    if(!(player2options.contains(imageNumber))){

                        ///the notselectedimagenumbers hold all the numbers of button thar not selected
                        notSelectedImageNumbers.add(imageNumber)

                    }
                }
            }

            try {

                var randomNumber = ((Math.random() * notSelectedImageNumbers.size)).toInt()
                var imageNumber = notSelectedImageNumbers[randomNumber]

                when(imageNumber){

                    1 -> selcetdImageButton = imgButton
                    2 -> selcetdImageButton = imgButton2
                    3 -> selcetdImageButton = imgButton3
                    4 -> selcetdImageButton = imgButton4
                    5 -> selcetdImageButton = imgButton5
                    6 -> selcetdImageButton = imgButton6
                    7 -> selcetdImageButton = imgButton7
                    8 -> selcetdImageButton = imgButton8
                    9 -> selcetdImageButton = imgButton9
                }
                selcetdImageButton.setImageResource(R.drawable.o_letter)
                player2options.add(imageNumber)
                selcetdImageButton.isEnabled = false
                allDisabledImages.add(selcetdImageButton)
                playingplayer = PLAYINGPLAYER.FIRST_PALYER

            }catch (e : Exception) {

                e.printStackTrace()
            }

        }

        specifyTheWinner()

    }

    private fun specifyTheWinner(){

        if(player1options.contains(1)&& player1options.contains(2)&& player1options.contains(3)
        ||player1options.contains(4) && player1options.contains(5)&& player1options.contains(6)
        ||player1options.contains(7)&&player1options.contains(8)&&player1options.contains(9)
        ||player1options.contains(1)&&player1options.contains(4)&&player1options.contains(7)
        ||player1options.contains(2)&&player1options.contains(5)&&player1options.contains(8)
        ||player1options.contains(3)&&player1options.contains(6)&&player1options.contains(9)
        ||player1options.contains(1)&&player1options.contains(5)&&player1options.contains(9)
        ||player1options.contains(3)&&player1options.contains(5)&&player1options.contains(7)){

            winner_Of_Game = WINNER_OF_GAME.PLAYER_ONE

        }else if(player2options.contains(1)&& player2options.contains(2)&& player2options.contains(3)
            ||player2options.contains(4) && player2options.contains(5)&& player2options.contains(6)
            ||player2options.contains(7)&&player2options.contains(8)&&player2options.contains(9)
            ||player2options.contains(1)&&player2options.contains(4)&&player2options.contains(7)
            ||player2options.contains(2)&&player2options.contains(5)&&player2options.contains(8)
            ||player2options.contains(3)&&player2options.contains(6)&&player2options.contains(9)
            ||player2options.contains(1)&&player2options.contains(5)&&player2options.contains(9)
            ||player2options.contains(3)&&player2options.contains(5)&&player2options.contains(7)) {

            winner_Of_Game = WINNER_OF_GAME.PLAYER_TWO

        }

        if(winner_Of_Game == WINNER_OF_GAME.PLAYER_ONE) {

            createAlert("Player one wins","Congratulations to player one"
                ,AlertDialog.BUTTON_POSITIVE , "OK" ,false)
            return

        }else if(winner_Of_Game == WINNER_OF_GAME.PLAYER_TWO){

            createAlert("Player two wins","Congratulations to player two"
                ,AlertDialog.BUTTON_POSITIVE , "OK" ,false)
            return

        }

            CheckIfDraw()


    }

    private fun createAlert (title : String , massage : String ,
                             whicebutton : Int , buttonText : String , cancelable : Boolean){

        val alertDialog : AlertDialog =
                AlertDialog.Builder(this@TicTacToeActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(massage)

        alertDialog.setButton(whicebutton,buttonText) {
                dialog: DialogInterface?, which: Int ->

            resetGame()

        }
        alertDialog.setCancelable(cancelable)

        alertDialog.show()

    }

    private fun resetGame () {

        player1options.clear()
        player2options.clear()
        allDisabledImages.clear()
        winner_Of_Game = WINNER_OF_GAME.NO_ONE
        playingplayer = PLAYINGPLAYER.FIRST_PALYER

        imgButton.setImageResource(R.drawable.back_table)
        imgButton2.setImageResource(R.drawable.back_table)
        imgButton3.setImageResource(R.drawable.back_table)
        imgButton4.setImageResource(R.drawable.back_table)
        imgButton5.setImageResource(R.drawable.back_table)
        imgButton6.setImageResource(R.drawable.back_table)
        imgButton7.setImageResource(R.drawable.back_table)
        imgButton8.setImageResource(R.drawable.back_table)
        imgButton9.setImageResource(R.drawable.back_table)

        imgButton.isEnabled = true
        imgButton2.isEnabled = true
        imgButton3.isEnabled = true
        imgButton4.isEnabled = true
        imgButton5.isEnabled = true
        imgButton6.isEnabled = true
        imgButton7.isEnabled = true
        imgButton8.isEnabled = true
        imgButton9.isEnabled = true

    }

    private fun CheckIfDraw(){

        if(allDisabledImages.size == 9 && winner_Of_Game != WINNER_OF_GAME.PLAYER_ONE
            && winner_Of_Game != WINNER_OF_GAME.PLAYER_TWO) {

            createAlert("Drwa","No one win this game",AlertDialog.BUTTON_POSITIVE,"OK",false)

        }


    }

}
