package com.sample.credentials.common.config.service;

import com.sample.credentials.common.config.exception.ExceptionMetier;

/**
 * Interface IService Interface dont hérite tous les services métiers unitaires, exposant une unique fonction execute().
 *
 * @param <E> Objet en entrée du service
 * @param <S> Objet en sortie du service
 */
public interface IService<E, S>
{

    /**
     * methode Execute : exécuter le business code relatif à ce service unitaire.
     *
     * @param object objet métier à traiter
     * @return S
     * @throws ExceptionMetier the exception metier
     */
    public S execute(E object) throws ExceptionMetier;
}