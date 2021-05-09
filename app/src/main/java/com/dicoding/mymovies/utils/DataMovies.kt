package com.dicoding.mymovies.utils

import com.dicoding.mymovies.data.FilmEntity
import com.dicoding.mymovies.data.SeriesEntity

object DataMovies {

    fun generateFilm(): List<FilmEntity> {
        val film = ArrayList<FilmEntity>()

        film.add(FilmEntity("m1",
        "Spider-Man: Into the Spider-Verse",
        "Bitten by a radioactive spider in the subway, Brooklyn teenager Miles Morales suddenly develops mysterious powers that transform him into the one and only Spider-Man. When he meets Peter Parker, he soon realizes that there are many others who share his special, high-flying talents. Miles must now use his newfound skills to battle the evil Kingpin, a hulking madman who can open portals to other universes and pull different versions of Spider-Man into our world.",
        4.5F,
        "Animation, Super Hero, Action",
        "1h 56min",
        "https://i.imgur.com/V6tZgPh.jpg"))

        film.add(FilmEntity("m2",
        "Avenger: Endgame",
        "Adrift in space with no food or water, Tony Stark sends a message to Pepper Potts as his oxygen supply starts to dwindle. Meanwhile, the remaining Avengers -- Thor, Black Widow, Captain America and Bruce Banner -- must figure out a way to bring back their vanquished allies for an epic showdown with Thanos -- the evil demigod who decimated the planet and the universe.",
        4.8F,
        "Action, Adventure, Sci-Fi",
        "3h 2min",
        "https://i.imgur.com/W1eXF34.jpg"))

        film.add(FilmEntity("m3",
        "Joker",
        "Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City. Arthur wears two masks -- the one he paints for his day job as a clown, and the guise he projects in a futile attempt to feel like he's part of the world around him. Isolated, bullied and disregarded by society, Fleck begins a slow descent into madness as he transforms into the criminal mastermind known as the Joker.",
        4.4F,
        "Crime, Drama, Thriller",
        "2h 2min",
        "https://i.imgur.com/04X2mHW.jpg"))

        film.add(FilmEntity("m4",
        "The Platform",
        "In the future, prisoners housed in vertical cells watch as inmates in the upper cells are fed while those below starve.",
        4.2F,
        "Horror, Thriller, Crime",
        "1h 34min",
        "https://i.imgur.com/OpFw6Is.jpg"))

        film.add(FilmEntity("m5",
        "Ralph Breaks the Internet: Wreck-It Ralph",
        "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
        4.0F,
        "Animation, Adventure, Comedy, Fantasy",
        "1h 56min",
        "https://i.imgur.com/IKUu7vw.jpg"))

        film.add(FilmEntity("m6",
        "Ready Player One",
        "In 2045, the planet is on the brink of chaos and collapse, but people find salvation in the OASIS, an expansive virtual reality universe created by James Halliday. When Halliday dies, he promises his immense fortune to the first person to discover a digital Easter egg that's hidden somewhere in the OASIS. When young Wade Watts joins the contest, he finds himself becoming an unlikely hero in a reality-bending treasure hunt through a fantastical world of mystery, discovery and danger.",
        4.3F,
        "Action, Adventure, Sci-Fi",
        "1h 56min",
        "https://i.imgur.com/cwqHFch.jpg"))

        film.add(FilmEntity("m7",
        "Shazam",
        "We all have a superhero inside of us -- it just takes a bit of magic to bring it out. In 14-year-old Billy Batson's case, all he needs to do is shout out one word to transform into the adult superhero Shazam. Still a kid at heart, Shazam revels in the new version of himself by doing what any other teen would do -- have fun while testing out his newfound powers. But he'll need to master them quickly before the evil Dr. Thaddeus Sivana can get his hands on Shazam's magical abilities.",
        4.5F,
        "Action, Adventure, Comedy, Fantasy",
        "2h 12min",
        "https://i.imgur.com/bbtu0kp.jpg"))

        film.add(FilmEntity("m8",
        "Interstellar",
        "In Earth's future, a global crop blight and second Dust Bowl are slowly rendering the planet uninhabitable. Professor Brand (Michael Caine), a brilliant NASA physicist, is working on plans to save mankind by transporting Earth's population to a new home via a wormhole. But first, Brand must send former NASA pilot Cooper (Matthew McConaughey) and a team of researchers through the wormhole and across the galaxy to find out which of three planets could be mankind's new home.",
        3.8F,
        "Adventure, Sci-Fi, Action",
        "1h 39min",
        "https://i.imgur.com/0Nw7yxt.jpg"))

        film.add(FilmEntity("m9",
        "Tenet",
        "A secret agent embarks on a dangerous, time-bending mission to prevent the start of World War III.",
        4.2F,
        "Action, Sci-Fi, Thriller",
        "2h 30min",
        "https://i.imgur.com/5zEC2Hl.jpg"))

        film.add(FilmEntity("m10",
        "Venom",
        "Journalist Eddie Brock is trying to take down Carlton Drake, the notorious and brilliant founder of the Life Foundation. While investigating one of Drake's experiments, Eddie's body merges with the alien Venom -- leaving him with superhuman strength and power. Twisted, dark and fueled by rage, Venom tries to control the new and dangerous abilities that Eddie finds so intoxicating.",
        4.1F,
        "Adventure, Action, Fantasy, Sci-Fi",
        "2h 20min",
        "https://i.imgur.com/RjZwUbd.jpg"))

        return film
    }

