public class HealCentre{
    public void healWork(Team team){
        for(int i = 0;i<team.myPokemons.size();i++){
            team.myPokemons.get(i).healMe();
            increasePP(team.myPokemons.get(i));
        }
    }

    public void increasePP(Pokemon pokemon){
        pokemon.moves.increasePP();
    }
}