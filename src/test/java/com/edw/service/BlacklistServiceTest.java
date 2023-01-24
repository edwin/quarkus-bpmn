package com.edw.service;

import com.edw.entity.Blacklist;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;

/**
 * <pre>
 *     com.edw.service.BlacklistServiceTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 24 Jan 2023 14:43
 */
@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlacklistServiceTest {

    @Inject
    BlacklistService blacklistService;

    @BeforeAll
    @Transactional
    public static void init() {
        Blacklist.persist(new Blacklist("Blacklist User 02"));
    }

    @Test
    @DisplayName("Test whether a user is blacklist or not")
    @Order(1)
    public void testResult() {
        Assertions.assertTrue(blacklistService.isBlacklist("Blacklist User 02"));
        Assertions.assertFalse(blacklistService.isBlacklist("Not Blacklist"));
    }
}
