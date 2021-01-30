package com.sample.credentials.common.config.annotation;

import com.sample.credentials.common.config.exception.ControleMetierException;

/**
 * Interface IControleMetier Interface étendue par toutes les règles de gestion
 */
public interface IControleMetier
{

    /**
     * methode Validate : valider l'objet métier selon la règle de gestion implémentée.
     *
     * @param data objet métier à controler
     * @throws ControleMetierException the controle metier exception
     */
    public void validate(Object data) throws ControleMetierException;

    /**
     * Verifie si la règle métier est bloquante ou non
     *
     * @return true, si c'est bloquant
     */
    public default boolean isBloquant()
    {
        return false;
    }
}
