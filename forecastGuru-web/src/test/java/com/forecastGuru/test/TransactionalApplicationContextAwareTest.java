package com.forecastGuru.test;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for any test which is transactional and needs to be aware of the
 * application context.
 * 
 * @author Martin A. Heras
 * 
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public abstract class TransactionalApplicationContextAwareTest extends ApplicationContextAwareTest {
}