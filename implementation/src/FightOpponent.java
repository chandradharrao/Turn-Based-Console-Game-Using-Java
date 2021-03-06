public class FightOpponent implements BattleState{
    public void doAction(BattleManager battleManager){
        while(battleManager.run){

            //display teams
            System.out.println("Your team details:");
            View.displayTeam(battleManager.player.myTeam);

            System.out.println(battleManager.currOpponent.name+ "'s team details:");
            View.displayTeam(battleManager.currOpponent.myTeam);

            int currPoke;
            do{
               try{
                    //choose current pokemon in team
                    System.out.println("Choose your current pokemon: ");
                    currPoke = Integer.parseInt(System.console().readLine());
                    battleManager.player.myTeam.equipPokemon(currPoke);
               }catch(Exception e){
                   System.out.println("Enter Move number again!");
                   Logger.print(""+e, true);
                   currPoke = 100;
               }
            }while(currPoke>=6 || currPoke<0);

            //display move of current pokemon
            Pokemon poke = battleManager.player.myTeam.myPokemons.get(battleManager.player.myTeam.currPokemon);

            View.displayMoves(poke.moves.theMoves,poke.Name);

            //ask player to choose move and attack
            while(true){
                System.out.println("Choose a move:");
                int move = Integer.parseInt(System.console().readLine());
                
                try{
                    battleManager.player.myTeam.useMove(move,battleManager.currOpponent.myTeam);
                    Logger.print("After player attacks,opponent health is: " + battleManager.currOpponent.myTeam.myPokemons.get(battleManager.currOpponent.myTeam.currPokemon).health);
                    break;
                }
                catch(Exception e){
                    Logger.print("Wrong move number,try again.");
                }
            }

            //opponent attacks player
            System.out.println(battleManager.currOpponent.name +" is attacking you......");
            Boolean didOpponentLoose = battleManager.currOpponent.attackPlayer(battleManager.player);
            if(!didOpponentLoose){
                View.displayWin();

                //player recieves badge upon victory
                battleManager.currOpponent.giveBadge(battleManager.player);
                System.out.println("You recived a badge from: "+battleManager.player.myBadges.get(battleManager.player.myBadges.size()-1).gymLeaderName+ " !");

                //pop opponent from the list of opponentts
                battleManager.allOpponents.remove(battleManager.currOpponent);
                
                //asking user if he wants to heal all his pokemons to maximum health
                System.out.println("Do you want to heal your pokemon before next battle?\nPress Y or N");
                char doHeal = System.console().readLine().charAt(0);
                
                if(doHeal=='Y' || doHeal=='y'){
                    battleManager.nurseJoy.healWork(battleManager.player.myTeam);
                }
                else{
                    System.out.println("Gotta admire your confidence to go with a wounded Team!");
                }
                break;
            }
            else{
                //opponent didnt loose,doesnt mean player lost
                if(battleManager.player.myTeam.didTeamLoose()){
                    System.out.println("You lost :(");
                    
                    //finish the game
                    EndGame endgame = new EndGame();
                    endgame.doAction(battleManager);
                }
            }
        }
    }
}
