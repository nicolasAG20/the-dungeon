package utilz;

// @author vanes

import thedungeon.main.Game;

/**
 * Clase que contiene constantes para los diferentes personajes y enemigos del juego
 * @author Vanessa Toro Sepulveda,Nicolas Agudelo Grajales
 */
public class Constants {

    /**
     * Constantes y metodos para el enemigo Enemy1
     */
    public static class Enemy1{
        public static final int IDLE = 0;
        public static final int ATTACK = 1;

        /**
         * Devuelve la cantidad de sprites segun la accion del enemigo
         * 
         * @param usuAction accion actual del enemigo
         * @return cantidad de sprites para esa accion
         */
        public static int GetSpriteAmount(int usuAction) {
             switch (usuAction) {
                 case IDLE:
                     return 3;
                 case ATTACK:
                     return 6; 
                 default:
                     return 1;
             }
         }
    }

    /**
     * Constantes y metodos para el enemigo Usu
     */
    public static class Usu{
        public static final int IDLE = 0;
        public static final int ATTACK = 1;

        /**
         * Devuelve la cantidad de sprites segun la accion del enemigo Usu
         * 
         * @param EnemyAction accion actual
         * @return cantidad de sprites para esa accion
         */
        public static int GetSpriteAmount(int EnemyAction) {
            switch (EnemyAction) {
                case IDLE:
                    return 2;
                case ATTACK:
                    return 6; 
                default:
                    return 1;
            }
        }
    }

    /**
     * Constantes y metodos para el jefe final
     */
    public static class FinalBossConstants{
        public static final int IDLE = 0;
        public static final int ATTACK_1 = 1;
        public static final int ATTACK_2 = 2;

        /**
         * Devuelve la cantidad de sprites segun la accion del jefe final
         * 
         * @param bossAction accion actual del jefe
         * @return cantidad de sprites para esa accion
         */
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

    /**
     * Constantes y metodos para el jugador
     */
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int ATTACK = 1;
        public static final int SHIELD = 2;
        public static final int ATTACK_MAGIC = 3;

        /**
         * Devuelve la cantidad de sprites segun la accion del jugador
         * 
         * @param player_action accion actual del jugador
         * @return cantidad de sprites para esa accion
         */
        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
            case IDLE:
                return 4;
            case ATTACK:
                return 10;
            case SHIELD:
                return 12;
            case ATTACK_MAGIC:
                return 8;
            default:
                return 1;
            }
        }
    }

}
