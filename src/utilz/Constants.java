package utilz;

// @author vanes

import thedungeon.main.Game;


public class Constants {
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    
    public static class Enemy1{
        //public static int GetSpriteAmount(){
            
        //}
    }
    public static class Enemy2{
        //public static int GetSpriteAmount(){
            
        //}        
    }
    
    public static class FinalBossConstants{
        public static final int IDLE = 0;
        public static final int ATTACK_1 = 1;
        public static final int ATTACK_2 = 2;        

        public static int GetSpriteAmount(int bossAction) {
            switch (bossAction) {
                case IDLE:
                    return 6;
                case ATTACK_1:
                    return 12;
                case ATTACK_2:
                    return 9;
                default:
                    return 1;

            }
        }
    }
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int ATTACK = 1;
        public static final int SHIELD = 2;
        public static final int ATTACK_MAGIC = 3;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
            case IDLE:
                return 4;
            case ATTACK:
                return 10;
            case SHIELD:
                return 12;
            case ATTACK_MAGIC:
                return 9;
            default:
                return 1;
            }
        }
    }

}
