package Modele;

public class Points {
    
    private int score;


    public Points()
    {
        this.score = 0;
    }

    public int getScore()
    {
        return this.score;
    }

    public void setScore(int _score)
    {
        this.score = _score;
    }

    public void addScore(int _score)
    {
        this.score += _score;
    }
}
