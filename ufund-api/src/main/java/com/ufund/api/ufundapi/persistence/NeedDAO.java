package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import com.model.Need;

/**
 * Defines the interface for Hero object persistence
 * 
 * @author 
 */
public interface NeedDAO {
    /**
     * Retrieves all {@linkplain Need needs}
     * 
     * @return An array of {@link Need need} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need[] getNeeds() throws IOException;

    /**
     * Finds all {@linkplain Need needs} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Need needs} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need[] findNeeds(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Need need} with the given id
     * 
     * @param id The name of the {@link Need need} to get
     * 
     * @return a {@link Need need} object with the matching name
     * <br>
     * null if no {@link Need need} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need getNeed(String name) throws IOException;

    /**
     * Creates and saves a {@linkplain Hero hero}
     * 
     * @param hero {@linkplain Need need} object to be created and saved
     * <br>
     * The name of the hero object is ignored and a new uniqe name is assigned
     *
     * @return new {@link Need need} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need createNeed(Need need) throws IOException;

    /**
     * Updates and saves a {@linkplain Need need}
     * 
     * @param {@link Need need} object to be updated and saved
     * 
     * @return updated {@link Need need} if successful, null if
     * {@link Need need} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Need updateNeed(Need need) throws IOException;

    /**
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param name The name of the {@link Need need}
     * 
     * @return true if the {@link Need need} was deleted
     * <br>
     * false if hero with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteNeed(String name) throws IOException;
}