    fun generateSeries(): List<SeriesEntity> {
        val series = ArrayList<SeriesEntity>()

        series.add(SeriesEntity("s1",
        "Stranger Things: Season 2",
        "A love letter to the '80s classics that captivated a generation, Stranger Things is set in 1983 Indiana, where a young boy vanishes into thin air. As friends, family and local police search for answers, they are drawn into an extraordinary mystery involving top-secret government experiments, terrifying supernatural forces and one very strange little girl.",
        4.5F,
        "Drama, Fantasy, Horror, Mystery",
        "9",
        "https://i.imgur.com/XlEeVTt.jpg"))

        series.add(SeriesEntity("s2",
        "Sherlock: Season 4",
        "Sherlock makes a welcome and shocking return, and Cumberbatch and Freeman are game, but it may prove difficult to live up to the lofty expectations created by the series' lengthy hiatus.",
        4.8F,
        "Crime, Drama, Mystery",
        "13",
        "https://i.imgur.com/YFw9STy.jpg"))

        series.add(SeriesEntity("s3",
        "Dark: Season 3",
        "Dark is set in a German town in present day where the disappearance of two young children exposes the double lives and fractured relationships among four families.",
        4.6F,
        "Crime, Drama, Sci-Fi, Thriller",
        "26",
        "https://i.imgur.com/v6hWuHY.jpg"))

        series.add(SeriesEntity("s4",
        "Queen's Gambit",
        "Based on the novel by Walter Tevis, the Netflix limited series drama The Queen's Gambit is a coming-of-age story that explores the true cost of genius. Abandoned and entrusted to a Kentucky orphanage in the late 1950s, a young Beth Harmon (Anya Taylor-Joy) discovers an astonishing talent for chess while developing an addiction to tranquilizers provided by the state as a sedative for the children. Haunted by her personal demons and fueled by a cocktail of narcotics and obsession, Beth transforms into an impressively skilled and glamorous outcast while determined to conquer the traditional boundaries established in the male-dominated world of competitive chess.",
        4.9F,
        "Drama, History",
        "7",
        "https://i.imgur.com/PcFDAzu.jpg"))

        series.add(SeriesEntity("s5",
        "Start-Up",
        "Set in South Korea's fictional Silicon Valley called Sandbox, Start-Up tells the story of people in the world of startup companies. Seo Dal-mi (Bae Suzy) is a bright and ambitious young woman who dreams of becoming Korea’s Steve Jobs. Dal-mi doesn’t have a fancy background but she’s passionate about her work. She has bright energy and is a person of great vitality, having experience in a wide range of part-time jobs. Nam Do-san (Nam Joo-hyuk), is the founder of Samsan Tech. A ‘math genius’ as a young boy, Do-san was once the pride of his family but became their shame now, as his business has been going down for the past two years. He finds out that Dal-mi mistakenly remembers him as her first love, so he decides to work his way up in hopes of turning that misunderstanding into reality.",
        4.4F,
        "Drama, Romance",
        "16",
        "https://i.imgur.com/FUDOSn9.jpg"))

        series.add(SeriesEntity("s6",
        "How To Sell Drug Online (Fast)",
        "A high school nerd trying to get back with his girlfriend starts an online drug business, and unwittingly ends up as one of Europe's most successful dealers.",
        4.1F,
        "Comedy, Crime, Drama",
        "12",
        "https://i.imgur.com/QsnOI7h.jpg"))

        series.add(SeriesEntity("s7",
        "Itaewon Class",
        "In a dynamic Seoul neighborhood, a former convict and his pals take on an enemy in order to make their business dreams come true.",
        4.3F,
        "Drama",
        "16",
        "https://i.imgur.com/uJM03OX.jpg"))

        series.add(SeriesEntity("s8",
        "Extracurricular",
        "Jisoo may come off as a shy model student, but he is actually the mastermind behind a criminal activity that is beyond imagination of his fellow schoolmates. He made this choice because he needed the money for living expenses and to save for college tuition. His risky double life seems to run without a hitch, until he gets mixed up with rich girl Gyuri and reckless troublemaker Minhee. Their bad choices come with irreversible consequences. There is no more turning back now. A life of crime and violence awaits them.",
        4.4F,
        "Drama, Crime",
        "10",
        "https://i.imgur.com/3FNCEwk.jpg"))

        series.add(SeriesEntity("s9",
        "The Falcon and the Winter Soldier",
        "Sam Wilson allies himself with Bucky Barnes to embark on a series of international adventures, the duo drawn together by their ties to Steve Rogers, to thwart the activities of the Flag-Smashers, an anti-patriotism terrorist cell.",
        4F,
        "Action, Adventure, Drama, Sci-Fi",
        "6",
        "https://i.imgur.com/j00u6CU.jpg"))

        series.add(SeriesEntity("s10",
        "Rick And Morty: Season 4",
        "Mad scientist Rick Sanchez moves in with his daughter's family after disappearing for 20 years and involves them in his wacky adventures in this animated comedy.",
        4.8F,
        "Adventure, Animation, Adult",
        "12",
        "https://i.imgur.com/44OzqM7.jpg"))

        return series
    }
}