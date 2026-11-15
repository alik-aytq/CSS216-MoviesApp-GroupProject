package com.example.moviesapp.ui.demo

import androidx.compose.ui.graphics.Color
import com.example.moviesapp.ui.model.MovieUiModel


val demoMovies = listOf(
    MovieUiModel(
        id = "fight-club",
        title = "Fight Club",
        year = 1999,
        genre = "Drama",
        duration = "2h 19m",
        rating = 8.8,
        description = "An insomniac office worker meets a mysterious soap salesman. Together, they establish an underground fight club that grows into something far more dangerous.",
        posterColor = Color(0xFF9C4444)
    ),

    MovieUiModel(
        id = "se7en",
        title = "Se7en",
        year = 1995,
        genre = "Crime / Thriller",
        duration = "2h 07m",
        rating = 8.6,
        description = "Two detectives investigate a series of murders inspired by the seven deadly sins.",
        posterColor = Color(0xFF596452)
    ),

    MovieUiModel(
        id = "inglourious-basterds",
        title = "Inglourious Basterds",
        year = 2009,
        genre = "War / Adventure",
        duration = "2h 33m",
        rating = 8.4,
        description = "During World War II, a group of Allied soldiers plans a daring mission against the Nazi leadership in occupied France.",
        posterColor = Color(0xFF987347)
    ),

    MovieUiModel(
        id = "moneyball",
        title = "Moneyball",
        year = 2011,
        genre = "Sports / Drama",
        duration = "2h 13m",
        rating = 7.6,
        description = "Baseball manager Billy Beane challenges traditional scouting methods by using statistics to build a competitive team.",
        posterColor = Color(0xFF467557)
    ),

    MovieUiModel(
        id = "fury",
        title = "Fury",
        year = 2014,
        genre = "War / Action",
        duration = "2h 14m",
        rating = 7.6,
        description = "An American tank commander leads his crew through dangerous missions during the final days of World War II.",
        posterColor = Color(0xFF646B4C)
    ),

    MovieUiModel(
        id = "once-upon-a-time-in-hollywood",
        title = "Once Upon a Time... in Hollywood",
        year = 2019,
        genre = "Comedy / Drama",
        duration = "2h 41m",
        rating = 7.6,
        description = "In 1969 Los Angeles, a fading television actor and his longtime stunt double navigate a rapidly changing Hollywood.",
        posterColor = Color(0xFFB67F46)
    )
)
