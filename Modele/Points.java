package Modele;

public class Points {
    
    private Grille grille;
    private int score_total;
    private int score_tempo;


    public Points(Grille _grille)
    {
        this.score_total = 0;
        this.score_tempo = 0;
        this.grille = _grille;
    }

    public int getScore()
    {
        return this.score_total;
    }

    public void setScore(int _score)
    {
        this.score_total = _score;
    }

    public void addScore(int _score)
    {
        this.score_total += _score;
    }

    public int getScoreTempo()
    {
        return this.score_tempo;
    }

    public void setScoreTempo(int _score_tempo)
    {
        this.score_tempo = _score_tempo;
    }

     public void addScoreTempo(int _score)
    {
        this.score_tempo += _score;
    }

    public void ajoutePoint(){
        int s= grille.descendLigne();
        addScore(s*150);
        addScoreTempo(s*150);
    }
}
