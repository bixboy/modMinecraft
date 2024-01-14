package fr.bixboy.csm.gui.playerSize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerSettings{
    private static float playerSize = 1.0f; // Taille par défaut du joueur
    private static boolean useSize = true;

    private static final Logger LOGGER = LogManager.getLogger();

    // Méthode pour sauvegarder la taille du joueur
    public static void savePlayerSize(float size, boolean usingSize) {
        playerSize = size;
        useSize = usingSize;
        LOGGER.debug("ui");
        if (useSize) {
            setPlayerSize(10);
        }
    }

    public static void setPlayerSize(float playerSize) {
        
    }

}
