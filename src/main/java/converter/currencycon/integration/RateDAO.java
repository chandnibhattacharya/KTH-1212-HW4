/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.currencycon.integration;

import converter.currencycon.model.Currency;
import converter.currencycon.model.Rate;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;



@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class RateDAO {
  @PersistenceContext(unitName = "HibernatePU")
  private EntityManager em;

  /**
   * Finds the conversion rate between
   *
   * @param from Rate from this currency.
   * @param to Rate to this currency.
   * @return The rate between the two currencies.
   */
  public Rate getRate(Currency from, Currency to) {
    try {
      return em.createNamedQuery("getRateFromCurrencies", Rate.class)
        .setParameter("fromCurr", from)
        .setParameter("toCurr", to)
        .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
