package Model;

import java.util.Random;

/**
 * Represents a football game between two Times.
 * One plays home and the other plays away, but this does not have any meaning.
 * The game can end in a win for either Time or a draw
 * @author Alexandruro
 *
 */
public class Game
{

    private Time home;
    private Time away;
    private Score result;

    /**
     * Creates a new game between two Times
     * @param home The Time that plays home
     * @param away The Time that plays away
     */
    public Game(Time home, Time away)
    {
        this.home = home;
        this.away = away;
        result = new Score();
        //System.out.printf("Game between %s and %s created\n", home, away);
    }

    /**
     * Simulates the game (if it is not played yet) and returns the result
     * @return The result of the game, a Score object
     * @throws GameAlreadyDecidedException Thrown if the game was already played
     */
    public Score simulateResult() throws GameAlreadyDecidedException
    {
        if (!result.isDecided())
            return resimulateResult();
        else throw new GameAlreadyDecidedException();

    }

    /**
     * Simulates the game, no matter if it is already decided or not) and returns the result 
     * @return The result of the game, a Score object
     */
    public Score resimulateResult()
    {
        result.initialise();
        Random generator = new Random();
        int chance, generated;
        try
        {
            for(int attack=1; attack<7; attack++)
            {
                chance = 25 + (home.getAttacking()-away.getDefending());
                generated = generator.nextInt(101);
                if(generated<=chance)
                    result.homeGoal();

                chance = 25 + (away.getAttacking()-home.getDefending());
                generated = generator.nextInt(101);
                if(generated<=chance)
                    result.awayGoal();
            }
        }
        catch (GameAlreadyDecidedException e)
        {
            System.err.println("Broken assumption in simulating game. It was decided too early.");
            throw new Error(e);
        }
        result.end();

        return result;
    }

    /**
     * Shows the result of the game
     * @return The result, a Score object
     */
    public Score getResult()
    {
        return result;
    }

    /**
     * Shows the Time that plays home
     * @return The home Time
     */
    public Time getHome()
    {
        return home;
    }

    /**
     * Shows the Time that plays away
     * @return The away Time
     */
    public Time getAway()
    {
        return away;
    }

}